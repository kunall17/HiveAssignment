package com.kunall17.hiveassignment.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.hiveassignment.R;
import com.kunall17.hiveassignment.adapter.RestuarantAdapter;
import com.kunall17.hiveassignment.viewmodels.DataViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView feedRv;
    private AppCompatEditText searchEt;
    private ProgressDialog progressDialog;
    private DataViewModel dataViewModel ;

    public void showLoader(boolean b) {
        if (isFinishing()) return;
        if (b) {
            if (progressDialog != null)
                return;
            this.progressDialog = ProgressDialog.show(this, "", "", true, false);
            if (this.progressDialog.getWindow() == null) return;
            this.progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.progressDialog.setContentView(R.layout.progressdialog);
        } else if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedRv = findViewById(R.id.recycler_view);
        searchEt = findViewById(R.id.searchEt);
        dataViewModel = new DataViewModel();

        RestuarantAdapter adapter = new RestuarantAdapter(dataViewModel);
        adapter.setHasStableIds(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        feedRv.setHasFixedSize(true);
        feedRv.setLayoutManager(layoutManager);
        feedRv.setAdapter(adapter);
        dataViewModel.getMutableLiveData().observe(this, adapter::setData);
        dataViewModel.getIsLoading().observe(this, this::showLoader);


        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dataViewModel.searchForQuery(editable.toString());
            }
        });

    }
}
