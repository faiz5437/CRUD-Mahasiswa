package com.example.crudmahasiswa;

public class DataModel {
    private int id;
    private String nim;
    private String nama;
    private String tgl;
    private String jk;

    public DataModel(int id, String nim, String nama, String tgl, String jk, String alamat) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.tgl = tgl;
        this.jk = jk;
        this.alamat = alamat;
    }

    private String alamat;



    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;

    }
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
