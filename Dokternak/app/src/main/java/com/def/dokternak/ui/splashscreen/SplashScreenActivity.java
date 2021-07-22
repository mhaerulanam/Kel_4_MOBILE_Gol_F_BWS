package com.def.dokternak.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.def.dokternak.MainActivity;
import com.def.dokternak.R;
import com.def.dokternak.ui.welcome.WelcomeActivity;
import com.def.dokternak.utils.PrefManager;
import com.def.dokternak.utils.Preferences;

public class SplashScreenActivity extends AppCompatActivity {

    private int waktu_loading = 4000;

    /** 4000 = 4 detik */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // setelah loading maka akan langsung berpindah ke intro slider
                Intent intro = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(intro);
                finish();
                overridePendingTransition(R.layout.fadein_splash,R.layout.fadeout_splash);
            }
        },waktu_loading);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }
}