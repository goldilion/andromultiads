package com.goldilion.samplemultiads;

import static com.goldilion.samplemultiads.SettingAds.HPK1;
import static com.goldilion.samplemultiads.SettingAds.HPK2;
import static com.goldilion.samplemultiads.SettingAds.HPK3;
import static com.goldilion.samplemultiads.SettingAds.HPK4;
import static com.goldilion.samplemultiads.SettingAds.HPK5;
import static com.goldilion.samplemultiads.SettingAds.INITIALIZE_SDK;
import static com.goldilion.samplemultiads.SettingAds.MAIN_ADS_BANNER;
import static com.goldilion.samplemultiads.SettingAds.MAIN_ADS_REWARDS;
import static com.goldilion.samplemultiads.SettingAds.NATIVE_ADS_ADMOB;
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
        AndroAdsInitialize.SelectAds(MainActivity.this, SettingAds.SELECT_ADS, INITIALIZE_SDK);
        AndroAdsBanner.MediumBanner(MainActivity.this, layAds,SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_BANNER,HPK1
        ,HPK2,HPK3,HPK4,HPK5);
        AndroAdsBanner.SmallBanner(MainActivity.this, layAdsmall,SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_BANNER,HPK1
                ,HPK2,HPK3,HPK4,HPK5);
        AndroAdsIntertitial.LoadIntertitial(MainActivity.this, SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_INTERTITIAL,HPK1
                ,HPK2,HPK3,HPK4,HPK5 );
        AndroAdsNative.SmallNativeAdmob(MainActivity.this,SELECT_ADS, SettingAds.BACKUP_ADS, nativeads, NATIVE_ADS_ADMOB,MAIN_ADS_BANNER, HPK1
                ,HPK2,HPK3,HPK4,HPK5);
        AndroAdsReward.LoadReward(MainActivity.this, SELECT_ADS, MAIN_ADS_REWARDS );
        AndroAdsOpenAds.ShowOpen(MainActivity.this);
    }

    public void showads(View view){
        AndroAdsIntertitial.ShowIntertitial(MainActivity.this,SettingAds.SELECT_ADS,
                SettingAds.MAIN_ADS_INTERTITIAL, 0,HPK1
                ,HPK2,HPK3,HPK4,HPK5);

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