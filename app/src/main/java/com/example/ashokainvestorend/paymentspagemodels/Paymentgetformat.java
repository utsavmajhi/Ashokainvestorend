
package com.example.ashokainvestorend.paymentspagemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paymentgetformat {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("investment")
    @Expose
    private Investment investment;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

}
