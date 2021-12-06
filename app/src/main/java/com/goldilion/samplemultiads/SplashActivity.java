package com.goldilion.samplemultiads;

import static com.goldilion.samplemultiads.SettingAds.BACKUP_ADS;
import static com.goldilion.samplemultiads.SettingAds.INITIALIZE_SDK;
import static com.goldilion.samplemultiads.SettingAds.INITIALIZE_SDK_BACKUPADS;
import static com.goldilion.samplemultiads.SettingAds.SELECT_ADS;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.goldilion.multiads.AndroAdsOpenAds;
import com.goldilion.multiads.AndroAdsInitialize;

public class SplashActivity extends AppCompatActivity {
    private static final int REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        switch (SELECT_ADS) {
            case "ADMOB":
                AndroAdsInitialize.SelectAdsAdmob(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK);
                break;
            case "APPLOVIN-D":
                AndroAdsInitialize.SelectAdsApplovinDis(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK);
                break;
            case "APPLOVIN-M":
                AndroAdsInitialize.SelectAdsApplovinMax(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK);
                break;
            case "MOPUB":
                AndroAdsInitialize.SelectAdsMopub(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUPADS);
                break;
            case "STARTAPP":
                AndroAdsInitialize.SelectAdsStartApp(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK,INITIALIZE_SDK_BACKUPADS);
                break;
            case "GOOGLE-ADS":
                AndroAdsInitialize.SelectAdsAdmob(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK);
                break;
            case "IRON":
                AndroAdsInitialize.SelectAdsIron(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUPADS);
                break;
        }
        AndroAdsOpenAds.LoadOpenAds(SettingAds.OPEN_ADS_ADMOB);

        new CountDownTimer(10000, 1000) {
                @Override
                public void onFinish() {

                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                }
                @Override
                public void onTick(long millisUntilFinished) {

                }
            }.start();

    }


}
