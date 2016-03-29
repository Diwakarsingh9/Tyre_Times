package com.apporio.tyretimes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.apporio.tyretimes.R;
import com.apporio.tyretimes.parsing.parsingforrecent;
import com.apporio.tyretimes.urlapi.Api_s;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListInnerActivity extends Activity {
    public static TextView headl,date11,title,descp,src,plc,relatedsearch;
    public static ImageView back,dp;
   // public static ImageLoader il;
    public static View v111;
    String dsecp ="";
    public static LinearLayout llforlist,llforrecnt;
    public static ProgressBar pb;
    public static ListInnerActivity lis;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_inner);
        lis = ListInnerActivity.this;

        title= (TextView) findViewById(R.id.titlehead);
        back= (ImageView) findViewById(R.id.back);
        headl= (TextView) findViewById(R.id.titleddd);
        pb= (ProgressBar)findViewById(R.id.progressBar22);
        date11= (TextView) findViewById(R.id.date);
        v111= (View) findViewById(R.id.vew);
        relatedsearch= (TextView) findViewById(R.id.related);
        llforlist= (LinearLayout) findViewById(R.id.listforrelated);
        llforrecnt= (LinearLayout) findViewById(R.id.listforrelated);
        src= (TextView) findViewById(R.id.src11);
        plc= (TextView) findViewById(R.id.plc);
        descp= (TextView) findViewById(R.id.descp);
        dp= (ImageView) findViewById(R.id.logo);
        llforlist.removeAllViews();
        ListInnerActivity.relatedsearch.setVisibility(View.GONE);
        ListInnerActivity.v111.setVisibility(View.GONE);
       // il=new ImageLoader(ListInnerActivity.this);
        Bundle bundle = getIntent().getExtras();
        String s= bundle.getString("title", null);
        String head= bundle.getString("head", null);
        String date1= bundle.getString("date", null);
        dsecp= bundle.getString("descp", null);
        String img= bundle.getString("img", null);
        String id1= bundle.getString("id", null);
        url= bundle.getString("src_link", null);
        relatedsearch.setText("Related " + "Articles");
        Log.e("string", "" + id1 + " " + s);
        parsingforrecent.parsing(ListInnerActivity.this, id1, s);
//        Log.e("foto", "" + img);
        Picasso.with(ListInnerActivity.this)
                .load(""+ Api_s.imageurl + img)
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(dp);
        title.setText("" + s.toUpperCase());
        src.setText("" + getIntent().getExtras().getString("src"));
        plc.setText("" +  getIntent().getExtras().getString("plc"));
        headl.setText("" + Html.fromHtml("" + head).toString());
        //String des =  Html.fromHtml(""+dsecp).toString();
        descp.setText(Html.fromHtml(dsecp));
        String outputDateStr = null;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            String inputDateStr=""+date1;
            Date date = null;
            try {
                date = inputFormat.parse(inputDateStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            outputDateStr = ""+ outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        date11.setText("" + outputDateStr);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        plc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("" + url));
                    startActivity(browserIntent);
                }
                else{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("" + url));
                    startActivity(browserIntent);
                }
            }
        });


    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = ListInnerActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ListInnerActivity.this.getResources().getColor(R.color.colorAccent));
        } else {
            Window window = ListInnerActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}


