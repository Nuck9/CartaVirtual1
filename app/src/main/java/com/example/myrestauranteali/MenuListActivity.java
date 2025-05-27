package com.example.myrestauranteali;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListActivity extends AppCompatActivity {

    private ListView listViewMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listViewMenu = findViewById(R.id.listViewMenu);
        String category = getIntent().getStringExtra("category");

        List<MenuItemInfo> items = new ArrayList<>();

        for (Plato plato : Catalogo.obtenerPlatos()) {
            if (plato.getTipo().equals(category)) {
                int imagen = obtenerImagenParaPlato(plato.getNombre());
                MenuItemInfo item = new MenuItemInfo(
                        plato.getNombre(),
                        plato.getDescripcion(),  // ✅ Se usa la descripción real
                        plato.getPrecio(),       // ✅ Se usa el precio real
                        imagen
                );
                item.setEstrellas(plato.getPromedioEstrellas());
                items.add(item);
            }
        }

        MenuItemAdapter adapter = new MenuItemAdapter(this, items);
        listViewMenu.setAdapter(adapter);

        listViewMenu.setOnItemClickListener((parent, view, position, id) -> {
            MenuItemInfo selectedItem = items.get(position);

            View dialogView = getLayoutInflater().inflate(R.layout.dialog_detalle_plato, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            ((TextView) dialogView.findViewById(R.id.txtNombre)).setText(selectedItem.getNombre());
            ((TextView) dialogView.findViewById(R.id.txtDescripcion)).setText(selectedItem.getDescripcion());
            ((TextView) dialogView.findViewById(R.id.txtPrecio)).setText("$" + selectedItem.getPrecio());
            ((ImageView) dialogView.findViewById(R.id.imgPlato)).setImageResource(selectedItem.getImagenResId());

            TextView txtEstrellas = dialogView.findViewById(R.id.txtEstrellas);
            if (txtEstrellas != null) {
                txtEstrellas.setText("★ " + String.format("%.1f", selectedItem.getEstrellas()));
            }

            builder.setView(dialogView);
            builder.setPositiveButton("Cerrar", null);
            builder.show();
        });
    }

    private int obtenerImagenParaPlato(String nombre) {
        switch (nombre) {
            case "Sopa de tomate":
                return R.drawable.sopato;
            case "Ensalada César":
                return R.drawable.ensaladace;
            case "Bruschetta":
                return R.drawable.bruschetta;
            case "Coca-Cola":
                return R.drawable.cocacola;
            case "Jugo de naranja":
                return R.drawable.jugonaranja;
            case "Limonada":
                return R.drawable.limonada;
            case "Pasta Alfredo":
                return R.drawable.pastaalfre;
            case "Filete de res":
                return R.drawable.fileteres;
            case "Pollo a la plancha":
                return R.drawable.pechuga;
            case "Tiramisú":
                return R.drawable.tiramisu;
            case "Flan":
                return R.drawable.flan;
            case "Brownie con helado":
                return R.drawable.brownieh;
            case "Vino tinto":
                return R.drawable.vinotinto;
            case "Whisky":
                return R.drawable.whisky;
            case "Tequila":
                return R.drawable.tequila;
            default:
                return R.drawable.ic_menu; // Imagen por defecto
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
