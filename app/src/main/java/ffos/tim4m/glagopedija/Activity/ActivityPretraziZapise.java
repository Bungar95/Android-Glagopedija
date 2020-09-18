package ffos.tim4m.glagopedija.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

import ffos.tim4m.glagopedija.Adapter.AdapterZapisa;
import ffos.tim4m.glagopedija.Klasa.Zapis;
import ffos.tim4m.glagopedija.Menu.OAplikaciji;
import ffos.tim4m.glagopedija.Menu.ONama;
import ffos.tim4m.glagopedija.Menu.PopisLiterature;
import ffos.tim4m.glagopedija.R;

public class ActivityPretraziZapise extends AppCompatActivity implements AdapterZapisa.ItemClickListener {

    private AdapterZapisa adapter;
    private RESTTask asyncTask;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretrazi__zapise);
        Toolbar toolbar;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Pretraživač zapisa");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_chevron_left_24));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterZapisa(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setQueryHint("Unesite barem 3 slova");
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length()>=3){
                asyncTask = new RESTTask();
                asyncTask.execute("https://oziz.ffos.hr/nastava20192020/bungar_19/glagodedija/nazivZapisa/"+newText);
            }
                return true;
            }
        });
    }

    private class RESTTask extends AsyncTask<String, Void, List<Zapis>> {
        protected List<Zapis> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<Zapis> vrati = null;
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
                Type listType = new TypeToken<ArrayList<Zapis>>() {
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

        protected void onPostExecute(List<Zapis> podaci) {
            adapter.setPodaci(podaci);
            adapter.notifyDataSetChanged();
        }
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
            case R.id.pretrazi:
                intent = new Intent(this, ActivityPretraziZapise.class);
                startActivity(intent);
                return true;
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
        Zapis z = adapter.getItem(position);
        Intent intent = new Intent(this, ActivityDetalji.class);
        intent.putExtra("detalji", z);
        startActivity(intent);

    }
}
