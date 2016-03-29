package com.apporio.tyretimes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.TextView;



import views.ProgressWheel;


public class Try extends Activity {

    ProgressWheel pb;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_try);
        Photoinneractivity.lis.finish();
        pb=(ProgressWheel)findViewById(R.id.pb);
        txt=(TextView)findViewById(R.id.textvvvvvvvv);
        Bundle bundle = getIntent().getExtras();
        final String s = bundle.getString("title", null);
        final String head = bundle.getString("head", null);
        final String date1 = bundle.getString("date", "");
        final  String dsecp = bundle.getString("descp", null);
        final String img = bundle.getString("img", null);
        final String id1 = bundle.getString("id", null);
        final  String src11 = bundle.getString("src", null);
        final String plc11 = bundle.getString("plc", null);
        final String idvideo = bundle.getString("key", null);
        ScreenResolution screenRes = deviceDimensions();
            pb.setVisibility(View.VISIBLE);
        Handler handler2 = new Handler();

        handler2.postDelayed(new Runnable() {

            @Override
            public void run()

            {
                Intent in = new Intent(Try.this,Photoinneractivity.class);
                in.putExtra("title", s);
                in.putExtra("head", head);
                in.putExtra("date", date1);
                in.putExtra("descp", dsecp);
                in.putExtra("img", img);
                in.putExtra("src", src11);
                in.putExtra("plc", plc11);
                in.putExtra("id", id1);
                in.putExtra("key", idvideo);
                startActivity(in);
                pb.setVisibility(View.VISIBLE);
                txt.setText("Success");
                finish();

            }
        }, 4000);

    }
    private class ScreenResolution {
        int width, height;
        public ScreenResolution(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    ScreenResolution deviceDimensions() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // getsize() is available from API 13
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return new ScreenResolution(size.x, size.y);
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            // getWidth() & getHeight() are depricated
            return new ScreenResolution(display.getWidth(), display.getHeight());
        }
    }

}
