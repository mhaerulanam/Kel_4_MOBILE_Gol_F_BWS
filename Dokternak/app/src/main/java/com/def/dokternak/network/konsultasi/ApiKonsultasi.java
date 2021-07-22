package com.def.dokternak.network.konsultasi;

import com.def.dokternak.data.Model.artikel.postPutDelArtikel;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiMasukDetail;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiTerkirim;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiMasuk;
import com.def.dokternak.data.Model.konsultasi.GetKonsultasiTerkirimDetail;
import com.def.dokternak.data.Model.konsultasi.postKonsultasi;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugasDetail;
import com.def.dokternak.data.Model.petugas.postPutDelPetugas;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiKonsultasi {
    @GET("api_konsultasi/konsultasimasuk/{id}")
    Call<GetKonsultasiMasuk> getKonsultasiMasuk(@Path("id")int id);

    @GET("api_konsultasi/konsultasiterkirim/{id}")
    Call<GetKonsultasiTerkirim> getKonsultasiTerkirim(@Path("id")int id);

//    @GET("api_konsultasi/konsultasimasuk")
//    Call<GetKonsultasiMasuk> getKonsultasiMasuk();
//
//    @GET("api_konsultasi/konsultasiterkirim")
//    Call<GetKonsultasiTerkirim> getKonsultasiTerkirim();

    @GET("api_konsultasi/detailmasuk/{id}")
    Call<GetKonsultasiMasukDetail> getKonsultasiMasukDetail(@Path("id") int id_riwayat);

    @GET("api_konsultasi/detailterkirim/{id}")
    Call<GetKonsultasiTerkirimDetail> getKonsultasiTerkirimDetail(@Path("id") int id_konsultasi);

    @FormUrlEncoded
    @POST("api_konsultasi")
    Call<postKonsultasi> postKonsultasi(@Field("id_peternak") int id_peternak ,
                                     @Field("id_dokter") int id_dokter,
                                     @Field("id_kategori") int id_kategori,
                                     @Field("id_ktg") int id_ktg,
                                     @Field("nama_hewan") String nama_hewan,
                                     @Field("keluhan") String keluhan,
                                     @Field("status_kirim") String status_kirim,
                                     @Field("tanggal") String tanggal);

}
