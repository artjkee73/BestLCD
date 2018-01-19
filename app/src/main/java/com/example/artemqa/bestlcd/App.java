package com.example.artemqa.bestlcd;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.artemqa.bestlcd.util.Helper;

import io.realm.Realm;

/**
 * Created by artemqa on 14.01.2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
