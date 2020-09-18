package ffos.tim4m.glagopedija.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ffos.tim4m.glagopedija.Klasa.Literatura;
import ffos.tim4m.glagopedija.R;

public class AdapterLiterature extends RecyclerView.Adapter<AdapterLiterature.Red> {

    private List<Literatura> podaci;
    private LayoutInflater mInflater;

    // Podatke proslijedimo kroz konstruktor
    public AdapterLiterature(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    // napuni predložak red (datoteka red_liste__grupe.xml)
    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        View view = mInflater.inflate(R.layout.red_liste__literature, roditelj, false);
        return new Red(view);
    }

    // Veže podatke za svaki red
    @Override
    public void onBindViewHolder(Red red, int position) {
        Literatura l = podaci.get(position);
        red.opis.setText(l.getOpis());
    }

    // Ukupan broj redova (mora biti implementirano)
    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }


    // Pohranjuje i reciklira pogled kako se prolazi kroz listu
    public class Red extends RecyclerView.ViewHolder {
        private TextView opis;

        Red(View itemView) {
            super(itemView);
            opis = itemView.findViewById(R.id.opis);
        }

    }

    // klikom na listu dobijemo samo poziciju koju stavku liste smo odabrali.
    // Ova metoda pomaže da na osnovu pozicije dobijemo cijeli objekt u toj stavci
    public Literatura getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<Literatura> itemList) {
        this.podaci = itemList;
    }

}

