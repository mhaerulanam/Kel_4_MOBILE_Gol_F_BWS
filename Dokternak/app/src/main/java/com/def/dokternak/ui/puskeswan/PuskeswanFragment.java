package com.def.dokternak.ui.puskeswan;

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

import com.def.dokternak.R;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.data.Model.puskeswan.GetCariPuskeswan;
import com.def.dokternak.data.Model.puskeswan.GetPuskeswan;
import com.def.dokternak.data.Model.puskeswan.Puskeswan;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.puskeswan.ApiPuskeswan;
import com.def.dokternak.ui.petugas.PetugasAdapter;
import com.def.dokternak.ui.petugas.PetugasFragment;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PuskeswanFragment extends Fragment {

    ApiPuskeswan mApiPuskeswan;
    private RecyclerView mRecyclerView;
    private PuskeswanAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static PuskeswanFragment ma;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_puskeswan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApiPuskeswan = ApiClient.getClient().create(ApiPuskeswan.class);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String myString = bundle.getString("nama");
            GetDataCariPuskeswan(myString);
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
        Call<GetPuskeswan> puskeswanCall = mApiPuskeswan.getPuskeswan();
        puskeswanCall.enqueue(new Callback<GetPuskeswan>() {
            @Override
            public void onResponse(Call<GetPuskeswan> call, Response<GetPuskeswan> response) {
                 List<Puskeswan> puskeswanList = response.body().getListDataPuskeswan();
                Log.d("Retrofit Get", "Jumlah data Puskeswan: " +
                        String.valueOf(puskeswanList.size()));
                mAdapter = new PuskeswanAdapter(puskeswanList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPuskeswan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }

        } );
    }

    public void GetDataCariPuskeswan(String nama){
        Call<GetCariPuskeswan> getCariPuskeswanCall = mApiPuskeswan.GetCariPuskeswan(nama);
        getCariPuskeswanCall.enqueue(new Callback<GetCariPuskeswan>() {
            @Override
            public void onResponse(Call<GetCariPuskeswan> call, Response<GetCariPuskeswan> response) {
                List<Puskeswan>puskeswanList = response.body().getListDataPuskeswan();
                Log.d("Retrofit Get", "Jumlah data Puskeswan: " +
                        String.valueOf(puskeswanList.size()));
                mAdapter = new PuskeswanAdapter(puskeswanList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<GetCariPuskeswan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }

        });
    }
}