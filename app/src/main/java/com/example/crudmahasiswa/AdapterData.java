package com.example.crudmahasiswa;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolder>{

    private ArrayList<DataModel> dataModelArrayList;
    private Context ctx;

    public AdapterData(ArrayList<DataModel> dataModelArrayList, Context ctx) {
        this.dataModelArrayList = dataModelArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AdapterData.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.ViewHolder holder, int position) {


        DataModel dm = dataModelArrayList.get(position);
        holder.idTv.setText(String.valueOf(dm.getId()));
        holder.namaTv.setText(dm.getNama());
        holder.nimTv.setText(dm.getNim());

        holder.mainCard.setOnClickListener(view -> {
            Intent i = new Intent(ctx, ReadBiodata.class);
            i.putExtra("id", dm.getId());
            ctx.startActivity(i);
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, UpdateBiodata.class);

                i.putExtra("id", dm.getId());
                i.putExtra("nim", dm.getNim());
                i.putExtra("nama", dm.getNama());
                i.putExtra("tgl", dm.getTgl());
                i.putExtra("jk", dm.getJk());
                i.putExtra("alamat", dm.getAlamat());

                ctx.startActivity(i);
//                ((Activity)ctx).finish();
            }
        });

        holder.btnHapus.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
            alertDialog.setTitle(Html.fromHtml("<font color = '#FF0000'><strong>Yakin Ingin HAPUS SEMUA DATA ? </strong></font>"));
            alertDialog.setMessage(Html.fromHtml("<font color = '#FF0000'>Klik Ya Untuk <strong>Hapus Semua Data</strong></font>"))
                    .setIcon(R.drawable.ic_baseline_warning_amber_24)
                    .setCancelable(true)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLHelper sqlHelper = new SQLHelper(ctx);
                            sqlHelper.deleteData(String.valueOf(dm.getId()));
//                            Intent k = new Intent(ctx, MainActivity.class);
//                            ctx.startActivity(k);
                            ((MainActivity)ctx).retrieveData();

                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.show();
                    SQLHelper sqlHelper = new SQLHelper(ctx);
                    this.notifyDataSetChanged();
//                            SQLHelper sqlHelper = new SQLHelper(ctx);
//                            sqlHelper.deleteData(String.valueOf(dm.getId()));
//                            this.notifyDataSetChanged();
//            Intent i = new Intent(ctx, MainActivity.class);
//            ctx.startActivity(i);

        });

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView idTv, nimTv, namaTv;
        private ImageView btnEdit, btnHapus;
        private static final String TABLE_NAME = "biodata";
        private CardView mainCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainCard = itemView.findViewById(R.id.mainCard);
            idTv = itemView.findViewById(R.id.tvId);
            nimTv = itemView.findViewById(R.id.tvNIM);
            namaTv = itemView.findViewById(R.id.tvNama);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnHapus = itemView.findViewById(R.id.btn_delete);

            itemView.setOnClickListener(view -> {
                SQLHelper sqlHelper = new SQLHelper(ctx);
                SQLiteDatabase db = sqlHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = 1", null);
                cursor.moveToFirst();
                String data = cursor.getString(2);
                Log.d( "ViewHolder: cekdatabes ", data);
            });


        }

    }
}
