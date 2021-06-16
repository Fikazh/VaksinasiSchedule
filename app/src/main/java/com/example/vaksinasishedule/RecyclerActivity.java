package com.example.vaksinasishedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reff;
    private FirebaseStorage storage;
    private StorageReference storageReff;
    private String userID;
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;

    private ArrayList<String> kodeTempat = new ArrayList<String>();
    private ArrayList<TempatJadwal> tJList = new ArrayList<TempatJadwal>();
    private ArrayList<String> imgList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recycler);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference();
        userID = user.getUid();

        storage = FirebaseStorage.getInstance();
        storageReff = storage.getReference();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ps : snapshot.child("Tempat").getChildren()) {
                    kodeTempat.add(ps.getKey());
                    TempatJadwal tJ = ps.getValue(TempatJadwal.class);
                    tJList.add(tJ);
                }
                ShowRecycle();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void ShowRecycle() {
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(kodeTempat, tJList, this);
        recyclerView.setAdapter(recycleAdapter);
    }
}