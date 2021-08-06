package com.def.dokternak.ui.home;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.data.Model.artikel.GetArtikel;
import com.def.dokternak.data.Model.kategori.GetKategori;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.GetKategoriPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.GetTerdekatPetugas;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.artikel.ApiArtikel;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.artikel.ArtikelAdapter;
import com.def.dokternak.ui.konsultasi.KonsultasiActivity;
import com.def.dokternak.ui.login.LoginActivity;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;
import com.def.dokternak.ui.petugas.PetugasAdapter;
import com.def.dokternak.ui.petugas.PetugasFragment;
import com.def.dokternak.ui.profile.EditProfilActivity;
import com.def.dokternak.ui.profile.ProfileFragment;
import com.def.dokternak.utils.Preferences;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.def.dokternak.network.ApiClient.PETERNAK_IMAGE_BASE_URL;
import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class Home extends Fragment {

    ApiPetugas mApiPetugas;
    ApiArtikel mApiArtikel;
    private RecyclerView mRecyclerView, mRecyclerView2;
    private TextView tvalert;
    private PetugasHomeAdapter mAdapter;
    public ImageView imgThumbnail;
    private ArtikelHomeAdapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager;
    private String alamat;
    public static Home ma;
    private Button btnTerdekat, btnSemua, btnDokter, btnParamedis, btnPetugasIb;
    private SearchView svCariPetugas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_home, container, false);

        tvalert = view.findViewById(R.id.alert_data_kosong);
        tvalert.setVisibility(View.INVISIBLE);

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

        GetDataTerdekatPetugas(alamat);

        btnTerdekat = view.findViewById(R.id.kategori_terdekat);
        btnSemua = view.findViewById(R.id.kategori_semua);
        btnDokter = view.findViewById(R.id.kategori_dokter);
        btnParamedis = view.findViewById(R.id.kategori_paramedis);
        btnPetugasIb = view.findViewById(R.id.kategori_petugas_ib);
        btnTerdekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //terdekat
                Toast.makeText(getContext(), "Kategori terdekat", Toast.LENGTH_LONG).show();
                alamat = Preferences.getAlamat(getContext());
                if (alamat.equals("")){
                    showDialog();
                }else{
                    GetDataTerdekatPetugas(alamat);
                }
            }
        });

        btnSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tombol kategori Semua", Toast.LENGTH_LONG).show();
                getDataPetugas();
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

        ImageButton btnKonsultasi = view.findViewById(R.id.image_btnKonsultasi);
        btnKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Tombol Konsultasi", Toast.LENGTH_LONG).show();
                Intent mIntent = new Intent(view.getContext(), KonsultasiActivity.class);
                int id = 0;
                mIntent.putExtra("21", id);
//                mIntent.putExtra("Id", mPetugasList.get(position).getId());
//                int id = mPetugasList.get(position).getId();
                view.getContext().startActivity(mIntent);
            }
        });

        imgThumbnail = view.findViewById(R.id.img_profile);
        Glide.with(imgThumbnail.getContext())
                .load(PETERNAK_IMAGE_BASE_URL + Preferences.getImagePicture(getContext()))
                .into(imgThumbnail);
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
                if(petugasList.size() > 0){
                    tvalert.setVisibility(View.INVISIBLE);
                    mAdapter = new PetugasHomeAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new PetugasHomeAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }
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
                    mAdapter = new PetugasHomeAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new PetugasHomeAdapter(petugasList);
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
                    mAdapter = new PetugasHomeAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    tvalert.setVisibility(View.VISIBLE);
                    mAdapter = new PetugasHomeAdapter(petugasList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
            @Override
            public void onFailure(Call<GetTerdekatPetugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        // set title dialog
        alertDialogBuilder.setTitle("Hai! " + Preferences.getNama(getContext()) +", untuk mencari lokasi terdekat.");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Silahkan lengkapi data diri Anda!")
//                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        startActivity(new Intent(getActivity().getBaseContext(), EditProfilActivity.class));
                        getActivity();

                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}