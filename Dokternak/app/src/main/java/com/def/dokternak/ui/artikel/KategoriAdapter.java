package com.def.dokternak.ui.artikel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.kategori.KategoriHewan;
import com.def.dokternak.ui.konsultasi.TulisKonsultasiActivity;
import com.def.dokternak.ui.petugas.PetugasFragment;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder>{
    List<KategoriHewan> mJenisList;
    ArtikelFragment fragmentB;

    public KategoriAdapter(List <KategoriHewan> jenisList) {
        mJenisList = jenisList;}

    @Override
    public KategoriAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_list, parent, false);
        KategoriAdapter.MyViewHolder mViewHolder = new KategoriAdapter.MyViewHolder(mView);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder (KategoriAdapter.MyViewHolder holder, final int position){
        holder.mTextViewKategori.setText("" + mJenisList.get(position).getKategori_artikel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("jenis_hewan", mJenisList.get(position).getKategori_artikel());
                transaction.replace(R.id.page_container, ArtikelFragment.class, bundle).commit();
            }
        });
    }

    @Override
    public int getItemCount () {
        return mJenisList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewKategori;
        public ImageView imgThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewKategori = itemView.findViewById(R.id.tv_kategori_artikel);
        }
    }
}
