package com.example.myrestauranteali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ElegirResenaActivity extends AppCompatActivity {

    Button btnResenarPlato, btnResenarSede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Activar el botón de retroceso en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_resena);

        btnResenarPlato = findViewById(R.id.btnResenarPlato);

        btnResenarPlato.setOnClickListener(v -> {
            Intent intent = new Intent(ElegirResenaActivity.this, ElegirCategoriaPlatoActivity.class);
            startActivity(intent);
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }
}
