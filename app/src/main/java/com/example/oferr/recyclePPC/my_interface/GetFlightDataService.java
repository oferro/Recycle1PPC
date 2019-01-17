package com.example.oferr.recyclePPC.my_interface;

import com.example.oferr.recyclePPC.model.Flight;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface GetFlightDataService
{
    @GET("and/flights")
    Call<ArrayList<Flight>> getFlightData();

    @PUT("and/flightUpdate")
    Call<Flight> updateFlightData(@Body Flight flight);
}
