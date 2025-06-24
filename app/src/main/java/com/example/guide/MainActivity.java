package com.example.guide;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView tvWhereStay = findViewById(R.id.tvWhereStay);
        //TextView tvDescription = findViewById(R.id.tvDescription);

        //Button buttonBase = findViewById(R.id.buttonBase);
        //Button buttonSanatori = findViewById(R.id.buttonSanatori);
        //Button buttonHotel = findViewById(R.id.buttonHotel);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setIconified(false);
        searchView.setQueryHint("Поиск");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Алтайский край");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /* to do
    public boolean onOptionItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show();
            return  true;
        }

        return super.onContextItemSelected(item);
    }*/
}