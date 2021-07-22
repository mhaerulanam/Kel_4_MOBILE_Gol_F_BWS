package com.def.dokternak.ui.petugas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikel;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.GetKategoriPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.GetTerdekatPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.artikel.ArtikelAdapter;
import com.def.dokternak.ui.artikel.ArtikelFragment;
import com.def.dokternak.ui.artikel.InsertArtikelActivity;
import com.def.dokternak.ui.home.PetugasHomeAdapter;
import com.def.dokternak.utils.Preferences;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetugasFragment extends Fragment {

    ApiPetugas mApiPetugas;
    private RecyclerView mRecyclerView;
    private PetugasAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static PetugasFragment ma;
    private TextView tvalert;
    private String alamat;
    private Button btnTerdekat, btnSemua, btnDokter, btnParamedis, btnPetugasIb;
    private SearchView svCariPetugas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_petugas, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvalert = view.findViewById(R.id.alert_data_kosong);
        tvalert.setVisibility(View.INVISIBLE);
        mApiPetugas = ApiClient.getClient().create(ApiPetugas.class);
        svCariPetugas = view.findViewById(R.id.pencarian);
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
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String myString = bundle.getString("nama");
            GetDataCariPetugas(myString);
        }
        else {
            refresh();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ma=this;

        btnTerdekat = view.findViewById(R.id.kategori_terdekat);
        btnSemua = view.findViewById(R.id.kategori_semua);
        btnDokter = view.findViewById(R.id.kategori_dokter);
        btnParamedis = view.findViewById(R.id.kategori_paramedis);
        btnPetugasIb = view.findViewById(R.id.kategori_petugas_ib);
        btnTerdekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori terdekat", Toast.LENGTH_LONG).show();
                alamat = Preferences.getAlamat(getContext());
                GetDataTerdekatPetugas(alamat);
            }
        });

        btnSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Semua", Toast.LENGTH_LONG).show();
                refresh();
            }
        });

        btnDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Dokter", Toast.LENGTH_LONG).show();
                GetDataKategoriPetugas("Dokter");
            }
        });

        btnParamedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Paramedis", Toast.LENGTH_LONG).show();
                GetDataKategoriPetugas("Paramedis");
            }
        });

        btnPetugasIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Petugas IB", Toast.LENGTH_LONG).show();
                GetDataKategoriPetugas("Petugas Inseminasi Buatan");
            }
        });
    }

    public void refresh(){
        Call<GetPetugas> petugasCall = mApiPetugas.getPetugas();
        petugasCall.enqueue(new Callback<GetPetugas>() {
            @Override
            public void onResponse(Call<GetPetugas> call, Response<GetPetugas> response) {
                List<Petugas> petugasList = response.body().getListDataPetugas();
                Log.d("Retrofit Get", "Jumlah data Petugas: " +
                        String.valueOf(petugasList.size()));
                if(petugasList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        } );
    }

    public void GetDataCariPetugas(String nama){
        Call<GetCariPetugas> petugasCariCall = mApiPetugas.getCariPetugas(nama);
        petugasCariCall.enqueue(new Callback<GetCariPetugas>() {
            @Override
            public void onResponse(Call<GetCariPetugas> call, Response<GetCariPetugas> response) {
                List<Petugas>petugasList = response.body().getListDataPetugas();
                Log.d("Retrofit Get", "Jumlah data Petugas: " +
                        String.valueOf(petugasList.size()));
                if(petugasList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetCariPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void GetDataKategoriPetugas(String jabatan){
        Call<GetKategoriPetugas> petugasCariCall = mApiPetugas.getKategoriPetugas(jabatan);
        petugasCariCall.enqueue(new Callback<GetKategoriPetugas>() {
            @Override
            public void onResponse(Call<GetKategoriPetugas> call, Response<GetKategoriPetugas> response) {
                List<Petugas>petugasList = response.body().getListDataPetugas();
                Log.d("Retrofit Get", "Jumlah data Petugas: " +
                        String.valueOf(petugasList.size()));
                if(petugasList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
            @Override
            public void onFailure(Call<GetKategoriPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
    public void GetDataTerdekatPetugas(String alamat){
        Call<GetTerdekatPetugas> petugasCariCall = mApiPetugas.getTerdekatPetugas(alamat);
        petugasCariCall.enqueue(new Callback<GetTerdekatPetugas>() {
            @Override
            public void onResponse(Call<GetTerdekatPetugas> call, Response<GetTerdekatPetugas> response) {
                List<Petugas>petugasList = response.body().getListDataPetugas();
                Log.d("Retrofit Get", "Jumlah data Petugas: " +
                        String.valueOf(petugasList.size()));
                if(petugasList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new PetugasAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
            @Override
            public void onFailure(Call<GetTerdekatPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}