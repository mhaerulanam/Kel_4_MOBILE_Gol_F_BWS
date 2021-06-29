package com.def.dokternak.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://192.168.43.28:8080/api/";
    public static final String ARTIKEL_IMAGE_BASE_URL = "http://192.168.43.28:8080/data/data_artikel/";
    public static final String PETUGAS_IMAGE_BASE_URL = "http://192.168.43.28:8080/data/data_dokter/";

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
