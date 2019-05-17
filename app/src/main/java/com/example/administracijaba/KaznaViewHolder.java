package com.example.administracijaba;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administracijaba.model.KaznaAutomobil;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KaznaViewHolder extends RecyclerView.ViewHolder{

    View mView;
    TextView iznos_txt;
    TextView opis_txt;
    ImageView slika;
    TextView broj_tablica_txt;
    public KaznaViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        this.iznos_txt = itemView.findViewById(R.id.iznos_txt);
        this.opis_txt = itemView.findViewById(R.id.opis_txt);
        this.slika = itemView.findViewById(R.id.slika_automobila);
        this.broj_tablica_txt = itemView.findViewById(R.id.broj_tablica_txt);
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

    public void setDetails(KaznaAutomobil kaznaAutomobil) {
        this.iznos_txt.setText(kaznaAutomobil.iznos);
        this.opis_txt.setText(kaznaAutomobil.opis);
        this.broj_tablica_txt.setText(kaznaAutomobil.broj_tablica);
        Picasso.get().load(kaznaAutomobil.slika).into(slika);



    }
    private ClickListener clickListener;

    public void setOnClickListener (ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public interface ClickListener{
        public void onItemClick(View v, int postion);
        public void onLongItemClickListener(View v, int position);
    }



}


