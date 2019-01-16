package com.example.oferr.recycle1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
//    private static final String BASE_URL = "http://192.168.71.2:8082/";
    private static final String BASE_URL = "https://ppcdb.herokuapp.com/";

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
