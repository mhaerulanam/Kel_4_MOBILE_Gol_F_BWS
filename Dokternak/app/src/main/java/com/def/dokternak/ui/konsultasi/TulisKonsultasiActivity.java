package com.def.dokternak.ui.konsultasi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.def.dokternak.R;
import com.def.dokternak.data.Model.kategori.GetCariJenis;
import com.def.dokternak.data.Model.kategori.GetKategori;
import com.def.dokternak.data.Model.kategori.KategoriHewan;
import com.def.dokternak.data.Model.konsultasi.postKonsultasi;
import com.def.dokternak.network.ApiClient;
import com.def.dokternak.network.kategori.ApiKategori;
import com.def.dokternak.network.konsultasi.ApiKonsultasi;
import com.def.dokternak.network.petugas.ApiPetugas;
import com.def.dokternak.ui.konsultasi.kategoriHewan.CariKategoriActivity;
import com.def.dokternak.ui.konsultasi.kategoriHewan.CariKategoriAdapter;
import com.def.dokternak.utils.Preferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TulisKonsultasiActivity extends AppCompatActivity {
    ApiPetugas mApiPetugas;
    EditText nama_petugas, kategori_hewan, nama_hewan, keluhan;
    Spinner jenis_hewan;
    Button btn_kirim, btn_batal;
    ImageButton btnCariPetugas, btnCariKategori;
    ApiKonsultasi mApiKonsultasi;
    ApiKategori mApiJenis;
    Context mContext;
    int id_kategori, id_peternak,id_dokter, id_jenis;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CariKategoriAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulis_konsultasi);
        mContext = this;

        id_peternak = Preferences.getIdPeternak(this);
//        Toast.makeText(getApplicationContext(), "Id adalah : " + id_peternak, Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = df.format(c.getTime());

        id_dokter = getIntent().getIntExtra("id_dokter", 0);
        String nama = getIntent().getStringExtra("nama_dokter");

//        id_jenis = getIntent().getIntExtra("id_ktg",0);
//        String jenis = getIntent().getStringExtra("kategori_artikel");


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.nama_kategori);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();
                if (text.equals("Hewan Ternak")){
                    id_kategori = 1;
                }else if(text.equals("Hewan Kesayangan")){
                    id_kategori = 2;
                }

            }
        });

        mApiKonsultasi = ApiClient.getClient().create(ApiKonsultasi.class);
        mApiJenis = ApiClient.getClient().create(ApiKategori.class);
        nama_petugas = (EditText) findViewById(R.id.nama_petugas);
        jenis_hewan = (Spinner) findViewById(R.id.jenis_hewan);
        nama_hewan = (EditText) findViewById(R.id.nama_hewan);
        keluhan = (EditText) findViewById(R.id.keluhan);
        btn_kirim = (Button) findViewById(R.id.btn_kirim);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        btnCariPetugas = (ImageButton) findViewById(R.id.btn_cari_petugas);
        btnCariKategori = (ImageButton) findViewById(R.id.btn_cari_kategori);

        nama_petugas.setText(nama);

        if (id_dokter != 0 && nama != null){
            nama_petugas.setText(nama);
        }

        initSpinnerDosen();

        jenis_hewan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_jenis = (int) parent.getItemIdAtPosition(position);
                String selectedName = parent.getItemAtPosition(position).toString();
//                requestDetailDosen(selectedName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        if (id_jenis != 0 && jenis != null){
//            jenis_hewan.setText(jenis);
//        }

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<postKonsultasi> postKonsultasiCall = mApiKonsultasi.postKonsultasi(id_peternak, id_dokter, id_kategori, id_jenis, nama_hewan.getText().toString(), keluhan.getText().toString(), "norespon", tanggal);
                postKonsultasiCall.enqueue(new Callback<postKonsultasi>() {
                    @Override
                    public void onResponse(Call<postKonsultasi> call, Response<postKonsultasi> response) {
                        startActivity(new Intent(TulisKonsultasiActivity.this, KonsultasiActivity.class));
                        Toast.makeText(getApplicationContext(), "Konsultasi Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<postKonsultasi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Konsultasi gagal", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnCariPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TulisKonsultasiActivity.this, CariPetugasActivity.class));
            }
        });

        btnCariKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TulisKonsultasiActivity.this, CariKategoriActivity.class));
            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        id_dokter = getIntent().getIntExtra("id_dokter", 0);
        String nama = getIntent().getStringExtra("nama_dokter");

        id_jenis = getIntent().getIntExtra("id_ktg",0);
        String jenis = getIntent().getStringExtra("kategori_artikel");
        super.onResume();
        if (id_dokter != 0 && nama != null){
            nama_petugas.setText(nama);
        }
//
//        if (id_jenis != 0 && jenis != null){
//            jenis_hewan.setText(jenis);
//        }

    }

    private void initSpinnerDosen(){
        Call<GetKategori> jenisCariCall = mApiJenis.getKategori();
        jenisCariCall.enqueue(new Callback<GetKategori>() {
            @Override
            public void onResponse(Call<GetKategori> call, Response<GetKategori> response) {
                if (response.isSuccessful()) {
                    List<KategoriHewan> semuadosenItems = response.body().getListDataKategori();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < semuadosenItems.size(); i++){
                        listSpinner.add(semuadosenItems.get(i).getKategori_artikel());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    jenis_hewan.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data jenis hewan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetKategori> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}