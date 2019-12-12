package com.example.ashokainvestorend;

public class alltransactionitem {


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

    public alltransactionitem(String timedate, String amount) {
        this.timedate = timedate;
        this.amount = amount;
    }
}
