package com.def.dokternak.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.def.dokternak.MainActivity;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiTerkirim;
import com.def.dokternak.data.Model.konsultasi.Konsultasi;
import com.def.dokternak.data.Model.konsultasi.postKonsultasi;
import com.def.dokternak.data.Model.users.PostUser;
import com.def.dokternak.data.Model.users.User;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.kategori.ApiKategori;
import com.def.dokternak.network.konsultasi.ApiKonsultasi;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.network.users.ApiUser;
import com.def.dokternak.ui.konsultasi.CariPetugasActivity;
import com.def.dokternak.ui.konsultasi.KonsultasiActivity;
import com.def.dokternak.ui.konsultasi.KonsultasiTerkirimAdapter;
import com.def.dokternak.ui.konsultasi.TulisKonsultasiActivity;
import com.def.dokternak.ui.konsultasi.kategoriHewan.CariKategoriActivity;
import com.def.dokternak.ui.konsultasi.kategoriHewan.CariKategoriAdapter;
import com.def.dokternak.ui.login.LoginActivity;
import com.def.dokternak.utils.Preferences;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.def.dokternak.network.ApiClient.PETERNAK_IMAGE_BASE_URL;

public class EditProfilActivity extends AppCompatActivity {
    ApiUser mApiUser;
    EditText nama_user, email_user, no_hpUser, alamat_user;
    Button btn_kirim, btn_batal, btn_galeri;
    Context mContext;
    String namaUser, jenis_kelamin, nama_gambar, password, part_image;
    ImageView imgHolder;
    MultipartBody.Part partImage;
    int  id;
    final int REQUEST_GALLERY = 9544;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CariKategoriAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        mContext = this;


        mApiUser = ApiClient.getClient().create(ApiUser.class);
        nama_user = (EditText) findViewById(R.id.edt_nama_peternak);
        email_user = (EditText) findViewById(R.id.edt_email_peternak);
        no_hpUser = (EditText) findViewById(R.id.edt_no_hp_peternak);
        alamat_user = (EditText) findViewById(R.id.edt_alamat_peternak);
//        imgHolder = (ImageView) findViewById(R.id.img_profil);
        btn_kirim = (Button) findViewById(R.id.btn_edit);
        btn_batal = (Button) findViewById(R.id.btn_batal);
//        btn_galeri = (Button) findViewById(R.id.btngallery);
        final RadioButton radio1 = (RadioButton)findViewById(R.id.rb_laki_laki);
        final RadioButton radio2 = (RadioButton)findViewById(R.id.rb_perempuan);

        id = Preferences.getId(getApplicationContext());
        namaUser = Preferences.getNama(getApplicationContext());
        String emailUser = Preferences.getEmail(getApplicationContext());
        String noHpUser = Preferences.getNoHp(getApplicationContext());
        String jk = Preferences.getJenisKelamin(getApplicationContext());
        String alamat = Preferences.getAlamat(getApplicationContext());
        nama_gambar = Preferences.getImagePicture(getApplicationContext());
        password = Preferences.getPassword(getApplicationContext());
//        Glide.with(getApplicationContext())
//                .load(PETERNAK_IMAGE_BASE_URL + nama_gambar)
//                .into(imgHolder);

//        edtId.setText(mIntent.getStringExtra("Id"));
//        edtId.setTag(edtId.getKeyListener());
//        edtId.setKeyListener(null);
        nama_user.setText(namaUser);
        email_user.setText(emailUser);
        no_hpUser.setText(noHpUser);
        alamat_user.setText(alamat);

        if(jk.equals("Laki-Laki")){
            radio1.setChecked(true);
            radio2.setChecked(false);
            jenis_kelamin = "Laki-Laki";
        }else if(jk.equals("Perempuan")){
            radio2.setChecked(true);
            radio1.setChecked(false);
            jenis_kelamin = "Perempuan";
        }

//        btn_galeri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"open gallery"),REQUEST_GALLERY);
//            }
//        });


        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File imagefile = new File(part_image);
//                RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"),imagefile);
//                partImage = MultipartBody.Part.createFormData("imageupload", imagefile.getName(),reqBody);

                Call<PostUser> updateKontakCall = mApiUser.putUser(
                        id,
                        nama_user.getText().toString(),
                        password,
                        email_user.getText().toString(),
                        no_hpUser.getText().toString(),
                        jenis_kelamin,
                        alamat_user.getText().toString(),
                        nama_gambar);
                updateKontakCall.enqueue(new Callback<PostUser>() {
                    @Override
                    public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                        Toast.makeText(getApplicationContext(), "Update Profil Berhasil", Toast.LENGTH_LONG).show();
                        Preferences.updateData(EditProfilActivity.this, response.body().getData());
                        startActivity(new Intent(EditProfilActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.nama_jk);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                String teks = checkedRadioButton.getText().toString();
                if(teks.equals("Laki-Laki")){
                    jenis_kelamin = "Laki-Laki";
                }else if(teks.equals("Perempuan")){
                    jenis_kelamin = "Perempuan";
                }
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK)
//        {
//            if(requestCode == REQUEST_GALLERY)
//            {
//                Uri dataimage = data.getData();
//                String[] imageprojection = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getContentResolver().query(dataimage,imageprojection,null,null,null);
//
//                if (cursor != null)
//                {
//                    cursor.moveToFirst();
//                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
//                    part_image = cursor.getString(indexImage);
//
//                    if(part_image != null)
//                    {
//                        File image = new File(part_image);
//                        imgHolder.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
//                    }
//                }
//            }
//        }
//    }
}
