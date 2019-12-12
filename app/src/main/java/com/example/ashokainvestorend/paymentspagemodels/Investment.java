
package com.example.ashokainvestorend.paymentspagemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Investment {

    @SerializedName("timestamp")
    @Expose
    private double timestamp;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("poolId")
    @Expose
    private String poolId;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("investorId")
    @Expose
    private String investorId;
    @SerializedName("__v")
    @Expose
    private double v;

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

}
