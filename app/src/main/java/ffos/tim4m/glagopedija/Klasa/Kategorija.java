package ffos.tim4m.glagopedija.Klasa;

import java.io.Serializable;

public class Kategorija implements Serializable {

    private int id;
    private String naziv;
    private int grupa;

    private Kategorija(int id, String naziv, int grupa){

        this.id = id;
        this.naziv = naziv;
        this.grupa = grupa;

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

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

}
