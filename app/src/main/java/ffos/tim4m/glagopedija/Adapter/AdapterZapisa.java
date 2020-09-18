package ffos.tim4m.glagopedija.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ffos.tim4m.glagopedija.Klasa.Zapis;
import ffos.tim4m.glagopedija.R;

public class AdapterZapisa extends RecyclerView.Adapter<AdapterZapisa.Red>{
    private List<Zapis> podaci;
    private LayoutInflater mInflater;
    private AdapterZapisa.ItemClickListener mClickListener;

    // Podatke proslijedimo kroz konstruktor
    public AdapterZapisa(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    // napuni predložak red (datoteka red_liste__grupe.xml)
    @Override
    public AdapterZapisa.Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        View view = mInflater.inflate(R.layout.red_liste__zapisa, roditelj, false);
        return new AdapterZapisa.Red(view);
    }

    // Veže podatke za svaki red
    @Override
    public void onBindViewHolder(AdapterZapisa.Red red, int position) {
        Zapis z = podaci.get(position);
        red.naziv.setText(z.getNaziv());
        //red.ikona.setImageResource(k.getIkona());
    }

    // Ukupan broj redova (mora biti implementirano)
    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }


    // Pohranjuje i reciklira pogled kako se prolazi kroz listu
    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView naziv;

        Red(View itemView) {
            super(itemView);
            naziv = itemView.findViewById(R.id.naziv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // klikom na listu dobijemo samo poziciju koju stavku liste smo odabrali.
    // Ova metoda pomaže da na osnovu pozicije dobijemo cijeli objekt u toj stavci
    public Zapis getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<Zapis> itemList) {
        this.podaci = itemList;
    }

    // dopusti hvatanje odabira (klik/dotakni)
    public void setClickListener(AdapterZapisa.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // potrebno kako bi mogli hvatati klikove/dodire
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
