package com.example.ashokainvestorend.particulartransactionmodels;

public class transactionitem {

    public String timedate;
    public String amount;

    public String getTimedate() {
        return timedate;
    }

    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public transactionitem(String timedate, String amount) {
        this.timedate = timedate;
        this.amount = amount;
    }
}
