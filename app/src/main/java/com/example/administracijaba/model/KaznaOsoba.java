package com.example.administracijaba.model;

public class KaznaOsoba {
    public String iznos;
    public String opis;
    public String broj_osobne;

    public KaznaOsoba() {
    }

    public KaznaOsoba(String iznos, String opis, String broj_osobne) {
        this.iznos = iznos;
        this.opis = opis;
        this.broj_osobne = broj_osobne;
    }

    public String getIznos() {
        return iznos;
    }

    public void setIznos(String iznos) {
        this.iznos = iznos;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getBroj_osobne() {
        return broj_osobne;
    }

    public void setBroj_osobne(String broj_osobne) {
        this.broj_osobne = broj_osobne;
    }
}
