package com.def.dokternak.ui.home;

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
import com.def.dokternak.ui.contohpetugas.DataFilter;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;

import java.util.ArrayList;
import java.util.List;

import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class PetugasHomeAdapter extends RecyclerView.Adapter<PetugasHomeAdapter.MyViewHolder>{
    List<Petugas> mPetugasList;

    public PetugasHomeAdapter(List <Petugas> petugasList) {
        mPetugasList = petugasList;
    }

    @Override
    public PetugasHomeAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.petugas_list_home, parent, false);
        PetugasHomeAdapter.MyViewHolder mViewHolder = new PetugasHomeAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (PetugasHomeAdapter.MyViewHolder holder, final int position){
        holder.mTextViewId.setText("" + mPetugasList.get(position).getId());
        holder.mTextViewNamaDokter.setText("" + mPetugasList.get(position).getNamaDokter());
        holder.mTextViewJabatan.setText("" + mPetugasList.get(position).getIdJabatan());
        holder.mTextViewTempat.setText("" + mPetugasList.get(position).getTempat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailPetugasActivity.class);
                mIntent.putExtra("Id", mPetugasList.get(position).getId());
                int id = mPetugasList.get(position).getId();
                view.getContext().startActivity(mIntent);
            }
        });
        Glide.with(holder.imgThumbnail.getContext())
                .load(PETUGAS_IMAGE_BASE_URL + mPetugasList.get(position).getFoto())
                .into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount () {
        return mPetugasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNamaDokter, mTextViewJabatan, mTextViewTempat;
        public ImageView imgThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tv_id);
            mTextViewNamaDokter = itemView.findViewById(R.id.tv_nama_dokter);
            mTextViewJabatan = itemView.findViewById(R.id.tv_jabatan);
            mTextViewTempat = itemView.findViewById(R.id.tv_tempat);
            imgThumbnail = itemView.findViewById(R.id.img);
        }
    }

}
