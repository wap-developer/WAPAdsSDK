package com.wapcreative.wapsdk.interfaces.reward.load;
public interface OnLoadRewardsAdmob {
    void onAdFailedToLoad();
    void onAdLoaded(String error);
}
