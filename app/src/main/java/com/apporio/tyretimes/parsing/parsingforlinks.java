package com.apporio.tyretimes.parsing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.tyretimes.Logregactivity;
import com.apporio.tyretimes.MainActivity;
import com.apporio.tyretimes.Signupactivity;
import com.apporio.tyretimes.SplashActivity;
import com.apporio.tyretimes.settergetter.Checker_link_settergetter;
import com.apporio.tyretimes.settergetter.Loginsettergetter;
import com.apporio.tyretimes.settergetter.Outer_links_setter_getter;
import com.apporio.tyretimes.settergetter.Signupsettergetter;
import com.apporio.tyretimes.singleton.VolleySingleton;
import com.apporio.tyretimes.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by saifi45 on 2/12/2016.
 */
public class parsingforlinks {
    public static RequestQueue queue;
    public static StringRequest sr2;


    public static void parsing(final Context activity){



        String locationurl2 = Api_s.linksfor;
        locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Checker_link_settergetter received2 = new Checker_link_settergetter();
                    received2 = gson.fromJson(response, Checker_link_settergetter.class);

                    String result1= received2.result;
                    Log.e("resss", "" + result1);



                    if(result1.equals("1")){
                        Outer_links_setter_getter received3 = new Outer_links_setter_getter();
                        received3 = gson.fromJson(response, Outer_links_setter_getter.class);
                        String trc =received3.innerlink.tyre_retail_caddy;
                        String bocmt = received3.innerlink.buy_on_change;
                        String sharethisapp = received3.innerlink.share_this_app;
                        String admin_email =received3.innerlink.admin_email;
                        String terms_of_use = received3.innerlink.terms_of_use;
                        String privacypolicy = received3.innerlink.privacy_policy;
                        String about_us = received3.innerlink.about_us;




                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                        SharedPreferences.Editor edit2 = prefs.edit();


                        edit2.putString("tyre_retail_caddy", "" + trc);
                        edit2.putString("buyoncmt", "" + bocmt);
                        edit2.putString("sharethisapp", "" + sharethisapp);
                        edit2.putString("adminemail", "" + admin_email);
                        edit2.putString("tou", "" + terms_of_use);
                        edit2.putString("pp", "" + privacypolicy);
                        edit2.putString("aboutus", "" + about_us);

                        Log.e("linkss",""+trc+" "+bocmt+" "+sharethisapp+" "+admin_email+" "+terms_of_use+" "+privacypolicy+" "+about_us);
                        edit2.commit();
                        Intent in = new Intent(activity, MainActivity.class);
                        activity.startActivity(in);
                        SplashActivity.spl.finish();

                    }

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Log.e("exception", "" + e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr2);



    }
}
