
package com.example.ashokainvestorend.loginmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getlogindataformat {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
