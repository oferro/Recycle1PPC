package com.example.oferr.recyclePPC.my_interface;

import com.example.oferr.recyclePPC.model.Flight;
import com.example.oferr.recyclePPC.model.Pilot;
import com.example.oferr.recyclePPC.model.Ppc;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetFlightDataService
{
    @GET("and/flights")
    Call<ArrayList<Flight>> getFlightData();

    @PUT("and/flightUpdate")
    Call<Flight> updateFlightData(@Body Flight flight);

    @DELETE("and/flightDelete/{id}")
    Call<Flight> deleteFlightData(@Path("id") int id);

    @PUT("and/flightAdd")
    Call<Flight> addFlightData(@Body Flight flight);

    @GET("and/pilots")
    Call<ArrayList<Pilot>> getPilotData();

    @GET("and/ppcs")
    Call<ArrayList<Ppc>> getPpcData();

}
