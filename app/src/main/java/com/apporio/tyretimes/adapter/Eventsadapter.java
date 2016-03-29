package com.apporio.tyretimes.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.apporio.tyretimes.Horizontal_ListView.HorizontalListView;
import com.apporio.tyretimes.ListInnerActivity;
import com.apporio.tyretimes.R;
import com.apporio.tyretimes.urlapi.Api_s;
import com.squareup.picasso.Picasso;

/**
 * Created by saifi45 on 1/23/2016.
 */
public class Eventsadapter extends BaseAdapter {
    public  static ArrayList<String> title11;
    public  static ArrayList<String> eventid;
    public  static ArrayList<String> dscvp;

    public  static ArrayList<String> imgtitle;

    LayoutInflater inflater;
    static Context ctc2;
    String profilename;
//    static String[] imgtitle ={"Image 1","Image 2","Image 3","Image 4","Image 5","Image 6","Image 7","Image 8","Image 9","Image 10","Image 11"};
//    static int[] c=new int []{R.drawable.flip2,R.drawable.bb,R.drawable.dd,
//            R.drawable.bgg,R.drawable.logo,R.drawable.flip1,
//            R.drawable.bgg,R.drawable.flip1,R.drawable.reall2,R.drawable.flip2,R.drawable.flip3,};




    public Eventsadapter(Context activity, ArrayList<String> title11, ArrayList<String> event_id, ArrayList<String> image11, ArrayList<String> description) {

        Log.e("ffff", "adapter method chla");
        this.ctc2=activity;

        this.title11=title11;
        this.eventid=event_id;
        this.dscvp=description;

        this.imgtitle = image11;

        inflater = LayoutInflater.from(this.ctc2);
    }



    @Override
    public int getCount() {

        return eventid.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView title11,date,city,source1;
        public ImageView img,minus;
        public     TextView descp;
        public LinearLayout llforlist;
        public HorizontalListView hlv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;


                convertView = inflater.inflate(R.layout.eventsitem, null);
                holder = new Holder();
                convertView.setTag(holder);
                holder.title11 = (TextView) convertView.findViewById(R.id.title2234);


                holder.descp = (TextView) convertView.findViewById(R.id.descp);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.title11.setText("" + Html.fromHtml("" + title11.get(position)).toString());

                holder.descp.setText("" + Html.fromHtml("" + dscvp.get(position)).toString());

                //il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img.get(i), viewHolder.img);
                Picasso.with(ctc2)
                        .load(""+ Api_s.imageurl + imgtitle.get(position))
                        .placeholder(R.drawable.stub) // optional
                        .error(R.drawable.stub)         // optional
                        .into(holder.img);
                holder.img.setTag(position);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = (int) holder.img.getTag();
                        Intent in = new Intent(ctc2, ListInnerActivity2.class);

                        in.putExtra("title", "Events");
                        in.putExtra("head", title11.get(pos));
                        in.putExtra("id", eventid.get(pos));
                        in.putExtra("descp", dscvp.get(pos));
                        in.putExtra("img", imgtitle.get(pos));

                        in.putExtra("date", "");

                        in.putExtra("src","");
                        in.putExtra("plc","");

                        in.putExtra("src_link","");
                        ctc2.startActivity(in);


                    }
                });




        return convertView;
    }

}
