package com.apporio.tyretimes.settergetter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saifi45 on 2/12/2016.
 */
public class Outer_links_setter_getter {
    @SerializedName("result")
    public String result;



    @SerializedName("url")
    public Innerlink innerlink = new Innerlink();

}
