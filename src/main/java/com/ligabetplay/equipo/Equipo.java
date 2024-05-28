package com.ligabetplay.equipo;

public class Equipo {
    int id;
    String nombreEquipo;
    int pj;
    int pg;
    int pp;
    int pe;
    int gf;
    int gc;
    int tp;
    int[] eqps;
    
    public Equipo(int id, String nombreEquipo) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.pj = 0;
        this.pg = 0;
        this.pp = 0;
        this.pe = 0;
        this.gf = 0;
        this.gc = 0;
        this.tp = 0;
        this.eqps = new int[]{};
    }

    public int getId(){
        return id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public int getPj() {
        return pj;
    }

    public void setPj(int pj) {
        this.pj += pj;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg += pg;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp += pp;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe += pe;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf += gf;
    }

    public int getGc() {
        return gc;
    }

    public void setGc(int gc) {
        this.gc += gc;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp += tp;
    }

    public int[] getEqps() {
        return eqps;
    }

    public void setEqps(int idE) {
        int[] listEqps = getEqps();
        int[] newArray = new int[listEqps.length+1];
        for (int i = 0; i < listEqps.length; i++) {
            newArray[i] = listEqps[i];
        }
        newArray[newArray.length-1] = idE;
        this.eqps = newArray;
    }

}