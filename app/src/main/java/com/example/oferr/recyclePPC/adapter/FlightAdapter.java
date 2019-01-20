package com.example.oferr.recyclePPC.adapter;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder>
{
    AlertDialog.Builder builder;

    AlertDialog dialog;

    Button add, btn_update, btn_cancel;

    EditText md_txt_date, md_txt_user, md_txt_ppc, md_txt_air_field, md_txt_flt_route, md_txt_to_hour, md_txt_lnd_hour;
    EditText ed_txt_date, ed_txt_user, ed_txt_ppc, ed_txt_air_field, ed_txt_flt_route, ed_txt_to_hour, ed_txt_lnd_hour;

    private ArrayList<Flight> dataList;

    ItemClickListener itemClickListener;

    public FlightAdapter(ArrayList<Flight> dataList) {
        this.dataList = dataList;
    }

    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_view, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder holder,final int position) {
        if (position >= 0 ) {
            final Flight flight = dataList.get(position);

            holder.txtDate.setText(dataList.get(position).getFlDate());
            holder.txtUser.setText(dataList.get(position).getFlPilot().getFullName());
            holder.txtPPC.setText(dataList.get(position).getFlPpc().getPpName());
            holder.txtAirFiled.setText(dataList.get(position).getFLAirField());
            holder.txtFltRoute.setText(dataList.get(position).getFlRoute());
            holder.txtToHour.setText(dataList.get(position).getFlToTime());
            holder.txtLndHour.setText(dataList.get(position).getFLLndTime());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                itemClickListener.OnItemClick(position,flight);
                System.out.println("Select Update flight :"+position);
                builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Update Flight Info");
                builder.setCancelable(false);
                view = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_update,null,false);
                InitUpdateDialog(position,view);
                builder.setView(view);
                dialog = builder.create();
                dialog.show();


            }
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataList.remove(position);
                notifyDataSetChanged();
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


    public void UpdateData(int position,Flight flight){
        if(position<0){
        } else {
            dataList.remove(position);
        }
        dataList.add(flight);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    private void InitUpdateDialog(final int position, View view) {

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
        ed_txt_user.setText(dataList.get(position).getFlPilot().getFullName());
        ed_txt_ppc.setText(dataList.get(position).getFlPpc().getPpName());
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
//ToDo : conect the oblects
//                flight.setFlPilot(user);
//                flight.setFlPpc(ppc);
                flight.setFLAirField(airField);
                flight.setFlRoute(fltRoute);
                flight.setFlToTime(toHour);
                flight.setFLLndTime(lndHour);

                UpdateData(position,flight);

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


