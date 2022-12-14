package com.example.crudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ReadBiodata extends AppCompatActivity {
    private Cursor cursor;
    private TextView tvNim, tvNama, tvTgl, tvJk, tvAlamat, tvNama1, tvNim1;
    String TAG = "Read Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_biodata);
        tvNim = findViewById(R.id.tv_nim);
        tvNim1 = findViewById(R.id.tv_nim1);
        tvNama = findViewById(R.id.tv_nama);
        tvNama1 = findViewById(R.id.tv_nama1);
        tvTgl = findViewById(R.id.tv_tgl);
        tvJk = findViewById(R.id.tv_jk);
        tvAlamat = findViewById(R.id.tv_alamat);


        int intId = getIntent().getIntExtra("id", 1);

        SQLHelper sqlHelper = new SQLHelper(this);

        cursor = sqlHelper.readById(String.valueOf(intId));
        cursor.moveToFirst();
        String idString = cursor.getString(0);
        String nimString = cursor.getString(1);
        String namaString = cursor.getString(2);
        String tglString = cursor.getString(3);
        String jkString = cursor.getString(4);
        String alamatString = cursor.getString(5);
        Log.d(TAG, "onCreate: gejaya " + jkString);

        tvNim.setText(nimString);
        tvNim1.setText(nimString);
        tvNama.setText(namaString);
        tvNama1.setText(namaString);
        tvTgl.setText(tglString);
        tvJk.setText(jkString);
        tvAlamat.setText(alamatString);

//        if(cursor!=null && cursor.getCount() > 0) {
//            if (cursor.moveToFirst()) {
//                do {
//                    String data = cursor.getString(2);
//                    String data1 = String.valueOf(cursor.getColumnIndex("jk"));
//                    String data2 = String.valueOf(cursor.getColumnIndex("id"));
//                    Log.d(TAG, "onCreate: gejaya "+ data);
//                } while (cursor.moveToNext());
//                cursor.close();
//            }
//
//        }
//        cursor.moveToFirst();
//        String idString = cursor.getString(0);
//        String nimString = cursor.getString(1);
//        String namaString = cursor.getString(2);
//        String tglString = cursor.getString(3);
//        String jkString = cursor.getString(4);
//        String alamatString = cursor.getString(5);


    }
}