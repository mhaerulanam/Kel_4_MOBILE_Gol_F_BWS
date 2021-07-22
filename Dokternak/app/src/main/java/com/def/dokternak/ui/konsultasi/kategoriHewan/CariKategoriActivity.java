package com.def.dokternak.ui.konsultasi.kategoriHewan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.kategori.GetCariJenis;
import com.def.dokternak.data.Model.kategori.KategoriHewan;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.kategori.ApiKategori;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.petugas.PetugasAdapter;
import com.def.dokternak.ui.petugas.PetugasFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CariKategoriActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ApiKategori mApiJenis;
    private RecyclerView.LayoutManager mLayoutManager;
    private CariKategoriAdapter mAdapter;
    private SearchView svCariJenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_kategori);
        mApiJenis = ApiClient.getClient().create(ApiKategori.class);
        svCariJenis = findViewById(R.id.pencarian_jenis_hewan);
        svCariJenis.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                GetDataCariKategori(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        GetDataCariKategori("");
    }

    public void GetDataCariKategori(String kategori_artikel){
        Call<GetCariJenis> jenisCariCall = mApiJenis.getCariJenisHewan(kategori_artikel);
        jenisCariCall.enqueue(new Callback<GetCariJenis>() {
            @Override
            public void onResponse(Call<GetCariJenis> call, Response<GetCariJenis> response) {
                List<KategoriHewan> kategoriList = response.body().getListDataJenisHewan();
                Log.d("Retrofit Get", "Jumlah data Kategori: " +
                        String.valueOf(kategoriList.size()));
                mAdapter = new CariKategoriAdapter(kategoriList);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(CariKategoriActivity.this));
            }

            @Override
            public void onFailure(Call<GetCariJenis> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}