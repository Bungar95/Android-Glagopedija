package ffos.tim4m.glagopedija.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ffos.tim4m.glagopedija.Klasa.Zapis;
import ffos.tim4m.glagopedija.Menu.OAplikaciji;
import ffos.tim4m.glagopedija.Menu.ONama;
import ffos.tim4m.glagopedija.Menu.PopisLiterature;
import ffos.tim4m.glagopedija.R;

public class ActivityDetalji extends AppCompatActivity {

    private Zapis zapis;
    private TextView naziv;
    private TextView mjesto;
    private TextView godina;
    private TextView velicina;
    private TextView jezik;
    private TextView pismo;
    private TextView sadrzaj;
    private TextView zanimljivosti;
    private Button nazad;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);
        Toolbar toolbar;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_chevron_left_24));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        naziv = findViewById(R.id.naziv2);
        mjesto = findViewById(R.id.mjesto2);
        godina = findViewById(R.id.godina2);
        velicina = findViewById(R.id.velicina2);
        jezik = findViewById(R.id.jezik2);
        pismo = findViewById(R.id.pismo2);
        sadrzaj = findViewById(R.id.sadrzaj2);
        zanimljivosti = findViewById(R.id.zanimljivosti2);
        nazad = findViewById(R.id.nazad);

        Intent i = getIntent();
        zapis = (Zapis) i.getSerializableExtra("detalji");

        setTitle(zapis.getNaziv());

        naziv.setText(zapis.getNaziv().isEmpty() ? "Trenutno nepoznato." : zapis.getNaziv());

        if (zapis.getMjesto().equals(" ")) {
            mjesto.setText("Trenutno nepoznato");
        } else {
            mjesto.setText(zapis.getMjesto());
        }

        if (zapis.getGodina().equals(" ")) {
            godina.setText("Trenutno nepoznato");
        } else {
            godina.setText(zapis.getGodina());
        }

        if (zapis.getVelicina().equals(" ")) {
            velicina.setText("Trenutno nepoznato");
        } else {
            velicina.setText(zapis.getVelicina());
        }

        if (zapis.getJezik().equals(" ")) {
            jezik.setText("Trenutno nepoznato");
        } else {
            jezik.setText(zapis.getJezik());
        }

        if (zapis.getPismo().equals(" ")) {
            pismo.setText("Trenutno nepoznato");
        } else {
            pismo.setText(zapis.getPismo());
        }

        if (zapis.getSadrzaj().equals(" ")) {
            sadrzaj.setText("Trenutno nepoznato");
        } else {
            sadrzaj.setText(zapis.getSadrzaj());
        }

        if (zapis.getZanimljivosti().equals(" ")) {
            zanimljivosti.setText("Trenutno nepoznato");
        } else {
            zanimljivosti.setText(zapis.getZanimljivosti());
        }

        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });
    }

    void nazad(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.o_aplikaciji:
                Intent intent = new Intent(this, OAplikaciji.class);
                startActivity(intent);
                return true;
            case R.id.o_nama:
                intent = new Intent(this, ONama.class);
                startActivity(intent);
                return true;
            /*case R.id.pretrazi:
                intent = new Intent(this, ActivityPretraziZapise.class);
                startActivity(intent);
                return true;*/
            case R.id.popis_literature:
                intent = new Intent(this, PopisLiterature.class);
                startActivity(intent);
                return true;
            case R.id.language_hr:
                //saveData("hr");
                return true;
            case R.id.language_en:
                //saveData("en");
                return true;
            case R.id.language_pl:
                //saveData("pl");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings, menu);
        return true;
    }
}
