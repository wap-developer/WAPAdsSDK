package com.wapcreative.wapsdk;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.wapcreative.wapsdk.interfaces.natives.OnLoadMediumNativesAdmob;
import com.wapcreative.wapsdk.interfaces.natives.OnLoadMediumNativesFacebook;
import com.wapcreative.wapsdk.interfaces.natives.OnLoadSmallNativesAdmob;
import com.wapcreative.wapsdk.interfaces.natives.OnLoadSmallNativesFacebook;

import java.util.ArrayList;
import java.util.List;

public class RexTNative {

    public static NativeAd nativeAd;
    public static com.facebook.ads.NativeAd nativeAdfan;
    public static NativeBannerAd nativeBannerAd;
    public static NativeAdLayout nativeAdLayout;

    public static LinearLayout adView;

    public static OnLoadSmallNativesAdmob onLoadSmallNativesAdmob;
    public static OnLoadSmallNativesFacebook onLoadSmallNativesFacebook;
    public static OnLoadMediumNativesAdmob onLoadMediumNativesAdmob;
    public static OnLoadMediumNativesFacebook onLoadMediumNativesFacebook;

    public static void SmallNativeAdmob(Activity activity, RelativeLayout layNative, String nativeId, String Hpk1,
                                        String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadSmallNativesAdmob != null) {
                    onLoadSmallNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_small_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();
        builder.withNativeAdOptions(adOptions);
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadSmallNativesAdmob != null) {
                                            onLoadSmallNativesAdmob.onAdFailedToLoad("");
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);

    }

    public static void SmallNativeFan(Activity activity, RelativeLayout layNative, String nativeId) {
        nativeBannerAd = new NativeBannerAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadSmallNativesFacebook != null) {
                    onLoadSmallNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadSmallNativesFacebook != null) {
                    onLoadSmallNativesFacebook.onError("");
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadSmallNativesFacebook != null) {
                    onLoadSmallNativesFacebook.onAdLoaded();
                }
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                inflateAd(nativeBannerAd, activity, layNative);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        nativeBannerAd.loadAd(
                nativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }

    public static void MediumNativeAdmob(Activity activity, RelativeLayout layNative, String nativeId, String Hpk1,
                                         String Hpk2, String Hpk3, String Hpk4, String Hpk5) {

        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadMediumNativesAdmob != null) {
                    onLoadMediumNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_big_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }


        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadMediumNativesAdmob != null) {
                                            onLoadMediumNativesAdmob.onAdFailedToLoad("");
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);
    }

    public static void MediumNativeFan(Activity activity, RelativeLayout layNative, String nativeId) {
        nativeAdfan = new com.facebook.ads.NativeAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onError("");
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onAdLoaded();
                }
                if (nativeAdfan == null || nativeAdfan != ad) {
                    return;
                }
                inflateAd2(nativeAdfan, activity, layNative);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        nativeAdfan.loadAd(
                nativeAdfan.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }

    public static void SmallNativeAdmobRectangle(Activity activity, RelativeLayout layNative, String nativeId, String Hpk1,
                                                 String Hpk2, String Hpk3, String Hpk4, String Hpk5) {

        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadMediumNativesAdmob != null) {
                    onLoadMediumNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_small_rectangle_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }


        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadMediumNativesAdmob != null) {
                                            onLoadMediumNativesAdmob.onAdFailedToLoad("");
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);
    }

    public static void SmallNativeFanRectangle(Activity activity, RelativeLayout layNative, String nativeId) {
        nativeBannerAd = new NativeBannerAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onError("");
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onAdLoaded();
                }
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                inflateAd3(nativeBannerAd, activity, layNative);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        nativeBannerAd.loadAd(
                nativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }

    private static void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.GONE);
        }
        adView.setNativeAd(nativeAd);
    }

    public static void inflateAd(NativeBannerAd nativeBannerAd, Activity activity, RelativeLayout layNative) {
        try {
            nativeBannerAd.unregisterView();
            nativeAdLayout = new NativeAdLayout(activity, null, 1);
            LayoutInflater inflater = LayoutInflater.from(activity);
            adView = (LinearLayout) inflater.inflate(R.layout.fan_small_native, nativeAdLayout, false);
            layNative.addView(adView);

            RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            com.facebook.ads.MediaView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
            nativeAdCallToAction.setVisibility(
                    nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
            nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
            sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);
            nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void inflateAd2(com.facebook.ads.NativeAd nativeAdfan, Activity activity, RelativeLayout layNative) {
        try {
            nativeAdfan.unregisterView();
            nativeAdLayout = new NativeAdLayout(activity, null, 1);
            LayoutInflater inflater = LayoutInflater.from(activity);
            adView = (LinearLayout) inflater.inflate(R.layout.fan_medium_native, nativeAdLayout, false);
            layNative.addView(adView);

            LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAdfan, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            nativeAdTitle.setText(nativeAdfan.getAdvertiserName());
            nativeAdBody.setText(nativeAdfan.getAdBodyText());
            nativeAdSocialContext.setText(nativeAdfan.getAdSocialContext());
            nativeAdCallToAction.setVisibility(nativeAdfan.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdCallToAction.setText(nativeAdfan.getAdCallToAction());
            sponsoredLabel.setText(nativeAdfan.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);

            nativeAdfan.registerViewForInteraction(
                    adView, nativeAdMedia, nativeAdIcon, clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inflateAd3(NativeBannerAd nativeBannerAd, Activity activity, RelativeLayout layNative) {
        try {
            nativeBannerAd.unregisterView();
            nativeAdLayout = new NativeAdLayout(activity, null, 1);
            LayoutInflater inflater = LayoutInflater.from(activity);
            adView = (LinearLayout) inflater.inflate(R.layout.fan_small_rectangle_native, nativeAdLayout, false);
            layNative.addView(adView);

            RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            com.facebook.ads.MediaView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
            nativeAdCallToAction.setVisibility(
                    nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
            nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
            sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);
            nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
