package com.example.crudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class ReadBiodata extends AppCompatActivity {
    private Cursor cursor;
    String TAG = "Read Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_biodata);
        int intId = getIntent().getIntExtra("id", 1);

        SQLHelper sqlHelper = new SQLHelper(this);

        cursor = sqlHelper.readById(String.valueOf(intId));
        String data = cursor.getString(2);
        Log.d(TAG, "onCreate: gejeya "+ cursor);


    }
}