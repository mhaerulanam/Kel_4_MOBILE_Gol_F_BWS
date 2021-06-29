package com.def.dokternak.network.artikel;


import com.def.dokternak.data.Model.GetKontak;
import com.def.dokternak.data.Model.PostPutDelKontak;
import com.def.dokternak.data.Model.artikel.GetArtikel;
import com.def.dokternak.data.Model.artikel.GetArtikelDetail;
import com.def.dokternak.data.Model.artikel.postPutDelArtikel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiArtikel {
    @GET("api_artikel")
    Call<GetArtikel> getArtikel();

    @FormUrlEncoded
    @POST("api_artikel")
    Call<postPutDelArtikel> postArtikel(@Field("id_ktg") int id_ktg ,
                                      @Field("tanggal") String tanggal ,
                                      @Field("nama_penulis") String nama_penulis ,
                                      @Field("judul") String judul ,
                                      @Field("isi") String isi ,
                                      @Field("gambar") String gambar ,
                                      @Field("sumber") String sumber ,
                                      @Field("status") String status);

    @FormUrlEncoded
    @PUT("api_artikel/{id}")
    Call<postPutDelArtikel> putArtikel(@Path("id") int id,
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
    Call<postPutDelArtikel> deleteArtikel(@Path("id") int id);

    @GET("api_artikel/{id}")
    Call<GetArtikelDetail> getArtikelDetail(@Path("id") int id);
}
