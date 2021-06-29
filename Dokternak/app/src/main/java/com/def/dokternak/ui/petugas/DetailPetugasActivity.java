package com.def.dokternak.ui.petugas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikelDetail;
import com.def.dokternak.data.Model.petugas.GetPetugasDetail;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;
import com.def.dokternak.network.petugas.ApiPetugas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.def.dokternak.network.ApiClient.ARTIKEL_IMAGE_BASE_URL;
import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class DetailPetugasActivity extends AppCompatActivity {
    ApiPetugas mApiPetugas;

    private ImageView imgThumbnail;
    private TextView tvNamaDokter, tvJabatan, tvEmail, tvJenisKelamin, tvAlamat, tvTempat, tvTelpon, tvJadwalKerja;
    private ImageButton imgBtnBack;


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
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getDetailPetugas();


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
                tvTelpon.setText(petugas.getTelpon());
                tvJenisKelamin.setText(petugas.getJenisKelamin());
                Toast.makeText(getApplicationContext(), "Detail Petugas Tampil" + idPetugas, Toast.LENGTH_LONG).show();
                Glide.with(getApplicationContext())
                        .load(PETUGAS_IMAGE_BASE_URL + petugas.getFoto())
                        .into(imgThumbnail);
            }

            @Override
            public void onFailure(Call<GetPetugasDetail> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }
}
