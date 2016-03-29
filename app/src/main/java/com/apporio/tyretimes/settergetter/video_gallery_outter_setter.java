package com.apporio.tyretimes.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 2/11/2016.
 */
public class video_gallery_outter_setter {


        @SerializedName("result")
        public String result;

        @SerializedName("msg")
        public List<Innervideosgallery> innervideosgallery = new ArrayList<Innervideosgallery>();

}
