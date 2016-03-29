package com.apporio.tyretimes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.apporio.tyretimes.parsing.parsingforsignup;

public class Signupactivity extends Activity {
    public static View v11,v2,v3,v41,v5,v6,v71,v8,v9,v10;
    public static EditText name,email,pass,mob;
    public static TextView next,back,loading;



    public  static Signupactivity signupactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        next = (TextView)findViewById(R.id.login34);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        mob = (EditText)findViewById(R.id.mob);
        loading = (TextView)findViewById(R.id.loading);
        v9 = (View)findViewById(R.id.view1111111);
        v10= (View)findViewById(R.id.view1121111);
        v11 = (View)findViewById(R.id.view11);
        v2 = (View)findViewById(R.id.view12);
        v3 = (View)findViewById(R.id.view21);
        v41 = (View)findViewById(R.id.view22);
        v5 = (View)findViewById(R.id.view111);
        v6 = (View)findViewById(R.id.view112);
        back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Signupactivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)Signupactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                finish();
            }
        });
        signupactivity=Signupactivity.this;

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v5.setVisibility(View.GONE);
                    v6.setVisibility(View.VISIBLE);
                }
                else{
                    v5.setVisibility(View.VISIBLE);
                    v6.setVisibility(View.GONE);
                }
            }
        });
        mob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v10.setVisibility(View.VISIBLE);
                    v9.setVisibility(View.GONE);
                }
                else{
                    v9.setVisibility(View.VISIBLE);
                    v10.setVisibility(View.GONE);

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Signupactivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)Signupactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                finish();

               parsingforsignup.parsing(Signupactivity.this, name.getText().toString().trim(),
                       email.getText().toString().trim(), pass.getText().toString().trim(),
                       mob.getText().toString().trim());
//                // Toast.makeText(getApplicationContext(), "Signing Up...", Toast.LENGTH_SHORT).show();


            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Signupactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Signupactivity.this.getResources().getColor(R.color.colorAccent));
        } else {
            Window window = Signupactivity.this.getWindow();
            // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
