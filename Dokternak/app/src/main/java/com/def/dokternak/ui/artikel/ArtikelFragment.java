package com.def.dokternak.ui.artikel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.GetKontak;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikel;
import com.def.dokternak.data.Model.artikel.GetCariArtikel;
import com.def.dokternak.data.Model.artikel.GetKategoriArtikel;
import com.def.dokternak.data.Model.kategori.GetCariJenis;
import com.def.dokternak.data.Model.kategori.GetKategori;
import com.def.dokternak.data.Model.kategori.KategoriHewan;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;
import com.def.dokternak.network.kategori.ApiKategori;
import com.def.dokternak.ui.konsultasi.kategoriHewan.CariKategoriActivity;
import com.def.dokternak.ui.konsultasi.kategoriHewan.CariKategoriAdapter;
import com.def.dokternak.ui.petugas.PetugasAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArtikelFragment extends Fragment{

    View view;
    Button btIns;
    ApiArtikel mApiArtikel;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private ArtikelAdapter mAdapter;
    ApiKategori mApiJenis;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager2;
    private KategoriAdapter mAdapter2;
    public static ArtikelFragment ma;
    private TextView tvalert;
    private SearchView svCariArtikel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_artikel, container, false);
        tvalert = view.findViewById(R.id.alert_data_kosong);
        tvalert.setVisibility(View.INVISIBLE);
        svCariArtikel = view.findViewById(R.id.pencarian);
        svCariArtikel.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                GetDataCariArtikel(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        String jenis = getActivity().getIntent().getStringExtra("kategori_artikel");

        if (jenis != null){
            GetDataCariKategori(jenis);
        }

        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            String myString = bundle.getString("judul");
//            GetDataCariArtikel(myString);
//        }




        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mApiArtikel = ApiClient.getClient().create(ApiArtikel.class);
        ma=this;

        mApiJenis = ApiClient.getClient().create(ApiKategori.class);
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView2.setLayoutManager(mLayoutManager2);
        if (bundle != null) {
            String myString = bundle.getString("jenis_hewan");
            Toast.makeText(getContext(), "Kategori "+ myString,Toast.LENGTH_LONG).show();
            GetDataKategori(myString);
        }else {
            refresh();
        }
        GetDataCariKategori("");
        return view;
    }


     public void refresh(){
        Call<GetArtikel> artikelCall = mApiArtikel.getArtikel();
        artikelCall.enqueue(new Callback<GetArtikel>() {
            @Override
            public void onResponse(Call<GetArtikel> call, Response<GetArtikel> response) {
                List<Artikel> artikelList = response.body().getListDataArtikel();
        Log.d("Retrofit Get", "Jumlah data Artikel: " +
                String.valueOf(artikelList.size()));
                if(artikelList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                }else{
                    tvalert.setVisibility(View.VISIBLE);
                }
                mAdapter = new ArtikelAdapter(artikelList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetArtikel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
     }

    public void GetDataCariArtikel(String judul) {
        Call<GetCariArtikel> artikelCariCall = mApiArtikel.getCariArtikel(judul);
        artikelCariCall.enqueue(new Callback<GetCariArtikel>() {
            @Override
            public void onResponse(Call<GetCariArtikel> call, Response<GetCariArtikel> response) {
                List<Artikel> artikelList = response.body().getListDataArtikel();

                Log.d("Retrofit Get", "Jumlah data Artikel:" +
                        " " +
                        String.valueOf(artikelList.size()));
                if(artikelList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                }
                mAdapter = new ArtikelAdapter(artikelList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetCariArtikel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void GetDataKategori(String kategori_artikel) {
        Call<GetKategoriArtikel> artikelKategoriCall = mApiArtikel.getKategoriArtikel(kategori_artikel);
        artikelKategoriCall.enqueue(new Callback<GetKategoriArtikel>() {
            @Override
            public void onResponse(Call<GetKategoriArtikel> call, Response<GetKategoriArtikel> response) {
                List<Artikel> artikelList = response.body().getListDataKategoriArtikel();
                Log.d("Retrofit Get", "Jumlah data Artikel:" +
                        " " +
                        String.valueOf(artikelList.size()));
                if(artikelList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                }
                mAdapter = new ArtikelAdapter(artikelList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKategoriArtikel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void GetDataCariKategori(String kategori_artikel){
        Call<GetCariJenis> jenisCariCall = mApiJenis.getCariJenisHewan(kategori_artikel);
        jenisCariCall.enqueue(new Callback<GetCariJenis>() {
            @Override
            public void onResponse(Call<GetCariJenis> call, Response<GetCariJenis> response) {
                List<KategoriHewan> kategoriList = response.body().getListDataJenisHewan();
                Log.d("Retrofit Get", "Jumlah data Kategori: " +
                        String.valueOf(kategoriList.size()));
                mAdapter2 = new KategoriAdapter(kategoriList);
                mRecyclerView2.setAdapter(mAdapter2);
            }

            @Override
            public void onFailure(Call<GetCariJenis> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}

