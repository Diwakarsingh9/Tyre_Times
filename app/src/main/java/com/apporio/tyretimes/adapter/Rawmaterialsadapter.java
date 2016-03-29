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

import com.apporio.tyretimes.Horizontal_ListView.HorizontalListView;
import com.apporio.tyretimes.ListInnerActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.apporio.tyretimes.Photoinneractivity;
import com.apporio.tyretimes.Photoinneractivity2;
import com.apporio.tyretimes.R;
import com.apporio.tyretimes.RawMaterialsfragment;
import com.apporio.tyretimes.urlapi.Api_s;
import com.squareup.picasso.Picasso;

/**
 * Created by saifi45 on 1/22/2016.
 */
public class Rawmaterialsadapter extends BaseAdapter {
    public  static ArrayList<String> title11;
    public  static ArrayList<String> date;
    public  static ArrayList<String> dscvp;
    public  static ArrayList<String> source2;
    public  static ArrayList<String> city2;
    public  static ArrayList<String> img;
    public  static ArrayList<String> imgtitle;
    public  static ArrayList<String> c;
    public  static ArrayList<String> video_link;
    public  static ArrayList<String> src_link;
    LayoutInflater inflater;
    static Context ctc2;
    String profilename;
//    static String[] imgtitle ={"Image 1","Image 2","Image 3","Image 4","Image 5","Image 6","Image 7","Image 8","Image 9","Image 10","Image 11"};
//    static int[] c=new int []{R.drawable.flip2,R.drawable.bb,R.drawable.dd,
//            R.drawable.bgg,R.drawable.logo,R.drawable.flip1,
//            R.drawable.bgg,R.drawable.flip1,R.drawable.reall2,R.drawable.flip2,R.drawable.flip3,};




    public Rawmaterialsadapter(Context activity, ArrayList<String> stringArrayList, ArrayList<String> strings, ArrayList<String> moduletilte, ArrayList<String> moduledate, ArrayList<String> moduledescp, ArrayList<String> moduleimg, ArrayList<String> image1, ArrayList<String> title1, ArrayList<String> modulesrc_link, ArrayList<String> video_key) {

        Log.e("ffff", "adapter method chla");
        this.ctc2=activity;

        this.title11=moduletilte;
        this.date=moduledate;
        this.dscvp=moduledescp;
        this.img=moduleimg;
        this.source2=stringArrayList;
        this.city2=strings;
        this.imgtitle = image1;
        this.c = title1;
        this.video_link = video_key;
        this.src_link = modulesrc_link;
        inflater = LayoutInflater.from(this.ctc2);
    }



    @Override
    public int getCount() {




        return title11.size();
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
        public     ImageView img,minus;
        public     TextView descp;
        public LinearLayout llforlist,llfortitle;
        public HorizontalListView hlv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
//        if(position==6) {
//            convertView = inflater.inflate(R.layout.itemlayoutforhorizontalview, null);
//            holder = new Holder();
//            convertView.setTag(holder);
//
//            holder.llforlist=(LinearLayout)convertView.findViewById(R.id.listhori);
//            holder.llfortitle=(LinearLayout)convertView.findViewById(R.id.llfortitle);
//            if(imgtitle.size()==0){
//                holder.llfortitle.setVisibility(View.GONE);
//            }
//            for (int i = 0; i < imgtitle.size(); i++) {
//
//                holder.llforlist.addView(ordersview(R.layout.item_coverflow, i));
//            }
//
//
//        }
//        else  {



//            if(position<6) {
                convertView = inflater.inflate(R.layout.recyclerview_item2, null);
                holder = new Holder();
                convertView.setTag(holder);
                holder.title11 = (TextView) convertView.findViewById(R.id.title2234);
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.city = (TextView) convertView.findViewById(R.id.city);
                holder.source1 = (TextView) convertView.findViewById(R.id.srce);
                holder.descp = (TextView) convertView.findViewById(R.id.descp);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.title11.setText("" + Html.fromHtml("" + title11.get(position)).toString());
                SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
                String inputDateStr=""+date.get(position);
                Date dateaa = null;
                String outputDateStr="";
                try {
                    dateaa = inputFormat.parse(inputDateStr);
                    outputDateStr = outputFormat.format(dateaa);
                    Log.e("date",""+dateaa+" "+outputDateStr);
                } catch (ParseException e) {
                    Log.e("", "" + e  );
                }

                holder. date.setText("" + outputDateStr);
                holder.descp.setText("" + Html.fromHtml("" + dscvp.get(position)).toString());
                holder.city.setText("" + source2.get(position));
                holder.source1.setText("" + city2.get(position));
                Picasso.with(ctc2)
                        .load(""+ Api_s.imageurl + img.get(position))
                        .placeholder(R.drawable.stub) // optional
                        .error(R.drawable.stub)         // optional
                        .into(holder.img);
                holder.img.setTag(position);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = (int) holder.img.getTag();
                        Intent in = new Intent(ctc2, ListInnerActivity.class);

                        in.putExtra("title", "Raw Material");
                        in.putExtra("head", title11.get(pos));
                        in.putExtra("date", date.get(pos));
                        in.putExtra("descp", dscvp.get(pos));
                        in.putExtra("img", img.get(pos));
                        in.putExtra("src", source2.get(pos));
                        in.putExtra("plc", city2.get(pos));
                        in.putExtra("id", RawMaterialsfragment.module_id.get(pos));
                        in.putExtra("src_link",src_link.get(pos));
                        ctc2.startActivity(in);


                    }
                });
