package com.apporio.tyretimes;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.tyretimes.drawer_fragment.Eventsfrag;
import com.apporio.tyretimes.drawer_fragment.Exhibitionfrag;
import com.apporio.tyretimes.parsing.parsingfornotification;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.io.IOException;


public class MainActivity extends FragmentActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    GoogleCloudMessaging gcmObj;
    Context applicationContext;
    String regId = "";
   
    SharedPreferences prefs2;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    String d_id;
    AsyncTask<Void, Void, String> createRegIdTask;
    public static Boolean previouslyStarted22=false;
    public static final String REG_ID = "regId";
    public static final String EMAIL_ID = "eMailId";
    Fragment fragment = null;
    ImageView drawer,settings,back;
    public static MenuDrawer mdrawer;
    public static  TextView headtext;
    public static ImageView head;
    public  static LinearLayout llforhide1,sharetheapp,sendfeedback,ratethisapp,aboutus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        mdrawer = MenuDrawer.attach(this);
        mdrawer = MenuDrawer.attach(this, Position.RIGHT);
        mdrawer.setContentView(R.layout.activity_main);
        mdrawer.setMenuView(R.layout.navigationdrawer);
        mdrawer.setDropShadowEnabled(false);
        head=(ImageView)findViewById(R.id.heading);
        drawer=(ImageView)findViewById(R.id.imgmenu);
        settings=(ImageView)findViewById(R.id.settings);

        back=(ImageView)findViewById(R.id.back);
        llforhide1 = (LinearLayout) findViewById(R.id.llforhide);
       // login = (LinearLayout) findViewById(R.id.loginll);
        sharetheapp = (LinearLayout) findViewById(R.id.share);
        sendfeedback = (LinearLayout) findViewById(R.id.sendfeed);
        ratethisapp = (LinearLayout) findViewById(R.id.rateapp);
        aboutus = (LinearLayout) findViewById(R.id.abtll);
//        termsofuse = (LinearLayout) findViewById(R.id.tous);
//        privacypolicy = (LinearLayout) findViewById(R.id.ppll);
//        logoutll = (LinearLayout) findViewById(R.id.logoutll);

        headtext = (TextView) findViewById(R.id.headtext);
        headtext.setVisibility(View.GONE);
        head.setVisibility(View.VISIBLE);
        llforhide1.setVisibility(View.GONE);
        //logoutll.setVisibility(View.GONE);
        applicationContext = getApplicationContext();
         prefs2 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        checkPlayServices();

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        MainActivity.loginheading.setText("" + prefs.getString("Username", "Login"));
//        if(  !MainActivity.loginheading.getText().toString().equals("Login"))
//        {
//            MainActivity.loginheading.setTextColor(Color.parseColor("#000000"));
//            MainActivity.loginheading.setTypeface(Typeface.DEFAULT_BOLD);
//            //MainActivity.login.setBackgroundColor(Color.parseColor("#ebeaea"));
//            MainActivity.dp.setVisibility(View.VISIBLE);
//            //logoutll.setVisibility(View.VISIBLE);
//
//        }
        fragment = new Mainfragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();
            // Drawer.closeDrawer(Gravity.LEFT);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showdrawerdialog();
            }
        });
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(loginheading.getText().toString().equals("Login")) {
//                    Intent in = new Intent(MainActivity.this, Logregactivity.class);
//                    startActivity(in);
//                }
//            }
//        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this,Aboutusactivity.class);
                startActivity(in);
            }
        });

//        termsofuse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(MainActivity.this, WebviewActivity.class);
//                in.putExtra("head","Terms Of Use");
//                in.putExtra("link",""+prefs2.getString("tou","www.tyretimes.com"));
//                startActivity(in);
////                Intent in = new Intent(MainActivity.this,Termsofuseactivity.class);
////                startActivity(in);
//            }
//        });

