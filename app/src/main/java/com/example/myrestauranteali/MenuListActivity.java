package com.example.myrestauranteali;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListActivity extends AppCompatActivity {

    private ListView listViewMenu;
    private Map<String, List<MenuItemInfo>> menuItems;

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

        List<MenuItemInfo> entradas = new ArrayList<>();
        entradas.add(new MenuItemInfo("Sopa de tomate", "Sopa casera con tomates frescos y especias", 12000, R.drawable.sopato));
        entradas.add(new MenuItemInfo("Ensalada César", "Lechuga, crutones, aderezo césar y queso parmesano", 15000, R.drawable.ensaladace));
        entradas.add(new MenuItemInfo("Bruschetta", "Pan tostado con tomate, ajo y albahaca", 10000, R.drawable.bruschetta));

        List<MenuItemInfo> bebidas = new ArrayList<>();
        bebidas.add(new MenuItemInfo("Coca-Cola", "Bebida gaseosa clásica", 5000, R.drawable.cocacola));
        bebidas.add(new MenuItemInfo("Jugo de naranja", "Natural, sin azúcar añadida", 7000, R.drawable.jugonaranja));
        bebidas.add(new MenuItemInfo("Limonada", "Refrescante, con limón natural", 6000, R.drawable.limonada));

        List<MenuItemInfo> platos = new ArrayList<>();
        platos.add(new MenuItemInfo("Pasta Alfredo", "Pasta cremosa con salsa Alfredo y queso parmesano", 22000, R.drawable.pastaalfre));
        platos.add(new MenuItemInfo("Filete de res", "Jugoso filete de res a la parrilla con salsa especial", 35000, R.drawable.fileteres));
        platos.add(new MenuItemInfo("Pollo a la plancha", "Pechuga de pollo a la plancha con especias y guarnición", 18000, R.drawable.pechuga));

        List<MenuItemInfo> postres = new ArrayList<>();
        postres.add(new MenuItemInfo("Tiramisú", "Postre italiano con capas de café y queso mascarpone", 12000, R.drawable.tiramisu));
        postres.add(new MenuItemInfo("Flan", "Flan casero con caramelo y textura cremosa", 9000, R.drawable.flan));
        postres.add(new MenuItemInfo("Brownie con helado", "Brownie de chocolate caliente con una bola de helado", 15000, R.drawable.brownieh));

        List<MenuItemInfo> licores = new ArrayList<>();
        licores.add(new MenuItemInfo("Vino tinto", "Vino tinto seco de la mejor calidad", 25000, R.drawable.vinotinto));
        licores.add(new MenuItemInfo("Whisky", "Whisky añejado con sabor suave y ahumado", 40000, R.drawable.whisky));
        licores.add(new MenuItemInfo("Tequila", "Tequila 100% agave, ideal para shots o cócteles", 30000, R.drawable.tequila));


        String category = getIntent().getStringExtra("category");

        List<MenuItemInfo> items = new ArrayList<>();
        if (category != null) {
            switch (category) {
                case "Entradas":
                    items = entradas;
                    break;
                case "Bebidas":
                    items = bebidas;
                    break;
                case "Platos":
                    items = platos;
                    break;
                case "Postres":
                    items = postres;
                    break;
                case "Licores":
                    items = licores;
                    break;
                default:
                    items = new ArrayList<>();
                    break;
            }
        }

            MenuItemAdapter adapter = new MenuItemAdapter(this, items);
            listViewMenu.setAdapter(adapter);

        List<MenuItemInfo> finalItems = items;

        listViewMenu.setOnItemClickListener((parent, view, position, id) -> {
            MenuItemInfo selectedItem = finalItems.get(position);

                View dialogView = getLayoutInflater().inflate(R.layout.dialog_detalle_plato, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                ((TextView) dialogView.findViewById(R.id.txtNombre)).setText(selectedItem.getNombre());
                ((TextView) dialogView.findViewById(R.id.txtDescripcion)).setText(selectedItem.getDescripcion());
                ((TextView) dialogView.findViewById(R.id.txtPrecio)).setText("$" + selectedItem.getPrecio());
                ((ImageView) dialogView.findViewById(R.id.imgPlato)).setImageResource(selectedItem.getImagenResId());

                builder.setView(dialogView);
                builder.setPositiveButton("Cerrar", null);
                builder.show();
            });
        }

    // Manejar el clic en el botón de retroceso
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }
}


