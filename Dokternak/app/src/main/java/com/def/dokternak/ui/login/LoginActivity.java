package com.def.dokternak.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.def.dokternak.MainActivity;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.users.PostUser;
import com.def.dokternak.data.Model.users.User;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.users.ApiUser;
import com.def.dokternak.ui.register.RegisterActivity;
import com.def.dokternak.utils.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Menginisialisasi variable dengan Form User dan Form Password dari Layout LoginActivity */
        mViewUser=findViewById(R.id.et_userameMasuk);
        mViewPassword =findViewById(R.id.et_passwordMasuk);
        /* Menjalankan Method razia() Jika tombol SignIn di keyboard di sentuh */
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });

        /* Menjalankan Method razia() jika merasakan tombol SignIn disentuh */
        findViewById(R.id.button_signinSignin).setOnClickListener(new View.OnClickListener() {
//            if(TextUtils.isEmpty(mViewUser.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
//                Toast.makeText(MainActivity.this,"Username / Password Required", Toast.LENGTH_LONG).show();
//            }else{
//                //proceed to login
//                login();
//            }
            @Override
            public void onClick(View v) {
                razia();
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

//    public void login(String email, String password){
//        mApiUser = ApiClient.getClient().create(ApiUser.class);
//        Call<PostUser> loginResponseCall = mApiUser.postLoginPetugas(email, password);
//        loginResponseCall.enqueue(new Callback<PostUser>() {
//            @Override
//            public void onResponse(Call<PostUser> call, Response<PostUser> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(LoginActivity2.this,"Login Successful", Toast.LENGTH_LONG).show();
//                    List<User> petugasList = response.body().getListDataUser();
//                    Log.d("Retrofit Get", "Jumlah data Petugas: " +
//                            String.valueOf(petugasList.size()));
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            startActivity(new Intent(LoginActivity2.this,MainActivity.class));
//                        }
//                    },700);
//
//                }else{
//                    Toast.makeText(LoginActivity2.this,"Login Failed", Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PostUser> call, Throwable t) {
//                Toast.makeText(LoginActivity2.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            }
//        });


//    }

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
    private void razia(){
        /* Mereset semua Error dan fokus menjadi default */
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari form User dan form Password dengan variable baru bertipe String*/
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        /* Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka, Set error
         *  di Form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if(!cekUser(user)){
            mViewUser.setError("This Username is not found");
            fokus = mViewUser;
            cancel = true;
        }

        /* Sama syarat percabangannya dengan User seperti di atas. Bedanya ini untuk Form Password*/
        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password)){
            mViewPassword.setError("This password is incorrect");
            fokus = mViewPassword;
            cancel = true;
        }

        /* Jika cancel true, variable fokus mendapatkan fokus */
        if (cancel) fokus.requestFocus();
        else masuk();
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