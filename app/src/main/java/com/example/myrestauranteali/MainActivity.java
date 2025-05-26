package com.example.myrestauranteali;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Cargar fragmento de inicio por defecto
        loadFragment(new InicioFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            int id = item.getItemId();
            if (id == R.id.nav_inicio) {
                fragment = new InicioFragment();
            } else if (id == R.id.nav_carrito) {
                fragment = new CarritoFragment();
            } else if (id == R.id.nav_perfil) {
                fragment = new PerfilFragment();
            }

            if (fragment != null) {
                loadFragment(fragment);
            }

            return true;
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
