package ffos.tim4m.glagopedija.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ffos.tim4m.glagopedija.Adapter.AdapterGrupe;
import ffos.tim4m.glagopedija.Klasa.Grupa;
import ffos.tim4m.glagopedija.Menu.OAplikaciji;
import ffos.tim4m.glagopedija.Menu.ONama;
import ffos.tim4m.glagopedija.Menu.PopisLiterature;
import ffos.tim4m.glagopedija.R;

public class ActivityGrupe extends AppCompatActivity implements AdapterGrupe.ItemClickListener {

    private AdapterGrupe adapter;
    private RESTTask asyncTask;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String JEZIK = "";
    private String jezik;

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
        adapter = new AdapterGrupe(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        asyncTask = new RESTTask();
        asyncTask.execute( getString(R.string.REST_URL_GRUPE));

    }

    class RESTTask extends AsyncTask<String, Void, List<Grupa>> {
        protected List<Grupa> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<Grupa> vrati = null;
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
                Type listType = new TypeToken<ArrayList<Grupa>>() {
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

        protected void onPostExecute(List<Grupa> podaci) {
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
        Grupa g = adapter.getItem(position);

        switch (g.getId()){
            case 4: // id (int) latinice
                MessageBox("Trenutno prazno.");
                break;
            case 13: // id (int) kanona
                MessageBox("Trenutno prazno.");
                break;
            default:
                Intent intent = new Intent(this, ActivityKategorije.class);
                intent.putExtra("grupa", g);
                startActivity(intent);
                break;
        }
    }

    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*public void saveData(String jezik) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(JEZIK, jezik);

        editor.apply();
        MessageBox(JEZIK + " je odabran.");
    }

    public void loadData(int kategorija_id) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        jezik = sharedPreferences.getString(JEZIK, String.valueOf(R.string.jezik_aplikacije));
    }*/

}
