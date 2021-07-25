package com.def.dokternak.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //URI Hosting Web Service Dokternak.id
//    public static final String BASE_URL = "http://www.webservice.dokternak.id/api/";
//    public static final String BASE_URL = "http://www.webservice.dokternak.id/public/api/";
//    public static final String ARTIKEL_IMAGE_BASE_URL = "http://www.webservice.dokternak.id/public/data/data_artikel/";
//    public static final String PETUGAS_IMAGE_BASE_URL = "http://www.webservice.dokternak.id/public/data/data_dokter/";
//    public static final String PETERNAK_IMAGE_BASE_URL = "http://www.webservice.dokternak.id/public/data/data_peternak/";
//    public static final String PUSKESWAN_IMAGE_BASE_URL = "http://www.webservice.dokternak.id/public/data/data_puskeswan/";

    //URI Hosting workshopjti
    public static final String BASE_URL = "http://dokternak.workshopjti.com/api/";
    public static final String ARTIKEL_IMAGE_BASE_URL = "http://dokternak.workshopjti.com/data/data_artikel/";
    public static final String PETUGAS_IMAGE_BASE_URL = "http://dokternak.workshopjti.com/data/data_dokter/";
    public static final String PETERNAK_IMAGE_BASE_URL = "http://dokternak.workshopjti.com/data/data_peternak/";
    public static final String PUSKESWAN_IMAGE_BASE_URL = "http://dokternak.workshopjti.com/data/data_puskeswan/";

    //URI Hosting WSJTI
//    public static final String BASE_URL = "https://wsjti.id/dokternaK/api/";
//    public static final String ARTIKEL_IMAGE_BASE_URL = "https://wsjti.id/dokternak/data/data_artikel/";
//    public static final String PETUGAS_IMAGE_BASE_URL = "https://wsjti.id/dokternak/data/data_dokter/";
//    public static final String PETERNAK_IMAGE_BASE_URL = "https://wsjti.id/dokternak/data/data_peternak/";
//    public static final String PUSKESWAN_IMAGE_BASE_URL = "https://wsjti.id/dokternak/data/data_puskeswan/";

//    public static final String BASE_URL = "http://192.168.0.102:8080/api/";
//    public static final String ARTIKEL_IMAGE_BASE_URL = "http://192.168.0.102:8080/data/data_artikel/";
//    public static final String PETUGAS_IMAGE_BASE_URL = "http://192.168.0.102:8080/data/data_dokter/";
//    public static final String PETERNAK_IMAGE_BASE_URL = "http://192.168.0.102:8080/data/data_peternak/";
//    public static final String PUSKESWAN_IMAGE_BASE_URL = "http://192.168.0.102:8080/data/data_puskeswan/";

    private static Retrofit retrofit = null;

    static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
