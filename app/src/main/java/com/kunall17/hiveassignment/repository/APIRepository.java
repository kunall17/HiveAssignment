package com.kunall17.hiveassignment.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kunall17.hiveassignment.network.RetrofitService;
import com.kunall17.hiveassignment.network.apis;
import com.kunall17.hiveassignment.pojo.Example;
import com.kunall17.hiveassignment.pojo.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRepository {
    private final MutableLiveData<Boolean> isLoading;
    private apis zomatoApi;
    private MutableLiveData<List<Restaurant>> restuarantList = new MutableLiveData<>();

    public APIRepository(MutableLiveData<Boolean> isLoading) {
        zomatoApi = RetrofitService.createService(apis.class);
        this.isLoading = isLoading;
    }

    public MutableLiveData<List<Restaurant>> getRestuarantList() {
        return restuarantList;
    }

    public void searchForQuery(String source) {
        isLoading.setValue(true);
        zomatoApi.searchApi(source).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    Example body = response.body();
                    restuarantList.setValue(body.getRestaurants());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();
                restuarantList.setValue(null);
                isLoading.setValue(false);
            }
        });
    }
}
