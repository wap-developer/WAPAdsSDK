package com.wapcreative.wapsdk;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication extends Application {
    private static RexTOpenAds rexTOpenAds;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });

        rexTOpenAds = new RexTOpenAds(this);
    }
}
