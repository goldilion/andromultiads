package com.goldilion.samplemultiads;

import static com.goldilion.samplemultiads.SettingAds.BACKUP_ADS;
import static com.goldilion.samplemultiads.SettingAds.BACKUP_ADS_BANNER;
import static com.goldilion.samplemultiads.SettingAds.BACKUP_ADS_INTERSTITIAL;
import static com.goldilion.samplemultiads.SettingAds.HPK1;
import static com.goldilion.samplemultiads.SettingAds.HPK2;
import static com.goldilion.samplemultiads.SettingAds.HPK3;
import static com.goldilion.samplemultiads.SettingAds.HPK4;
import static com.goldilion.samplemultiads.SettingAds.HPK5;
import static com.goldilion.samplemultiads.SettingAds.INTERVAL;
import static com.goldilion.samplemultiads.SettingAds.MAIN_ADS_BANNER;
import static com.goldilion.samplemultiads.SettingAds.MAIN_ADS_INTERSTITIAL;
import static com.goldilion.samplemultiads.SettingAds.MAIN_ADS_REWARDS;
import static com.goldilion.samplemultiads.SettingAds.SELECT_ADS;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.goldilion.multiads.AndroGDPR;
import com.goldilion.multiads.AndroAdsOpenAds;
import com.goldilion.multiads.AndroAdsBanner;
import com.goldilion.multiads.AndroAdsInitialize;
import com.goldilion.multiads.AndroAdsInterstitial;
import com.goldilion.multiads.AndroAdsNative;
import com.goldilion.multiads.AndroAdsReward;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layAds = findViewById(R.id.layAds);
        FrameLayout nativeads = findViewById(R.id.laynative);
        RelativeLayout layAdsmall = findViewById(R.id.laysAdsmall);
        AndroGDPR.loadGdpr(MainActivity.this,SELECT_ADS,true);

        switch (SELECT_ADS) {
            case "ADMOB":
                AndroAdsBanner.SmallBannerAdmob(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, HPK1
                        ,HPK2,HPK3,HPK4,HPK5);
                AndroAdsInterstitial.LoadInterstitialAdmob(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERSTITIAL, BACKUP_ADS_INTERSTITIAL, HPK1
                        ,HPK2,HPK3,HPK4,HPK5);
                AndroAdsOpenAds.ShowOpen(MainActivity.this);
                break;
            case "APPLOVIN-M":
                AndroAdsBanner.SmallBannerApplovinMax(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AndroAdsInterstitial.LoadInterstitialApplovinMax(MainActivity.this, BACKUP_ADS,MAIN_ADS_INTERSTITIAL,BACKUP_ADS_INTERSTITIAL);
                break;
            case "APPLOVIN-D":
                AndroAdsBanner.SmallBannerApplovinDis(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AndroAdsInterstitial.LoadInterstitialApplovinDis(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERSTITIAL, BACKUP_ADS_INTERSTITIAL);
                break;
            case "MOPUB" :
                AndroAdsBanner.SmallBannerMopub(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "STARTAPP":
                AndroAdsBanner.SmallBannerStartApp(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
        }
    }

    public void showads(View view){
        switch (SELECT_ADS) {
            case "ADMOB":
                AndroAdsInterstitial.ShowInterstitialAdmob(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERSTITIAL, BACKUP_ADS_INTERSTITIAL, INTERVAL,
                        HPK1,HPK2,HPK3,HPK4,HPK5);
                break;
            case "APPLOVIN-D":
                AndroAdsInterstitial.ShowInterstitialApplovinDis(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERSTITIAL, BACKUP_ADS_INTERSTITIAL, INTERVAL);
                break;
            case "APPLOVIN-M":
                AndroAdsInterstitial.ShowInterstitialApplovinMax(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERSTITIAL, BACKUP_ADS_INTERSTITIAL, INTERVAL);
                break;
        }

    }

    public void showreward(View view){
        AndroAdsReward.ShowReward(MainActivity.this,SELECT_ADS,MAIN_ADS_REWARDS);

    }

    public void onResume(){
        super.onResume();
        if (AndroAdsReward.unlockreward){
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
        }
    }


}