package com.apporio.tyretimes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apporio.tyretimes.R;
import com.apporio.tyretimes.parsing.parsingforlogin;

public class Logregactivity extends Activity {
    public static View v11,v2,v3,v41;
    public static EditText email,pass;
    public static Logregactivity logact;
    public static TextView next,forgot,signup,back,loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logregactivity);
        next = (TextView)findViewById(R.id.login);
        signup = (TextView)findViewById(R.id.signup);
        forgot = (TextView)findViewById(R.id.textView2);
        email = (EditText)findViewById(R.id.username);
        loading = (TextView)findViewById(R.id.loading);
        pass = (EditText)findViewById(R.id.zpc);
        v11 = (View)findViewById(R.id.view11);
        v2 = (View)findViewById(R.id.view12);
        v3 = (View)findViewById(R.id.view21);
        v41 = (View)findViewById(R.id.view22);
        back=(TextView)findViewById(R.id.back);

        logact= Logregactivity.this;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Logregactivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)Logregactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                finish();
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v11.setVisibility(View.GONE);
                    v2.setVisibility(View.VISIBLE);
                }
                else{
                    v11.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.GONE);
                }
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    v3.setVisibility(View.GONE);
                    v41.setVisibility(View.VISIBLE);
                } else {
                    v3.setVisibility(View.VISIBLE);
                    v41.setVisibility(View.GONE);
                }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), "Signing Up...", Toast.LENGTH_SHORT).show();


                Intent in = new Intent(Logregactivity.this, ForgotActivity.class);
                startActivity(in);


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Logregactivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)Logregactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

               parsingforlogin.parsing(Logregactivity.this, email.getText().toString().trim(), pass.getText().toString().trim());
//                Toast.makeText(getApplicationContext(), "Login Successfully...", Toast.LENGTH_SHORT).show();
//                Handler handler1 = new Handler();
//
//                handler1.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run()
//
//                    {
////                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
////                        SharedPreferences.Editor edit2 = prefs.edit();
////
////                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
////                        edit2.commit();
//                        Intent in = new Intent(Logregactivity.this, MainActivity.class);
//                        startActivity(in);
//                        finish();
                        // Logregactivity.logct.finish();

                 //   }
              //  }, 1000);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Logregactivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)Logregactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                //   parsingforlogin.parsing(Loginactivity.this,email.getText().toString().trim(),zip.getText().toString().trim());
//                Toast.makeText(getApplicationContext(), "Login Successfully...", Toast.LENGTH_SHORT).show();
                Handler handler1 = new Handler();

                handler1.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
//                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//                        SharedPreferences.Editor edit2 = prefs.edit();
//
//                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
//                        edit2.commit();
                        Intent in = new Intent(Logregactivity.this, Signupactivity.class);
                        startActivity(in);

                        // Logregactivity.logct.finish();

                    }
                }, 1000);

            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        View view = Logregactivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)Logregactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Logregactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Logregactivity.this.getResources().getColor(R.color.red));
        } else {
            Window window = Logregactivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
