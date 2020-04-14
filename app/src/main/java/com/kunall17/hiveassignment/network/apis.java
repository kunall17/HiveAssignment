package com.kunall17.hiveassignment.network;

import com.kunall17.hiveassignment.pojo.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface apis {

    @GET("api/v2.1/search")
    Call<Example> searchApi(@Query("q") String newsSource);

}
