package com.goldilion.samplemultiads;

import static com.goldilion.samplemultiads.SettingAds.BACKUP_ADS;
import static com.goldilion.samplemultiads.SettingAds.BACKUP_ADS_BANNER;
import static com.goldilion.samplemultiads.SettingAds.BACKUP_ADS_INTERTITIAL;
import static com.goldilion.samplemultiads.SettingAds.HPK1;
import static com.goldilion.samplemultiads.SettingAds.HPK2;
import static com.goldilion.samplemultiads.SettingAds.HPK3;
import static com.goldilion.samplemultiads.SettingAds.HPK4;
import static com.goldilion.samplemultiads.SettingAds.HPK5;
import static com.goldilion.samplemultiads.SettingAds.INTERVAL;
import static com.goldilion.samplemultiads.SettingAds.MAIN_ADS_BANNER;
import static com.goldilion.samplemultiads.SettingAds.MAIN_ADS_INTERTITIAL;
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
import com.goldilion.multiads.AndroAdsIntertitial;
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
                AndroAdsIntertitial.LoadIntertitialAdmob(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, HPK1
                        ,HPK2,HPK3,HPK4,HPK5);
                AndroAdsOpenAds.ShowOpen(MainActivity.this);
                break;
            case "APPLOVIN-M":
                AndroAdsBanner.SmallBannerApplovinMax(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AndroAdsIntertitial.LoadIntertitialApplovinMax(MainActivity.this, BACKUP_ADS,MAIN_ADS_INTERTITIAL,BACKUP_ADS_INTERTITIAL);
                break;
            case "APPLOVIN-D":
                AndroAdsBanner.SmallBannerApplovinDis(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AndroAdsIntertitial.LoadIntertitialApplovinDis(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
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
                AndroAdsIntertitial.ShowIntertitialAdmob(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL,
                        HPK1,HPK2,HPK3,HPK4,HPK5);
                break;
            case "APPLOVIN-D":
                AndroAdsIntertitial.ShowIntertitialApplovinDis(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                break;
            case "APPLOVIN-M":
                AndroAdsIntertitial.ShowIntertitialApplovinMax(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
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