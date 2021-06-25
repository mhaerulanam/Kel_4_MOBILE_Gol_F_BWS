package com.def.dokternak;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.Nullable;

public class Home extends Fragment {

    Button buttonKatTerdekat,buttonKatSemua,buttonKatDokter,buttonKatParamedis,buttonKatInseminator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Memanggil button kategori
        buttonKatTerdekat = view.findViewById(R.id.kat_terdekat);
        buttonKatSemua = view.findViewById(R.id.kat_semua);
        buttonKatDokter = view.findViewById(R.id.kat_dokter);
        buttonKatParamedis = view.findViewById(R.id.kat_paramedis);
        buttonKatInseminator = view.findViewById(R.id.kat_ib);

        //Menampilkan fragment untuk setiap tombol
        buttonKatTerdekat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                KategoriTerdekatFragment kategoriTerdekatFragment = new KategoriTerdekatFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_kategori, kategoriTerdekatFragment);
                transaction.commit();
            }
        });

        buttonKatSemua.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                KategoriSemuaFragment kategoriSemuaFragment = new KategoriSemuaFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_kategori, kategoriSemuaFragment);
                transaction.commit();
            }
        });

        buttonKatDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KategoriDokterFragment kategoriDokterFragment = new KategoriDokterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_kategori, kategoriDokterFragment);
                transaction.commit();
            }
        });

        buttonKatParamedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KategoriParamedisFragment kategoriParamedisFragment = new KategoriParamedisFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_kategori, kategoriParamedisFragment);
                transaction.commit();
            }
        });

        buttonKatInseminator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KategoriInseminatorFragment kategoriInseminatorFragment = new KategoriInseminatorFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_kategori, kategoriInseminatorFragment);
                transaction.commit();
            }
        });

        return view;
    }


}