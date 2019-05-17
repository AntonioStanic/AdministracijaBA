package com.example.administracijaba;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administracijaba.model.KaznaAutomobil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KazneAutomobilaFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseRecyclerAdapter<KaznaAutomobil, KaznaViewHolder> adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kazne_automobila_activity, container, false);
        this.recyclerView = v.findViewById(R.id.recyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("kazne_automobila");
        pretraziFirebase();
        return v;
    }

    private void pretraziFirebase() {
        // Postavite postavke za pretvorbu JSON objekta u Movie klasu
        FirebaseRecyclerOptions<KaznaAutomobil> options = new FirebaseRecyclerOptions.Builder<KaznaAutomobil>().setQuery(
                reference, new SnapshotParser<KaznaAutomobil>() {
                    @NonNull
                    @Override
                    public KaznaAutomobil parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return snapshot.getValue(KaznaAutomobil.class);
                    }
                }
        ).build();

        // Adapter koji dohvaca elemente iz baze
        adapter = new FirebaseRecyclerAdapter<KaznaAutomobil, KaznaViewHolder>(options) {
            @NonNull
            @Override
            public KaznaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_kazni_automobil, viewGroup, false);

                KaznaViewHolder ViewHolderautomobil = new KaznaViewHolder(view);

                ViewHolderautomobil.setOnClickListener(new KaznaViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View v, int postion) {
                        String id = getRef(postion).getKey();
                        //reference.child("filmovi").child(id).child("naziv").setValue("TEST");
                        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onLongItemClickListener(View v, int position) {
                        final String id_kazne = getRef(position).getKey();
                        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Želite li izbrisati kaznu?");
                        builder.setCancelable(true);
                        builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                reference.child(id_kazne).removeValue();
                                Toast.makeText(getActivity(), "Uspješno ste izbrisali kaznu", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                        nbutton.setTextColor(Color.RED);
                        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                        pbutton.setTextColor(Color.RED);
                    }
                });
                return ViewHolderautomobil;
            }

            @Override
            protected void onBindViewHolder(@NonNull KaznaViewHolder holder, int position, @NonNull KaznaAutomobil kaznaAutomobil) {
                holder.setDetails(kaznaAutomobil);

            }
        };

        this.recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }
}
