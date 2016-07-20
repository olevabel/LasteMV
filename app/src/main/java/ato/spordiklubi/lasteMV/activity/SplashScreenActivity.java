package ato.spordiklubi.lasteMV.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ato.spordiklubi.lasteMV.R;


/**
 * Created by Olev on 13/08/2015.
 */
public class SplashScreenActivity extends BaseActivity {


    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
