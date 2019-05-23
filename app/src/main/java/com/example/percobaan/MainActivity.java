package com.example.percobaan;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class MainActivity extends AppCompatActivity {
    public static final int ADD_CONTACT_REQUEST = 1;
    RecyclerView recyclerView;
    FloatingActionButton Fab;
    private ContactViewModel contactViewModel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final KontakAdapter adapter = new KontakAdapter();

        recyclerView.setAdapter(adapter);

        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this, InputKontak.class);
                startActivityForResult(add, ADD_CONTACT_REQUEST);
            }
        });

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.getListContact().observe(this, new Observer<List<Kontak>>() {
            @Override
            public void onChanged(List<Kontak> kontaks) {
                adapter.setContacts(kontaks);
                Toast.makeText(MainActivity.this, "onChange", Toast.LENGTH_SHORT).show();
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {


            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Yakin mau hapus ?").setTitle("Hapus Data");


            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int id = adapter.getKontakAt(viewHolder.getAdapterPosition()).getIdContact();
                    contactViewModel.deleteContact(id,MainActivity.this);
                }
            });

            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }).attachToRecyclerView(recyclerView);

}


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ADD_CONTACT_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(InputKontak.EXTRA_NAME);
            String email = data.getStringExtra(InputKontak.EXTRA_EMAIL);
            String phone = data.getStringExtra(InputKontak.EXTRA_PHONE);
            String address = data.getStringExtra(InputKontak.EXTRA_ADDRESS);

            Kontak contact = new Kontak(name,email,phone,address);

            contactViewModel.insertContact(contact,context);
        }
        else{
            Toast.makeText(context,"Saved Contact Not Success",Toast.LENGTH_SHORT).show();
        }
    }


}
