package com.example.artemqa.bestlcd.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.artemqa.bestlcd.R;
import com.example.artemqa.bestlcd.model.Monitor;
import com.example.artemqa.bestlcd.ui.adapter.MyRecyclerViewAdapter;
import com.example.artemqa.bestlcd.util.Helper;
import io.realm.Realm;
import io.realm.Sort;


public class MainActivity extends AppCompatActivity {
    Realm realm;
    MyRecyclerViewAdapter adapter;
    TextView tvLabelRv;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        tvLabelRv = findViewById(R.id.tv_label_rv_main_a);
        recyclerView = findViewById(R.id.rv_main_a);
        setUpRecyclerView();


    }

    private void setUpRecyclerView() {
        adapter = new MyRecyclerViewAdapter(realm.where(Monitor.class).sort("scoreAlgorithm", Sort.DESCENDING).findAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.game_item_menu:
                setGamePref();
                return true;
            case R.id.low_price_item_menu:
                setLowCostPref();
                return true;
            case R.id.top_item_menu:
                setTopPref();
                return true;
            case R.id.settings_item_menu:
                setCustom();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setGamePref() {
        SharedPreferences sharedPreferences = getSharedPreferences(Helper.NAME_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Helper.PREF_COST_INDEX, 5);
        editor.putInt(Helper.PREF_RESOLUTION_INDEX, 25);
        editor.putInt(Helper.PREF_DIAGONAL_INDEX, 10);
        editor.putInt(Helper.PREF_MAX_FRR_INDEX, 25);
        editor.putInt(Helper.PREF_SCORE_MARKET_INDEX, 10);
        editor.putInt(Helper.PREF_TYPE_MATRIX_INDEX, 25);
        editor.apply();
    }

    private void setLowCostPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(Helper.NAME_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Helper.PREF_COST_INDEX, 70);
        editor.putInt(Helper.PREF_RESOLUTION_INDEX, 5);
        editor.putInt(Helper.PREF_DIAGONAL_INDEX, 5);
        editor.putInt(Helper.PREF_MAX_FRR_INDEX, 5);
        editor.putInt(Helper.PREF_SCORE_MARKET_INDEX, 10);
        editor.putInt(Helper.PREF_TYPE_MATRIX_INDEX, 5);
        editor.apply();
    }

    private void setTopPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(Helper.NAME_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Helper.PREF_COST_INDEX, 5);
        editor.putInt(Helper.PREF_RESOLUTION_INDEX, 30);
        editor.putInt(Helper.PREF_DIAGONAL_INDEX, 15);
        editor.putInt(Helper.PREF_MAX_FRR_INDEX, 5);
        editor.putInt(Helper.PREF_SCORE_MARKET_INDEX, 10);
        editor.putInt(Helper.PREF_TYPE_MATRIX_INDEX, 35);
        editor.apply();
    }

    private void setCustom() {
        Intent intent = new Intent(this, CustomIndexActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
