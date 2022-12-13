package com.example.crudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateBiodata extends AppCompatActivity {
    private TextInputEditText etNIM, etNama, etTgl, etJk, etAlamat;
    private AppCompatButton btnUpdate;
    private SQLHelper sqlHelper;
    private String nimMhs, namaMhs, tglMhs, jkMhs, alamatMhs;
    private int intId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);

        etNIM = findViewById(R.id.etNIM);
        etNama = findViewById(R.id.etNama);
        etTgl = findViewById(R.id.etTGL);
        etJk = findViewById(R.id.etJK);
        etAlamat = findViewById(R.id.etAlamat);
        btnUpdate = findViewById(R.id.btn_update);

        sqlHelper = new SQLHelper(this);

        intId = getIntent().getIntExtra("id", -1);
        nimMhs = getIntent().getStringExtra("nim");
        namaMhs = getIntent().getStringExtra("nama");
        tglMhs = getIntent().getStringExtra("tgl");
        jkMhs = getIntent().getStringExtra("jk");
        alamatMhs = getIntent().getStringExtra("alamat");

        etNIM.setText(nimMhs);
        etNama.setText(namaMhs);
        etTgl.setText(tglMhs);
        etJk.setText(jkMhs);
        etAlamat.setText(alamatMhs);

        btnUpdate.setOnClickListener(view -> {
            sqlHelper.updateData(String.valueOf(intId), etNIM.getText().toString(), etNama.getText().toString(),
                    etTgl.getText().toString(), etJk.getText().toString(), etAlamat.getText().toString());
            Toast.makeText(this, "Data Sudah Di Update", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });



    }
}