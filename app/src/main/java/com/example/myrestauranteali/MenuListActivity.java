package com.example.myrestauranteali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuListActivity extends AppCompatActivity {

    private ListView listViewMenu;
    private Map<String, List<String>> menuItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        // Activar el botón de retroceso en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listViewMenu = findViewById(R.id.listViewMenu);

        // Cargar la lista según la categoría recibida
        menuItems = new HashMap<>();
        menuItems.put("Entradas", List.of("Sopa de tomate", "Ensalada César", "Bruschetta"));
        menuItems.put("Bebidas", List.of("Coca-Cola", "Jugo de naranja", "Limonada"));
        menuItems.put("Platos", List.of("Pasta Alfredo", "Filete de res", "Pollo a la plancha"));
        menuItems.put("Postres", List.of("Tiramisú", "Flan", "Brownie con helado"));
        menuItems.put("Licores", List.of("Vino tinto", "Whisky", "Tequila"));

        String category = getIntent().getStringExtra("category");

        if (category != null && menuItems.containsKey(category)) {
            List<String> items = menuItems.get(category);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            listViewMenu.setAdapter(adapter);
        }
    }

    // Manejar el clic en el botón de retroceso
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }

}
