package com.def.dokternak.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText mViewUser, mViewPassword;
    ApiUser mApiUser;
    Button btnLogin;
    TextView tvSalam;
    String salam;
    int jam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvSalam = findViewById(R.id.salam);

        /* Menginisialisasi variable dengan Form User dan Form Password dari Layout LoginActivity */
        mViewUser=findViewById(R.id.et_emailMasuk);
        mViewPassword =findViewById(R.id.et_passwordMasuk);
        /* Menjalankan Method razia() Jika tombol SignIn di keyboard di sentuh */
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    validateUserData();
                    return true;
                }
                return false;
            }
        });

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("H");
        String jamku = df.format(c.getTime());

        jam = Integer.parseInt(jamku);

        if (jam >= 05 && jam < 10) {
            salam = "Pagi";
        }else if (jam >= 10 && jam < 15) {
            salam = "Siang";
        }else if (jam >= 15 && jam <= 18) {
            salam = "Sore";
        }else {
            salam = "Malam";
        }

//        Toast.makeText(getApplicationContext(), salam,Toast.LENGTH_LONG).show();

        tvSalam.setText("Selamat " + salam + "!");

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
//    private void razia(){
//        /* Mereset semua Error dan fokus menjadi default */
//        mViewUser.setError(null);
//        mViewPassword.setError(null);
//        View fokus = null;
//        boolean cancel = false;
//
//        /* Mengambil text dari form User dan form Password dengan variable baru bertipe String*/
//        String user = mViewUser.getText().toString();
//        String password = mViewPassword.getText().toString();
//
//        /* Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka, Set error
//         *  di Form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
//        if (TextUtils.isEmpty(user)){
//            mViewUser.setError("This field is required");
//            fokus = mViewUser;
//            cancel = true;
//        }else if(!cekUser(user)){
//            mViewUser.setError("This Username is not found");
//            fokus = mViewUser;
//            cancel = true;
//        }
//
//        /* Sama syarat percabangannya dengan User seperti di atas. Bedanya ini untuk Form Password*/
//        if (TextUtils.isEmpty(password)){
//            mViewPassword.setError("This field is required");
//            fokus = mViewPassword;
//            cancel = true;
//        }else if (!cekPassword(password)){
//            mViewPassword.setError("This password is incorrect");
//            fokus = mViewPassword;
//            cancel = true;
//        }
//
//        /* Jika cancel true, variable fokus mendapatkan fokus */
//        if (cancel) fokus.requestFocus();
//        else
//        masuk();
//    }

    private void validateUserData() {

        Toast.makeText(getApplicationContext(),"MasukValidate",Toast.LENGTH_SHORT);

        final String email = mViewUser.getText().toString();
        final String password = mViewPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            mViewUser.setError("Email tidak boleh kosong");
            mViewUser.requestFocus();
            btnLogin.setEnabled(true);
            return;
        }

        if (TextUtils.isEmpty(password)){
            mViewUser.setError("Password tidak boleh kosong");
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
                int code = response.code();

//                Log.d("Data", response.body().getData().toString());

                if (response.code() == 402){
                    Toast.makeText(LoginActivity.this, "Gagal Login!, Email Salah", Toast.LENGTH_LONG).show();
                }else if(response.code() == 403){
                    Toast.makeText(LoginActivity.this, "Gagal Login!, Password Salah", Toast.LENGTH_LONG).show();
                }else if(response.code() == 401){
                    Toast.makeText(LoginActivity.this, "Gagal Login!, Password anda Benar, tetapi email Salah", Toast.LENGTH_LONG).show();
                }else if (response.code() == 200){
                    masuk(response.body().getData());
                }else{
                    Toast.makeText(LoginActivity.this, "Gagal Login!, Email dan Passsword Salah", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
//                Toast.makeText(LoginActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences */
    private void masuk(User user){
        Preferences.setLoggedInUser(LoginActivity.this, user.getEmail());
        Preferences.setLoggedInStatus(getBaseContext(), true);
        Preferences.saveData(LoginActivity.this, user);
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
        Toast.makeText(getApplicationContext(), "Login Berhasil, Hai "+ user.getNamaPeternak(), Toast.LENGTH_LONG).show();
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