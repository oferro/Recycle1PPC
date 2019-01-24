package com.example.oferr.recyclePPC;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oferr.recycle1.R;
import com.example.oferr.recyclePPC.adapter.FlightAdapter;
import com.example.oferr.recyclePPC.adapter.ItemClickListener;
import com.example.oferr.recyclePPC.model.Flight;
import com.example.oferr.recyclePPC.model.Pilot;
import com.example.oferr.recyclePPC.model.Ppc;
import com.example.oferr.recyclePPC.my_interface.GetFlightDataService;
import com.example.oferr.recyclePPC.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Button add, btn_add, btn_cancel;

    ArrayList<Flight> list = new ArrayList<>();
    FlightAdapter adapter = new FlightAdapter(list);

    ArrayList<Pilot> listPilot = new ArrayList<>();
    ArrayList<Ppc> listPpc = new ArrayList<>();

    AlertDialog.Builder builder;

    AlertDialog dialog;

    EditText md_txt_date, md_txt_user, md_txt_ppc, md_txt_air_field, md_txt_flt_route, md_txt_to_hour, md_txt_lnd_hour;
    EditText ad_txt_date, ad_txt_user, ad_txt_ppc, ad_txt_air_field, ad_txt_flt_route, ad_txt_to_hour, ad_txt_lnd_hour;

