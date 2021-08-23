package com.goldilion.samplemultiads;

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

        AndroAdsInitialize.SelectAds(SplashActivity.this,"ADMOB",
                "");
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
