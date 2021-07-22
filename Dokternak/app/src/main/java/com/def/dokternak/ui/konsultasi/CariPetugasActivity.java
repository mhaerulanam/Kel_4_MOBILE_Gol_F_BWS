package com.def.dokternak.ui.konsultasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.petugas.PetugasAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CariPetugasActivity extends AppCompatActivity {

    ApiPetugas mApiPetugas;
    private RecyclerView mRecyclerView;
    private CariPetugasAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SearchView svCariPetugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_petugas);
        mApiPetugas = ApiClient.getClient().create(ApiPetugas.class);

        svCariPetugas = findViewById(R.id.pencarian_petugas);
        svCariPetugas.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                GetDataCariPetugas(s);
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

        GetDataCariPetugas("");
    }

    public void GetDataCariPetugas(String nama_dokter){
        Call<GetCariPetugas> petugasCariCall = mApiPetugas.getCariPetugas(nama_dokter);
        petugasCariCall.enqueue(new Callback<GetCariPetugas>() {
            @Override
            public void onResponse(Call<GetCariPetugas> call, Response<GetCariPetugas> response) {
                List<Petugas> petugasList = response.body().getListDataPetugas();
                Log.d("Retrofit Get", "Jumlah data Petugas: " +
                        String.valueOf(petugasList.size()));
                mAdapter = new CariPetugasAdapter(petugasList);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(CariPetugasActivity.this));
            }

            @Override
            public void onFailure(Call<GetCariPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}