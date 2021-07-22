package com.def.dokternak.ui.konsultasi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;

import java.util.List;

import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class CariPetugasAdapter extends RecyclerView.Adapter<CariPetugasAdapter.MyViewHolder>{
    List<Petugas> mPetugasList;

    public CariPetugasAdapter(List <Petugas> petugasList) {
        mPetugasList = petugasList;
    }

    @Override
    public CariPetugasAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cari_petugas_list, parent, false);
        CariPetugasAdapter.MyViewHolder mViewHolder = new CariPetugasAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (CariPetugasAdapter.MyViewHolder holder, final int position){
        holder.mTextViewNamaDokter.setText(mPetugasList.get(position).getNamaDokter());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), TulisKonsultasiActivity.class);
                mIntent.putExtra("id_dokter", mPetugasList.get(position).getId());
                mIntent.putExtra("nama_dokter",mPetugasList.get(position).getNamaDokter());
                view.getContext().startActivity(mIntent);
                ((CariPetugasActivity)view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount () {
        return mPetugasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNamaDokter;
        public ImageView imgThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNamaDokter = itemView.findViewById(R.id.tv_nama_petugas);
        }
    }
}
