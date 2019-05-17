package com.example.administracijaba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class PocetnaAktivnostAdministrator extends AppCompatActivity {
    LinearLayout dodaj_osobu;
    LinearLayout dodaj_policajca;
    LinearLayout dodaj_automobil;
    LinearLayout lista_kazni;
    LinearLayout odjavi_me;
    TextView korisnik;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna_aktivnost_administrator);

        this.dodaj_osobu = findViewById(R.id.dodaj_osobu);
        this.dodaj_policajca = findViewById(R.id.dodaj_policajca);
        this.dodaj_automobil = findViewById(R.id.dodaj_automobil);
        this.lista_kazni = findViewById(R.id.lista_kazni);
        this.korisnik = findViewById(R.id.korisnik);
        this.odjavi_me = findViewById(R.id.odjavi_me);
        String email = user.getEmail();
        korisnik.setText(email);

        dodaj_osobu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PocetnaAktivnostAdministrator.this, DodajOsobuAktivnost.class);
                startActivity(i);
            }
        });

        dodaj_policajca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PocetnaAktivnostAdministrator.this, DodajPolicajcaAktivnost.class);
                startActivity(i);
            }
        });

        dodaj_automobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PocetnaAktivnostAdministrator.this, DodajAutomobil.class);
                startActivity(i);
            }
        });

        lista_kazni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PocetnaAktivnostAdministrator.this, ListaKazniAktivnost.class);
                startActivity(i);
            }
        });


        odjavi_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PocetnaAktivnostAdministrator.this, MainActivity.class);
                startActivity(i);
                finish();
                FirebaseAuth.getInstance().signOut();
            }
        });
    }
}
