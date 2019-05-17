package com.example.administracijaba;

import android.view.View;
import android.widget.TextView;

import com.example.administracijaba.model.KaznaOsoba;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KaznaOsobeViewHolder extends RecyclerView.ViewHolder{

    View mView;
    TextView iznos_txt;
    TextView opis_txt;
    TextView broj_osobne_txt;
    public KaznaOsobeViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        this.iznos_txt = itemView.findViewById(R.id.iznos_txt);
        this.opis_txt = itemView.findViewById(R.id.opis_txt);
        this.broj_osobne_txt = itemView.findViewById(R.id.broj_osobne_txt);
        this.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v,getAdapterPosition());
            }
        });
        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clickListener.onLongItemClickListener(v, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(KaznaOsoba kaznaOsoba) {
        this.iznos_txt.setText(kaznaOsoba.iznos);
        this.opis_txt.setText(kaznaOsoba.opis);
        this.broj_osobne_txt.setText(kaznaOsoba.broj_osobne);



    }
    private KaznaOsobeViewHolder.ClickListener clickListener;

    public void setOnClickListener (KaznaOsobeViewHolder.ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public interface ClickListener{
        public void onItemClick(View v, int postion);
        public void onLongItemClickListener(View v, int position);
    }



}



