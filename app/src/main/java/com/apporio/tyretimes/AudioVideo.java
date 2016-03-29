package com.apporio.tyretimes;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.tyretimes.adapter.AudioVideoAdapter;

import java.util.ArrayList;
import java.util.List;

import com.apporio.tyretimes.R;
import com.apporio.tyretimes.settergetter.Innerpagefraglist;
import com.apporio.tyretimes.settergetter.Innervideosgallery;
import com.apporio.tyretimes.settergetter.Specificfraglistsettergetter;
import com.apporio.tyretimes.settergetter.video_gallery_outter_setter;
import com.apporio.tyretimes.singleton.VolleySingleton;
import com.apporio.tyretimes.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import views.ProgressWheel;


public class AudioVideo extends Fragment {
    public static String s1="";
    public static String s44="";
    public  static  int i=0;
    public  static  int c=0;

    public  static  int y=0;
    public  static String d[]={"1","2","3","4","5"};
   // public  static NewsAdapter adp;
    public  static GridView gridView;
    public static String pos;
    public static ProgressWheel pb;
    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Innervideosgallery> data_list1;
    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> modulekey = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();
    public static ArrayList<String> moduleet = new ArrayList<String>();
    public static ArrayList<String> decrp = new ArrayList<String>();
    public static ArrayList<String> modulesrc_link = new ArrayList<String>();
    public static Fragment frag = null;
    public static ArrayList<String> video_date = new ArrayList<String>();
    public static ArrayList<String> video_key = new ArrayList<String>();
    public static ArrayList<String> gallery_id = new ArrayList<String>();
    public static ArrayList<String> category = new ArrayList<String>();
    public static ArrayList<String> title1 = new ArrayList<String>();
    public static ArrayList<String> image1 = new ArrayList<String>();
    public static SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.frament_av, null);
        gridView=(GridView)root.findViewById(R.id.gridview);
        pb=(ProgressWheel)root.findViewById(R.id.pb);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.activity_main_swipe_refresh_layout);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        pos=getArguments().getString("msg22");
        parsing(getActivity(), s1, pos);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green, R.color.orange);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       // parsing(getActivity(), s1, pos);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        //Toast.makeText(getActivity(), "create", Toast.LENGTH_SHORT).show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }




    public static AudioVideo newInstance(FragmentActivity activity,String position ) {
        AudioVideo f = new AudioVideo();
        Bundle b = new Bundle();
        b.putString("msg22", position);
        //Log.e("possss",""+position);

        f.setArguments(b);

        return f;
    }










    public static void parsing(final Context activity,String s1, String s2) {

//        s44=s2;
//
        String locationurl2 = Api_s.videosgaallery;
        locationurl2 = locationurl2.replace(" ", "%20");
        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                gallery_id.clear();
                category.clear();
                image1.clear();
                title1.clear();
                video_date.clear();
                video_key.clear();
                decrp.clear();
                AudioVideo.pb.setVisibility(View.GONE);
                AudioVideo.gridView.setVisibility(View.VISIBLE);

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    video_gallery_outter_setter received2 = new video_gallery_outter_setter();
                    received2 = gson.fromJson(response, video_gallery_outter_setter.class);


                    data_list1 = received2.innervideosgallery;
                    for (int i = 0; i < data_list1.size(); i++) {
                        gallery_id.add(data_list1.get(i).gallery_id);
                        category.add(data_list1.get(i).category);
                        image1.add(data_list1.get(i).them_image);
                        title1.add(data_list1.get(i).titless);
                        video_key.add(data_list1.get(i).video_key);
                        video_date.add(data_list1.get(i).video_date);
                        decrp.add(data_list1.get(i).description);

                    }
//
//                    Log.e("title", SplashActivity.moduletilte + "");
//                    //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
//


                    gridView.setAdapter(new AudioVideoAdapter(activity,gallery_id,category, image1, title1,
                            video_key, video_date,decrp));
                    //adp.notifyDataSetChanged();
//

                        AudioVideo.gridView.setFocusable(false);


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
        AudioVideo.pb.setVisibility(View.VISIBLE);
        AudioVideo.gridView.setVisibility(View.GONE);

    }

    @Override
    public void onResume() {
        super.onResume();

        //Toast.makeText(getActivity(), "chla resume", Toast.LENGTH_SHORT).show();
        parsing(getActivity(), s1, pos);

    }






}

