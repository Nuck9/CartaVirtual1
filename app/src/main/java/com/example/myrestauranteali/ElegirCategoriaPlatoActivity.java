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
}
