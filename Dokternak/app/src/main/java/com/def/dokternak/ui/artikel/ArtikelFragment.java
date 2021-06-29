package com.def.dokternak.ui.artikel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.GetKontak;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikel;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArtikelFragment extends Fragment {

    View view;
    Button btIns;
    ApiArtikel mApiArtikel;
    private RecyclerView mRecyclerView;
    private ArtikelAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ArtikelFragment ma;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_artikel, container, false);
        btIns = (Button) view.findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InsertArtikelActivity.class));
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mApiArtikel = ApiClient.getClient().create(ApiArtikel.class);
        ma=this;
        refresh();
        return view;
    }

//    public void refresh() {
//        Call<GetArtikel> artikelCall = mApiArtikel.getArtikel();
//        artikelCall.enqueue(new Callback<GetKontak>() {
//            @Override
//            public void onResponse(Call<GetKontak> call, Response<GetKontak>
//                    response) {
//                List<Artikel> artikelListl = response.body().get);
//                Log.d("Retrofit Get", "Jumlah data Artikel: " +
//                        String.valueOf(artikelFragmentList.size()));
//                mAdapter = new ArtikelAdapter(artikelFragmentList);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<GetKontak> call, Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
//    }

     public void refresh(){
        Call<GetArtikel> artikelCall = mApiArtikel.getArtikel();
        artikelCall.enqueue(new Callback<GetArtikel>() {
            @Override
            public void onResponse(Call<GetArtikel> call, Response<GetArtikel> response) {
                List<Artikel> artikelList = response.body().getListDataArtikel();
        Log.d("Retrofit Get", "Jumlah data Artikel: " +
                String.valueOf(artikelList.size()));
        mAdapter = new ArtikelAdapter(artikelList);
        mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetArtikel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
     }
}

