package com.example.activityassignment5;

import java.io.Serializable;

public class Soal implements Serializable {
    private String soal;
    private String[] pilihan;
    private  String jawaban;
    private String nilai;

    public Soal(String soal, String[] pilihan, String jawaban, String nilai) {
        this.soal = soal;
        this.pilihan = pilihan;
        this.jawaban = jawaban;
        this.nilai = nilai;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String[] getPilihan() {
        return pilihan;
    }

    public void setPilihan(String[] pilihan) {
        this.pilihan = pilihan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}