//        privacypolicy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(MainActivity.this, WebviewActivity.class);
//                in.putExtra("head","Privacy Policy");
//                in.putExtra("link",""+prefs2.getString("pp","www.tyretimes.com"));
//                startActivity(in);
////                Intent in = new Intent(MainActivity.this,Privacypolicyactivity.class);
////                startActivity(in);
//            }
//        });
//        logoutll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showlogoutdialog();
//
//            }
//        });
        sendfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", ""+prefs.getString("adminemail","amit.s@tyretimes.com"), null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for TyreTimes");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        emailIntent.setType("text/plain");
            }
        });
        ratethisapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rateApp(""+getApplicationContext().getPackageName());
            }
        });

        sharetheapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendSMS();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               mdrawer.openMenu();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdrawer.closeMenu();
            }
        });

    }
    public  void showdrawerdialog() {

        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        llforhide1.setVisibility(View.VISIBLE);

        dialog.getWindow().setWindowAnimations(
                R.style.customDialogAnimation);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogfordrawer);
        ImageView cancel = (ImageView)dialog.findViewById(R.id.circleView);
        LinearLayout homell = (LinearLayout)dialog.findViewById(R.id.homell);
        LinearLayout exhbll = (LinearLayout)dialog.findViewById(R.id.exhibitionll);
        LinearLayout trcll = (LinearLayout)dialog.findViewById(R.id.trcll);
        LinearLayout bcmtll = (LinearLayout)dialog.findViewById(R.id.bcmtll);
        LinearLayout eventsll = (LinearLayout)dialog.findViewById(R.id.eventsll);

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    llforhide1.setVisibility(View.GONE);
                    dialog.dismiss();
                }
                return true;
            }
        });

        homell.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                headtext.setVisibility(View.GONE);
                head.setVisibility(View.VISIBLE);

                fragment = new Mainfragment();

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();
                    // Drawer.closeDrawer(Gravity.LEFT);

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
                dialog.dismiss();
                llforhide1.setVisibility(View.GONE);


            }
        });
        eventsll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                headtext.setVisibility(View.VISIBLE);
                head.setVisibility(View.GONE);
                headtext.setText("Events");
                fragment = new Eventsfrag();
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();
                    // Drawer.closeDrawer(Gravity.LEFT);

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
                dialog.dismiss();
                llforhide1.setVisibility(View.GONE);


            }
        });
        exhbll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                headtext.setVisibility(View.VISIBLE);
                head.setVisibility(View.GONE);
                headtext.setText("Exhibition");
                fragment = new Exhibitionfrag();
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();


                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
                dialog.dismiss();
                llforhide1.setVisibility(View.GONE);


            }
        });
        trcll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url =""+prefs2.getString("tyre_retail_caddy","");
                if (!url.startsWith("http://") && !url.startsWith("https://")){
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""+url));
                startActivity(browserIntent);
               // Toast.makeText(MainActivity.this, "Tyre Retail Candy", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                llforhide1.setVisibility(View.GONE);


            }
        });
        bcmtll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String url =""+prefs2.getString("buyoncmt","");
                if (!url.startsWith("http://") && !url.startsWith("https://")){
                    url = "http://" + url;

                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""+url));
                startActivity(browserIntent);
