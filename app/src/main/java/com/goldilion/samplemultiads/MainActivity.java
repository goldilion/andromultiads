package com.goldilion.samplemultiads;

import static com.goldilion.samplemultiads.SettingAds.HIGH_PAYING_KEYWORD1;
import static com.goldilion.samplemultiads.SettingAds.HIGH_PAYING_KEYWORD2;
import static com.goldilion.samplemultiads.SettingAds.HIGH_PAYING_KEYWORD3;
import static com.goldilion.samplemultiads.SettingAds.HIGH_PAYING_KEYWORD4;
import static com.goldilion.samplemultiads.SettingAds.HIGH_PAYING_KEYWORD5;
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

    /*
    APPLOVIN_BANNER = "db4d5e8718b97d78";
    APPLOVIN_INTER = "518cd97722c60b52";

    BANNER_MOPUB = "b195f8dd8ded45fe847ad89ed1d016da";
    INTER_MOPUB = "24534e1901884e398f1253216226017e";

    ADMOB_INTER = "ca-app-pub-3940256099942544/1033173712";
    ADMOB_BANNER = "ca-app-pub-3940256099942544/6300978111";
    ADMOB_OPENAD ="ca-app-pub-3940256099942544/3419835294"

    IRON_BANNER="DefaultBanner";
    IRON_INTERTITIAL="Game_Screen";
    IRON_APPID="10301c6e9";

    STARTAPPID="123456789";
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layAds = findViewById(R.id.layAds);
        FrameLayout nativeads = findViewById(R.id.laynative);
        RelativeLayout layAdsmall = findViewById(R.id.laysAdsmall);
        AndroGDPR.loadGdpr(MainActivity.this,SELECT_ADS,true);
        AndroAdsInitialize.SelectAds(MainActivity.this, SettingAds.SELECT_ADS, INITIALIZE_SDK);
        AndroAdsBanner.MediumBanner(MainActivity.this, layAds,SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_BANNER,HIGH_PAYING_KEYWORD1
        ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5);
        AndroAdsBanner.SmallBanner(MainActivity.this, layAdsmall,SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_BANNER,HIGH_PAYING_KEYWORD1
                ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5);
        AndroAdsIntertitial.LoadIntertitial(MainActivity.this, SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_INTERTITIAL,HIGH_PAYING_KEYWORD1
                ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5 );
        AndroAdsNative.SmallNativeAdmob(MainActivity.this,SELECT_ADS, SettingAds.BACKUP_ADS, nativeads, NATIVE_ADS_ADMOB,MAIN_ADS_BANNER, HIGH_PAYING_KEYWORD1
                ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5);
        AndroAdsReward.LoadReward(MainActivity.this, SELECT_ADS, MAIN_ADS_REWARDS );
        AndroAdsOpenAds.ShowOpen(MainActivity.this);
    }

    public void munculiklan(View view){
        AndroAdsIntertitial.ShowIntertitial(MainActivity.this,SettingAds.SELECT_ADS,
                SettingAds.MAIN_ADS_INTERTITIAL, 0,HIGH_PAYING_KEYWORD1
                ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5);

    }

    public void munculreward(View view){
        AndroAdsReward.ShowReward(MainActivity.this,SELECT_ADS,MAIN_ADS_REWARDS);

    }

    public void onResume(){
        super.onResume();
        if (AndroAdsReward.unlockreward){
            Toast.makeText(getApplicationContext(), "OK Berhasil", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_LONG).show();
        }
    }


}