package com.example.administracijaba;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administracijaba.model.Osoba;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DodajOsobuAktivnost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private CardView dodajOsobuBtn;
    private TextInputLayout imeOsobe;
    private TextInputLayout prezimeOsobe;
    private TextInputLayout adresaOsobe;
    private TextInputLayout gradOsobe;
    private TextInputLayout jmbg;
    private TextInputLayout broj_osobne;
    private ImageButton imageButton;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Spinner zupanijaSpinner;
    private Uri imageUri; //sluzi za prikazivanje slike u image view i da mozemo onda prenijeti u storage na firebase
    String zupanija;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String spol ="Muško";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_osobu_aktivnost);


        dodajOsobuBtn = findViewById(R.id.dodaj_osobu_btn);
        imeOsobe = findViewById(R.id.ime_osobe_txt);
        prezimeOsobe = findViewById(R.id.prezime_osobe_txt);
        adresaOsobe = findViewById(R.id.adresa_osobe_txt);
        gradOsobe = findViewById(R.id.grad_osobe_txt);
        jmbg = findViewById(R.id.jmbg_txt);
        broj_osobne = findViewById(R.id.broj_osobne_txt);
        zupanijaSpinner = findViewById(R.id.zupanije_spinner);
        imageButton = findViewById(R.id.odaberi_sliku_btn);

        progressBar = findViewById(R.id.progress_bar);
        radioGroup = findViewById(R.id.radioGroup);

        storageReference = FirebaseStorage.getInstance().getReference("slike_osoba");//mapa slike u koju cemo spremati slike, moze biti i prazno ako zelimo spremiti direktno u bazu, ali dolje kod currentTimeMillis mora biti "uploads/" +
        databaseReference = FirebaseDatabase.getInstance().getReference("osoba");  //referenca na tablicu




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        dodajOsobuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provjeriUznos(imeOsobe);
                provjeriUznos(prezimeOsobe);
                provjeriUznos(adresaOsobe);
                provjeriUznos(gradOsobe);
                provjeriUznos(jmbg);
                provjeriUznos(broj_osobne);
                dodavanjeOsobe();

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_zupanija, R.layout.izgled_spinner);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        zupanijaSpinner.setAdapter(adapter);

        zupanijaSpinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        zupanija = parent.getItemAtPosition(pos).toString();
        if (zupanija.equals("Molimo odaberite županiju")){
            zupanija = null;
        }
        else{
            Toast.makeText(this, zupanija, Toast.LENGTH_SHORT).show();}
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    public void provjeriButton(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.musko:
                if (checked)
                    spol = "Muško";
                    break;
            case R.id.zensko:
                if (checked)
                    spol = "Žensko";
                    break;
        }
    }

    private String dohvatiEkstenziju(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void dodavanjeOsobe() {
        if(imageUri != null && spol != null && imeOsobe != null && prezimeOsobe != null && adresaOsobe != null && gradOsobe != null && zupanija != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()//currentTimeMillis sluzi da nam nazivi slika u bazi budu jedinstveni
                    + "." + dohvatiEkstenziju(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(DodajOsobuAktivnost.this, "Prijenos uspješan!", Toast.LENGTH_SHORT).show();
                            Osoba osoba = new Osoba(imeOsobe.getEditText().getText().toString().trim(),
                                    prezimeOsobe.getEditText().getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString(),
                                    adresaOsobe.getEditText().getText().toString().trim(),
                                    gradOsobe.getEditText().getText().toString().trim(),
                                    zupanija,
                                    spol,
                                    jmbg.getEditText().getText().toString().trim(),
                                    broj_osobne.getEditText().getText().toString().trim());
                            String osobaId = databaseReference.push().getKey();
                            databaseReference.child(osobaId).setValue(osoba);

                            imageUri = null;
                            imeOsobe.getEditText().setText("");
                            prezimeOsobe.getEditText().setText("");
                            adresaOsobe.getEditText().setText("");
                            gradOsobe.getEditText().setText("");
                            jmbg.getEditText().setText("");
                            broj_osobne.getEditText().setText("");
                            zupanijaSpinner.setSelection(0, true);
                            imageButton.setImageResource(R.drawable.ic_add_circle_black_24dp);




                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DodajOsobuAktivnost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });
        }else{
            Toast.makeText(DodajOsobuAktivnost.this, "Molimo popunite sve podatke!", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null ){//ako je korisnik uzeo sliku i ispunio sva polja onda izvrsavamo naredbe
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);
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

