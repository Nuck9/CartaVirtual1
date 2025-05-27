package com.example.myrestauranteali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ElegirCategoriaPlatoActivity extends AppCompatActivity {

    ListView listCategorias;
    String[] categorias = {"Entradas", "Platos", "Postres", "Bebidas" , "Licores"};  // Ajusta según tu menú

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Activar el botón de retroceso en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_categoria_plato);

        listCategorias = findViewById(R.id.listCategorias);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categorias);
        listCategorias.setAdapter(adapter);

        listCategorias.setOnItemClickListener((parent, view, position, id) -> {
            String categoriaSeleccionada = categorias[position];
            Intent intent = new Intent(ElegirCategoriaPlatoActivity.this, ElegirPlatoActivity.class);
            intent.putExtra("categoria", categoriaSeleccionada);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }
}
