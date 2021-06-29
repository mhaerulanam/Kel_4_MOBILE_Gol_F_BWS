package com.def.dokternak.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.def.dokternak.MainActivity;
import com.def.dokternak.data.Model.users.PostUser;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.users.ApiUser;
import com.def.dokternak.utils.Preferences;
import com.def.dokternak.R;
import com.def.dokternak.ui.register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity4 extends AppCompatActivity {
    ApiUser mApiUser;
    EditText mViewUser, mViewPassword;
    Button btnLogin;

    final

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Menginisialisasi variable dengan Form User dan Form Password dari Layout LoginActivity */
        mViewUser=findViewById(R.id.et_userameMasuk);
        mViewPassword =findViewById(R.id.et_passwordMasuk);

        btnLogin = findViewById(R.id.button_signinSignin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateUserData();


            }
        });


        /* Ke RegisterActivity jika merasakan tombol SignUp disentuh */

        findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }


    private void validateUserData() {

        Toast.makeText(getApplicationContext(),"MasukValidate",Toast.LENGTH_SHORT);

        final String email = mViewUser.getText().toString();
        final String password = mViewPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            mViewUser.setError("Please Enter Email");
            mViewUser.requestFocus();
            btnLogin.setEnabled(true);
            return;
        }

        if (TextUtils.isEmpty(password)){
            mViewUser.setError("Please Enter Password");
            mViewUser.requestFocus();
            btnLogin.setEnabled(true);
            return;
        }

        loginUser(email, password);

    }

    private void loginUser(String email, String password) {
        Toast.makeText(getApplicationContext(), "Heiii",Toast.LENGTH_SHORT);
        mApiUser = ApiClient.getClient().create(ApiUser.class);
        Call<PostUser> loginResponseCall = mApiUser.postLoginUser(email, password);

        loginResponseCall.enqueue(new Callback<PostUser>() {
            @Override
            public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                startActivity(new Intent(LoginActivity4.this,MainActivity.class));
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
//                Toast.makeText(LoginActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** ke MainActivity jika data Status Login dari Data Preferences bernilai true */
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }



    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences */
    private void masuk(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }


}