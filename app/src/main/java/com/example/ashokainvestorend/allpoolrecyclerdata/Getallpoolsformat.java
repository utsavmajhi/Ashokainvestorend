
package com.example.ashokainvestorend.allpoolrecyclerdata;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getallpoolsformat {

    @SerializedName("pools")
    @Expose
    private List<Pool> pools = null;

    public List<Pool> getPools() {
        return pools;
    }

    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }

}
