package com.example.administracijaba;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DodajPolicajcaAktivnost extends AppCompatActivity {
    private TextInputLayout email_txt;
    private TextInputLayout lozinka_txt;
    private TextInputLayout lozinka_potvrda_txt;
    private CardView spremi_btn;
    FirebaseAuth auth;
    FirebaseAuth auth2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_policajca_aktivnost);
        auth = FirebaseAuth.getInstance();
        auth2 = FirebaseAuth.getInstance();

        email_txt = findViewById(R.id.email_korisnika_txt);
        lozinka_txt = findViewById(R.id.lozinka_korisnika_txt);
        lozinka_potvrda_txt = findViewById(R.id.lozinka_korisnika_txt2);
        this.spremi_btn = findViewById(R.id.registrirajSe_btn);

        this.spremi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_txt.getEditText().getText().toString();
                String lozinka = lozinka_txt.getEditText().getText().toString();
                String potvrda_lozinke = lozinka_potvrda_txt.getEditText().getText().toString();
                provjeriUznos(email_txt);
                provjeriUznos(lozinka_txt);
                provjeriUznos(lozinka_potvrda_txt);
                if(email.equals("") || lozinka.equals("") || lozinka_potvrda_txt.equals("")){
                    Toast.makeText(DodajPolicajcaAktivnost.this, "Molimo popunite sve podatke", Toast.LENGTH_SHORT).show();
                }
                else{
                if (lozinka.equals(potvrda_lozinke) && email != null && lozinka != null){

                    auth.createUserWithEmailAndPassword(email, lozinka)
                            .addOnCompleteListener(DodajPolicajcaAktivnost.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(getApplicationContext(), "Uspješno ste registrirani.", Toast.LENGTH_LONG).show();
                                    email_txt.getEditText().setText("");
                                    lozinka_txt.getEditText().setText("");
                                    lozinka_potvrda_txt.getEditText().setText("");


                                }
                            });
            }
            else{
                Toast.makeText(DodajPolicajcaAktivnost.this, "Lozinke se ne podudaraju", Toast.LENGTH_SHORT).show();

            }}
            }
        });



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
