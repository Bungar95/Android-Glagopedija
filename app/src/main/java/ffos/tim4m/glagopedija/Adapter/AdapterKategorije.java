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

import ffos.tim4m.glagopedija.Klasa.Kategorija;
import ffos.tim4m.glagopedija.R;

public class AdapterKategorije extends RecyclerView.Adapter<AdapterKategorije.Red> {

    private List<Kategorija> podaci;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    int[] listaIkonaGlagoljica = {R.drawable.pic1_glagoljica, R.drawable.pic2_glagoljica, R.drawable.pic3_glagoljica,
            R.drawable.pic4_glagoljica, R.drawable.pic5_glagoljica, R.drawable.pic6_glagoljica, R.drawable.pic7_glagoljica,
            R.drawable.pic8_glagoljica, R.drawable.pic9_glagoljica, R.drawable.pic10_glagoljica};

    int[] listaIkonaCirilica = {R.drawable.pic1_cirilica, R.drawable.pic2_cirilica, R.drawable.pic3_cirilica,
            R.drawable.pic4_cirilica, R.drawable.pic5_cirilica, R.drawable.pic6_cirilica, R.drawable.pic7_cirilica};

    int[] listaIkonaLatinica = {}; //trenutno prazni
    int[] listaIkonaKanon = {}; //trenutno prazni

    // Podatke proslijedimo kroz konstruktor
    public AdapterKategorije(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    // napuni predložak red (datoteka red_liste__grupe.xml)
    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        View view = mInflater.inflate(R.layout.red_liste__kategorije, roditelj, false);
        return new Red(view);
    }

    // Veže podatke za svaki red
    @Override
    public void onBindViewHolder(Red red, int position) {
        Kategorija k = podaci.get(position);
        red.naziv.setText(k.getNaziv());

        switch (k.getGrupa()){
            case 2:
                {
                    red.ikona.setImageResource(listaIkonaGlagoljica[k.getId()-1]);
                    break;
                }
            case 3:
                {
                    red.ikona.setImageResource(listaIkonaCirilica[k.getId()-11]); // cirilicin ID se u bazi nastavlja nakon glagoljice, kojih je 10 pa je zato -11
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
    public Kategorija getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<Kategorija> itemList) {
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

