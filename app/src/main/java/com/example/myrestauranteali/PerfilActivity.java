package com.example.myrestauranteali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    TextView tvBienvenida, tvCorreo;
    Button btnCerrarSesion, btnIrResenas;
    LinearLayout layoutLogueado, layoutNoLogueado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        layoutLogueado = findViewById(R.id.layoutLogueado);
        layoutNoLogueado = findViewById(R.id.layoutNoLogueado);

        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvCorreo = findViewById(R.id.tvCorreo);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // Recuperar datos del Intent
        String nombre = getIntent().getStringExtra("nombre");
        String correo = getIntent().getStringExtra("correo");

        // Aquí decides qué layout mostrar
        if (nombre != null && correo != null && !nombre.isEmpty() && !correo.isEmpty()) {
            // Usuario logueado
            layoutLogueado.setVisibility(View.VISIBLE);
            layoutNoLogueado.setVisibility(View.GONE);

            tvBienvenida.setText("Bienvenido, " + nombre);
            tvCorreo.setText(correo);
        } else {
            // Usuario no logueado
            layoutLogueado.setVisibility(View.GONE);
            layoutNoLogueado.setVisibility(View.VISIBLE);
        }

        btnCerrarSesion.setOnClickListener(v -> {
            // Limpiar sesión
            SharedPreferences sessionPrefs = getSharedPreferences("sesion", MODE_PRIVATE);
            sessionPrefs.edit().clear().apply();

            // Volver al login
            Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
