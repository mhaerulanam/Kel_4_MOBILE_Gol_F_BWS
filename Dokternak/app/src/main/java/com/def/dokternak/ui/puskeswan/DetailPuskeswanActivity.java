package com.def.dokternak.ui.puskeswan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.petugas.GetPetugasDetail;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.data.Model.puskeswan.GetPuskeswanDetail;
import com.def.dokternak.data.Model.puskeswan.Puskeswan;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.network.puskeswan.ApiPuskeswan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;
import static com.def.dokternak.network.ApiClient.PUSKESWAN_IMAGE_BASE_URL;

public class DetailPuskeswanActivity extends AppCompatActivity {
    ApiPuskeswan mApiPuskeswan;

    String url;
    private ImageView imgThumbnail;
    private TextView tvNamaPuskeswan, tvAlamat, tvJamKerja, tvMaps;
    public Button btnLokasi;
    private ImageButton imgBtnBack, imgWa;
    String nohp, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_puskeswan);
        mApiPuskeswan = ApiClient.getClient().create(ApiPuskeswan.class);
        imgThumbnail = findViewById(R.id.img_gambar_puskeswan);
        tvNamaPuskeswan = findViewById(R.id.tv_nama_puskeswan);
        tvJamKerja = findViewById(R.id.tv_jadwal_puskeswan);
        tvAlamat = findViewById(R.id.tv_alamat_puskeswan);
        btnLokasi = findViewById(R.id.btn_lokasi);
        imgBtnBack = findViewById(R.id.img_btn_back);
        imgWa = findViewById(R.id.img_btn_wa);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getDetailPuskeswan();

        imgWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Nomer WA Koordinator " + nama,Toast.LENGTH_LONG).show();
                String contact = nohp; // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



    }

    private void getDetailPuskeswan() {
        int idPuskeswan = getIntent().getIntExtra("Id", 0);
        Call<GetPuskeswanDetail> puskeswanDetail = mApiPuskeswan.getPuskeswanDetail(idPuskeswan);
        puskeswanDetail.enqueue(new Callback<GetPuskeswanDetail>() {
            @Override
            public void onResponse(Call<GetPuskeswanDetail> call, Response<GetPuskeswanDetail> response) {
                Puskeswan puskeswan = response.body().getPuskeswan();
                tvNamaPuskeswan.setText(puskeswan.getNamaPuskeswan());
                tvAlamat.setText(puskeswan.getAlamat());
                tvJamKerja.setText(puskeswan.getJamKerja());
                url = puskeswan.getMaps();
                Glide.with(getApplicationContext())
                        .load(PUSKESWAN_IMAGE_BASE_URL + puskeswan.getGambar())
                        .into(imgThumbnail);
                nohp = puskeswan.getNomer();
                nama = puskeswan.getNamaPuskeswan();
            }

            @Override
            public void onFailure(Call<GetPuskeswanDetail> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
