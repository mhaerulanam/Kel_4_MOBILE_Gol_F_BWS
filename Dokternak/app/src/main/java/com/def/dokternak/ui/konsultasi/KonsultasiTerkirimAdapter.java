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
import com.def.dokternak.data.Model.konsultasi.Konsultasi;
import com.def.dokternak.data.Model.konsultasi.RiwayatKonsultasi;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;

import java.util.List;

import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class KonsultasiTerkirimAdapter extends RecyclerView.Adapter<KonsultasiTerkirimAdapter.MyViewHolder>{
    List<Konsultasi> mKonsultasiList;

    public KonsultasiTerkirimAdapter(List <Konsultasi> konsultasiList) {
        mKonsultasiList = konsultasiList;
    }

    @Override
    public KonsultasiTerkirimAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kotak_terkirim, parent, false);
        KonsultasiTerkirimAdapter.MyViewHolder mViewHolder = new KonsultasiTerkirimAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (KonsultasiTerkirimAdapter.MyViewHolder holder, final int position){
        holder.mTextViewId.setText("" + mKonsultasiList.get(position).getId_konsultasi());
        holder.mTextViewNamaDokter.setText("" + mKonsultasiList.get(position).getNamaDokter());
        holder.mTextViewTanggal.setText("" + mKonsultasiList.get(position).getTanggal());
        holder.mTextViewKeluhan.setText("" + mKonsultasiList.get(position).getKeluhan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailKonsultasiTekirimActivity.class);
                mIntent.putExtra("id_konsultasi", mKonsultasiList.get(position).getId_konsultasi());
                int id_konsultasi = mKonsultasiList.get(position).getId_konsultasi();
                view.getContext().startActivity(mIntent);
            }
        });
        Glide.with(holder.imgThumbnail.getContext())
                .load(PETUGAS_IMAGE_BASE_URL + mKonsultasiList.get(position).getFoto())
                .into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount () {
        return mKonsultasiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNamaDokter, mTextViewTanggal, mTextViewKeluhan;
        public ImageView imgThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tv_id);
            mTextViewNamaDokter = itemView.findViewById(R.id.tv_nama_dokter);
            mTextViewTanggal = itemView.findViewById(R.id.tv_tanggal);
            mTextViewKeluhan = itemView.findViewById(R.id.tv_keluhan);
            imgThumbnail = itemView.findViewById(R.id.img_petugas);
        }
    }
}
