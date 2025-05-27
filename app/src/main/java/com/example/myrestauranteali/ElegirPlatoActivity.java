package com.example.myrestauranteali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ElegirPlatoActivity extends AppCompatActivity {

    TextView tvCategoria;
    ListView listPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Activar el botón de retroceso en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_plato);

        tvCategoria = findViewById(R.id.tvCategoria);
        listPlatos = findViewById(R.id.listPlatos);

        // Recibir categoría del Intent
        String categoria = getIntent().getStringExtra("categoria");
        tvCategoria.setText("Platos: " + categoria);

        // Filtrar platos del catálogo según la categoría recibida
        List<String> nombresPlatosFiltrados = new ArrayList<>();

        for (Plato plato : Catalogo.obtenerPlatos()) {
            if (plato.getTipo().equalsIgnoreCase(categoria)) {
                nombresPlatosFiltrados.add(plato.getNombre());
            }
        }

        String[] platosArray = nombresPlatosFiltrados.toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, platosArray);
        listPlatos.setAdapter(adapter);

        listPlatos.setOnItemClickListener((parent, view, position, id) -> {
            String platoSeleccionado = platosArray[position];
            Intent intent = new Intent(ElegirPlatoActivity.this, DejarResenaPlatoActivity.class);
            intent.putExtra("categoria", categoria);
            intent.putExtra("plato", platoSeleccionado);
            startActivityForResult(intent, 100); // Código de solicitud
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            String platoActualizado = data.getStringExtra("plato");
            float nuevaCalificacion = data.getFloatExtra("nuevaCalificacion", 0f);

            // Actualiza el plato en la lista (ya actualizado en el modelo, pero si tienes UI específica, refresca)
            // Por ejemplo, recargar la lista completa o actualizar solo el item afectado
            recargarLista();
        }
    }

    private void recargarLista() {
        List<String> nombresPlatosFiltrados = new ArrayList<>();

        String categoria = getIntent().getStringExtra("categoria");
        for (Plato plato : Catalogo.obtenerPlatos()) {
            if (plato.getTipo().equalsIgnoreCase(categoria)) {
                nombresPlatosFiltrados.add(plato.getNombre());
            }
        }

        String[] platosArray = nombresPlatosFiltrados.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, platosArray);
        listPlatos.setAdapter(adapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }
}

