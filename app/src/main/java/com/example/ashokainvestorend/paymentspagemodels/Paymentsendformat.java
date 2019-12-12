
package com.example.ashokainvestorend.paymentspagemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paymentsendformat {

    @SerializedName("poolId")
    @Expose
    private String poolId;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Paymentsendformat(String poolId, Integer amount) {
        this.poolId = poolId;
        this.amount = amount;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
