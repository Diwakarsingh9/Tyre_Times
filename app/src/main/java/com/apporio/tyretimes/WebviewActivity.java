package com.apporio.tyretimes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.apporio.tyretimes.R;

public class WebviewActivity extends Activity {

    private WebView mWebview;
    TextView head;
    ImageView back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        head = (TextView)findViewById(R.id.titlehead);
        back = (ImageView)findViewById(R.id.back);
        Bundle b = getIntent().getExtras();
        head.setText("" + b.getString("head", "NA"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebview = (WebView)findViewById(R.id.webView);;

//        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
//
//        final Activity activity = this;
//
//        mWebview.setWebViewClient(new WebViewClient() {
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                //Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
//            }
//        });

        mWebview.loadUrl(""+b.getString("link","www.google.com"));
        //setContentView(mWebview);

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = WebviewActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(WebviewActivity.this.getResources().getColor(R.color.colorAccent));
        } else {
            Window window = WebviewActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}