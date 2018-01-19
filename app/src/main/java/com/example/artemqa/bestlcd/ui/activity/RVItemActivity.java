package com.example.artemqa.bestlcd.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.artemqa.bestlcd.R;
import com.example.artemqa.bestlcd.model.Monitor;
import com.example.artemqa.bestlcd.util.Helper;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class RVItemActivity extends AppCompatActivity {
    Monitor currentMonitor;
    Realm realm;
    FloatingActionButton fab;
    Toolbar toolbar;
    ImageView imageView;
    TextView tvCost,tvResolution,tvMarketScore,tvTypeMatrix,tvMaxFrr,tvToolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvitem);
        realm = Realm.getDefaultInstance();
        currentMonitor = getMonitorFromRealm();
        fab = findViewById(R.id.fab_rv_item_a);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentMonitor.getUrl()));
                startActivity(browserIntent);
            }
        });
        toolbar = findViewById(R.id.main_toolbar_rv_item);
        toolbar.setTitle(currentMonitor.getName());
        imageView = findViewById(R.id.image_view_monitor);
        setImageView();
        tvCost = findViewById(R.id.tv_cost_rv_item_a);
        tvCost.setText(String.valueOf(currentMonitor.getCost()).concat( " руб."));
        tvResolution = findViewById(R.id.tv_resolution_rv_item_a);
        tvResolution.setText(currentMonitor.getResolution().concat(" px"));
        tvMarketScore = findViewById(R.id.tv_market_score_rv_item_a);
        tvMarketScore.setText(String.valueOf(currentMonitor.getScoreMarket()));
        tvTypeMatrix = findViewById(R.id.tv_type_matrix_rv_item_a);
        tvTypeMatrix.setText("TFT ".concat(currentMonitor.getTypeMatrix()));
        tvMaxFrr = findViewById(R.id.tv_max_frr_rv_item_a);
        tvMaxFrr.setText(String.valueOf(currentMonitor.getMaxFRR()).concat(" Гц"));
    }

    private void setImageView() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;
        Picasso.with(this).load(currentMonitor.getUrlPicture()).resize(screenWidth, (int) Helper.convertDpToPixel(250,this)).into(imageView);
    }


    private Monitor getMonitorFromRealm() {
        String currentMonitorName = getStringFromIntent();
        return realm.where(Monitor.class).equalTo("name",currentMonitorName).findFirst();
    }

    private String getStringFromIntent() {
       return getIntent().getExtras().getString(Helper.INTENT_RV_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
