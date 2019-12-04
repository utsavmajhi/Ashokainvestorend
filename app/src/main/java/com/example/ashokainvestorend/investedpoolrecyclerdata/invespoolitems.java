package com.example.ashokainvestorend.investedpoolrecyclerdata;

public class invespoolitems {

    private String mImageurl;

    public invespoolitems(String mImageurl, String poolname, String area, String location, String report, String profit) {
        this.mImageurl = mImageurl;
        this.poolname = poolname;
        this.area = area;
        this.location = location;
        Report = report;
        this.profit = profit;
    }

    private String poolname;
    private String area;
    private String location;
    private String Report;

    public String getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(String mImageurl) {
        this.mImageurl = mImageurl;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    private String profit;
}
