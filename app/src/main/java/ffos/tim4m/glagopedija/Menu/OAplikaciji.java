package ffos.tim4m.glagopedija.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import ffos.tim4m.glagopedija.Activity.ActivityPretraziZapise;
import ffos.tim4m.glagopedija.Adapter.OaPagerAdapter;
import ffos.tim4m.glagopedija.R;

import androidx.viewpager2.widget.ViewPager2;

public class OAplikaciji extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_o__aplikaciji);
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

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new OaPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0: {
                        tab.setText("Projekt");
                        break;
                    }
                    case 1: {
                        tab.setText("Cilj");
                        break;
                    }
                    case 2: {
                        tab.setText("Sadržaj");
                        break;
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();

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
}
