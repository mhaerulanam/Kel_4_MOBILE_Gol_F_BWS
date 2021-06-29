package com.def.dokternak.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.def.dokternak.MainActivity;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.users.PostUser;
import com.def.dokternak.data.Model.users.User;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.users.ApiUser;
import com.def.dokternak.ui.petugas.PetugasAdapter;
import com.def.dokternak.ui.register.RegisterActivity;
import com.def.dokternak.utils.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity3 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PetugasAdapter mAdapter;
    ApiUser mApiUser;
    EditText mViewUser, mViewPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Menginisialisasi variable dengan Form User dan Form Password dari Layout LoginActivity */
        mViewUser=findViewById(R.id.et_userameMasuk);
        mViewPassword =findViewById(R.id.et_passwordMasuk);
        /* Menjalankan Method razia() Jika tombol SignIn di keyboard di sentuh *//*
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
//                    razia();
                    String email=mViewUser.getText().toString();
                    String pass=mViewPassword.getText().toString();
                    logIn(email, pass);
                    return true;
                }
                return false;
            }
        });*/

//        btnLogin = findViewById(R.id.button_signinSignin);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /* Mereset semua Error dan fokus menjadi default */
//                mViewUser.setError(null);
//                mViewPassword.setError(null);
//                String email=mViewUser.getText().toString();
//                String pass=mViewPassword.getText().toString();
//                raziaEmail(email);
//                raziaPass(pass);
//                mApiUser = ApiClient.getClient().create(ApiUser.class);
//                Call<PostUser> call = mApiUser.postLoginPetugas(email, pass);
//                call.enqueue(new Callback<PostUser>() {
//                    @Override
//                    public void onResponse(Call<PostUser> call, Response<PostUser> response) {
//                        List<User> userList = response.body().getListDataUser();
//                        Intent intent=new Intent(LoginActivity3.this, MainActivity.class);
//                        Log.d("Retrofit Get", "Jumlah data Petugas: " +
//                                String.valueOf(userList.size()));
//                        startActivity(intent);
//                        masuk();
//                    }
//
//                    @Override
//                    public void onFailure(Call<PostUser> call, Throwable t) {
//
//                    }
//                });
//            }
//        });


        /* Ke RegisterActivity jika merasakan tombol SignUp disentuh */

        findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
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

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity */
    private void raziaEmail(String email){
        Toast.makeText(getApplicationContext(),"Hai", Toast.LENGTH_SHORT);
        if (TextUtils.isEmpty(email)){
            mViewUser.setError("Please Enter Email");
            mViewUser.requestFocus();
            return;
        }
    }

    private void raziaPass(String pass){
        if (TextUtils.isEmpty(pass)){
            mViewPassword.setError("Please Enter Password");
            mViewPassword.requestFocus();
            return;
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

    private void logIn(String email, String pass) {


    }
}