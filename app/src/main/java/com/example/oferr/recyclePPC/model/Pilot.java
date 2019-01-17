package com.example.oferr.recyclePPC.model;

import com.google.gson.annotations.SerializedName;

public class Pilot {

    @SerializedName("id")
    private String id;
    @SerializedName("piEmail")
    private String piEmail;
    @SerializedName("piPw")
    private String piPw;
    @SerializedName("piFirstName")
    private String piFirstName;
    @SerializedName("piLastName")
    private String piLastName;
    @SerializedName("piPhone")
    private String piPhone;

    public Pilot(String id, String piEmail, String piPw, String piFirstName, String piLastName, String piPhone) {
        this.id = id;
        this.piEmail = piEmail;
        this.piPw = piPw;
        this.piFirstName = piFirstName;
        this.piLastName = piLastName;
        this.piPhone = piPhone;
    }

    public Pilot(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPiEmail() {
        return piEmail;
    }

    public void setPiEmail(String piEmail) {
        this.piEmail = piEmail;
    }

    public String getPiPw() {
        return piPw;
    }

    public void setPiPw(String piPw) {
        this.piPw = piPw;
    }

    public String getPiFirstName() {
        return piFirstName;
    }

    public void setPiFirstName(String piFirstName) {
        this.piFirstName = piFirstName;
    }

    public String getPiLastName() {
        return piLastName;
    }

    public void setPiLastName(String piLastName) {
        this.piLastName = piLastName;
    }

    public String getPiPhone() {
        return piPhone;
    }

    public void setPiPhone(String piPhone) {
        this.piPhone = piPhone;
    }

    // ---------------------

    public String getFullName(){
        String fn = getPiFirstName() + " " + getPiLastName();
        return  fn;
    }
}
