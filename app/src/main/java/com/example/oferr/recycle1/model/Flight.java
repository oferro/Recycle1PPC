package com.example.oferr.recycle1.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Flight {

    @SerializedName("id")
    private String id;
    @SerializedName("flPpc")
    private Ppc flPpc;
    @SerializedName("flPilot")
    private Pilot flPilot;
    @SerializedName("flDate")
    private String flDate;
    @SerializedName("flToTime")
    private String flToTime;
    @SerializedName("flLndTime")
    private String FLLndTime;
    @SerializedName("flAirField")
    private String FLAirField;
    @SerializedName("flRoute")
    private String flRoute;
    @SerializedName("flEngHourStart")
    private String flEngHourStart;
    @SerializedName("flEngHouEnd")
    private String flEngHouEnd;
    @SerializedName("flFuelQt")
    private String flFuelQt;
    @SerializedName("flOilQt")
    private String flOilQt;
    @SerializedName("flOtherExp")
    private String flOtherExp;
    @SerializedName("flMaintenance")
    private String flMaintenance;
    @SerializedName("flRemark")
    private String flRemark;


    public Flight(String id, Ppc flPpc, Pilot flPilot, String flDate,
                  String flToTime, String FLLndTime, String FLAirField, String flRoute,
                  String flEngHourStart, String flEngHouEnd, String flFuelQt, String flOilQt,
                  String flOtherExp, String flMaintenance, String flRemark) {
        this.id = id;
        this.flPpc = flPpc;
        this.flPilot = flPilot;
        this.flDate = flDate;
        this.flToTime = flToTime;
        this.FLLndTime = FLLndTime;
        this.FLAirField = FLAirField;
        this.flRoute = flRoute;
        this.flEngHourStart = flEngHourStart;
        this.flEngHouEnd = flEngHouEnd;
        this.flFuelQt = flFuelQt;
        this.flOilQt = flOilQt;
        this.flOtherExp = flOtherExp;
        this.flMaintenance = flMaintenance;
        this.flRemark = flRemark;
    }

    public Flight(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ppc getFlPpc() {
        return flPpc;
    }

    public void setFlPpc(Ppc flPpc) {
        this.flPpc = flPpc;
    }

    public Pilot getFlPilot() {
        return flPilot;
    }

    public void setFlPilot(Pilot flPilot) {
        this.flPilot = flPilot;
    }

    public String getFlDate() {
        return flDate;
    }

    public void setFlDate(String flDate) {
        this.flDate = flDate;
    }

    public String getFlToTime() {
        return flToTime;
    }

    public void setFlToTime(String flToTime) {
        this.flToTime = flToTime;
    }

    public String getFLLndTime() {
        return FLLndTime;
    }

    public void setFLLndTime(String FLLndTime) {
        this.FLLndTime = FLLndTime;
    }

    public String getFLAirField() {
        return FLAirField;
    }

    public void setFLAirField(String FLAirField) {
        this.FLAirField = FLAirField;
    }

    public String getFlRoute() {
        return flRoute;
    }

    public void setFlRoute(String flRoute) {
        this.flRoute = flRoute;
    }

    public String getFlEngHourStart() {
        return flEngHourStart;
    }

    public void setFlEngHourStart(String flEngHourStart) {
        this.flEngHourStart = flEngHourStart;
    }

    public String getFlEngHouEnd() {
        return flEngHouEnd;
    }

    public void setFlEngHouEnd(String flEngHouEnd) {
        this.flEngHouEnd = flEngHouEnd;
    }

    public String getFlFuelQt() {
        return flFuelQt;
    }

    public void setFlFuelQt(String flFuelQt) {
        this.flFuelQt = flFuelQt;
    }

    public String getFlOilQt() {
        return flOilQt;
    }

    public void setFlOilQt(String flOilQt) {
        this.flOilQt = flOilQt;
    }

    public String getFlOtherExp() {
        return flOtherExp;
    }

    public void setFlOtherExp(String flOtherExp) {
        this.flOtherExp = flOtherExp;
    }

    public String getFlMaintenance() {
        return flMaintenance;
    }

    public void setFlMaintenance(String flMaintenance) {
        this.flMaintenance = flMaintenance;
    }

    public String getFlRemark() {
        return flRemark;
    }

    public void setFlRemark(String flRemark) {
        this.flRemark = flRemark;
    }
}

