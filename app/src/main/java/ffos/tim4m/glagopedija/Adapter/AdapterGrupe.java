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

import ffos.tim4m.glagopedija.Klasa.Grupa;
import ffos.tim4m.glagopedija.R;

public class AdapterGrupe extends RecyclerView.Adapter<AdapterGrupe.Red> {

    private List<Grupa> podaci;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Podatke proslijedimo kroz konstruktor
    public AdapterGrupe(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    // napuni predložak red (datoteka red_liste__grupe.xml)
    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        View view = mInflater.inflate(R.layout.red_liste__grupe, roditelj, false);
        return new Red(view);
    }

    // Veže podatke za svaki red
    @Override
    public void onBindViewHolder(Red red, int position) {
        Grupa g = podaci.get(position);
        red.naziv.setText(g.getNaziv());
        switch (g.getId()){
            case 2:
            {
                red.ikona.setImageResource(R.drawable.pic1_glagoljica);
                break;
            }
            case 3:
            {
                red.ikona.setImageResource(R.drawable.pic1_cirilica);
                break;
            }
            default:
                red.ikona.setImageResource(R.drawable.kategorija);
        }
    }

    // Ukupan broj redova (mora biti implementirano)
    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }


    // Pohranjuje i reciklira pogled kako se prolazi kroz listu
    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView naziv;
        private ImageView ikona;

        Red(View itemView) {
            super(itemView);
            naziv = itemView.findViewById(R.id.naziv);
            ikona = itemView.findViewById(R.id.ikona);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // klikom na listu dobijemo samo poziciju koju stavku liste smo odabrali.
    // Ova metoda pomaže da na osnovu pozicije dobijemo cijeli objekt u toj stavci
    public Grupa getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<Grupa> itemList) {
        this.podaci = itemList;
    }

    // dopusti hvatanje odabira (klik/dotakni)
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // potrebno kako bi mogli hvatati klikove/dodire
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

