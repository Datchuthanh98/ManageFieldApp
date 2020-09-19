package com.example.managefield.Auth;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managefield.View.Field.Activity.ActivityMainField;
import com.example.managefield.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {

    private Button btnEmail;
    private  Button btnOff;
    EditText txtEmail,txtPassword;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPref;
    private Dialog loginLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (FirebaseAuth.getInstance().getCurrentUser() != null){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
        setContentView(R.layout.login);
//
//        sharedPref = getApplicationContext().getSharedPreferences("sessionUser", Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = sharedPref.edit();
        mAuth = FirebaseAuth.getInstance();
        //Email
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        btnEmail=findViewById(R.id.loginEmail);


        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtEmail.getText().toString())){
                    Toast.makeText(ActivityLogin.this, "Pleas Enter Email Address", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()){
                    Toast.makeText(ActivityLogin.this, "Pleas Enter valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(txtPassword.getText().toString())){
                    Toast.makeText(ActivityLogin.this, "Pleas Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(txtPassword.getText().toString().length()<6){
                    Toast.makeText(ActivityLogin.this, "Pleas Enter 6 or more than digit password", Toast.LENGTH_SHORT).show();
                }
                else {

                    loginLoading = new Dialog(ActivityLogin.this);
                    loginLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    loginLoading.setContentView(R.layout.custom_loading_layout);
                    loginLoading.setCancelable(false);
                    loginLoading.show();

                   // firebaseAuthWithEmail();
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = authResult.getUser();
                                    Toast.makeText(ActivityLogin.this, "Login Successful....", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActivityLogin.this, ActivityMainField.class);
//                                    editor.putString("session",user.getEmail());
//                                    editor.apply();
                                    loginLoading.cancel();
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ActivityLogin.this, "Failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    loginLoading.cancel();
                                }
                            });
                }
            }
        });

        btnOff=findViewById(R.id.btnOffline);
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivityMainField.class);
//                editor.putString("session","");
//                editor.apply();
                startActivity(intent);
                finish();
            }
        });
    }
}
