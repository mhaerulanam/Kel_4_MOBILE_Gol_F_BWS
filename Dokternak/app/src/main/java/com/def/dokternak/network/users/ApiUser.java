package com.def.dokternak.network.users;

import com.def.dokternak.data.Model.petugas.GetPetugas;
import com.def.dokternak.data.Model.petugas.postPutDelPetugas;
import com.def.dokternak.data.Model.users.GetUser;
import com.def.dokternak.data.Model.users.PostUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
