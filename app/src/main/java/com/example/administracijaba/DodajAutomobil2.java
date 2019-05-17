package com.example.administracijaba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administracijaba.model.Automobil;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class DodajAutomobil2 extends AppCompatActivity {
    private CardView Spremi_automobil_Btn;

    private TextInputLayout marka;
    private TextInputLayout broj_tablica;
    private TextInputLayout registriran_do;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String osoba_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_automobil2);

        marka = findViewById(R.id.marka_txt);
        broj_tablica = findViewById(R.id.broj_tablica_txt);
        registriran_do = findViewById(R.id.registriran_do_txt);
        Spremi_automobil_Btn = findViewById(R.id.Spremi_automobil_Btn);
        Intent i = getIntent();
        osoba_id = i.getStringExtra("Rezultat");


        databaseReference = FirebaseDatabase.getInstance().getReference("automobili");  //referenca na tablicu

        Spremi_automobil_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provjeriUznos(marka);
                provjeriUznos(broj_tablica);
                provjeriUznos(registriran_do);
                prenesiPodatke();
            }
        });


    }

    private void prenesiPodatke() {
        if (marka != null && broj_tablica != null && registriran_do!= null && osoba_id != null) {
            Toast.makeText(DodajAutomobil2.this, "Prijenos uspješno završen", Toast.LENGTH_SHORT).show();
            Automobil upload = new Automobil(
                    marka.getEditText().getText().toString().trim(),
                    broj_tablica.getEditText().getText().toString().toUpperCase().trim(),
                    registriran_do.getEditText().getText().toString().trim(),
                    osoba_id.trim());
            String uploadId = databaseReference.push().getKey();
            databaseReference.child(uploadId).setValue(upload);
            marka.getEditText().setText("");
            broj_tablica.getEditText().setText("");
            registriran_do.getEditText().setText("");
            osoba_id=null;

        }


        else {
            Toast.makeText(DodajAutomobil2.this, "Molimo popunite sve podatke", Toast.LENGTH_SHORT).show();
            provjeriUznos(marka);
            provjeriUznos(broj_tablica);
            provjeriUznos(registriran_do);

        }
    }
    private boolean provjeriUznos(TextInputLayout text){
        String ispitaj = text.getEditText().getText().toString().trim();

        if (ispitaj.isEmpty()) {
            text.setError("Ovo polje ne može biti prazno");
            return false;
        }

        else {
            text.setError(null);
            return true;
        }
    }
}
