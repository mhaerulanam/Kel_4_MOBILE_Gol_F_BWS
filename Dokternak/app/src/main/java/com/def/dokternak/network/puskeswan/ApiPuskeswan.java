package com.def.dokternak.network.puskeswan;

import com.def.dokternak.data.Model.petugas.GetCariPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.GetPetugasDetail;
import com.def.dokternak.data.Model.petugas.postPutDelPetugas;
import com.def.dokternak.data.Model.puskeswan.GetCariPuskeswan;
import com.def.dokternak.data.Model.puskeswan.GetPuskeswan;
import com.def.dokternak.data.Model.puskeswan.GetPuskeswanDetail;
import com.def.dokternak.data.Model.puskeswan.postPutDelPuskeswan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPuskeswan {
    @GET("api_puskeswan")
    Call<GetPuskeswan> getPuskeswan();

    @FormUrlEncoded
    @POST("api_puskeswan")
    Call<postPutDelPuskeswan> postPuskeswan(@Field("nama_puskeswan") String nama_puskeswan ,
                                            @Field("alamat") String alamat ,
                                            @Field("jam_kerja") String jam_kerja ,
                                            @Field("gambar") String gambar ,
                                            @Field("maps") String maps ,
                                            @Field("status") String status);

    @FormUrlEncoded
    @PUT("api_puskeswan/{id}")
    Call<postPutDelPuskeswan> putPuskeswan(@Path("id") int id,
                                       @Field("nama_puskeswan") String nama_puskeswan ,
                                       @Field("alamat") String alamat ,
                                       @Field("jam_kerja") String jam_kerja ,
                                       @Field("gambar") String gambar ,
                                       @Field("maps") String maps ,
                                       @Field("status") String status);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "api_puskwswan/{id}", hasBody = true)
    Call<postPutDelPuskeswan> deletePuskeswan(@Path("id") int id);

    @GET("api_puskeswan/{id}")
    Call<GetPuskeswanDetail> getPuskeswanDetail(@Path("id") int id);

    //uri pencarian
    @GET("api_puskeswan/cari/puskeswan")

    Call<GetCariPuskeswan> GetCariPuskeswan(@Query("nama_puskeswan") String nama_puskeswan);
}
