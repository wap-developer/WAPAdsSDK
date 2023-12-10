package com.wapcreative.wapsdk;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.wapcreative.wapsdk.interfaces.interstitial.admob.OnFullScreenContentCallbackAdmob;
import com.wapcreative.wapsdk.interfaces.interstitial.load.OnLoadInterstitialAdmob;
import com.wapcreative.wapsdk.interfaces.interstitial.load.OnLoadInterstitialFacebook;
import com.wapcreative.wapsdk.interfaces.interstitial.show.OnShowInterstitialAdmob;
import com.wapcreative.wapsdk.interfaces.interstitial.show.OnShowInterstitialFacebook;

public class RexTInterstitial {

    public static InterstitialAd mInterstitialAd;
    public static com.facebook.ads.InterstitialAd FBinterstitialAd;

    public static int counter = 0;

    public static OnLoadInterstitialAdmob onLoadInterstitialAdmob;
    public static OnLoadInterstitialFacebook onLoadInterstitialFacebook;
    public static OnFullScreenContentCallbackAdmob onFullScreenContentCallbackAdmob;
    public static OnShowInterstitialAdmob onShowInterstitialAdmob;
    public static OnShowInterstitialFacebook onShowInterstitialFacebook;

    public static void LoadIntertitialAdmob(Activity activity, String idIntertitial, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();

        InterstitialAd.load(activity, idIntertitial, request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        if (onLoadInterstitialAdmob != null) {
                            onLoadInterstitialAdmob.onInterstitialAdLoaded();
                        }
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        if (onLoadInterstitialAdmob != null) {
                            onLoadInterstitialAdmob.onInterstitialAdFailedToLoad("");
                        }
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;

                    }
                });
    }

    public static void LoadIntertitialFAN(Activity activity, String idIntertitial) {
        FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitial);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onInterstitialDisplayed();
                }
            }

            @Override
            public void onInterstitialDismissed(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onInterstitialDismissed();
                }
            }

            @Override
            public void onError(com.facebook.ads.Ad ad, AdError adError) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onError();
                }
            }

            @Override
            public void onAdLoaded(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onAdLoaded();
                }
            }

            @Override
            public void onAdClicked(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onAdClicked();
                }
            }

            @Override
            public void onLoggingImpression(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onLoggingImpression();
                }
            }
        };
        FBinterstitialAd.loadAd(
                FBinterstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
    }

    public static void ShowIntertitialAdmob(Activity activity, String idIntertitial,
                                            int interval, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {
            if (mInterstitialAd != null) {
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdClicked();
                        }

                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdDismissedFullScreenContent();
                        }
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdFailedToShowFullScreenContent();
                        }
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdImpression();
                        }
                        Log.d(ContentValues.TAG, "Ad recorded an impression.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(ContentValues.TAG, "Ad showed fullscreen content.");
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdShowedFullScreenContent();
                        }
                    }
                });
                mInterstitialAd.show(activity);
                if (onShowInterstitialAdmob != null) {
                    onShowInterstitialAdmob.onAdSuccess();
                }
                // LoadIntertitialAdmob(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            } else {
                if (onShowInterstitialAdmob != null) {
                    onShowInterstitialAdmob.onAdFailedShow();
                }
            }
            LoadIntertitialAdmob(activity, idIntertitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialFAN(Activity activity, String idIntertitial,
                                          int interval) {
        if (counter >= interval) {
            if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                LoadIntertitialFAN(activity, idIntertitial);
                if (onShowInterstitialFacebook != null) {
                    onShowInterstitialFacebook.onAdFailedShow();
                }
            } else {
                FBinterstitialAd.show();
                LoadIntertitialFAN(activity, idIntertitial);
                if (onShowInterstitialFacebook != null) {
                    onShowInterstitialFacebook.onAdSuccess();
                }
            }
            counter = 0;
        } else {
            counter++;
        }
    }

}
