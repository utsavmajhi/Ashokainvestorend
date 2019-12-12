package com.example.ashokainvestorend.investedpoolrecyclerdata;

public class invespoolitems {

    private String poolid;
    private String engineerid;
    private String poolname;
    private String totalinvests;
    private String location;
    private String Report;
    private String prevprofit;
    public String getPoolid() {
        return poolid;
    }

    public void setPoolid(String poolid) {
        this.poolid = poolid;
    }

    public String getEngineerid() {
        return engineerid;
    }

    public void setEngineerid(String engineerid) {
        this.engineerid = engineerid;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public String getTotalinvests() {
        return totalinvests;
    }

    public void setTotalinvests(String totalinvests) {
        this.totalinvests = totalinvests;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }

    public String getPrevprofit() {
        return prevprofit;
    }

    public void setPrevprofit(String prevprofit) {
        this.prevprofit = prevprofit;
    }

    public invespoolitems(String poolid, String engineerid, String poolname, String totalinvests, String location, String report, String prevprofit) {
        this.poolid = poolid;
        this.engineerid = engineerid;
        this.poolname = poolname;
        this.totalinvests = totalinvests;
        this.location = location;
        Report = report;
        this.prevprofit = prevprofit;
    }



}
