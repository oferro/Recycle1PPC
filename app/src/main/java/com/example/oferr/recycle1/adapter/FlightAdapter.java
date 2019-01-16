package com.example.oferr.recycle1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oferr.recycle1.R;
import com.example.oferr.recycle1.model.Flight;

import java.util.ArrayList;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder>
{

    ItemClickListener itemClickListener;
    private ArrayList<Flight> dataList;

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
    public void onBindViewHolder(FlightViewHolder holder, int position) {
        holder.txtDate.setText(dataList.get(position).getFlDate());
//todo: Fitch data from json nested object - work fine !!
        holder.txtUser.setText(dataList.get(position).getFlPilot().getFullName());
        holder.txtPPC.setText(dataList.get(position).getFlPpc().getPpName());
        holder.txtAirFiled.setText(dataList.get(position).getFLAirField());
        holder.txtFltRoute.setText(dataList.get(position).getFlRoute());
        holder.txtToHour.setText(dataList.get(position).getFlToTime());
        holder.txtLndHour.setText(dataList.get(position).getFLLndTime());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class FlightViewHolder extends RecyclerView.ViewHolder {

        TextView txtDate, txtUser, txtPPC,txtAirFiled, txtFltRoute, txtToHour, txtLndHour, txtTimeStemp;

        FlightViewHolder(View itemView) {
            super(itemView);
            txtDate =  itemView.findViewById(R.id.txt_date);
            txtUser =  itemView.findViewById(R.id.txt_user);
            txtPPC =  itemView.findViewById(R.id.txt_ppc);
            txtAirFiled =  itemView.findViewById(R.id.txt_air_field);
            txtFltRoute =  itemView.findViewById(R.id.txt_flt_route);
            txtToHour =  itemView.findViewById(R.id.txt_to_hour);
            txtLndHour =  itemView.findViewById(R.id.txt_lnd_hour);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public void UpdateData(int position,Flight flight){

        dataList.remove(position);
        dataList.add(flight);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

}


