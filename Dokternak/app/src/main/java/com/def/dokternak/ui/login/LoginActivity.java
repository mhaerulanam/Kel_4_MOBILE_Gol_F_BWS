package com.def.dokternak.ui.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.users.ApiUser;
import com.def.dokternak.ui.register.RegisterActivity;
import com.def.dokternak.ui.register.RegisterActivityBackup;
import com.def.dokternak.utils.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin, btnDaftar;

    ProgressDialog loading;
    Context mContext;
    ApiUser apiUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Menginisialisasi variable dengan Form User dan Form Password dari Layout LoginActivity */
        edtEmail = findViewById(R.id.edt_email);
        edtPassword =findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnDaftar = findViewById(R.id.btn_daftar);

        Klik();
    }

    private void Klik(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mIntent);
                finish();
            }
        });
    }

    private void validasi() {
        String mName = edtEmail.getText().toString().trim();
        String mPassword = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(mName)) {
            edtEmail.setError("Nama tidak boleh kosong");
            edtEmail.requestFocus();
            return;
        } else if (TextUtils.isEmpty(mPassword)) {
            edtPassword.setError("Password tidak boleh kosong");
            edtPassword.requestFocus();
            return;
        }else {
            loading = ProgressDialog.show(mContext, null,
                    "Harap Tunggu...", true, false);
            requestLogin();
        }
    }

    private void requestLogin() {
        apiUser = ApiClient.getClient().create(ApiUser.class);
        Call<PostUser> loginResponseCall = apiUser.postLoginUser(edtEmail.getText().toString(),
                edtPassword.getText().toString());
        loginResponseCall.enqueue(new Callback<PostUser>() {
                    @Override
                    public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_LONG).show();

                        //Pindah ke Main Activity
                        Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mIntent);
                        finish();
//                        if (response.isSuccessful()){
//                            loading.dismiss();
//                            Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_LONG).show();
//
//                            //Pindah ke Main Activity
//                            Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(mIntent);
//                            finish();
//
////                            try {
////                                JSONObject jsonRESULTS = new JSONObject(response.body().toString());
////                                if (jsonRESULTS.getString("error").equals("false")){
////                                    // Jika login berhasil maka data nama yang ada di response API
////                                    // akan diparsing ke activity selanjutnya.
////                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
////                                    String nama = jsonRESULTS.getJSONObject("user").getString("nama");
////                                    Intent intent = new Intent(mContext, MainActivity.class);
////                                    intent.putExtra("result_nama", nama);
////                                    startActivity(intent);
////                                } else {
////                                    // Jika login gagal
////                                    String error_message = jsonRESULTS.getString("error_msg");
////                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
////                                }
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
//                        } else {
//                            loading.dismiss();
//                        }
                    }

                    @Override
                    public void onFailure(Call<PostUser> call, Throwable t) {
//                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        loading.dismiss();
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

    /**DIBAWAH MASIH SALAH, BELUM TERHUBUNG KE API */
    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}