//            }
//
//            else{
//                convertView = inflater.inflate(R.layout.recyclerview_item2, null);
//                holder = new Holder();
//                convertView.setTag(holder);
//                holder.title11 = (TextView) convertView.findViewById(R.id.title2234);
//                holder.date = (TextView) convertView.findViewById(R.id.date);
//                holder.city = (TextView) convertView.findViewById(R.id.city);
//                holder.source1 = (TextView) convertView.findViewById(R.id.srce);
//                holder.descp = (TextView) convertView.findViewById(R.id.descp);
//                holder.img = (ImageView) convertView.findViewById(R.id.img);
//                holder.title11.setText("" + Html.fromHtml("" + title11.get(position-1)).toString());
//                SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
//                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
//                String inputDateStr=""+date.get(position-1);
//                Date dateaa = null;
//                String outputDateStr="";
//                try {
//                    dateaa = inputFormat.parse(inputDateStr);
//                    outputDateStr = outputFormat.format(dateaa);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                holder. date.setText("" + outputDateStr);
//                // holder.date.setText("" + date.get(position-1));
//                holder.descp.setText("" + Html.fromHtml("" + dscvp.get(position - 1)).toString());
//                holder.city.setText("" + source2.get(position - 1));
//                holder.source1.setText("" + city2.get(position - 1));
//                Picasso.with(ctc2)
//                        .load(""+Api_s.imageurl + img.get(position-1))
//                        .placeholder(R.drawable.stub) // optional
//                        .error(R.drawable.stub)         // optional
//                        .into(holder.img);
////
//                holder.img.setTag(position-1);
//                convertView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int pos = (int) holder.img.getTag();
//                        Intent in = new Intent(ctc2, ListInnerActivity.class);
//
//                        in.putExtra("title", "Raw Material");
//                        in.putExtra("head", title11.get(pos));
//                        in.putExtra("date", date.get(pos));
//                        in.putExtra("descp", dscvp.get(pos));
//                        in.putExtra("img", img.get(pos));
//                        in.putExtra("src", source2.get(pos));
//                        in.putExtra("plc", city2.get(pos));
//                         in.putExtra("id", RawMaterialsfragment.module_id.get(pos));
//                        in.putExtra("src_link",src_link.get(pos));
//                        ctc2.startActivity(in);
//
//
//                    }
//                });
//            }
//        }


        return convertView;
    }
//    public static View ordersview(int layout_name, int i) {
//
//        LayoutInflater layoutInflater =
//                (LayoutInflater)ctc2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        final View addView = layoutInflater.inflate(layout_name, null);
//        final  TextView txt = (TextView)addView.findViewById(R.id.text33);
//        final  ImageView img = (ImageView)addView.findViewById(R.id.imagess);
////        img.setImageResource(c[i]);
//        if(c.size()==0){}
//        else {
//            //txt.setText("" + "video");
//            txt.setText("" + c.get(i));
//        }
//        if(imgtitle.size()==0){}
//        else {
//            Picasso.with(ctc2)
//                    .load(""+ imgtitle.get(i))
//                    .placeholder(R.drawable.stub) // optional
//                    .error(R.drawable.stub)         // optional
//                    .into(img);
//        }
//        addView.setTag(i);
//        addView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int pos= (int) addView.getTag();
//                Intent in = new Intent(ctc2,Photoinneractivity2.class);
//                in.putExtra("key", ""+video_link.get(pos));
//                ctc2.startActivity(in);
//            }
//        });
//        return addView ;
//    }
}










