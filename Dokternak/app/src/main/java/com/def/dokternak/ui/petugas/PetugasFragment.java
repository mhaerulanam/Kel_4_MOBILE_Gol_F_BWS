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

import com.def.dokternak.R;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikel;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.artikel.ArtikelAdapter;
import com.def.dokternak.ui.artikel.ArtikelFragment;
import com.def.dokternak.ui.artikel.InsertArtikelActivity;

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
        mApiPetugas = ApiClient.getClient().create(ApiPetugas.class);
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
    }

    public void refresh(){
        Call<GetPetugas> petugasCall = mApiPetugas.getPetugas();
        petugasCall.enqueue(new Callback<GetPetugas>() {
            @Override
            public void onResponse(Call<GetPetugas> call, Response<GetPetugas> response) {
                List<Petugas> petugasList = response.body().getListDataPetugas();
                Log.d("Retrofit Get", "Jumlah data Petugas: " +
                        String.valueOf(petugasList.size()));
                mAdapter = new PetugasAdapter(petugasList);
                mRecyclerView.setAdapter(mAdapter);
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
                mAdapter = new PetugasAdapter(petugasList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetCariPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}