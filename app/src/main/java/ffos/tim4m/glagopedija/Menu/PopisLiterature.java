package ffos.tim4m.glagopedija.Menu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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

import ffos.tim4m.glagopedija.Activity.ActivityPretraziZapise;
import ffos.tim4m.glagopedija.Adapter.AdapterLiterature;
import ffos.tim4m.glagopedija.Klasa.Grupa;
import ffos.tim4m.glagopedija.Klasa.Literatura;
import ffos.tim4m.glagopedija.R;

public class PopisLiterature extends AppCompatActivity {

    private AdapterLiterature adapter;
    private RESTTask asyncTask;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String JEZIK = "";
    private String jezik;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_popis__literature);
        Toolbar toolbar;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Popis literature");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_chevron_left_24));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.lista_literature);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterLiterature(this);
        recyclerView.setAdapter(adapter);

        //loadData();

        asyncTask = new RESTTask();
        asyncTask.execute( getString(R.string.LITERATURA));
    }

    class RESTTask extends AsyncTask<String, Void, List<Literatura>> {
        protected List<Literatura> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<Literatura> vrati = null;
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
                Type listType = new TypeToken<ArrayList<Literatura>>() {
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

        protected void onPostExecute(List<Literatura> podaci) {
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
}
