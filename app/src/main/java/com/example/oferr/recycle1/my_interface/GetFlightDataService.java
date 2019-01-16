package com.example.oferr.recycle1.my_interface;

import com.example.oferr.recycle1.model.Flight;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetFlightDataService
{
    @GET("and/flights")
    Call<ArrayList<Flight>> getFlightData();

    @PUT("and/flightUpdate")
    Call<Flight> updateFlightData(@Body Flight flight);
}
