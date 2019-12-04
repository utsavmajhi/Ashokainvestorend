package com.example.ashokainvestorend.allpoolrecyclerdata;

public class poolitems {
    private String mImageurl;
    private String poolname;
    private String area;
    private String location;

    public poolitems(String mImageurl, String poolname, String area, String location, String report, String profit) {
        this.mImageurl = mImageurl;
        this.poolname = poolname;
        this.area = area;
        this.location = location;
        this.Report = report;
        this.profit = profit;
    }

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
