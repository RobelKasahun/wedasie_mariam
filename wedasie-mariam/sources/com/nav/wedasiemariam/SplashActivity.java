package com.nav.wedasiemariam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_splash);
        splashScreen();
    }

    public void splashScreen() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashActivity splashActivity = SplashActivity.this;
                splashActivity.startActivity(new Intent(splashActivity, MainActivity.class));
                SplashActivity.this.finish();
            }
        }, 3000);
    }
}
