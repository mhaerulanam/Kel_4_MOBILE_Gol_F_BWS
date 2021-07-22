package com.def.dokternak.ui.konsultasi;

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
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiTerkirim;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiTerkirimDetail;
import com.def.dokternak.data.Model.konsultasi.Konsultasi;
import com.def.dokternak.data.Model.konsultasi.RiwayatKonsultasi;
import com.def.dokternak.data.Model.petugas.GetPetugasDetail;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.konsultasi.ApiKonsultasi;
import com.def.dokternak.network.petugas.ApiPetugas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class DetailKonsultasiTekirimActivity extends AppCompatActivity {
    ApiKonsultasi mApiKonsultasi;

    private ImageView imgThumbnail;
    private TextView tvNamaDokter, tvTanggal, tvKategori, tvNamaHewan, tvKeluhan;
    private ImageButton imgBtnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_konsultasi_terkirim);
        mApiKonsultasi = ApiClient.getClient().create(ApiKonsultasi.class);
        imgThumbnail = findViewById(R.id.img_profile);
        tvNamaDokter = findViewById(R.id.tv_nama_dokter);
        tvKategori =  findViewById(R.id.tv_kategori);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvNamaHewan = findViewById(R.id.tv_nama_hewan);
        tvKeluhan = findViewById(R.id.tv_keluhan);
        imgBtnBack = findViewById(R.id.img_btn_back);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getDetailKonsultasiTerkirim();
    }

    private void getDetailKonsultasiTerkirim() {
        int idKonsultasi = getIntent().getIntExtra("id_konsultasi", 0);
        Call<GetKonsultasiTerkirimDetail> detailTerkirim =  mApiKonsultasi.getKonsultasiTerkirimDetail(idKonsultasi);
        detailTerkirim.enqueue(new Callback<GetKonsultasiTerkirimDetail>() {
            @Override
            public void onResponse(Call<GetKonsultasiTerkirimDetail> call, Response<GetKonsultasiTerkirimDetail> response) {
                Konsultasi konsultasi = response.body().getKonsultasi();
                tvNamaDokter.setText(konsultasi.getNamaDokter());
                tvTanggal.setText(konsultasi.getTanggal());
                tvKategori.setText(konsultasi.getJenisHewan());
                tvNamaHewan.setText(konsultasi.getNamaHewan());
                tvKeluhan.setText(konsultasi.getKeluhan());
                Glide.with(getApplicationContext())
                        .load(PETUGAS_IMAGE_BASE_URL + konsultasi.getFoto())
                        .into(imgThumbnail);
            }

            @Override
            public void onFailure(Call<GetKonsultasiTerkirimDetail> call, Throwable t) {

            }
        });
    }
}