//                Intent in = new Intent(MainActivity.this, WebviewActivity.class);
//                in.putExtra("head","Buy On C.M.T.");
//                startActivity(in);
//                Toast.makeText(MainActivity.this, "Buy On C.M.T.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                llforhide1.setVisibility(View.GONE);


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
               llforhide1.setVisibility(View.GONE);


            }
        });

        dialog.show();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = MainActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.colorAccent));
        } else {
            Window window = MainActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void sendSMS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out TyreTimes for your smartphone. Download it today from " + "" + prefs2.getString("sharethisapp", "play store"));

            if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
            // any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            startActivity(sendIntent);

        }
        else // For early versions, do what worked for you before.
        {
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("sms_body","Check out Realty Singh for your smartphone. Download it today from "+""+prefs2.getString("sharethisapp","play store"));
            startActivity(smsIntent);
        }
    }


        public void rateApp(String packageName) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + packageName)));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    private void registerInBackground(final String emailID) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcmObj == null) {
                        gcmObj = GoogleCloudMessaging
                                .getInstance(applicationContext);
                    }
                    regId = gcmObj
                            .register(ApplicationConstants.GOOGLE_PROJ_ID);
                    msg = "Registration ID :" + regId;
                    prefs2 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor edit2 = prefs2.edit();
                    edit2.putString("reg_id",""+regId);
                    edit2.commit();
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }

                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                if (!TextUtils.isEmpty(regId)) {
                    storeRegIdinSharedPref(applicationContext, regId, emailID);

                } else {
                    Toast.makeText(MainActivity.this, "Please check your internet connection .....", Toast.LENGTH_SHORT).show();

                }
            }
        }.execute(null, null, null);
    }

    private void storeRegIdinSharedPref(Context context, String regId,
                                        String emailID) {
        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putString(EMAIL_ID, emailID);
        editor.commit();
        storeRegIdinServer(regId);

    }

    private void storeRegIdinServer(String regId) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            try {

                String permission = "android.permission.READ_PHONE_STATE";

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_PHONE_STATE);

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.READ_PHONE_STATE)) {
                        Toast.makeText(getApplicationContext(), "Please allow phone permission to receive push notifications !!!", Toast.LENGTH_LONG).show();
                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.


                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.READ_PHONE_STATE},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                        TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        String dd = mngr.getDeviceId();

                        parsingfornotification.parsing(MainActivity.this, "" + dd, regId);
                        //Toast.makeText(MainActivity.this, "not mm", Toast.LENGTH_SHORT).show();
                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }
                else {
                    TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    String dd = mngr.getDeviceId();

                    parsingfornotification.parsing(MainActivity.this, ""+dd, regId);
                }
            } catch (Exception e) {
                Log.e("ghghghhg", "" + e);
            }
        }
        else {
            TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            String dd = mngr.getDeviceId();

            parsingfornotification.parsing(MainActivity.this, ""+dd, regId);
        }

        // parsingfornotification.parsing(MainActivity.this,regId,dd);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    String dd = mngr.getDeviceId();

                    parsingfornotification.parsing(MainActivity.this, ""+dd, regId);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
//                Toast.makeText(
//                        applicationContext,
//                        "This device doesn't support Play services, App will not get notifications",
//                        Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        } else {
            registerInBackground("tyretimes");
//            Toast.makeText(
//                    applicationContext,
//                    "This device supports Play services, App will work normally",
//                    Toast.LENGTH_LONG).show();
        }
        return true;
    }




    @Override
    protected void onResume() {
        super.onResume();
        Notificationclass.activityopen= true;
        View view = MainActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

//    public  void showlogoutdialog() {
//
//        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        Window window=dialog.getWindow();
//        dialog.setCancelable(false);
//        window.setGravity(Gravity.CENTER);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setContentView(R.layout.dialogforlogout);
//        TextView yes = (TextView)dialog.findViewById(R.id.yes);
//        TextView no = (TextView)dialog.findViewById(R.id.no);
//
//        yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//
//                SharedPreferences.Editor edit2 = prefs.edit();
//                edit2.putBoolean("pref_previously_started", Boolean.FALSE);
//                edit2.commit();
//                SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//                SharedPreferences.Editor edit22 = prefs2.edit();
//                edit22.putString("Username", "Login");
//                edit22.commit();
//                MainActivity.loginheading.setText("" + prefs.getString("Username", "Login"));
//                if(  MainActivity.loginheading.getText().toString().equals("Login"))
//                {
//                    Typeface font = Typeface.createFromAsset(MainActivity.this.getAssets(), "fonts/JosefinSans-SemiBold.ttf");
//                    MainActivity.loginheading.setTextColor(Color.parseColor("#474747"));
//                    MainActivity.loginheading.setTypeface(font);
//                    MainActivity.login.setBackgroundColor(Color.parseColor("#ffffff"));
//                    MainActivity.dp.setVisibility(View.GONE);
//                    MainActivity.logoutll.setVisibility(View.GONE);
//                }
//
//                dialog.dismiss();
//            }
//        });
//
//        no.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }


}
