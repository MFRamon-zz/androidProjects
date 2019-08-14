package com.example.rmanrique.ContactsApp.Adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.rmanrique.ContactsApp.Activities.InformationActivity;
import com.example.rmanrique.ContactsApp.Models.Contact;
import com.example.rmanrique.ContactsApp.R;
import com.google.gson.Gson;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final Context mCtx;
    private List<Contact> contactList;

    public ContactAdapter(Context mCtx, List<Contact> contactList) {
        this.mCtx = mCtx;
        this.contactList = contactList;
    }
    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        CardView _CardView;
        TextView _TextViewName;
        TextView _TextViewLastName;
        TextView _TextViewAge;
        CircleImageView _image;

        public ContactViewHolder (final View itemView,final List<Contact> contactList) {
            super(itemView);
            _CardView = itemView.findViewById(R.id.card);
            _TextViewName = itemView.findViewById(R.id.textViewName);
            _TextViewLastName = itemView.findViewById(R.id.textViewLastName);
            _TextViewAge = itemView.findViewById(R.id.textViewAge);
            _image = itemView.findViewById(R.id.profile_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String ObjName = contactList.get(position).Name; // esto guarda elk nombre del objeto.

                    Gson gson = new Gson();
                    final String json = gson.toJson(contactList.get(position));

                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Do you wish to continue?")
                            .setTitle("You chose " + ObjName)
                            .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(builder.getContext(), InformationActivity.class);
                                    intent.putExtra("json", json);
                                    builder.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    builder.create().show();
                }
            });

        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public ContactViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts, parent, false);
        ContactViewHolder pvh = new ContactViewHolder(v, this.contactList);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder._TextViewName.setText(contactList.get(position).Name);
        holder._TextViewLastName.setText(contactList.get(position).LastName);
        holder._TextViewAge.setText(String.valueOf(contactList.get(position).Age));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
