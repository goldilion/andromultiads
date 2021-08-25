package com.goldilion.multiads;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.AppLovinExtras;
import com.applovin.mediation.ApplovinAdapter;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.mopub.mobileads.MoPubInterstitial;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

public class AndroAdsInterstitial {
    public static InterstitialAd mInterstitialAd;
    public static MaxInterstitialAd interstitialAd;
    public static MoPubInterstitial mInterstitial;
    public static int counter = 0;
    public static AppLovinInterstitialAdDialog interstitialAdlovin;
    public static AppLovinAd loadedAd;
    private static StartAppAd startAppAd;

    public static void LoadInterstitial(Activity activity, String selectAds, String idInterstitial, String Hpk1,
                                       String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        switch (selectAds) {
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitial, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "APPLOVIN-M":
                if (idInterstitial == null){
                    interstitialAd = new MaxInterstitialAd("qwerty1234", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idInterstitial, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idInterstitial);
                mInterstitial.load();
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idInterstitial);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;

        }
    }

    public static void LoadInterstitialAdmob(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        Bundle extras = new AppLovinExtras.Builder()
                .setMuteAudio(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                .build();
        InterstitialAd.load(activity, idInterstitial, request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idInterstitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idInterstitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idInterstitialBackup);
                mInterstitial.load();
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idInterstitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;

        }
    }

    public static void LoadInterstitialApplovinDis(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup
    ) {

        AdRequest.Builder builder = new AdRequest.Builder();
        Bundle interstitialExtras = new Bundle();
        interstitialExtras.putString("zone_id", idInterstitial);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                loadedAd = ad;
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                // Look at AppLovinErrorCodes.java for list of error codes.
            }
        });
        interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idInterstitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idInterstitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idInterstitialBackup);
                mInterstitial.load();
                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitial, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;

        }
    }

    public static void LoadInterstitialApplovinMax(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup)
    {

        interstitialAd = new MaxInterstitialAd(idInterstitial, activity);
        interstitialAd.loadAd();

        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idInterstitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idInterstitialBackup);
                mInterstitial.load();
                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitial, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;

        }
    }

    public static void LoadInterstitialMopub(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup)
    {

        mInterstitial = new MoPubInterstitial(activity, idIntertitial);
        mInterstitial.load();
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;

        }
    }

    public static void LoadInterstitialStartApp(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup)
    {
        startAppAd = new StartAppAd(activity);
        startAppAd.loadAd (new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
            }
            @Override
            public void onFailedToReceiveAd(Ad ad) {

            }
        });
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "MOPUB" :
                mInterstitial = new MoPubInterstitial(activity, idIntertitial);
                mInterstitial.load();
                break;

        }
    }

    public static void ShowInterstitial(Activity activity, String selectAds, String idInterstitial, int interval,String Hpk1,
                                       String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {
            switch (selectAds) {
                case "ADMOB":
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(activity);
                        LoadInterstitial(activity, selectAds, idInterstitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
                    } else {
                        LoadInterstitial(activity, selectAds, idInterstitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
                    }
                    break;
                case "APPLOVIN-M":
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                        interstitialAd.loadAd();
                    } else {
                        interstitialAd.loadAd();
                    }
                    break;
                case "MOPUB":
                    if (mInterstitial.isReady()) {
                        mInterstitial.show();
                        mInterstitial.load();
                    } else {
                        mInterstitial.load();
                    }
                    break;
                case "STARTAPP":
                    StartAppAd.showAd(activity);
                    break;
                case "APPLOVIN-D":
                    interstitialAdlovin.showAndRender(loadedAd);
                    break;
            }
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowInterstitialAdmob(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup,
                                             int interval, String Hpk1, String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {

            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
                LoadInterstitialAdmob(activity, selectAdsBackup, idInterstitial, idInterstitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                            interstitialAd.loadAd();
                        } else {
                            interstitialAd.loadAd();
                        }
                        break;
                    case "MOPUB":
                        if (mInterstitial.isReady()) {
                            mInterstitial.show();
                            mInterstitial.load();
                        } else {
                            mInterstitial.load();
                        }
                        break;
                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                }
                LoadInterstitialAdmob(activity, selectAdsBackup, idInterstitial, idInterstitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            }

            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowInterstitialApplovinDis(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup,
                                                  int interval) {
        if (counter >= interval) {
            if (interstitialAdlovin != null) {
                AppLovinAdDisplayListener listener = new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd ad) {

                    }

                    @Override
                    public void adHidden(AppLovinAd ad) {
                        switch (selectAdsBackup) {
                            case "APPLOVIN-M":
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                    interstitialAd.loadAd();
                                } else {
                                    interstitialAd.loadAd();
                                }
                                break;
                            case "MOPUB":
                                if (mInterstitial.isReady()) {
                                    mInterstitial.show();
                                    mInterstitial.load();
                                } else {
                                    mInterstitial.load();
                                }
                                break;
                            case "STARTAPP":
                                StartAppAd.showAd(activity);
                                break;
                            case "ADMOB":
                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(activity);
                                }
                                break;
                        }
                        LoadInterstitialApplovinDis(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
                    }
                };
                interstitialAdlovin.setAdDisplayListener(listener);
                interstitialAdlovin.showAndRender(loadedAd);
                LoadInterstitialApplovinDis(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
            }

            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowInterstitialApplovinMax(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup,
                                                  int interval) {
        if (counter >= interval) {
            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
                LoadInterstitialApplovinMax(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "MOPUB":
                        if (mInterstitial.isReady()) {
                            mInterstitial.show();
                            mInterstitial.load();
                        } else {
                            mInterstitial.load();
                        }
                        break;
                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                }
                LoadInterstitialApplovinMax(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
                interstitialAd.loadAd();
            }

            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowInterstitialMopub(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                            int interval) {
        if (counter >= interval) {
            if (mInterstitial.isReady()) {
                mInterstitial.show();
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                        }
                        break;
                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                }
            }
            LoadInterstitialMopub(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }


    public static void ShowInterstitialStartApp(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                              int interval) {
        if (counter >= interval) {
            startAppAd.showAd();
            startAppAd.showAd(new AdDisplayListener() {
                @Override
                public void adHidden(Ad ad) {
                }
                @Override
                public void adDisplayed(Ad ad) {
                }
                @Override
                public void adClicked(Ad ad) {
                }
                @Override
                public void adNotDisplayed(Ad ad) {
                    switch (selectAdsBackup) {
                        case "APPLOVIN-D":
                            if (interstitialAdlovin != null) {
                                interstitialAdlovin.showAndRender(loadedAd);
                            }
                            break;
                        case "APPLOVIN-M":
                            if (interstitialAd.isReady()) {
                                interstitialAd.showAd();
                            }
                            break;
                        case "MOPUB":
                            if (mInterstitial.isReady()) {
                                mInterstitial.show();
                            }
                            break;
                        case "ADMOB":
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(activity);
                            }
                            break;
                    }
                }
            });

            LoadInterstitialStartApp(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }
}
