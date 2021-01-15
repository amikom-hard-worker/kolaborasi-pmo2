package com.hardwoker.database;

public class DatabaseTB {
    String kunci;
    String isi;

    public DatabaseTB(){

    }
    public DatabaseTB(String kunci, String isi){
        this.kunci = kunci;
        this.isi = isi;
    }
    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
