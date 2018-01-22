package com.example.artemqa.bestlcd.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.artemqa.bestlcd.R;
import com.example.artemqa.bestlcd.model.Monitor;
import com.example.artemqa.bestlcd.util.Helper;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RVItemActivity extends AppCompatActivity {
    public static final int FIRST_NUMBER = 0;
    public static final int SECOND_NUMBER = 1;
    Monitor currentMonitor;
    Realm realm;
    FloatingActionButton fab;
    Toolbar toolbar;
    ImageView imageView;
    LinearLayout linearLayout;
    TextView tvCost, tvResolution, tvMarketScore, tvTypeMatrix, tvMaxFrr, tvDiagonal, tvLinguisticConclusion;

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
        tvCost.setText(String.valueOf(currentMonitor.getCost()).concat(" руб."));
        tvResolution = findViewById(R.id.tv_resolution_rv_item_a);
        tvResolution.setText(currentMonitor.getResolution().concat(" px"));
        tvMarketScore = findViewById(R.id.tv_market_score_rv_item_a);
        tvMarketScore.setText(String.valueOf(currentMonitor.getScoreMarket()));
        tvTypeMatrix = findViewById(R.id.tv_type_matrix_rv_item_a);
        tvTypeMatrix.setText("TFT ".concat(currentMonitor.getTypeMatrix()));
        tvMaxFrr = findViewById(R.id.tv_max_frr_rv_item_a);
        tvMaxFrr.setText(String.valueOf(currentMonitor.getMaxFRR()).concat(" Гц"));
        tvDiagonal = findViewById(R.id.tv_diagonal_rv_item_a);
        tvDiagonal.setText(String.valueOf(currentMonitor.getDiagonal()).concat("\""));
        linearLayout = findViewById(R.id.linear_layout_linguistic_conclusion);
        tvLinguisticConclusion = findViewById(R.id.tv_linguistic_conclusion_rv_item_a);
        setLinguisticConclusionIfFirst();
    }

    private void setLinguisticConclusionIfFirst() {
        RealmResults<Monitor> results = realm.where(Monitor.class).sort("scoreAlgorithm", Sort.DESCENDING).findAll();
        Monitor firstMonitor = results.get(FIRST_NUMBER);
        if (currentMonitor.equals(firstMonitor)) {
            linearLayout.setVisibility(LinearLayout.VISIBLE);
            SharedPreferences sharedPreferences = getSharedPreferences(Helper.NAME_PREFERENCES, MODE_PRIVATE);
            Monitor secondMonitor = results.get(SECOND_NUMBER);
            String FIRST_MONITOR_NAME = currentMonitor.getName();
            int SP_COST = sharedPreferences.getInt(Helper.PREF_COST_INDEX,0);
            int SP_DIAGONAL = sharedPreferences.getInt(Helper.PREF_DIAGONAL_INDEX, 0);
            int SP_MAX_FRR = sharedPreferences.getInt(Helper.PREF_MAX_FRR_INDEX, 0);
            int SP_TYPE_MATRIX = sharedPreferences.getInt(Helper.PREF_TYPE_MATRIX_INDEX, 0);
            int SP_RESOLUTION = sharedPreferences.getInt(Helper.PREF_RESOLUTION_INDEX, 0);
            int SP_MARKET_SCORE = sharedPreferences.getInt(Helper.PREF_SCORE_MARKET_INDEX, 0);
            double COST_DIFFERENCE_FIRST_THEN_SECOND = ((firstMonitor.getCost() - secondMonitor.getCost())/(firstMonitor.getCost()/100));
            String MORE_OR_LESS_FIRST_THEN_SECOND_COST;
            if (COST_DIFFERENCE_FIRST_THEN_SECOND > 0){
                MORE_OR_LESS_FIRST_THEN_SECOND_COST = "больше";
            }else{
                MORE_OR_LESS_FIRST_THEN_SECOND_COST = "меньше";
            }
            String SECOND_MONITOR_NAME = secondMonitor.getName();

            double SCORE_ALG_DIFFERENCE_FIRST_THEN_SECOND = ((firstMonitor.getScoreAlgorithm() - secondMonitor.getScoreAlgorithm())/(firstMonitor.getScoreAlgorithm()/100));
            String MORE_OR_LESS_FIRST_THEN_SECOND_ALG_SCORE;
            if (SCORE_ALG_DIFFERENCE_FIRST_THEN_SECOND > 0){
                MORE_OR_LESS_FIRST_THEN_SECOND_ALG_SCORE = "больше";
            }else{
                MORE_OR_LESS_FIRST_THEN_SECOND_ALG_SCORE = "меньше";
            }
            String linguisticConclusion = getResources().getString(R.string.tv_linguistic_conclusion_rv_item_a, FIRST_MONITOR_NAME, SP_COST, SP_DIAGONAL,
                    SP_MAX_FRR, SP_TYPE_MATRIX, SP_RESOLUTION, SP_MARKET_SCORE,COST_DIFFERENCE_FIRST_THEN_SECOND,MORE_OR_LESS_FIRST_THEN_SECOND_COST,
                    SECOND_MONITOR_NAME,SCORE_ALG_DIFFERENCE_FIRST_THEN_SECOND,MORE_OR_LESS_FIRST_THEN_SECOND_ALG_SCORE);
            tvLinguisticConclusion.setText(linguisticConclusion);
        }


    }

    private void setImageView() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;
        Picasso.with(this).load(currentMonitor.getUrlPicture()).resize(screenWidth, (int) Helper.convertDpToPixel(250, this)).into(imageView);
    }


    private Monitor getMonitorFromRealm() {
        String currentMonitorName = getStringFromIntent();
        return realm.where(Monitor.class).equalTo("name", currentMonitorName).findFirst();
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
