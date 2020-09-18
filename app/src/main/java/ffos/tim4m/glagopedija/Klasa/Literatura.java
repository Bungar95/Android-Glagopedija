package ffos.tim4m.glagopedija.Klasa;

public class Literatura {

    private int id;
    private String opis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Literatura(int id, String opis) {
        this.id = id;
        this.opis = opis;
    }
}
