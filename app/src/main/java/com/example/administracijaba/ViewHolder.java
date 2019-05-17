package com.example.administracijaba;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administracijaba.model.Osoba;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder{
    TextView ime_txt;
    TextView prezime_txt;
    TextView broj_osobne_txt;
    TextView spol_txt;
    TextView adresa_txt;
    TextView grad_txt;
    TextView zupanija_txt;
    TextView JMBG_txt;
    ImageView slika_img;
    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        this.ime_txt = itemView.findViewById(R.id.ime_txt);
        this.prezime_txt = itemView.findViewById(R.id.prezime_txt);
        this.JMBG_txt = itemView.findViewById(R.id.JMBG_txt);
        this.broj_osobne_txt = itemView.findViewById(R.id.broj_osobne_txt);
        this.spol_txt = itemView.findViewById(R.id.spol_txt);
        this.adresa_txt = itemView.findViewById(R.id.adresa_txt);
        this.grad_txt = itemView.findViewById(R.id.grad_txt);
        this.zupanija_txt = itemView.findViewById(R.id.zupanija_txt);
        this.slika_img = itemView.findViewById(R.id.slika_osobe);
        this.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v,getAdapterPosition());
            }
        });
    }


    public void setDetails(Osoba osoba) {

        this.ime_txt.setText(osoba.ime);
        this.prezime_txt.setText(osoba.prezime);
        Picasso.get().load(osoba.slika).into(slika_img);
        this.adresa_txt.setText(osoba.adresa);
        this.grad_txt.setText(osoba.grad);
        this.zupanija_txt.setText(osoba.zupanija);
        this.spol_txt.setText(osoba.spol);
        this.JMBG_txt.setText(osoba.jmbg);
        this.broj_osobne_txt.setText(osoba.broj_osobne);





    }
    private ViewHolder.ClickListener clickListener;

    public void setOnClickListener (ViewHolder.ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public interface ClickListener{
        public void onItemClick(View v, int postion);
    }
}

