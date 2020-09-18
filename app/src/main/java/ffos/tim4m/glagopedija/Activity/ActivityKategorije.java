package ffos.tim4m.glagopedija.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ffos.tim4m.glagopedija.Adapter.AdapterKategorije;
import ffos.tim4m.glagopedija.Klasa.Grupa;
import ffos.tim4m.glagopedija.Klasa.Kategorija;
import ffos.tim4m.glagopedija.Menu.OAplikaciji;
import ffos.tim4m.glagopedija.Menu.ONama;
import ffos.tim4m.glagopedija.Menu.PopisLiterature;
import ffos.tim4m.glagopedija.R;

public class ActivityKategorije extends AppCompatActivity implements AdapterKategorije.ItemClickListener {

    private AdapterKategorije adapter;
    private ActivityKategorije.RESTTask asyncTask;
    private Grupa intent_grupa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
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

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterKategorije(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        intent_grupa = (Grupa)intent.getSerializableExtra("grupa");
        int grupa_id = intent_grupa.getId();
        asyncTask = new RESTTask();

        setTitle(intent_grupa.getNaziv());

        switch (grupa_id){
            case 13: // Kanon staroslavenskih spisa
                break;
            case 2: // Glagoljica
                asyncTask.execute( getString(R.string.REST_URL_KATEGORIJA)+"grupa=2&jezik=hr");
                break;
            case 3: // Ćirilica
                asyncTask.execute( getString(R.string.REST_URL_KATEGORIJA)+"grupa=3&jezik=hr");
                break;
            case 4: // Latinica
                break;
        }


    }

    private class RESTTask extends AsyncTask<String, Void, List<Kategorija>> {
        protected List<Kategorija> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<Kategorija> vrati = null;
            try {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                Type listType = new TypeToken<ArrayList<Kategorija>>() {
                }.getType();
                vrati = new Gson().fromJson(reader, listType);
                reader.close();
                streamReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return vrati;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<Kategorija> podaci) {
            adapter.setPodaci(podaci);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings, menu);
        return true;
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
    public void onItemClick(View view, int position) {
        Kategorija k = adapter.getItem(position);

        if (k.getGrupa() == 3) {
            MessageBox(k.getNaziv() + " je trenutno prazno.");
        } else {
            Intent intent = new Intent(this, ActivityZapisa.class);
            intent.putExtra("kategorija", k);
            intent.putExtra("grupa", intent_grupa);
            startActivity(intent);
        }
    }

    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
