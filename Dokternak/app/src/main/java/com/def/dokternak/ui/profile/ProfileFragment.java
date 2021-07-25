package com.def.dokternak.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.def.dokternak.MainActivity;
import com.def.dokternak.R;
import com.def.dokternak.ui.konsultasi.DetailKonsultasiMasukActivity;
import com.def.dokternak.ui.konsultasi.KonsultasiActivity;
import com.def.dokternak.ui.konsultasi.TulisKonsultasiActivity;
import com.def.dokternak.ui.login.LoginActivity;
import com.def.dokternak.ui.login.LoginActivity4;
import com.def.dokternak.ui.petugas.DetailPetugasActivity;
import com.def.dokternak.utils.Preferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.def.dokternak.network.ApiClient.PETERNAK_IMAGE_BASE_URL;
import static com.def.dokternak.network.ApiClient.PETUGAS_IMAGE_BASE_URL;

public class ProfileFragment extends Fragment {
    View view;
    TextView nama;
    String salam;
    int jam, id;
    private ImageView imgThumbnail, imgNoHp, imgJk, imgAlamat;
    public TextView tvNamaUser, tvEmailUser, tvNoHpUser, tvJenisKelaminUser, tvAlamatUser, tvSalam;
    String namaUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button btnLogout = view.findViewById(R.id.button_logoutMain);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Menghapus Status login dan kembali ke Login Activity
//                Preferences.clearLoggedInUser(getActivity().getBaseContext());
//                Toast.makeText(getContext(), "Log Out Berhasil", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
//                getActivity().finish();

                showDialog();
            }
        });

        id = Preferences.getId(getContext());

        Button btnEdit = view.findViewById(R.id.button_editProfile);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditProfilActivity.class);
                mIntent.putExtra("id", id);
                view.getContext().startActivity(mIntent);
            }
        });

        tvSalam = view.findViewById(R.id.salam);
        tvNamaUser = view.findViewById(R.id.tv_namaUser);
        tvEmailUser = view.findViewById(R.id.tv_emailUser);
        tvNoHpUser =  view.findViewById(R.id.tv_noHpUser);
        tvJenisKelaminUser = view.findViewById(R.id.tv_jenisKelaminUser);
        tvAlamatUser = view.findViewById(R.id.tv_alamatUser);
        imgThumbnail = view.findViewById(R.id.img_profileUser);
        imgNoHp = view.findViewById(R.id.imgNoHP);
        imgJk = view.findViewById(R.id.imgJenisKelamin);
        imgAlamat = view.findViewById(R.id.imgAlamat);

        //sembunyikan teks
        tvNoHpUser.setVisibility(View.GONE);
        tvJenisKelaminUser.setVisibility(View.GONE);
        tvAlamatUser.setVisibility(View.GONE);
        imgNoHp.setVisibility(View.GONE);
        imgAlamat.setVisibility(View.GONE);
        imgJk.setVisibility(View.GONE);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("H");
        String jamku = df.format(c.getTime());

        jam = Integer.parseInt(jamku);

        if (jam >= 05 && jam < 10) {
            salam = "Pagi";
        }else if (jam >= 10 && jam < 15) {
            salam = "Siang";
        }else if (jam >= 15 && jam <= 18) {
            salam = "Sore";
        }else {
            salam = "Malam";
        }

        tvSalam.setText("Selamat " + salam + "!");

        namaUser = Preferences.getNama(getContext());
        String emailUser = Preferences.getEmail(getContext());
        String noHpUser = Preferences.getNoHp(getContext());
        String jk = Preferences.getJenisKelamin(getContext());
        String alamat = Preferences.getAlamat(getContext());

        if (!noHpUser.equals("")) {
            imgNoHp.setVisibility(View.VISIBLE);
            tvNoHpUser.setVisibility(View.VISIBLE);
            tvNoHpUser.setText(noHpUser);
        }

        if (!jk.equals("")) {
            imgJk.setVisibility(View.VISIBLE);
            tvJenisKelaminUser.setVisibility(View.VISIBLE);
            tvJenisKelaminUser.setText(jk);
        }

        if (!alamat.equals("")) {
            imgAlamat.setVisibility(View.VISIBLE);
            tvAlamatUser.setVisibility(View.VISIBLE);
            tvAlamatUser.setText(alamat);
        }

        tvNamaUser.setText(namaUser);
        tvEmailUser.setText(emailUser);
        Glide.with(getContext())
                .load(PETERNAK_IMAGE_BASE_URL + Preferences.getImagePicture(getContext()))
                .into(imgThumbnail);

        return view;
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        // set title dialog
        alertDialogBuilder.setTitle("Hai! " + namaUser +", Apakah Anda yakin mau Logout dari aplikasi?");

        // set pesan dari dialog
        alertDialogBuilder
//                .setMessage("Klik "+"''"+" untuk keluar!")
//                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        Preferences.clearLoggedInUser(getActivity().getBaseContext());
                        Toast.makeText(getContext(), "Log Out Berhasil", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                        getActivity().finish();
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