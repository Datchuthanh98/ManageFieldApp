package com.example.managefield.auth;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managefield.Interface.LoginCallBack;
import com.example.managefield.R;
import com.example.managefield.data.datasource.PlayerDataSource;
import com.example.managefield.databinding.ActivityLoginBinding;
import com.example.managefield.databinding.LoadingLayoutBinding;
import com.example.managefield.main.ActivityMainField;
import com.example.managefield.model.Player;
import com.example.managefield.viewModel.PlayerViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import java.util.Arrays;

public class ActivityLogin extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private PlayerDataSource playerDataSource = PlayerDataSource.getInstance();
    private ActivityLoginBinding binding;
    //google
    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSignInClient;

    //facebook
    private LinearLayout fb;

    private Dialog loadingDialog;
    private LoadingLayoutBinding loadingLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initLoadingDialog(ActivityLogin.this);
//        loginWithEmail();


        mAuth = FirebaseAuth.getInstance();


        //Login withEmail
        binding.btnLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithEmail();

            }
        });
    }



    private  void loginWithEmail(){
        loadingDialog.show();
        playerDataSource.login(binding.txtEmail.getText().toString(), binding.txtPassword.getText().toString(), new LoginCallBack() {
            @Override
            public void onSuccess(Player player) {
                PlayerViewModel.getInstance().onUserChange(player);
                loadingDialog.dismiss();
                Intent intent = new Intent(ActivityLogin.this,ActivityMainField.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String message) {
                loadingDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Error "+message,Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void initLoadingDialog(Context context) {
        loadingDialog = new Dialog(context);
        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loadingLayoutBinding = LoadingLayoutBinding.inflate(getLayoutInflater());
        loadingDialog.setContentView(loadingLayoutBinding.getRoot());
//        loadingLayoutBinding.title.setText(R.string.updating_information);
        loadingDialog.setCancelable(false);
    }


}
