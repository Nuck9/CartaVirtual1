package com.example.myrestauranteali;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class SedesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedes);

        // =================== SEDE ENVIGADO ===================
        ImageView imgEnvigado = findViewById(R.id.imgEnvigado);
        TextView txtDireccionEnvigado = findViewById(R.id.txtDireccionEnvigado);
        TextView txtHorarioEnvigado = findViewById(R.id.txtHorarioEnvigado);
        TextView estadoEnvigado = findViewById(R.id.estadoEnvigado);
        Button btnVerMapaEnvigado = findViewById(R.id.btnVerMapaEnvigado);

        imgEnvigado.setImageResource(R.drawable.sede_envigado);
        txtDireccionEnvigado.setText("Calle 37 Sur #35-06, Envigado");
        txtHorarioEnvigado.setText("Horario: 9:00 AM - 10:00 PM");
        setEstadoAbiertoCerrado(estadoEnvigado, 9, 22);

        btnVerMapaEnvigado.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=6.167257013805915,-75.58365855493402");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        // =================== SEDE MEDELLÍN ===================
        ImageView imgMedellin = findViewById(R.id.imgMedellin);
        TextView txtDireccionMedellin = findViewById(R.id.txtDireccionMedellin);
        TextView txtHorarioMedellin = findViewById(R.id.txtHorarioMedellin);
        TextView estadoMedellin = findViewById(R.id.estadoMedellin);
        Button btnVerMapaMedellin = findViewById(R.id.btnVerMapaMedellin);

        imgMedellin.setImageResource(R.drawable.sede_medellin);
        txtDireccionMedellin.setText("Carrera 43F #18-60, Medellín");
        txtHorarioMedellin.setText("Horario: 10:00 AM - 11:00 PM");
        setEstadoAbiertoCerrado(estadoMedellin, 10, 23);

        btnVerMapaMedellin.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=6.246228229270464,-75.56991334442355");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
        String usuarioActivo = prefs.getString("usuario_activo", null);

    }

    private void setEstadoAbiertoCerrado(TextView textView, int horaApertura, int horaCierre) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int horaActual = calendar.get(Calendar.HOUR_OF_DAY);

        if (horaActual >= horaApertura && horaActual < horaCierre) {
            textView.setText("Estado: Abierto");
            textView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            textView.setText("Estado: Cerrado");
            textView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

