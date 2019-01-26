package com.example.oferr.recyclePPC.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oferr.recycle1.R;
import com.example.oferr.recyclePPC.MainActivity;
import com.example.oferr.recyclePPC.model.Flight;
import com.example.oferr.recyclePPC.my_interface.GetFlightDataService;
import com.example.oferr.recyclePPC.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.content.ContextCompat.startActivity;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder>
{
    AlertDialog.Builder builder;

    AlertDialog dialog;

    Button add, btn_update, btn_cancel;

    EditText md_txt_date, md_txt_user, md_txt_ppc, md_txt_air_field, md_txt_flt_route, md_txt_to_hour, md_txt_lnd_hour;
    EditText ed_txt_date,  ed_txt_air_field, ed_txt_flt_route, ed_txt_to_hour, ed_txt_lnd_hour;
    TextView ed_txt_user, ed_txt_ppc;
    private ArrayList<Flight> dataList;

    ItemClickListener itemClickListener;
    private String msg;

    public FlightAdapter(ArrayList<Flight> dataList) {
        this.dataList = dataList;
    }

    /** Create handle for the RetrofitInstance interface*/
    GetFlightDataService service = RetrofitInstance.getRetrofitInstance().create(GetFlightDataService.class);


    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_view, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder holder,final int position) {
            final Flight flight = dataList.get(position);


            holder.txtDate.setText(dataList.get(position).getFlDate());
            holder.txtUser.setText(dataList.get(position).getFlPilot().getFullName());
            holder.txtPPC.setText(dataList.get(position).getFlPpc().getPpName());
            holder.txtAirFiled.setText(dataList.get(position).getFLAirField());
            holder.txtFltRoute.setText(dataList.get(position).getFlRoute());
            holder.txtToHour.setText(dataList.get(position).getFlToTime());
            holder.txtLndHour.setText(dataList.get(position).getFLLndTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                itemClickListener.OnItemClick(position,flight);
                System.out.println("Select Update flight :"+position);
                builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Update Flight Info");
                builder.setCancelable(false);
                view = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_update,null,false);
                InitUpdateDialog(position,view, flight);
                builder.setView(view);
                dialog = builder.create();
                dialog.show();


            }
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flight flight = dataList.get(position);
                int id = Integer.parseInt(flight.getId());
                dataList.remove(position);

                Call<Flight> callFlightDel = service.deleteFlightData(id);
                callFlightDel.enqueue(new Callback<Flight>() {
                    @Override
                    public void onResponse(Call<Flight> call, Response<Flight> response) {
                        if (response.isSuccessful()) {
                            Log.i("INFO: ", "Flight deleted at position: " + position);
                        }
                    }

                    @Override
                    public void onFailure(Call<Flight> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });


                notifyDataSetChanged();
                Log.i("Info", "Flight deleted ta position: " + position + ", id = " + id);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class FlightViewHolder extends RecyclerView.ViewHolder {

        TextView txtDate, txtUser, txtPPC,txtAirFiled, txtFltRoute, txtToHour, txtLndHour, tv_delete;

        FlightViewHolder(View itemView) {
            super(itemView);
            txtDate =  itemView.findViewById(R.id.txt_date);
            txtUser =  itemView.findViewById(R.id.txt_user);
            txtPPC =  itemView.findViewById(R.id.txt_ppc);
            txtAirFiled =  itemView.findViewById(R.id.txt_air_field);
            txtFltRoute =  itemView.findViewById(R.id.txt_flt_route);
            txtToHour =  itemView.findViewById(R.id.txt_to_hour);
            txtLndHour =  itemView.findViewById(R.id.txt_lnd_hour);
            tv_delete = itemView.findViewById(R.id.tv_delete_item);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public String AddData (final Flight flight){
        dataList.add(flight);
        Call<Flight> call = service.addFlightData(flight);
            call.enqueue(new Callback<Flight>(){
                @Override
                public void onResponse(Call<Flight> call, Response<Flight> response) {
                    if(response.isSuccessful()){
//ToDo: Find a way to update the new Flight ID
                        msg = "User created successfully!";
                    }
                }

                @Override
                public void onFailure(Call<Flight> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                    msg = "Had problem add Flight";
                }

            });



        notifyDataSetChanged();

        return msg;
    }

    public String UpdateData(int position, Flight flight) {
        dataList.remove(position);
        dataList.add(flight);

//        service.updateFlightData(flight);

        Call<Flight> call = service.updateFlightData(flight);
        call.enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight> response) {
                if (response.isSuccessful()) {
//                        Toast.makeText(.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                    String msg = "User updated successfully!";
                    Log.i("INFO: ", "User Updated successfully");
                }
            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                String msg = "Had problem update Flight";
            }
        });


        notifyDataSetChanged();


        notifyItemChanged(position);
        notifyDataSetChanged();
        return msg;
    }

    private void InitUpdateDialog(final int position, View view, Flight flight) {

        final String id= dataList.get(position).getId();

        ed_txt_date = view.findViewById(R.id.ed_txt_date);
        ed_txt_user = view.findViewById(R.id.ed_txt_user);
        ed_txt_ppc = view.findViewById(R.id.ed_txt_ppc);
        ed_txt_air_field = view.findViewById(R.id.ed_txt_air_field);
        ed_txt_flt_route = view.findViewById(R.id.ed_txt_flt_route);
        ed_txt_to_hour = view.findViewById(R.id.ed_txt_to_hour);
        ed_txt_lnd_hour = view.findViewById(R.id.ed_txt_lnd_hour);
        btn_update = view.findViewById(R.id.btn_update_flight);
        btn_cancel = view.findViewById(R.id.btn_update_cancel);


        ed_txt_date.setText(dataList.get(position).getFlDate());
        ed_txt_user.setText(dataList.get(position).getFlPilot().getFullName()); //ReadOnly
        ed_txt_ppc.setText(dataList.get(position).getFlPpc().getPpName());      //ReadOnly
        ed_txt_air_field.setText(dataList.get(position).getFLAirField());
        ed_txt_flt_route.setText(dataList.get(position).getFlRoute());
        ed_txt_to_hour.setText(dataList.get(position).getFlToTime());
        ed_txt_lnd_hour.setText(dataList.get(position).getFLLndTime());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date, user, ppc, airField, fltRoute, toHour, lndHour;
                Flight flight = dataList.get(position);

                date = ed_txt_date.getText().toString();
                airField = ed_txt_air_field.getText().toString();
                fltRoute = ed_txt_flt_route.getText().toString();
                toHour = ed_txt_to_hour.getText().toString();
                lndHour = ed_txt_lnd_hour.getText().toString();


//                flight.setId(id);
                flight.setFlDate(date);
//ToDo : conect the objects
//                flight.setFlPilot(user);
//                flight.setFlPpc(ppc);
                flight.setFLAirField(airField);
                flight.setFlRoute(fltRoute);
                flight.setFlToTime(toHour);
                flight.setFLLndTime(lndHour);

                String msg = UpdateData(position,flight);
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

}


