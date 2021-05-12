package com.example.lab6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    ArrayList<Person> arrayList;
    Context context;
    View itemView;
    private LayoutInflater layoutInflater;
    public PersonAdapter(ArrayList<Person> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = layoutInflater.inflate(R.layout.person_row, parent, false);
        return new PersonViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.txtName.setText(arrayList.get(position).getName());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        public PersonViewHolder(View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.textPerson);
        }
    }
    public void filterList(ArrayList<Person> filteredList){
        arrayList = filteredList;
        notifyDataSetChanged();
    }
}
