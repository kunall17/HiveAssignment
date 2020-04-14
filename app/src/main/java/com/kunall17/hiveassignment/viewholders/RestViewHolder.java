package com.kunall17.hiveassignment.viewholders;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.hiveassignment.R;
import com.kunall17.hiveassignment.pojo.Restaurant;
import com.kunall17.hiveassignment.viewmodels.DataViewModel;

public class RestViewHolder extends RecyclerView.ViewHolder {

    private final DataViewModel dataViewModel;
    private AppCompatTextView name;
    private AppCompatTextView cuizines;
    private AppCompatTextView averageCostForTwo;
    private AppCompatTextView rating;
    private AppCompatTextView menuUrl;

    public RestViewHolder(@NonNull View rootView, DataViewModel dataViewModel) {
        super(rootView);
        name = (AppCompatTextView) rootView.findViewById(R.id.name);
        cuizines = (AppCompatTextView) rootView.findViewById(R.id.cuizines);
        averageCostForTwo = (AppCompatTextView) rootView.findViewById(R.id.averageCostForTwo);
        rating = (AppCompatTextView) rootView.findViewById(R.id.rating);
        menuUrl = (AppCompatTextView) rootView.findViewById(R.id.menuUrl);
        this.dataViewModel = dataViewModel;

        SpannableString spannedString = new SpannableString("View Menu");
        spannedString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                dataViewModel.viewMenuClick(getAdapterPosition(), rootView.getContext());
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        }, 0, spannedString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        menuUrl.setText(spannedString);
        menuUrl.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void set(Restaurant restaurant) {
        name.setText(restaurant.getRestaurant().getName());
        cuizines.setText(restaurant.getRestaurant().getCuisines());
        averageCostForTwo.setText(String.format("Cost for two: %s | %s", restaurant.getRestaurant().getAverageCostForTwo(), restaurant.getRestaurant().getTimings()));
        rating.setText(String.format("%s", restaurant.getRestaurant().getUserRating().getAggregateRating()));
    }
}
