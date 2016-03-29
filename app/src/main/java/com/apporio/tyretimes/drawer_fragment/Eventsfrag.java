package com.apporio.tyretimes.drawer_fragment;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.tyretimes.adapter.Eventsadapter;
import com.apporio.tyretimes.adapter.Exhibitionadapter;

import java.util.ArrayList;
import java.util.List;

import com.apporio.tyretimes.R;
import com.apporio.tyretimes.settergetter.Innerpagefraglist;
import com.apporio.tyretimes.settergetter.Innerpagefraglist1;
import com.apporio.tyretimes.settergetter.Specificfraglistsettergetter;
import com.apporio.tyretimes.settergetter.Specificfraglistsettergetter1;
import com.apporio.tyretimes.singleton.VolleySingleton;
import com.apporio.tyretimes.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import views.ProgressWheel;

/**
 * Created by saifi45 on 1/23/2016.
 */
public class Eventsfrag extends Fragment {

    FrameLayout news, realtytrends, banking, blog, videos;
    LinearLayout newscolor, newsgrey, realtytrendscolor, realtytrendsgrey, bankingcolor, bankinggrey, blogcolor, bloggrey, videocolor, videogrey;
    public static ListView lv;
    public static String pos;
    public static ProgressWheel pb;
    public static Eventsadapter adp;
    public static ArrayList<String> event_id = new ArrayList<String>();
    public static ArrayList<String> title11 = new ArrayList<String>();
    public static ArrayList<String> description = new ArrayList<String>();
    public static ArrayList<String> image11 = new ArrayList<String>();

        public static RequestQueue queue;
    public static StringRequest sr1, sr2;
    public static List<Innerpagefraglist1> data_list1;
//    public static ArrayList<String> module_id = new ArrayList<String>();
//    public static ArrayList<String> moduletilte = new ArrayList<String>();
//    public static ArrayList<String> moduledate = new ArrayList<String>();
//    public static ArrayList<String> moduledescp = new ArrayList<String>();
//    public static ArrayList<String> moduleimg = new ArrayList<String>();
//    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();
    public static SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exhibition, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.activity_main_swipe_refresh_layout);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        lv = (ListView) v.findViewById(R.id.listView22);
        pb = (ProgressWheel) v.findViewById(R.id.pb);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green, R.color.orange);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                            parsing(getActivity(), s1, pos);
//                            parsingforphotos(getActivity(), s44);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        return v;
    }

    public static Eventsfrag newInstance(FragmentActivity activity, String position) {
        Eventsfrag f = new Eventsfrag();
        Bundle b = new Bundle();
        b.putString("msg22", position);
        //Log.e("possss",""+position);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getActivity().getCurrentFocus();
        parsing(getActivity(), "", pos);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
//        Toast.makeText(getActivity(), "chlafragres", Toast.LENGTH_SHORT).show();
        // mainfragmentwork();
    }

    public static void parsing(final Context activity, String s1, String s2) {
//
//            s44=s2;
//
                String locationurl2 = Api_s.events;
                locationurl2 = locationurl2.replace(" ", "%20");
                Log.e("url", "" + locationurl2);

                sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
//
                        title11.clear();
                        event_id.clear();
                        image11.clear();
                        description.clear();

                        Eventsfrag.pb.setVisibility(View.GONE);
                        Eventsfrag.lv.setVisibility(View.VISIBLE);

                        try {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            Specificfraglistsettergetter1 received2 = new Specificfraglistsettergetter1();
                            received2 = gson.fromJson(response, Specificfraglistsettergetter1.class);


                            data_list1 = received2.innerpagefraglist1;
                            for (int i = 0; i < data_list1.size(); i++) {
                                title11.add(data_list1.get(i).title11);
                                event_id.add(data_list1.get(i).event_id);
                                image11.add(data_list1.get(i).image11);
                                description.add(data_list1.get(i).description);

                            }

                            //Log.e("title", SplashActivity.moduletilte + "");
//                            //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
//
//
//
        lv.setAdapter(new Eventsadapter(activity, title11,event_id,image11,description));
//                            //adp.notifyDataSetChanged();
//
//
        Eventsfrag.lv.setFocusable(false);


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
                   Eventsfrag.pb.setVisibility(View.VISIBLE);
            Eventsfrag.lv.setVisibility(View.GONE);
//
    }


}

