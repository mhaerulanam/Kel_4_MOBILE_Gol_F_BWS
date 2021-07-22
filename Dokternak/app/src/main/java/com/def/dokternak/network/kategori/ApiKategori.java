package com.def.dokternak.network.kategori;

import com.def.dokternak.data.Model.kategori.GetCariJenis;
import com.def.dokternak.data.Model.kategori.GetKategori;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiKategori {
    @GET("api_kategori")
    Call<GetKategori> getKategori();

    //uri pencarian
    @GET("api_kategori/kategori/cari")
    Call<GetCariJenis> getCariJenisHewan(@Query("kategori_artikel") String kategori_artikel);
}
