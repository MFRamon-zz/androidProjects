package com.example.rmanrique.airlines.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rmanrique.airlines.Models.Passenger;
import com.example.rmanrique.airlines.R;

import java.util.List;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder> {
    private final Context mCtx;
    private List<Passenger> passengerList;

    public PassengerAdapter(Context mCtx, List<Passenger> passengerList) {
        this.mCtx = mCtx;
        this.passengerList = passengerList;
    }
    public static class PassengerViewHolder extends RecyclerView.ViewHolder{

        CardView _CardView;
        TextView _TextViewName;
        TextView _TextViewLastName;
        TextView _TextViewAge;
//        CircleImageView _image;
        public PassengerViewHolder (final View itemView, final List<Passenger> passengerList) {
            super(itemView);
            _CardView = itemView.findViewById(R.id.card);
            _TextViewName = itemView.findViewById(R.id.textViewName);
            _TextViewLastName = itemView.findViewById(R.id.textViewLastName);
            _TextViewAge = itemView.findViewById(R.id.textViewAge);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public PassengerViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_card, parent, false);
        PassengerViewHolder pvh = new PassengerViewHolder(v, this.passengerList);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PassengerViewHolder holder, int position) {
        holder._TextViewName.setText(passengerList.get(position).Name);
        holder._TextViewLastName.setText(passengerList.get(position).LastName);
        holder._TextViewAge.setText(String.valueOf(passengerList.get(position).Age));
    }

    @Override
    public int getItemCount() {
        return passengerList.size();
    }
}
