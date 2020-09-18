package ffos.tim4m.glagopedija.Klasa;

import java.io.Serializable;

public class Zapis implements Serializable {

    private int id;

    private String naziv;

    private int kategorija;

    private String mjesto;

    private String godina;

    private String pismo;

    private String sadrzaj;

    private String velicina;

    private String zanimljivosti;

    private String vrijeme;

    private String danasnjePocivaliste;

    private String jezik;

    private Zapis(int id,
                  String naziv,
                  int kategorija,
                  String mjesto,
                  String godina,
                  String pismo,
                  String sadrzaj,
                  String velicina,
                  String zanimljivosti,
                  String vrijeme,
                  String danasnjePocivaliste,
                  String jezik){

        this.id = id;
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.mjesto = mjesto;
        this.godina = godina;
        this.pismo = pismo;
        this.sadrzaj = sadrzaj;
        this.velicina = velicina;
        this.zanimljivosti = zanimljivosti;
        this.vrijeme = vrijeme;
        this.danasnjePocivaliste = danasnjePocivaliste;
        this.jezik = jezik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKategorija() {
        return kategorija;
    }

    public void setKategorija(int kategorija) {
        this.kategorija = kategorija;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public String getPismo() {
        return pismo;
    }

    public void setPismo(String pismo) {
        this.pismo = pismo;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getVelicina() {
        return velicina;
    }

    public void setVelicina(String velicina) {
        this.velicina = velicina;
    }

    public String getZanimljivosti() {
        return zanimljivosti;
    }

    public void setZanimljivosti(String zanimljivosti) {
        this.zanimljivosti = zanimljivosti;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public String getDanasnjePocivaliste() {
        return danasnjePocivaliste;
    }

    public void setDanasnjePocivaliste(String danasnjePocivaliste) {
        this.danasnjePocivaliste = danasnjePocivaliste;
    }

    public String getJezik() {
        return jezik;
    }

    public void setJezik(String jezik) {
        this.jezik = jezik;
    }
}
