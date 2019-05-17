package com.example.administracijaba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.administracijaba.model.KaznaAutomobil;
import com.example.administracijaba.model.KaznaOsoba;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListaKazniAktivnost extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseRecyclerAdapter<KaznaAutomobil, KaznaViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_kazni_aktivnost);

        contextOfApplication = getApplicationContext();
        Fragment odabraniFragment = new KazneOsobeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.prikaz_fragmenta, odabraniFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.podnozje_nav);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment odabraniFragment = new KazneOsobeFragment();
                switch (item.getItemId()) {
                    case R.id.navigation_osoba:
                        odabraniFragment = new KazneOsobeFragment();
                        break;
                    case R.id.navigation_automobil:
                        odabraniFragment = new KazneAutomobilaFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.prikaz_fragmenta, odabraniFragment).commit();
                return true;
            }
        });

    }

    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

}
