package com.example.crudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<DataModel> dataModelArrayList;
    private SQLHelper sqlHelper;
    private AdapterData adapterData;
    private RecyclerView recyclerView;
    private FloatingActionButton fabTambah;
    private SwipeRefreshLayout srlData;
    private String TAG = "MainActivity";
    private static final String TABLE_NAME = "biodata";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvData);
        fabTambah = findViewById(R.id.fab_tambah);
        srlData = findViewById(R.id.swl_data);
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        }
        );
        fabTambah.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, CreateBiodata.class);
            startActivity(i);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        retrieveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
    }
    public void retrieveData(){
        dataModelArrayList = new ArrayList<>();
        sqlHelper = new SQLHelper(this);

        dataModelArrayList = sqlHelper.readData();
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = 1", null);
        cursor.moveToFirst();
        String data0 = cursor.getString(0);
        String data1 = cursor.getString(1);
        String data2 = cursor.getString(2);
        String data3 = cursor.getString(3);
        String data4 = cursor.getString(4);
        String data5 = cursor.getString(5);

//        String data = cursor.getString(1);
        Log.d(TAG, "retrieveData: currertye 0: "+ data0);
        Log.d(TAG, "retrieveData: currertye 1: "+ data1);
        Log.d(TAG, "retrieveData: currertye 2: "+ data2);
        Log.d(TAG, "retrieveData: currertye 3: "+ data3);
        Log.d(TAG, "retrieveData: currertye 4: "+ data4);
        Log.d(TAG, "retrieveData: currertye 5: "+ data5);
//        Log.d(TAG, "retrieveData: ReadData : "+ dataModelArrayList.toString());


        adapterData = new AdapterData(dataModelArrayList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();
        recyclerView.invalidate();
    }


}