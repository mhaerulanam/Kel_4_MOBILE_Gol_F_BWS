package com.def.dokternak.ui.konsultasi.kategoriHewan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.kategori.KategoriHewan;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.ui.konsultasi.CariPetugasActivity;
import com.def.dokternak.ui.konsultasi.TulisKonsultasiActivity;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;

import java.util.List;

public class CariKategoriAdapter extends RecyclerView.Adapter<CariKategoriAdapter.MyViewHolder>{
    List<KategoriHewan> mJenisList;


    public CariKategoriAdapter(List <KategoriHewan> jenisList) {
        mJenisList = jenisList;
    }

    @Override
    public CariKategoriAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cari_kategori_list, parent, false);
        CariKategoriAdapter.MyViewHolder mViewHolder = new CariKategoriAdapter.MyViewHolder(mView);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder (CariKategoriAdapter.MyViewHolder holder, final int position){
        holder.mTextViewKategori.setText("" + mJenisList.get(position).getKategori_artikel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), TulisKonsultasiActivity.class);
                mIntent.putExtra("id_ktg", mJenisList.get(position).getId());
                mIntent.putExtra("kategori_artikel",mJenisList.get(position).getKategori_artikel());
                view.getContext().startActivity(mIntent);
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
