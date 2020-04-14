package com.kunall17.hiveassignment.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kunall17.hiveassignment.pojo.Restaurant;
import com.kunall17.hiveassignment.repository.APIRepository;

import java.util.List;

public class DataViewModel extends ViewModel {

    private APIRepository APIRepository;
    private Handler handler;
    private Runnable workRunnable;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public DataViewModel() {
        isLoading.setValue(false);
        APIRepository = new APIRepository(isLoading);
        handler = new Handler(Looper.getMainLooper() /*UI thread*/);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void searchForQuery(String query) {
        if (query.length() < 2) {
            return;
        }

        handler.removeCallbacks(workRunnable);
        workRunnable = () -> APIRepository.searchForQuery(query);
        handler.postDelayed(workRunnable, 350 /*delay*/);
    }

    public MutableLiveData<List<Restaurant>> getMutableLiveData() {
        return APIRepository.getRestuarantList();
    }

    public void viewMenuClick(int adapterPosition, Context context) {
        List<Restaurant> value = APIRepository.getRestuarantList().getValue();
        if (value != null) {
            Restaurant restaurant = value.get(adapterPosition);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.getRestaurant().getMenuUrl()));
            context.startActivity(browserIntent);
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
