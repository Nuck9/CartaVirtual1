package com.example.myrestauranteali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    EditText etNombre, etCorreo, etContrasena, etConfirmarContrasena;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etConfirmarContrasena = findViewById(R.id.etConfirmarContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String contrasena = etContrasena.getText().toString().trim();
            String confirmar = etConfirmarContrasena.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!contrasena.equals(confirmar)) {
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
            if (prefs.contains(correo)) {
                Toast.makeText(this, "Ya existe un usuario con ese correo.", Toast.LENGTH_SHORT).show();
                return;
            }

            String datos = nombre + ";" + contrasena;
            prefs.edit().putString(correo, datos).apply();

            Toast.makeText(this, "Registro exitoso. Ahora puedes iniciar sesión.", Toast.LENGTH_SHORT).show();

            // Redirige al login después del registro
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
