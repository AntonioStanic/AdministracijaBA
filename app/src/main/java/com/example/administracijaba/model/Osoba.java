package com.example.administracijaba.model;

public class Osoba {
    public String ime;
    public String prezime;
    public String slika;
    public String adresa;
    public String grad;
    public String zupanija;
    public String spol;
    public String jmbg;
    public String broj_osobne;

    public Osoba() {
    }

    public Osoba(String ime, String prezime, String slika, String adresa, String grad, String zupanija, String spol, String jmbg, String broj_osobne) {
        this.ime = ime;
        this.prezime = prezime;
        this.slika = slika;
        this.adresa = adresa;
        this.grad = grad;
        this.zupanija = zupanija;
        this.spol = spol;
        this.jmbg = jmbg;
        this.broj_osobne = broj_osobne;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getZupanija() {
        return zupanija;
    }

    public void setZupanija(String zupanija) {
        this.zupanija = zupanija;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBroj_osobne() {
        return broj_osobne;
    }

    public void setBroj_osobne(String broj_osobne) {
        this.broj_osobne = broj_osobne;
    }
}
