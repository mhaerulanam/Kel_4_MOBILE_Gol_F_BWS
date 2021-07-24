package com.def.dokternak.network.users;

import com.def.dokternak.data.Model.users.GetUser;
import com.def.dokternak.data.Model.users.PostUser;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiUser {
    @GET("api_users")
    Call<GetUser> getUser();

    @FormUrlEncoded
    @POST("api_users/login/peternak")
    Call<PostUser> postLoginUser(@Field("email") String email,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("api_users")
    Call<PostUser> postPetugas(@Field("id") int id ,
                                @Field("name") String name,
                                @Field("email") String email,
                                @Field("is_admin") String role,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("api_users/register/peternak")
    Call<PostUser> postRegisterUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded

    @PUT("api_users/{id}/updatepeternak")
    Call<PostUser> putUser(@Path("id") int id,
                           @Field("name") String nama,
                           @Field("password") String password,
                           @Field("email") String email,
                           @Field("no_hp") String noHp,
                           @Field("jenis_kelamin") String jenis_kelamin,
                           @Field("alamat") String alamat,
                           @Field("nama_gambar") String nama_gambar);
//                           @Part MultipartBody.Part foto_peternak);
}
