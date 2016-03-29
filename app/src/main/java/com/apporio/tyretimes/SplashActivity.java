package com.apporio.tyretimes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.apporio.tyretimes.networkchecker.NetworkChecker;
import com.apporio.tyretimes.parsing.parsingforlinks;
import com.apporio.tyretimes.parsing.parsingforrecent;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import com.apporio.tyretimes.R;


public class SplashActivity extends Activity {
    boolean previouslyStarted;
    TextView nointernet  ,loadingtext;
    public static SplashActivity spl;
    Button tryagain ;

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        nointernet = (TextView) findViewById(R.id.nointernet);
        tryagain = (Button) findViewById(R.id.tryagain);

        loadingtext = (TextView) findViewById(R.id.loadintextinsplash);
        nointernet.setVisibility(View.GONE);
        tryagain.setVisibility(View.GONE);

        spl= SplashActivity.this;
        loadingtext.setVisibility(View.GONE);

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread();
            }
        });
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previouslyStarted = prefs.getBoolean("pref_previously_started", false);


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run()

            {
                YoYo.with(Techniques.DropOut)
                        .duration(700)
                        .playOn(findViewById(R.id.ggt));







            }
        }, 50);




        Handler handler2 = new Handler();

        handler2.postDelayed(new Runnable() {

            @Override
            public void run()

            {
                startThread();

            }
        }, 1200);
    }
    private void startThread() {
        loadingtext.setVisibility(View.VISIBLE);
        nointernet.setVisibility(View.GONE);
        tryagain.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getTheGPSBROO();
            }
        }, SPLASH_TIME_OUT);
    }


    private void getTheGPSBROO() {
        if(new NetworkChecker().isNetworkConnected(SplashActivity.this)){
            nointernet.setVisibility(View.GONE);
            tryagain.setVisibility(View.GONE);
            startactivity();


        }else {
            loadingtext.setVisibility(View.GONE);
            nointernet.setVisibility(View.VISIBLE);
            tryagain.setVisibility(View.VISIBLE);

        }
    }

    private void startactivity() {
//        if (!previouslyStarted) {
//            Intent in = new Intent(SplashActivity.this, Logregactivity.class);
//            startActivity(in);
//            SplashActivity.spl.finish();
//
//        } else {
        parsingforlinks.parsing(SplashActivity.this);
       // }
    }



}
