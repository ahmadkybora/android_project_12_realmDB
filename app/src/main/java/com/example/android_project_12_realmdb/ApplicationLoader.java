package com.example.android_project_12_realmdb;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ApplicationLoader extends Application {
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration defaultConfig = new RealmConfiguration
                .Builder()
                .name("myRealm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(defaultConfig);

        Realm realm = Realm.getInstance(defaultConfig);
    }
}
