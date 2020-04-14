package com.kunall17.hiveassignment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.hiveassignment.R;
import com.kunall17.hiveassignment.pojo.Restaurant;
import com.kunall17.hiveassignment.viewholders.RestViewHolder;
import com.kunall17.hiveassignment.viewmodels.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestuarantAdapter extends RecyclerView.Adapter<RestViewHolder> {

    private List<Restaurant> restaurantList = new ArrayList<>();
    private DataViewModel dataViewModel;

    public RestuarantAdapter(DataViewModel dataViewModel) {
        this.dataViewModel = dataViewModel;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void setData(List<Restaurant> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new Diff(this.restaurantList, newList));
        restaurantList = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public RestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false), dataViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull RestViewHolder holder, int position) {
        holder.set(restaurantList.get(position));
    }
}
