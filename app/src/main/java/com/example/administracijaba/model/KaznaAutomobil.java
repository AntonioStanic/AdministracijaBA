package com.example.administracijaba.model;

public class KaznaAutomobil {
    public String iznos;
    public String opis;
    public String slika;
    public String broj_tablica;

    public KaznaAutomobil() {
    }

    public KaznaAutomobil(String iznos, String opis, String slika, String broj_tablica) {
        this.iznos = iznos;
        this.opis = opis;
        this.slika = slika;
        this.broj_tablica = broj_tablica;
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

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getBroj_tablica() {
        return broj_tablica;
    }

    public void setBroj_tablica(String broj_tablica) {
        this.broj_tablica = broj_tablica;
    }
}
