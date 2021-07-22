package com.def.dokternak.network.petugas;

import com.def.dokternak.data.Model.kategori.GetKategori;
import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.GetKategoriPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugasDetail;
import com.def.dokternak.data.Model.petugas.GetTerdekatPetugas;
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

public interface ApiPetugas {
    @GET("api_petugas")
    Call<GetPetugas> getPetugas();

    @FormUrlEncoded
    @POST("api_petugas")
    Call<postPutDelPetugas> postPetugas(@Field("id_ktg") int id_ktg ,
                                       @Field("tanggal") String tanggal ,
                                       @Field("nama_penulis") String nama_penulis ,
                                       @Field("judul") String judul ,
                                       @Field("isi") String isi ,
                                       @Field("gambar") String gambar ,
                                       @Field("sumber") String sumber ,
                                       @Field("status") String status);

    @FormUrlEncoded
    @PUT("api_petugas/{id}")
    Call<postPutDelPetugas> putPetugas(@Path("id") int id,
                                     @Field("id_ktg") int id_ktg ,
                                     @Field("tanggal") String tanggal ,
                                     @Field("nama_penulis") String nama_penulis ,
                                     @Field("judul") String judul ,
                                     @Field("isi") String isi ,
                                     @Field("gambar") String gambar ,
                                     @Field("sumber") String sumber ,
                                     @Field("status") String status);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "api_artikel/{id}", hasBody = true)
    Call<postPutDelPetugas> deletePetugas(@Path("id") int id);

    @GET("api_petugas/{id}")
    Call<GetPetugasDetail> getPetugasDetail(@Path("id") int id);

    //uri pencarian
    @GET("api_petugas/cari/petugas")
    Call<GetCariPetugas> getCariPetugas(@Query("nama_dokter") String nama_dokter);

    //uri kategori
    @GET("api_petugas/kategori/petugas")
    Call<GetKategoriPetugas> getKategoriPetugas(@Query("jabatan") String jabatan);

    //uri terdekat
    @GET("api_petugas/terdekat/petugas")
    Call<GetTerdekatPetugas> getTerdekatPetugas(@Query("alamat") String alamat);
}
