package com.wapcreative.wapsdk;

import android.app.Activity;
import android.util.Log;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.BuildConfig;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.wapcreative.rextsdk.config.AudienceNetworkInitializeHelper;

import java.util.Map;

public class RexTInitialize {
    public static RequestConfiguration requestConfiguration;
    public static void SelectAdsAdmobTargeting(Activity activity, String type) {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }
                switch (type) {
                    case "1":
                        requestConfiguration = MobileAds.getRequestConfiguration()
                                .toBuilder()
                                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                                .build();
                        break;
                    case "2":
                        requestConfiguration = MobileAds.getRequestConfiguration()
                                .toBuilder()
                                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE)
                                .build();
                        break;
                    case "3":
                        requestConfiguration = MobileAds.getRequestConfiguration()
                                .toBuilder()
                                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED)
                                .build();
                        break;
                    case "4":
                        requestConfiguration = MobileAds.getRequestConfiguration()
                                .toBuilder()
                                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
                                .build();
                        break;
                    case "5":
                        requestConfiguration = MobileAds.getRequestConfiguration()
                                .toBuilder()
                                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_PG)
                                .build();
                        break;
                    case "6":
                        requestConfiguration = MobileAds.getRequestConfiguration()
                                .toBuilder()
                                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_T)
                                .build();
                        break;
                    case "7":
                        requestConfiguration = MobileAds.getRequestConfiguration()
                                .toBuilder()
                                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_MA)
                                .build();
                        break;
                }
                MobileAds.setRequestConfiguration(requestConfiguration);
            }
        });
    }

    public static void SelectAdsAdmob(Activity activity) {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }
            }
        });
    }

    public static void SelectAdsFAN(Activity activity) {
        if (!AudienceNetworkAds.isInitialized(activity)) {
            if (BuildConfig.DEBUG) {
                AdSettings.turnOnSDKDebugger(activity);
                AdSettings.setTestMode(true);
            }
            AudienceNetworkAds
                    .buildInitSettings(activity)
                    .withInitListener(new AudienceNetworkInitializeHelper())
                    .initialize();
        }
    }
}
