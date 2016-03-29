package com.apporio.tyretimes.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apporio.tyretimes.R;
import com.apporio.tyretimes.parsing.parsingforrecent;
import com.apporio.tyretimes.urlapi.Api_s;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListInnerActivity2 extends Activity {
    public static TextView headl,title,descp,relatedsearch;
    public static ImageView back,dp;
    // public static ImageLoader il;
    public static View v111;
    public static LinearLayout llforlist,llforrecnt;
    public static ProgressBar pb;
    public static ListInnerActivity2 lis;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_inner2);
        lis = ListInnerActivity2.this;

        title= (TextView) findViewById(R.id.titlehead);
        back= (ImageView) findViewById(R.id.back);
        headl= (TextView) findViewById(R.id.titleddd);
        pb= (ProgressBar)findViewById(R.id.progressBar);

        v111= (View) findViewById(R.id.vew);
        relatedsearch= (TextView) findViewById(R.id.related);
        llforlist= (LinearLayout) findViewById(R.id.listforrelated);
        llforrecnt= (LinearLayout) findViewById(R.id.listforrelated);

        descp= (TextView) findViewById(R.id.descp);
        dp= (ImageView) findViewById(R.id.logo);
        llforlist.removeAllViews();
        ListInnerActivity2.relatedsearch.setVisibility(View.GONE);
        ListInnerActivity2.v111.setVisibility(View.GONE);
        // il=new ImageLoader(ListInnerActivity.this);
        Bundle bundle = getIntent().getExtras();
        String s= bundle.getString("title", null);
        String head= bundle.getString("head", null);

        String dsecp= bundle.getString("descp", null);
        String img= bundle.getString("img", null);
        String id1= bundle.getString("id", null);

        relatedsearch.setText("Related " + s);

       // parsingforrecent.parsing(ListInnerActivity2.this, id1, s);
//        Log.e("foto", "" + img);
        Picasso.with(ListInnerActivity2.this)
                .load(""+ Api_s.imageurl + img)
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(dp);
        title.setText("" + s.toUpperCase());

        headl.setText("" + Html.fromHtml("" + head).toString());
        String des =  Html.fromHtml(""+dsecp).toString();
        descp.setText(des);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = ListInnerActivity2.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ListInnerActivity2.this.getResources().getColor(R.color.red));
        } else {
            Window window = ListInnerActivity2.this.getWindow();
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}
