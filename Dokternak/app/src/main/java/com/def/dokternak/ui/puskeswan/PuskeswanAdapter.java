package com.def.dokternak.ui.puskeswan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.def.dokternak.data.Model.puskeswan.Puskeswan;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;
import com.def.dokternak.ui.petugas.PetugasAdapter;

import java.util.List;

import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;
import static com.def.dokternak.network.ApiClient.PUSKESWAN_IMAGE_BASE_URL;

public class PuskeswanAdapter extends RecyclerView.Adapter<PuskeswanAdapter.MyViewHolder> {
    List<Puskeswan> mPuskeswanList;

    public PuskeswanAdapter(List <Puskeswan> puskeswanList) {
        mPuskeswanList = puskeswanList;
    }

    @NonNull
    @Override
    public PuskeswanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.puskeswan_list, parent, false);
        PuskeswanAdapter.MyViewHolder mViewHolder = new PuskeswanAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PuskeswanAdapter.MyViewHolder holder, int position) {
        holder.mTextViewId.setText("" + mPuskeswanList.get(position).getId());
        holder.mTextViewNamaPuskeswan.setText("" + mPuskeswanList.get(position).getNamaPuskeswan());
        holder.mTextViewAlamat.setText("" + mPuskeswanList.get(position).getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailPuskeswanActivity.class);
                mIntent.putExtra("Id", mPuskeswanList.get(position).getId());
                int id = mPuskeswanList.get(position).getId();
                view.getContext().startActivity(mIntent);
            }
        });
        Glide.with(holder.imgThumbnail.getContext())
                .load(PUSKESWAN_IMAGE_BASE_URL + mPuskeswanList.get(position).getGambar())
                .into(holder.imgThumbnail);

    }

    @Override
    public int getItemCount () {
        return mPuskeswanList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNamaPuskeswan, mTextViewAlamat;
        public ImageView imgThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tv_id);
            mTextViewNamaPuskeswan = itemView.findViewById(R.id.tv_nama_puskeswan);
            mTextViewAlamat = itemView.findViewById(R.id.tv_alamat);
            imgThumbnail = itemView.findViewById(R.id.img);
        }
    }
}
