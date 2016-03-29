package com.apporio.tyretimes.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 2/2/2016.
 */
public class Specificfraglistsettergetter1 {

    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public List<Innerpagefraglist1> innerpagefraglist1 = new ArrayList<Innerpagefraglist1>();
}