//    String date, user, ppc, airField, fltRoute, toHour, lndHour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        md_txt_date = (EditText) findViewById(R.id.md_txt_date);
//        md_txt_user = (EditText) findViewById(R.id.md_txt_user);
//        md_txt_ppc = (EditText) findViewById(R.id.md_txt_ppc);
//        md_txt_air_field = (EditText) findViewById(R.id.md_txt_air_field);
//        md_txt_flt_route = (EditText) findViewById(R.id.md_txt_flt_route);
//        md_txt_to_hour = (EditText) findViewById(R.id.md_txt_to_hour);
//        md_txt_lnd_hour = (EditText) findViewById(R.id.md_txt_lnd_hour);

        add = (Button) findViewById(R.id.btn_add);
        btn_add = (Button) findViewById(R.id.btn_add_flight);
        btn_cancel = (Button) findViewById(R.id.btn_add_cancel);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /** Create handle for the RetrofitInstance interface*/
        GetFlightDataService service = RetrofitInstance.getRetrofitInstance().create(GetFlightDataService.class);

        /** Call the method with parameter in the interface to get the flight data*/
        Call<ArrayList<Flight>> call = service.getFlightData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ArrayList<Flight>>() {
            @Override
            public void onResponse(Call<ArrayList<Flight>> call, Response<ArrayList<Flight>> response) {
                generateFlightList(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Flight>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        /** Call the method with parameter in the interface to get the flight data*/
        Call<ArrayList<Pilot>> callPilot = service.getPilotData();

        /**Log the URL called*/
        Log.wtf("URL Called", callPilot.request().url() + "");

        callPilot.enqueue(new Callback<ArrayList<Pilot>>() {
            @Override
            public void onResponse(Call<ArrayList<Pilot>> call, Response<ArrayList<Pilot>> response) {
                listPilot = (response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Pilot>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        /** Call the method with parameter in the interface to get the flight data*/
        Call<ArrayList<Ppc>> callPpc = service.getPpcData();

        /**Log the URL called*/
        Log.wtf("URL Called", callPpc.request().url() + "");

        callPpc.enqueue(new Callback<ArrayList<Ppc>>() {
            @Override
            public void onResponse(Call<ArrayList<Ppc>> call, Response<ArrayList<Ppc>> response) {
                listPpc = (response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Ppc>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad_txt_date = view.findViewById(R.id.ed_txt_date);
                ad_txt_user = view.findViewById(R.id.ed_txt_user);
                ad_txt_ppc = view.findViewById(R.id.ed_txt_ppc);
                ad_txt_air_field = view.findViewById(R.id.ed_txt_air_field);
                ad_txt_flt_route = view.findViewById(R.id.ed_txt_flt_route);
                ad_txt_to_hour = view.findViewById(R.id.ed_txt_to_hour);
                ad_txt_lnd_hour = view.findViewById(R.id.ed_txt_lnd_hour);
                btn_add = view.findViewById(R.id.btn_add_flight);
                btn_cancel = view.findViewById(R.id.btn_add_cancel);

                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add New Flight Info");
                builder.setCancelable(false);
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add,null,false);
                InitAddDialog(view, listPilot, listPpc);
                builder.setView(view);
                dialog = builder.create();
                dialog.show();
            }
        });
    }


    private void generateFlightList(ArrayList<Flight> flightArrayList) {
        list = flightArrayList;
        recyclerView = findViewById(R.id.recycler_view_flight_list);
        adapter = new FlightAdapter(flightArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


//TODO: Insted of dialog I will open an add activity
    private void InitAddDialog(View view, final ArrayList<Pilot> listPi, ArrayList<Ppc> listPp) {

        ad_txt_date = view.findViewById(R.id.ad_txt_date);
//        ad_txt_user = view.findViewById(R.id.ad_txt_user);
        final Spinner pilotSpinner = view.findViewById(R.id.ad_spinner_pilot);
//        ad_txt_ppc = view.findViewById(R.id.ad_txt_ppc);
        final Spinner ppcSpinner = view.findViewById(R.id.ad_spinner_ppc);
        ad_txt_air_field = view.findViewById(R.id.ad_txt_air_field);
        ad_txt_flt_route = view.findViewById(R.id.ad_txt_flt_route);
        ad_txt_to_hour = view.findViewById(R.id.ad_txt_to_hour);
        ad_txt_lnd_hour = view.findViewById(R.id.ad_txt_lnd_hour);

        btn_add = view.findViewById(R.id.btn_add_flight);
        btn_cancel = view.findViewById(R.id.btn_add_cancel);


        ArrayAdapter<Pilot> adpPilot = new ArrayAdapter<Pilot>(this, android.R.layout.simple_spinner_item, listPi);
        adpPilot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilotSpinner.setAdapter(adpPilot);

        ArrayAdapter<Ppc> adpPpc = new ArrayAdapter<Ppc>(this, android.R.layout.simple_spinner_item, listPp);
        adpPpc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ppcSpinner.setAdapter(adpPpc);


//
//        ed_txt_date.setText(list.get(position).getFlDate());
//        ed_txt_user.setText(list.get(position).getFlPilot().getFullName());
//        ed_txt_ppc.setText(list.get(position).getFlPpc().getPpName());
//        ed_txt_air_field.setText(list.get(position).getFLAirField());
//        ed_txt_flt_route.setText(list.get(position).getFlRoute());
//        ed_txt_to_hour.setText(list.get(position).getFlToTime());
//        ed_txt_lnd_hour.setText(list.get(position).getFLLndTime());
//
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date,  airField, fltRoute, toHour, lndHour;
                final List<Pilot> lPilot = listPi;
                final List<Ppc> lPpc = listPpc;

                date = ad_txt_date.getText().toString();
                airField = ad_txt_air_field.getText().toString();
                fltRoute = ad_txt_flt_route.getText().toString();
                toHour = ad_txt_to_hour.getText().toString();
                lndHour = ad_txt_lnd_hour.getText().toString();
//ToDo :ok conect the oblects - work !!
                Pilot user = listPilot.get(pilotSpinner.getSelectedItemPosition());
                Ppc ppc = listPpc.get(ppcSpinner.getSelectedItemPosition());

                Flight flight = new Flight();

                flight.setFlDate(date);
                flight.setFlPilot(user);
                flight.setFlPpc(ppc);
                flight.setFLAirField(airField);
                flight.setFlRoute(fltRoute);
                flight.setFlToTime(toHour);
                flight.setFLLndTime(lndHour);

                String msg = adapter.AddData(flight);


                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
