package com.example.administracijaba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout email_korisnika_txt;
    private TextInputLayout lozinka_korisnika_txt;
    CardView prijaviSe_btn;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
            Intent i = new Intent(MainActivity.this, PocetnaAktivnostAdministrator.class);
            startActivity(i);
            finish();
        }
        email_korisnika_txt = findViewById(R.id.email_korisnika_txt);
        lozinka_korisnika_txt = findViewById(R.id.lozinka_korisnika_txt);
        prijaviSe_btn = findViewById(R.id.Spremi_Kaznu_Btn);


        prijaviSe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provjeriUnos(email_korisnika_txt);
                provjeriUnos(lozinka_korisnika_txt);
                final String email = email_korisnika_txt.getEditText().getText().toString();
                String lozinka = lozinka_korisnika_txt.getEditText().getText().toString();

                if (email.equals("") || lozinka.equals("")){
                    Toast.makeText(MainActivity.this, "Molimo popunite sva polja !", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(email, lozinka).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                email_korisnika_txt.getEditText().setText("");
                                lozinka_korisnika_txt.getEditText().setText("");
                                Toast.makeText(getApplicationContext(), "Uspješna prijava na sustav.", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(MainActivity.this, PocetnaAktivnostAdministrator.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Neuspješna prijava na sustav.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });



    }
    private boolean provjeriUnos(TextInputLayout text){
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
