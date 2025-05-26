package com.example.myrestauranteali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnSedes = findViewById(R.id.btnSedes);
        Button btnContacto = findViewById(R.id.btnContacto);
        Button btnReservas = findViewById(R.id.btnReservas);
        Button btnConfiguracion = findViewById(R.id.btnConfiguracion);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistrar = findViewById(R.id.btnRegistro);

        btnMenu.setOnClickListener(view -> {
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
        });

        btnSedes.setOnClickListener(view -> {
            Intent i = new Intent(this, SedesActivity.class);
            startActivity(i);

        });

        btnContacto.setOnClickListener(view -> {
            Intent i = new Intent(this, ReservaActivity.class);
            startActivity(i);


        });

        btnReservas.setOnClickListener(view -> {
            Intent i = new Intent(this, ReservaAutomaticaActivity.class);
            startActivity(i);


        });

        btnConfiguracion.setOnClickListener(view -> {
            Intent i = new Intent(this, ConfiguracionActivity.class);
            startActivity(i);


        });

        btnLogin.setOnClickListener(view -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);


        });

        btnRegistrar.setOnClickListener(view -> {
            Intent i = new Intent(this, RegistroActivity.class);
            startActivity(i);


        });

        SharedPreferences sessionPrefs = getSharedPreferences("sesion", MODE_PRIVATE);
        boolean logueado = sessionPrefs.getBoolean("logueado", false);

        if (logueado) {
            String correo = sessionPrefs.getString("correo", "");
            SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
            String datos = prefs.getString(correo, null);

            if (datos != null) {
                String nombre = datos.split(";")[0];
                Intent intent = new Intent(this, PerfilActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("correo", correo);
                startActivity(intent);
                finish();
            }
        }

    }
}