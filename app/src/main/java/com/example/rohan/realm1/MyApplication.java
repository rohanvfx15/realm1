package com.example.rohan.realm1;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("myrealmdb.realm") // by defaul name db default.realm
                .build();

        Realm.setDefaultConfiguration(configuration);

    }
}
