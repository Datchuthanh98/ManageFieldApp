package com.example.managefield.auth;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.data.datasource.FieldDataSource;
import com.example.managefield.databinding.ActivityLoginBinding;
import com.example.managefield.databinding.LoadingLayoutBinding;
import com.example.managefield.main.ActivityMain;
import com.example.managefield.model.Field;
import com.example.managefield.Session.SessionField;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FieldDataSource fieldDataSource = FieldDataSource.getInstance();
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
        fieldDataSource.login(binding.txtEmail.getText().toString(), binding.txtPassword.getText().toString(), new CallBack<Field, String>() {
            @Override
            public void onSuccess(Field field) {
                SessionField.getInstance().onUserChange(field);
                loadingDialog.dismiss();
                Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
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
