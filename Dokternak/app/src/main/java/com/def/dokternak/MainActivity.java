package com.def.dokternak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.def.dokternak.ui.artikel.ArtikelFragment;
import com.def.dokternak.ui.home.Home;
import com.def.dokternak.ui.login.LoginActivity;
import com.def.dokternak.ui.petugas.PetugasFragment;
import com.def.dokternak.ui.profile.ProfileFragment;
import com.def.dokternak.ui.puskeswan.PuskeswanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (checkInternet()){
//            openAlertDialog();
//        }

//        if(adaInternet()){
//            // tampilkan pesan
//            Toast.makeText(getApplicationContext(), "Terhubung ke internet", Toast.LENGTH_LONG).show();
//        }else{
//            // tampilkan pesan
//            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_LONG).show();
//        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Menampilkan halaman Fragment yang pertama kali muncul
        getFragmentPage(new Home());

        /*Inisialisasi BottomNavigationView beserta listenernya untuk
         *menangkap setiap kejadian saat salah satu menu item diklik
         */
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new Home();
                        break;

                    case R.id.petugas:
                        fragment = new PetugasFragment();
                        break;

                    case R.id.artikel:
                        fragment = new ArtikelFragment();
                        break;
                    case R.id.puskeswan:
                        fragment = new PuskeswanFragment();
                        break;
                    case R.id.Profil:
                        fragment = new ProfileFragment();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });
    }

    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

//    public boolean checkInternet(){
//        boolean connectStatus = true;
//        ConnectivityManager cm =
//                (ConnectivityManager)getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//        if(networkInfo != null && networkInfo.isConnected()==true ) {
//            connectStatus = true;
//        }
//        else {
//            connectStatus = false;
//        }
//        return connectStatus;
//    }

//    private boolean adaInternet(){
//        ConnectivityManager koneksi = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        return koneksi.getActiveNetworkInfo() != null;
//    }
//
//    public void openAlertDialog() {
//        new AlertDialog.Builder(MainActivity.this)
////                .setIcon(R.mipmap.ic_launcher)
//                .setTitle("No Internet")
//                .setMessage("Maaf, Koneksi Anda tidak terhubung!")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
//                    }
//                })
////                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(MainActivity.this, "Kamu memilih Cancel", Toast.LENGTH_SHORT).show();
////                    }
////                })
//                .show();
//    }
}