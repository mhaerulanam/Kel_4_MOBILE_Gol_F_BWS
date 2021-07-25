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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiTerkirim;
import com.def.dokternak.data.Model.konsultasi.Konsultasi;
import com.def.dokternak.data.Model.konsultasi.deleteKonsultasi;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.konsultasi.ApiKonsultasi;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.login.LoginActivity;
import com.def.dokternak.ui.petugas.PetugasAdapter;
import com.def.dokternak.ui.profile.EditProfilActivity;
import com.def.dokternak.utils.Preferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KontakTerkirimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KontakTerkirimFragment extends Fragment {

    ApiKonsultasi mApiKonsultasi;
    private RecyclerView mRecyclerView;
    private KonsultasiTerkirimAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView tvalert;

    int id_peternak;
    public static KontakTerkirimFragment ma;

    public KontakTerkirimFragment() {
        // Required empty public constructor
    }

    public static KontakTerkirimFragment newInstance() {

        Bundle args = new Bundle();

        KontakTerkirimFragment fragment = new KontakTerkirimFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kontak_terkirim, container, false);

//        FloatingActionButton fab = view.findViewById(R.id.tulis_konsultasi);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in = new Intent(view.getContext(), TulisKonsultasiActivity.class);
//                view.getContext().startActivity(in);
//            }
//        });

        ImageButton imgTulisKonsultasi = view.findViewById(R.id.tulis_konsultasi);
        imgTulisKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), TulisKonsultasiActivity.class);
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
        Call<GetKonsultasiTerkirim> konsultasiTerkirimCall = mApiKonsultasi.getKonsultasiTerkirim(id);
        konsultasiTerkirimCall.enqueue(new Callback<GetKonsultasiTerkirim>() {
            @Override
            public void onResponse(Call<GetKonsultasiTerkirim> call, Response<GetKonsultasiTerkirim> response) {
                List<Konsultasi> konsultasiList = response.body().getListDataKonsultasi();
                Log.d("Retrofit Get", "Jumlah data Konsultasi Terkirim: " +
                        String.valueOf(konsultasiList.size()));
                mAdapter = new KonsultasiTerkirimAdapter(konsultasiList);
                mRecyclerView.setAdapter(mAdapter);
                if(konsultasiList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                    mAdapter = new KonsultasiTerkirimAdapter(konsultasiList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new KonsultasiTerkirimAdapter(konsultasiList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetKonsultasiTerkirim> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        } );
    }
}