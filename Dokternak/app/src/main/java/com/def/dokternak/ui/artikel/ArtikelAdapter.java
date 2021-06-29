package com.def.dokternak.ui.artikel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.def.dokternak.R;
import com.def.dokternak.data.Model.Kontak;
import com.def.dokternak.data.Model.artikel.Artikel;
import com.def.dokternak.ui.kontak.EditActivity;

import java.util.List;

import static com.def.dokternak.network.ApiClient.ARTIKEL_IMAGE_BASE_URL;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.MyViewHolder> {
    List<Artikel> mArtikelList;

    public ArtikelAdapter(List <Artikel> artikelList) {
        mArtikelList = artikelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artikel_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewId.setText("Id = " + mArtikelList.get(position).getId());
        holder.mTextViewIdKtg.setText(" " + mArtikelList.get(position).getIdKtg());
        holder.mTextViewTanggal.setText(" " + mArtikelList.get(position).getTanggal());
//        holder.mTextViewNamaPenulis.setText("Nama Penulis = " + mArtikelList.get(position).getNamaPenulis());
        holder.mTextViewJudul.setText(" " + mArtikelList.get(position).getJudul());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailArtikelActivity.class);
                mIntent.putExtra("Id", mArtikelList.get(position).getId());
                int id = mArtikelList.get(position).getId();
                view.getContext().startActivity(mIntent);
            }
        });
        Glide.with(holder.imgThumbnail.getContext())
                .load(ARTIKEL_IMAGE_BASE_URL + mArtikelList.get(position).getGambar())
                .into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount () {
        return mArtikelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewIdKtg, mTextViewTanggal, mTextViewNamaPenulis, mTextViewJudul;
        public ImageView imgThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.tvId);
            mTextViewIdKtg = (TextView) itemView.findViewById(R.id.tvIdKtg);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tvTanggal);
//            mTextViewNamaPenulis = (TextView) itemView.findViewById(R.id.tvNamaPenulis);
            mTextViewJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            imgThumbnail = itemView.findViewById(R.id.ivGambar);
        }
    }
}
