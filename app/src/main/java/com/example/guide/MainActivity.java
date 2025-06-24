package com.example.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guide.dialog.DialogInfo;
import com.example.guide.fragment.HomeFragment;
import com.example.guide.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        View headerView = navigationView.getHeaderView(0);

        TextView emailTextView = headerView.findViewById(R.id.user_name_header);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            if (email != null && !email.isEmpty()) {
                emailTextView.setText(email);
            } else {
                emailTextView.setText("Email не указан");
            }
        } else {
            emailTextView.setText("Гость");
        }
        if (currentUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        FirebaseApp.initializeApp(this);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loadFragment(new HomeFragment());

        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        // Установка кнопки меню (burger)
        toolbar.setNavigationIcon(R.drawable.icon_menu);
        toolbar.setNavigationOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.boottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                loadFragment(new HomeFragment());
            }else if (id == R.id.search) {
                loadFragment(new SearchFragment());
            } else if (id == R.id.map) {
                loadFragment(new MapFragment());
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        View headerView = navigationView.getHeaderView(0);

        int id = item.getItemId();
        if (id == R.id.home) {
            loadFragment(new HomeFragment());
        } else if (id == R.id.nav_settings) {
            //loadFragment(new SettingsFragment());
        } else if (id == R.id.nav_about) {
            showDialog(headerView);
        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Если есть фрагменты в BackStack — закрываем их
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                drawerLayout.openDrawer(navigationView);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog(View v) {
        DialogInfo dialogInfo = new DialogInfo();
        dialogInfo.show(getSupportFragmentManager(), "custom");
    }
}