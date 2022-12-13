package com.example.crudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
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

        adapterData = new AdapterData(dataModelArrayList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();
        recyclerView.invalidate();
    }


}