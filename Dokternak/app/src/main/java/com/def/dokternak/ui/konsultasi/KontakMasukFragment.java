package com.def.dokternak.ui.konsultasi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiMasuk;
import com.def.dokternak.data.Model.konsultasi.RiwayatKonsultasi;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.konsultasi.ApiKonsultasi;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.petugas.PetugasAdapter;
import com.def.dokternak.ui.petugas.PetugasFragment;
import com.def.dokternak.utils.Preferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KontakMasukFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KontakMasukFragment extends Fragment {
    ApiKonsultasi mApiKonsultasi;
    private RecyclerView mRecyclerView;
    private KonsultasiMasukAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView tvalert;
    int id_peternak;
    public static KontakMasukFragment ma;

    public KontakMasukFragment() {
        // Required empty public constructor
    }

    public static KontakMasukFragment newInstance() {

        Bundle args = new Bundle();

        KontakMasukFragment fragment = new KontakMasukFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kontak_masuk, container, false);

//        FloatingActionButton fab = view.findViewById(R.id.tulis_konsultasi);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               Intent in = new Intent(view.getContext(), CariPetugasActivity.class);
//               view.getContext().startActivity(in);
//            }
//        });

        ImageButton imgKontakKonsultasi = view.findViewById(R.id.tulis_konsultasi_kontak);
        imgKontakKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), CariPetugasActivity.class);
                view.getContext().startActivity(in);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApiKonsultasi = ApiClient.getClient().create(ApiKonsultasi.class);
        tvalert = view.findViewById(R.id.alert_data_kosong);
        tvalert.setVisibility(View.INVISIBLE);
        Bundle bundle = this.getArguments();
        id_peternak = Preferences.getIdPeternak(getContext());
        refresh(id_peternak);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ma=this;
    }

    public void refresh(int id){
        Call<GetKonsultasiMasuk> konsultasiMasuk = mApiKonsultasi.getKonsultasiMasuk(id);
        konsultasiMasuk.enqueue(new Callback<GetKonsultasiMasuk>() {
            @Override
            public void onResponse(Call<GetKonsultasiMasuk> call, Response<GetKonsultasiMasuk> response) {
                List<RiwayatKonsultasi> konsultasiMasukList = response.body().getListDataKonsultasiMasuk();
                Log.d("Retrofit Get", "Jumlah data konsultasi Masuk: " +
                        String.valueOf(konsultasiMasukList.size()));
                if(konsultasiMasukList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                    mAdapter = new KonsultasiMasukAdapter(konsultasiMasukList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new KonsultasiMasukAdapter(konsultasiMasukList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetKonsultasiMasuk> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        } );
    }

}