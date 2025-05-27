package com.example.myrestauranteali;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MenuItemAdapter extends ArrayAdapter<MenuItemInfo> {
    private Context context;
    private List<MenuItemInfo> menuItems;

    public MenuItemAdapter(Context context, List<MenuItemInfo> items) {
        super(context, 0, items);
        this.context = context;
        this.menuItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItemInfo item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_menu_list, parent, false);
        }

        TextView txtNombre = convertView.findViewById(R.id.txtNombrePlato);
        ImageView imgPlato = convertView.findViewById(R.id.imgPlatoMiniatura);

        txtNombre.setText(item.getNombre());
        imgPlato.setImageResource(item.getImagenResId());

        return convertView;
    }
}

