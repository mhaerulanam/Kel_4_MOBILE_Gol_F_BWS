package com.def.dokternak.ui.artikel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikelDetail;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.def.dokternak.network.ApiClient.ARTIKEL_IMAGE_BASE_URL;

public class DetailArtikelActivity extends AppCompatActivity {

    ApiArtikel mApiArtikel;

    private ImageView imgThumbnail;
    private TextView tvKategori, tvTanggal, tvNamaPenulis, tvJudul, tvIsi, tvSumber;
    private ImageButton imgBtnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        mApiArtikel = ApiClient.getClient().create(ApiArtikel.class);
        imgThumbnail = findViewById(R.id.img_gambar_artikel);
        tvKategori = findViewById(R.id.tv_id_ktg);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvNamaPenulis = findViewById(R.id.tv_nama_penulis);
        tvJudul = findViewById(R.id.tv_judul);
        tvIsi = findViewById(R.id.tv_isi);
        tvSumber = findViewById(R.id.tv_sumber);

        imgBtnBack = findViewById(R.id.img_btn_back);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getDetailArtikel();


    }

    private void getDetailArtikel() {
        int idArtikel = getIntent().getIntExtra("Id", 0);
        Call<GetArtikelDetail> artikelDetail = mApiArtikel.getArtikelDetail(idArtikel);
        artikelDetail.enqueue(new Callback<GetArtikelDetail>() {
            @Override
            public void onResponse(Call<GetArtikelDetail> call, Response<GetArtikelDetail> response) {
                Artikel artikel = response.body().getArtikel();
                tvKategori.setText(String.valueOf(artikel.getIdKtg()));
                tvTanggal.setText(artikel.getTanggal());
                tvNamaPenulis.setText(artikel.getNamaPenulis());
                tvJudul.setText(artikel.getJudul());
                tvIsi.setText(artikel.getIsi());
                tvSumber.setText(artikel.getSumber());
//                Toast.makeText(getApplicationContext(), "Detail Artikel Tampil" + idArtikel, Toast.LENGTH_LONG).show();
                Glide.with(getApplicationContext())
                        .load(ARTIKEL_IMAGE_BASE_URL + artikel.getGambar())
                        .into(imgThumbnail);
            }

            @Override
            public void onFailure(Call<GetArtikelDetail> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}