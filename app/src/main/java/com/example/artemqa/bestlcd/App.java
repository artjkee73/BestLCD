package com.example.artemqa.bestlcd;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.artemqa.bestlcd.model.Monitor;
import com.example.artemqa.bestlcd.ui.activity.MainActivity;
import com.example.artemqa.bestlcd.util.Helper;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

/**
 * Created by artemqa on 14.01.2018.
 */

public class App extends Application {
    private static SyncConfiguration syncConfiguration;
    private static final String TAG = "LOG";
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        loginToRealm(Helper.REALM_LOGIN , Helper.REALM_PASS);
    }

    private void loginToRealm(final String userName, String password) {
        SyncUser.Callback<SyncUser> callback = new SyncUser.Callback<SyncUser>() {
            @Override
            public void onSuccess(SyncUser user) {
                Log.d(TAG,"SyncUser OK");
                syncConfiguration = getSyncConfiguration(user, Helper.REALM_URL);
                loadAllData();
            }

            @Override
            public void onError(ObjectServerError error) {
                Log.d(TAG,"SyncUser NOT OK");
            }
        };
        SyncCredentials syncCredentials = SyncCredentials.usernamePassword(userName, password, false);
        SyncUser.loginAsync(syncCredentials,Helper.REALM_AUTH, callback);
    }
    private SyncConfiguration getSyncConfiguration(SyncUser syncUser, String realmURL) {
        SyncConfiguration syncConfiguration = new SyncConfiguration.Builder(syncUser, realmURL)
                .waitForInitialRemoteData()
                .build();
        return syncConfiguration;
    }

    private void loadAllData() {
        Realm.getInstanceAsync(syncConfiguration, new Realm.Callback() {
            @Override
            public void onSuccess(Realm realm) {
                Log.d(TAG,"Данные загружены");
                Realm localRealm = Realm.getDefaultInstance();
                localRealm.beginTransaction();
                localRealm.copyToRealmOrUpdate(realm.where(Monitor.class).findAll());
                localRealm.commitTransaction();
                realm.close();
                localRealm.close();
                Helper.updateDefaultConfiguration(syncConfiguration);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG,"Данные не загружены");
            }
        });
    }
}
