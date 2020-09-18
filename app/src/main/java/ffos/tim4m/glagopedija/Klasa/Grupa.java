package ffos.tim4m.glagopedija.Klasa;

import java.io.Serializable;

public class Grupa implements Serializable {

    private int id;
    private String naziv;

    public Grupa(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }
    public int getId() {
        return id;
    }

    public void setId(int Grupa) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}