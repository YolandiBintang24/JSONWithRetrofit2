package com.example.percobaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputKontak extends AppCompatActivity {
    private EditText editTextNama,editTextEmail,editTextAlamat,editTextNohp;
    Button btnSave;
    public static final String EXTRA_NAME = " com.example.percobaan.EXTRA_NAME";
    public static final String EXTRA_EMAIL = " com.example.percobaan.EXTRA_EMAIL";
    public static final String EXTRA_PHONE = " com.example.percobaan.EXTRA_PHONE";
    public static final String EXTRA_ADDRESS = " com.example.percobaan.EXTRA_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kontak);
        editTextNama = (EditText)findViewById(R.id.txtInputNama);
        editTextEmail = (EditText)findViewById(R.id.txtInputEmail);
        editTextAlamat = (EditText)findViewById(R.id.txtInputAlamat);
        editTextNohp = (EditText)findViewById(R.id.txtInputNohp);
        btnSave = (Button)findViewById(R.id.btnSaveData);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });


    }

    public void saveContact(){
        String nama = editTextNama.getText().toString();
        String email = editTextEmail.getText().toString();
        String  alamat = editTextAlamat.getText().toString();
        String nohp = editTextNohp.getText().toString();

        if(nama.trim().isEmpty() || nohp.trim().isEmpty()){
            Toast.makeText(this,"Please insert name and phone",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,nama);
        data.putExtra(EXTRA_EMAIL,email);
        data.putExtra(EXTRA_PHONE,nohp);
        data.putExtra(EXTRA_ADDRESS,alamat);

        setResult(RESULT_OK,data);
        finish();
    }



}
