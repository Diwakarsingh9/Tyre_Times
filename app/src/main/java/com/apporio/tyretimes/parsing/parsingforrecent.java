package com.apporio.tyretimes.parsing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.tyretimes.ListInnerActivity;
import com.apporio.tyretimes.R;
import com.apporio.tyretimes.settergetter.innerrecent;
import com.apporio.tyretimes.settergetter.recentsettergetter;
import com.apporio.tyretimes.settergetter.recentsettergetter2;
import com.apporio.tyretimes.singleton.VolleySingleton;
import com.apporio.tyretimes.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by saifi45 on 11/18/2015.
 */
public class parsingforrecent {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<innerrecent> data_list1;
    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();
    public static ArrayList<String> moduleet = new ArrayList<String>();
    public static ArrayList<String> modulesrc = new ArrayList<String>();
    public static ArrayList<String> modulesrclink = new ArrayList<String>();
    public static Context ctc;
    public static String s22;




    public static void parsing(final Context activity,String s1, String s2) {

            ctc = activity;
        s22=s2;
        queue = VolleySingleton.getInstance(activity).getRequestQueue();

        Log.e("string", "" + s1 +" "+s2);
            String locationurl2 = Api_s.recentnews.concat(s1).concat(Api_s.recentnews2).concat(s2);
            locationurl2 = locationurl2.replace(" ", "%20");
            locationurl2 = locationurl2.replace("Tyres","Tyre");
            Log.e("url", "" + locationurl2);

            sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
//
                    module_id.clear();
                    moduletilte.clear();
                    moduledate.clear();
                    moduledescp.clear();
                    moduleimg.clear();
                    moduleinnertitle.clear();
                    moduleet.clear();
                    modulesrc.clear();
                    modulesrclink.clear();
                   ListInnerActivity.pb.setVisibility(View.GONE);
                    ListInnerActivity.llforlist.setVisibility(View.VISIBLE);

                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        recentsettergetter2 received2 = new recentsettergetter2();
                        received2 = gson.fromJson(response, recentsettergetter2.class);

                        String result = received2.result;
                        if(result.equals("0")){
                        ListInnerActivity.relatedsearch.setVisibility(View.GONE);
                            ListInnerActivity.v111.setVisibility(View.GONE);
                        }
                        else {

                            recentsettergetter received = new recentsettergetter();
                            received = gson.fromJson(response, recentsettergetter.class);
                            data_list1 = received.innerrecents;
                            for (int i = 0; i < data_list1.size(); i++) {
                                module_id.add(data_list1.get(i).module_id);
                                moduletilte.add(data_list1.get(i).module_name);
                                moduledate.add(data_list1.get(i).module_date);
                                moduledescp.add(data_list1.get(i).module_desc_new);
                                moduleimg.add(data_list1.get(i).module_img);
                                moduleet.add(data_list1.get(i).module_state);
                                modulesrc.add(data_list1.get(i).module_source);
                                moduleinnertitle.add(data_list1.get(i).module_excerpt);
                                modulesrclink.add(data_list1.get(i).source_link);
                            }

                            //Log.e("title", SplashActivity.moduletilte + "");
                            //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < 3; i++) {
                                if (data_list1.size() > 3) {
                                    ListInnerActivity.relatedsearch.setVisibility(View.VISIBLE);
                                    ListInnerActivity.v111.setVisibility(View.VISIBLE);
                                    ListInnerActivity.llforrecnt.setVisibility(View.VISIBLE);
                                    ListInnerActivity.llforlist.addView(ordersview(R.layout.itemforrecent, i));
                                }
                                else {
                                    ListInnerActivity.llforrecnt.setVisibility(View.GONE);
                                    ListInnerActivity.relatedsearch.setVisibility(View.GONE);
                                    ListInnerActivity.v111.setVisibility(View.GONE);
                                }
                            }
                        }
                        //adp.notifyDataSetChanged();


//                        Newsfragment.lv.setFocusable(false);


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
            ListInnerActivity.pb.setVisibility(View.VISIBLE);
            ListInnerActivity.llforlist.setVisibility(View.GONE);

        }

    public static View ordersview(int layout_name, int i) {

        LayoutInflater layoutInflater =
                (LayoutInflater)ctc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final TextView title11 = (TextView)addView.findViewById(R.id.title2234);
        final  TextView date = (TextView)addView.findViewById(R.id.date);
        final  TextView city = (TextView)addView.findViewById(R.id.city);
        final   TextView source1 = (TextView)addView.findViewById(R.id.srce);
        final  TextView descp = (TextView)addView.findViewById(R.id.descp);
        final  ImageView img = (ImageView)addView.findViewById(R.id.img);
        addView.setTag(i);
    addView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         int position = (int) addView.getTag();
            Intent in = new Intent(ctc,ListInnerActivity.class);
            in.putExtra("title", s22);
            in.putExtra("head", moduletilte.get(position));
            in.putExtra("date", moduledate.get(position));
            in.putExtra("descp", moduledescp.get(position));
            in.putExtra("img", moduleimg.get(position));
            in.putExtra("src", moduleet.get(position) );
            in.putExtra("plc",modulesrc.get(position));
            in.putExtra("id", module_id.get(position));
            in.putExtra("src_link", modulesrclink.get(position));
           // Toast.makeText(ctc, ""+s22+module_id.get(position), Toast.LENGTH_SHORT).show();
            ctc.startActivity(in);
            ListInnerActivity.lis.finish();



        }
    });
        title11.setText(""+ Html.fromHtml("" + moduletilte.get(i)).toString());
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String inputDateStr=""+moduledate.get(i);
        Date dateaa = null;
        try {
            dateaa = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(dateaa);
        date.setText("" + outputDateStr);
       // date.setText(""+moduledate.get(i));
       descp.setText(""+Html.fromHtml("" + moduledescp.get(i)).toString());
       city.setText(""+moduleet.get(i));
        source1.setText(""+modulesrc.get(i));
        //il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img.get(i), viewHolder.img);
        Picasso.with(ctc)
                .load(""+Api_s.imageurl + moduleimg.get(i))
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(img);

        return addView ;
    }


    }


