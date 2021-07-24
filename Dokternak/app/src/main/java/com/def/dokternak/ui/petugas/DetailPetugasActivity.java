package com.def.dokternak.ui.petugas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikelDetail;
import com.def.dokternak.data.Model.petugas.GetPetugasDetail;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.konsultasi.KonsultasiActivity;
import com.def.dokternak.ui.konsultasi.TulisKonsultasiActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.def.dokternak.network.ApiClient.ARTIKEL_IMAGE_BASE_URL;
import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class DetailPetugasActivity extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL = 1;
    ApiPetugas mApiPetugas;

    private ImageView imgThumbnail;
    private TextView tvNamaDokter, tvJabatan, tvEmail, tvJenisKelamin, tvAlamat, tvTempat, tvTelpon, tvJadwalKerja;
    private ImageButton imgBtnBack, imgTelepone, imgWa;
    String nohp, nama;
    int idDokter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_petugas);
        mApiPetugas = ApiClient.getClient().create(ApiPetugas.class);
        imgThumbnail = findViewById(R.id.img_gambar_petugas);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvEmail = findViewById(R.id.tv_email);
        tvJabatan = findViewById(R.id.tv_djabatan);
        tvTempat = findViewById(R.id.tv_tempat);
        tvJadwalKerja = findViewById(R.id.tv_jadwal);
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin);
        tvNamaDokter = findViewById(R.id.tv_dnama_dokter);
        tvTelpon = findViewById(R.id.tv_telpon);
        imgBtnBack = findViewById(R.id.img_btn_back);
        imgTelepone = findViewById(R.id.img_btn_telepone);
        imgWa = findViewById(R.id.img_btn_wa);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getDetailPetugas();

        imgWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact = nohp; // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        imgTelepone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                intentCall.setData(Uri.parse("tel:" +"+"+ nohp));
                if (ContextCompat.checkSelfPermission(DetailPetugasActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DetailPetugasActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {
                    Toast.makeText(getApplicationContext(),"Telepon " + nama,Toast.LENGTH_LONG).show();
                    startActivity(intentCall);
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.btn_konsultasi);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), TulisKonsultasiActivity.class);
                mIntent.putExtra("id_dokter", idDokter);
                mIntent.putExtra("nama_dokter", nama);
                view.getContext().startActivity(mIntent);
                ((DetailPetugasActivity)view.getContext()).finish();
            }
        });
    }


    private void getDetailPetugas() {
        int idPetugas = getIntent().getIntExtra("Id", 0);
        Call<GetPetugasDetail> petugasDetail = mApiPetugas.getPetugasDetail(idPetugas);
        petugasDetail.enqueue(new Callback<GetPetugasDetail>() {
            @Override
            public void onResponse(Call<GetPetugasDetail> call, Response<GetPetugasDetail> response) {
                Petugas petugas = response.body().getPetugas();
                tvNamaDokter.setText(petugas.getNamaDokter());
                tvAlamat.setText(petugas.getAlamat());
                tvEmail.setText(petugas.getEmail());
                tvJabatan.setText(petugas.getIdJabatan());
                tvTempat.setText(petugas.getTempat());
                tvJadwalKerja.setText(petugas.getJadwalKerja());
                tvTelpon.setText("+" + petugas.getTelpon());
                tvJenisKelamin.setText(petugas.getJenisKelamin());
                Glide.with(getApplicationContext())
                        .load(PETUGAS_IMAGE_BASE_URL + petugas.getFoto())
                        .into(imgThumbnail);
                nohp = petugas.getTelpon();
                idDokter = petugas.getId();
                nama = petugas.getNamaDokter();
            }

            @Override
            public void onFailure(Call<GetPetugasDetail> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }
}
