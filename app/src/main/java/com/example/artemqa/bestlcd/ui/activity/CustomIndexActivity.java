package com.example.artemqa.bestlcd.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.artemqa.bestlcd.R;
import com.example.artemqa.bestlcd.model.Monitor;
import com.example.artemqa.bestlcd.util.Algorithm;
import com.example.artemqa.bestlcd.util.Helper;

import io.realm.Realm;
import io.realm.RealmResults;

public class CustomIndexActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etCost, etResolution, etDiagonal, etMaxFrr, etMarketScore, etMatrixType;
    Button btnApply;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_index);
        etCost = findViewById(R.id.et_cost_custom_index_a);
        etResolution = findViewById(R.id.et_resolution_custom_index_a);
        etDiagonal = findViewById(R.id.et_diagonal_custom_index_a);
        etMaxFrr = findViewById(R.id.et_max_frr_custom_index_a);
        etMarketScore = findViewById(R.id.et_market_score_custom_index_a);
        etMatrixType = findViewById(R.id.et_type_matrix_custom_index_a);
        realm = Realm.getDefaultInstance();
        setIndexFromSharedPref();

        btnApply = findViewById(R.id.btn_apply_custom_index_a);
        btnApply.setOnClickListener(this);

    }

    private void setIndexFromSharedPref() {
        SharedPreferences sp = getSharedPreferences(Helper.NAME_PREFERENCES, MODE_PRIVATE);
        etCost.setText(String.valueOf(sp.getInt(Helper.PREF_COST_INDEX, 0)));
        etResolution.setText(String.valueOf(sp.getInt(Helper.PREF_RESOLUTION_INDEX, 0)));
        etDiagonal.setText(String.valueOf(sp.getInt(Helper.PREF_DIAGONAL_INDEX, 0)));
        etMaxFrr.setText(String.valueOf(sp.getInt(Helper.PREF_MAX_FRR_INDEX, 0)));
        etMarketScore.setText(String.valueOf(sp.getInt(Helper.PREF_SCORE_MARKET_INDEX, 0)));
        etMatrixType.setText(String.valueOf(sp.getInt(Helper.PREF_TYPE_MATRIX_INDEX, 0)));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_apply_custom_index_a) {
            if (validateForm()) {
                SharedPreferences sp = getSharedPreferences(Helper.NAME_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(Helper.PREF_COST_INDEX, Integer.parseInt(etCost.getText().toString()));
                editor.putInt(Helper.PREF_RESOLUTION_INDEX, Integer.parseInt(etResolution.getText().toString()));
                editor.putInt(Helper.PREF_DIAGONAL_INDEX, Integer.parseInt(etDiagonal.getText().toString()));
                editor.putInt(Helper.PREF_MAX_FRR_INDEX, Integer.parseInt(etMaxFrr.getText().toString()));
                editor.putInt(Helper.PREF_SCORE_MARKET_INDEX, Integer.parseInt(etMarketScore.getText().toString()));
                editor.putInt(Helper.PREF_TYPE_MATRIX_INDEX, Integer.parseInt(etMatrixType.getText().toString()));
                editor.apply();

                updateAlgorithmScore();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Данные введены некорректно, повторите ввод",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateAlgorithmScore() {
        SharedPreferences sp = this.getSharedPreferences(Helper.NAME_PREFERENCES, MODE_PRIVATE);
        RealmResults<Monitor> results = realm.where(Monitor.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            Algorithm algorithm = new Algorithm(results.get(i), sp);
            algorithm.start();
        }
    }

    private boolean validateForm() {
        boolean foo = false;
        if ( (Integer.parseInt(etCost.getText().toString()) > 0) && (Integer.parseInt(etResolution.getText().toString()) > 0) && (Integer.parseInt(etDiagonal.getText().toString()) > 0)
                && (Integer.parseInt(etMaxFrr.getText().toString()) > 0) && (Integer.parseInt(etMarketScore.getText().toString()) > 0) && (Integer.parseInt(etMatrixType.getText().toString()) > 0)) {
            if((Integer.parseInt(etCost.getText().toString()) + Integer.parseInt(etResolution.getText().toString()) + Integer.parseInt(etDiagonal.getText().toString())
                    + Integer.parseInt(etMaxFrr.getText().toString()) + Integer.parseInt(etMarketScore.getText().toString()) + Integer.parseInt(etMatrixType.getText().toString())) == 100){
                foo = true;
            } else foo = false;
        }
        return foo;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
