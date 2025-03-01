package com.example.myrestauranteali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SedesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Activar el botón de retroceso en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedes);

        ImageView imgEnvigado = findViewById(R.id.imgEnvigado);
        TextView txtDireccionEnvigado = findViewById(R.id.txtDireccionEnvigado);
        Button btnVerMapaEnvigado = findViewById(R.id.btnVerMapaEnvigado);

        imgEnvigado.setImageResource(R.drawable.sede_envigado);
        txtDireccionEnvigado.setText("Calle 37 Sur #35-06, Envigado");

        btnVerMapaEnvigado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanza Google Maps con la ubicación de la sede en Envigado
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=6.167257013805915,-75.58365855493402");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        // Configura la información de la sede en Medellín
        ImageView imgMedellin = findViewById(R.id.imgMedellin);
        TextView txtDireccionMedellin = findViewById(R.id.txtDireccionMedellin);
        Button btnVerMapaMedellin = findViewById(R.id.btnVerMapaMedellin);

        imgMedellin.setImageResource(R.drawable.sede_medellin);
        txtDireccionMedellin.setText("Carrera 43F #18-60, Medellín");

        btnVerMapaMedellin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanza Google Maps con la ubicación de la sede en Medellín
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=6.246228229270464, -75.56991334442355");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    // Manejar el clic en el botón de retroceso
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }
}
