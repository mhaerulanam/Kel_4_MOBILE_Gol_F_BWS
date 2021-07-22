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
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;

import java.util.List;

import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class KonsultasiMasukAdapter extends RecyclerView.Adapter<KonsultasiMasukAdapter.MyViewHolder>{
    List<RiwayatKonsultasi> mKonsultasiList;

    public KonsultasiMasukAdapter(List <RiwayatKonsultasi> konsultasiMasukList) {
        mKonsultasiList = konsultasiMasukList;
    }

    @Override
    public KonsultasiMasukAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kotak_masuk, parent, false);
        KonsultasiMasukAdapter.MyViewHolder mViewHolder = new KonsultasiMasukAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (KonsultasiMasukAdapter.MyViewHolder holder, final int position){
        holder.mTextViewId.setText("" + mKonsultasiList.get(position).getId_riwayat());
        holder.mTextViewNamaDokter.setText("" + mKonsultasiList.get(position).getNamaDokter());
        holder.mTextViewTanggal.setText("" + mKonsultasiList.get(position).getTanggalRespon());
        holder.mTextViewRespon.setText("" + mKonsultasiList.get(position).getRespon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailKonsultasiMasukActivity.class);
                mIntent.putExtra("id_riwayat", mKonsultasiList.get(position).getId_riwayat());
                int id_riwayat = mKonsultasiList.get(position).getId_riwayat();
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
        public TextView mTextViewId, mTextViewNamaDokter, mTextViewTanggal, mTextViewRespon;
        public ImageView imgThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tv_id);
            mTextViewNamaDokter = itemView.findViewById(R.id.tv_nama_dokter);
            mTextViewTanggal = itemView.findViewById(R.id.tv_tanggal);
            mTextViewRespon = itemView.findViewById(R.id.tv_respon);
            imgThumbnail = itemView.findViewById(R.id.img_petugas);
        }
    }
}
