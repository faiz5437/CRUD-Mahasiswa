package com.example.crudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CreateBiodata extends AppCompatActivity {
    private TextInputEditText etNIM, etNama, etTgl, etJk, etAlamat;
    private AppCompatButton btnSimpan;
    private SQLHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_biodata);

        etNIM = findViewById(R.id.etNIM);
        etNama = findViewById(R.id.etNama);
        etTgl = findViewById(R.id.etTGL);
        etJk = findViewById(R.id.etJK);
        etAlamat = findViewById(R.id.etAlamat);
        btnSimpan = findViewById(R.id.btn_simpan);

        sqlHelper = new SQLHelper(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueNim = etNIM.getText().toString();
                String valueNama = etNama.getText().toString();
                String valueTgl = etTgl.getText().toString();
                String valueJk = etJk.getText().toString();
                String valueAlamat = etAlamat.getText().toString();

                if (valueNim.isEmpty() && valueNama.isEmpty() && valueTgl.isEmpty() && valueJk.isEmpty() && valueAlamat.isEmpty()) {
                    Toast.makeText(CreateBiodata.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    sqlHelper.addNewData(valueNim, valueNama, valueTgl, valueJk, valueAlamat);
                    Toast.makeText(CreateBiodata.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CreateBiodata.this, MainActivity.class);
                    startActivity(i);
                }

            }
        });








    }
}