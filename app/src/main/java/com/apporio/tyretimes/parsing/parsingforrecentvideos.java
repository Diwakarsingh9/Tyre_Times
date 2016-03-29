package com.apporio.tyretimes.parsing;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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
import com.apporio.tyretimes.Photoinneractivity;
import com.apporio.tyretimes.R;
import com.apporio.tyretimes.Try;
import com.apporio.tyretimes.settergetter.Innervideosgallery;
import com.apporio.tyretimes.settergetter.innerrecent;
import com.apporio.tyretimes.settergetter.recentsettergetter;
import com.apporio.tyretimes.settergetter.recentsettergetter2;
import com.apporio.tyretimes.settergetter.video_gallery_outter_setter;
import com.apporio.tyretimes.singleton.VolleySingleton;
import com.apporio.tyretimes.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/7/2015.
 */
public class parsingforrecentvideos {
    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Innervideosgallery> data_list1;
    public static ArrayList<String> decrp = new ArrayList<String>();

    public static ArrayList<String> video_date = new ArrayList<String>();
    public static ArrayList<String> video_key = new ArrayList<String>();
    public static ArrayList<String> gallery_id = new ArrayList<String>();
    public static ArrayList<String> category = new ArrayList<String>();
    public static ArrayList<String> title1 = new ArrayList<String>();
    public static ArrayList<String> image1 = new ArrayList<String>();
    public static Context ctc;
    public static String s22;



    public static void parsing(final Context activity, String s2) {

        ctc = activity;
        s22=s2;
        queue = VolleySingleton.getInstance(activity).getRequestQueue();


        String locationurl2 = "http://www.tyretimes.com/app/api/gallery_video.php?video_id=".concat(""+s2);
        locationurl2 = locationurl2.replace(" ", "%20");
        locationurl2 = locationurl2.replace("VIDEOS","VIDEO");
        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//
                gallery_id.clear();
                category.clear();
                image1.clear();
                title1.clear();
                video_date.clear();
                video_key.clear();
                decrp.clear();
                Photoinneractivity.pb.setVisibility(View.GONE);
                Photoinneractivity.llforlist.setVisibility(View.VISIBLE);

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    recentsettergetter2 received2 = new recentsettergetter2();
                    received2 = gson.fromJson(response, recentsettergetter2.class);

                    String result = received2.result;
                    if(result.equals("0")){
                        Photoinneractivity.relatedsearch.setVisibility(View.GONE);
                        Photoinneractivity.v111.setVisibility(View.GONE);
                    }
                    else {
                        video_gallery_outter_setter received23 = new video_gallery_outter_setter();
                        received23 = gson.fromJson(response, video_gallery_outter_setter.class);


                        data_list1 = received23.innervideosgallery;
                        for (int i = 0; i < data_list1.size(); i++) {
                            gallery_id.add(data_list1.get(i).gallery_id);
                            category.add(data_list1.get(i).category);
                            image1.add(data_list1.get(i).them_image);
                            title1.add(data_list1.get(i).titless);
                            video_key.add(data_list1.get(i).video_key);
                            video_date.add(data_list1.get(i).video_date);
                            decrp.add(data_list1.get(i).description);

                        }


                        //Log.e("title", SplashActivity.moduletilte + "");
                      //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < 3; i++) {
                            if (data_list1.size() > 3) {
                                Photoinneractivity.relatedsearch.setVisibility(View.VISIBLE);
                                Photoinneractivity.v111.setVisibility(View.VISIBLE);
                                Photoinneractivity.llforrecnt.setVisibility(View.VISIBLE);
                                Photoinneractivity.llforlist.addView(ordersview(R.layout.itemforrecent, i));
                            }
                            else {
                                Photoinneractivity.llforrecnt.setVisibility(View.GONE);
                                Photoinneractivity.relatedsearch.setVisibility(View.GONE);
                                Photoinneractivity.v111.setVisibility(View.GONE);
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
        Photoinneractivity.pb.setVisibility(View.VISIBLE);
        Photoinneractivity.llforlist.setVisibility(View.GONE);

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
        final ImageView img = (ImageView)addView.findViewById(R.id.img);
        addView.setTag(i);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photoinneractivity.lis.finish();
                int position = (int) addView.getTag();

                Intent in = new Intent(Photoinneractivity.lis,Try.class);
                in.putExtra("title", "Videos");
                in.putExtra("head", title1.get(position));
                in.putExtra("key", video_key.get(position));
                in.putExtra("date", video_date.get(position));
                in.putExtra("descp", decrp.get(position));
                in.putExtra("img", image1.get(position));
                in.putExtra("src","");
                in.putExtra("plc","");
                in.putExtra("src_link","");
                in.putExtra("id", gallery_id.get(position));

//                Toast.makeText(ctc, ""+"  "+moduledescp, Toast.LENGTH_SHORT).show();
                ctc.startActivity(in);


               // Toast.makeText(ctc, ""+position + moduleinnertitle.get(position) + moduledescp.get(position) , Toast.LENGTH_SHORT).show();
            }
        });
        title11.setText(""+ Html.fromHtml("" + title1.get(i)).toString());
        date.setText(""+video_date.get(i));
        descp.setText(""+Html.fromHtml("" + decrp.get(i)).toString());
        city.setText("");
        source1.setText("");
        //il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img.get(i), viewHolder.img);
        Picasso.with(ctc)
                .load(""+ image1.get(i))
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(img);

        return addView ;
    }
}
