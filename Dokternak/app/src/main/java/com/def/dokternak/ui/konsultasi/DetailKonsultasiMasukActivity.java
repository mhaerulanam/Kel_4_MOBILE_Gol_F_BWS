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
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiMasukDetail;
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

public class DetailKonsultasiMasukActivity extends AppCompatActivity {
    ApiKonsultasi mApiKonsultasi;

    private ImageView imgThumbnail;
    private TextView tvNamaDokter, tvTanggal, tvKategori, tvNamaHewan, tvKeluhan, tvRespon, tvTanggalRespon;
    private ImageButton imgBtnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_konsultasi_masuk);
        mApiKonsultasi = ApiClient.getClient().create(ApiKonsultasi.class);
        imgThumbnail = findViewById(R.id.img_profile);
        tvNamaDokter = findViewById(R.id.tv_nama_dokter);
        tvKategori =  findViewById(R.id.tv_kategori);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvNamaHewan = findViewById(R.id.tv_nama_hewan);
        tvKeluhan = findViewById(R.id.tv_keluhan);
        tvRespon = findViewById(R.id.tv_respon);
        tvTanggalRespon = findViewById(R.id.tv_tanggal_respon);
        imgBtnBack = findViewById(R.id.img_btn_back);
        getDetailKonsultasiMasuk();
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void getDetailKonsultasiMasuk() {
        int idRiwayat = getIntent().getIntExtra("id_riwayat", 0);
        Call<GetKonsultasiMasukDetail> konsultasiDetail = mApiKonsultasi.getKonsultasiMasukDetail(idRiwayat);
        konsultasiDetail.enqueue(new Callback<GetKonsultasiMasukDetail>() {
            @Override
            public void onResponse(Call<GetKonsultasiMasukDetail> call, Response<GetKonsultasiMasukDetail> response) {
                RiwayatKonsultasi riwayatKonsultasi = response.body().getKonsultasi();
                tvNamaDokter.setText(riwayatKonsultasi.getNamaDokter());
                tvTanggal.setText(riwayatKonsultasi.getTanggal());
                tvKategori.setText(riwayatKonsultasi.getJenisHewan());
                tvNamaHewan.setText(riwayatKonsultasi.getNamaHewan());
                tvKeluhan.setText(riwayatKonsultasi.getKeluhan());
                tvTanggalRespon.setText(riwayatKonsultasi.getTanggalRespon());
                tvRespon.setText(riwayatKonsultasi.getRespon());
                Glide.with(getApplicationContext())
                        .load(PETUGAS_IMAGE_BASE_URL + riwayatKonsultasi.getFoto())
                        .into(imgThumbnail);
            }

            @Override
            public void onFailure(Call<GetKonsultasiMasukDetail> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }
}
