package com.example.oferr.recyclePPC.model;

import com.google.gson.annotations.SerializedName;

public class Ppc {

    @SerializedName("id")
    private String id;
    @SerializedName("ppName")
    private String ppName;
    @SerializedName("ppManuf")
    private String ppManuf;
    @SerializedName("ppEnginType")
    private String ppEnginType;
    @SerializedName("ppEngHourStart")
    private String ppEngHourStart;
    @SerializedName("ppFuelQt")
    private String ppFuelQt;

    public Ppc(String id, String ppName, String ppManuf, String ppEnginType, String ppEngHourStart, String ppFuelQt) {
        this.id = id;
        this.ppName = ppName;
        this.ppManuf = ppManuf;
        this.ppEnginType = ppEnginType;
        this.ppEngHourStart = ppEngHourStart;
        this.ppFuelQt = ppFuelQt;
    }

    public Ppc (){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPpName() {
        return ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName;
    }

    public String getPpManuf() {
        return ppManuf;
    }

    public void setPpManuf(String ppManuf) {
        this.ppManuf = ppManuf;
    }

    public String getPpEnginType() {
        return ppEnginType;
    }

    public void setPpEnginType(String ppEnginType) {
        this.ppEnginType = ppEnginType;
    }

    public String getPpEngHourStart() {
        return ppEngHourStart;
    }

    public void setPpEngHourStart(String ppEngHourStart) {
        this.ppEngHourStart = ppEngHourStart;
    }

    public String getPpFuelQt() {
        return ppFuelQt;
    }

    public void setPpFuelQt(String ppFuelQt) {
        this.ppFuelQt = ppFuelQt;
    }
}
