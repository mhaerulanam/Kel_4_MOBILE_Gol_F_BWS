package com.def.dokternak.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.def.dokternak.MainActivity;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.users.PostUser;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.users.ApiUser;
import com.def.dokternak.utils.Preferences;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText edtNama, edtEmail, edtPassword;

    ProgressDialog loading;
    Context mContext;
    ApiUser apiUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNama = findViewById(R.id.edt_nama);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);

        btnRegister = findViewById(R.id.btn_register);

        mContext = this;
//        apiUser = ApiClient.getAPIUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();
            }
        });
    }

    private void validasi() {
        String mName = edtNama.getText().toString().trim();
        String mEmail = edtEmail.getText().toString().trim();
        String mPassword = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(mName)) {
            edtNama.setError("Nama tidak boleh kosong");
            edtNama.requestFocus();
            return;
        } else if (TextUtils.isEmpty(mEmail)) {
            edtEmail.setError("Email tidak boleh kosong");
            edtEmail.requestFocus();
            return;
        } else if (TextUtils.isEmpty(mPassword)) {
            edtPassword.setError("Password tidak boleh kosong");
            edtPassword.requestFocus();
            return;
        }else {
            loading = ProgressDialog.show(mContext, null,
                    "Harap Tunggu...", true, false);
            requestRegister();
        }

    }

    private void requestRegister() {
        apiUser = ApiClient.getClient().create(ApiUser.class);
        Call<PostUser> registerResponseCall = apiUser.postRegisterUser(edtNama.getText().toString(),
                edtEmail.getText().toString(),edtPassword.getText().toString());
        registerResponseCall.enqueue(new Callback<PostUser>() {
            @Override
            public void onResponse(@NotNull Call<PostUser> call, @NotNull Response<PostUser> response) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Registrasi Berhasil!", Toast.LENGTH_LONG).show();

                //Pindah ke Main Activity
                Intent mIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();

//                if (response.isSuccessful()){
//                    loading.dismiss();
//                    try {
//                        JSONObject jsonRESULTS = new JSONObject(response.body().toString());
//                        if (jsonRESULTS.getString("success").equals("1")){
//                            Toast.makeText(mContext, "Register Berhasil!",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            String error_message = jsonRESULTS.getString("error_msg");
//                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    loading.dismiss();
//                }
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}