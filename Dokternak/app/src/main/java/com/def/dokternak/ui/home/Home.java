package com.def.dokternak.ui.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikel;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.petugas.PetugasFragment;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    ApiPetugas mApiPetugas;
    ApiArtikel mApiArtikel;
    private RecyclerView mRecyclerView, mRecyclerView2;
    private PetugasHomeAdapter mAdapter;
    private ArtikelHomeAdapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager;
    public static Home ma;
    private Button btnTerdekat, btnSemua, btnDokter, btnParamedis, btnPetugasIb;
    private SearchView svCariPetugas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_home, container, false);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView = view.findViewById(R.id.recyclerView_petugas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView2 = view.findViewById(R.id.recyclerView_artikel);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mApiPetugas = ApiClient.getClient().create(ApiPetugas.class);
        mApiArtikel = ApiClient.getClient().create(ApiArtikel.class);
        ma=this;

        svCariPetugas = view.findViewById(R.id.pencarian);
        svCariPetugas.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("nama", s);
                transaction.replace(R.id.page_container, PetugasFragment.class, bundle).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        btnTerdekat = view.findViewById(R.id.kategori_terdekat);
        btnSemua = view.findViewById(R.id.kategori_semua);
        btnDokter = view.findViewById(R.id.kategori_dokter);
        btnParamedis = view.findViewById(R.id.kategori_paramedis);
        btnPetugasIb = view.findViewById(R.id.kategori_petugas_ib);

        btnTerdekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori terdekat", Toast.LENGTH_LONG).show();
                getDataPetugas();
            }
        });

        btnSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Semua", Toast.LENGTH_LONG).show();
            }
        });

        btnDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Dokter", Toast.LENGTH_LONG).show();
            }
        });

        btnParamedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Paramedis", Toast.LENGTH_LONG).show();
            }
        });

        btnPetugasIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Petugas IB", Toast.LENGTH_LONG).show();
            }
        });

//        getDataPetugas();
        getDataArtikel();
        return view;
    }

    public void getDataPetugas(){
        Call<GetPetugas> petugasCall = mApiPetugas.getPetugas();
        petugasCall.enqueue(new Callback<GetPetugas>() {
            @Override
            public void onResponse(Call<GetPetugas> call, Response<GetPetugas> response) {
                List<Petugas> petugasList = response.body().getListDataPetugas();
                Log.d("Retrofit Get", "Jumlah data Petugas: " +
                        String.valueOf(petugasList.size()));
                mAdapter = new PetugasHomeAdapter(petugasList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        } );
    }

    public void getDataArtikel(){
        Call<GetArtikel> artikelCall = mApiArtikel.getArtikel();
        artikelCall.enqueue(new Callback<GetArtikel>() {
            @Override
            public void onResponse(Call<GetArtikel> call, Response<GetArtikel> response) {
                List<Artikel> artikelList = response.body().getListDataArtikel();
                Log.d("Retrofit Get", "Jumlah data Artikel: " +
                        String.valueOf(artikelList.size()));
                mAdapter2 = new ArtikelHomeAdapter(artikelList);
                mRecyclerView2.setAdapter(mAdapter2);
            }

            @Override
            public void onFailure(Call<GetArtikel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }


}