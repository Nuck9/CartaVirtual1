package com.example.myrestauranteali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Activar el botón de retroceso en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnEntradas = findViewById(R.id.btnEntradas);
        Button btnBebidas = findViewById(R.id.btnBebidas);
        Button btnPlatos = findViewById(R.id.btnPlatos);
        Button btnPostres = findViewById(R.id.btnPostres);
        Button btnLicores = findViewById(R.id.btnLicores);

        btnEntradas.setOnClickListener(v -> openMenuList("Entradas"));
        btnBebidas.setOnClickListener(v -> openMenuList("Bebidas"));
        btnPlatos.setOnClickListener(v -> openMenuList("Platos"));
        btnPostres.setOnClickListener(v -> openMenuList("Postres"));
        btnLicores.setOnClickListener(v -> openMenuList("Licores"));
    }

    private void openMenuList(String category) {
        Intent intent = new Intent(this, MenuListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }


    // Manejar el clic en el botón de retroceso
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }


}
