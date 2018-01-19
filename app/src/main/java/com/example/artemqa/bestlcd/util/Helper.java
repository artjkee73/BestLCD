package com.example.artemqa.bestlcd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.artemqa.bestlcd.model.Monitor;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.SyncConfiguration;

/**
 * Created by artemqa on 14.01.2018.
 */

public class Helper {
public static final String NAME_PREFERENCES = "PREF_MONITOR";
    public static final String PREF_COST_INDEX = "COST_INDEX";
    public static final String PREF_RESOLUTION_INDEX = "RESOLUTION_INDEX";
    public static final String PREF_DIAGONAL_INDEX ="DIAGONAL_INDEX";
    public static final String PREF_MAX_FRR_INDEX = "MAX_FRR_INDEX";
    public static final String PREF_TYPE_MATRIX_INDEX = "TYPE_MATRIX_INDEX";
    public static final String PREF_SCORE_MARKET_INDEX = "SCORE_MARKET_INDEX";
    public static final String INTENT_RV_URL = "INTENT_RECYCLE_VIEW_URL";
    public static final String REALM_URL = "http://94.250.253.154:9080/~/bestlcd";
    public static final String REALM_AUTH = "http://94.250.253.154:9080/auth";
    public static final String REALM_LOGIN = "bestlcduser";
    public static final String REALM_PASS = "12345678";

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static void updateDefaultConfiguration(SyncConfiguration syncConfiguration) {
        Realm.setDefaultConfiguration(syncConfiguration);
    }
}
