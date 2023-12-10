package com.wapcreative.wapsdk;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.RewardedVideoAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.wapcreative.rextsdk.interfaces.reward.load.OnLoadRewardsAdmob;
import com.wapcreative.rextsdk.interfaces.reward.show.OnShowRewardsAdmob;

public class RexTReward {

    public static boolean unlockreward = false;
    public static RewardedVideoAd rewardedVideoAdFan;
    public static RewardedAd mRewardedAd;

    public static OnLoadRewardsAdmob onLoadRewardsAdmob;
    public static OnShowRewardsAdmob onShowRewardsAdmob;
    public static void LoadRewardFan(Activity activity, String idReward) {
        try {

            rewardedVideoAdFan = new RewardedVideoAd(activity, idReward);
            com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener = new com.facebook.ads.RewardedVideoAdListener() {

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Rewarded video ad is loaded and ready to be displayed
                    Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Rewarded video ad clicked
                    Log.d(TAG, "Rewarded video ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Rewarded Video ad impression - the event will fire when the
                    // video starts playing
                    Log.d(TAG, "Rewarded video ad impression logged!");
                }

                @Override
                public void onRewardedVideoCompleted() {
                    unlockreward = true;
                }

                @Override
                public void onRewardedVideoClosed() {
                    // The Rewarded Video ad was closed - this can occur during the video
                    // by closing the app, or closing the end card.
                    Log.d(TAG, "Rewarded video ad closed!");
                }
            };
            rewardedVideoAdFan.loadAd(
                    rewardedVideoAdFan.buildLoadAdConfig()
                            .withAdListener(rewardedVideoAdListener)
                            .build());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardAdmob(Activity activity, String idReward) {
        try {

            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            if (onLoadRewardsAdmob != null) {
                                onLoadRewardsAdmob.onAdFailedToLoad();
                            }
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            if (onLoadRewardsAdmob != null) {
                                onLoadRewardsAdmob.onAdLoaded("");
                            }
                            mRewardedAd = rewardedAd;

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ShowRewardAdmob(Activity activity, String idReward) {
        try {
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        if (onShowRewardsAdmob != null) {
                            onShowRewardsAdmob.onUserEarnedReward();
                        }
                        unlockreward = true;
                        LoadRewardAdmob(activity, idReward);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAdmob(activity, idReward);
    }

    public static void ShowRewardFan(Activity activity, String idReward) {
        try {
            if (rewardedVideoAdFan == null || !rewardedVideoAdFan.isAdLoaded()) {

            } else {
                rewardedVideoAdFan.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardFan(activity, idReward);
    }

}
