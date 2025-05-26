package com.example.myrestauranteali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etCorreoLogin, etContrasenaLogin;
    Button btnIniciarSesion;
    TextView tvIrARegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCorreoLogin = findViewById(R.id.etCorreoLogin);
        etContrasenaLogin = findViewById(R.id.etContrasenaLogin);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        tvIrARegistro = findViewById(R.id.tvIrARegistro);

        btnIniciarSesion.setOnClickListener(v -> {
            String correo = etCorreoLogin.getText().toString().trim();
            String contrasena = etContrasenaLogin.getText().toString().trim();

            if (correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
            String datos = prefs.getString(correo, null);

            if (datos == null) {
                Toast.makeText(this, "Usuario no registrado.", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] partes = datos.split(";");
            String nombreGuardado = partes[0];
            String contrasenaGuardada = partes[1];

            if (!contrasena.equals(contrasenaGuardada)) {
                Toast.makeText(this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Guardar sesión activa
            SharedPreferences sessionPrefs = getSharedPreferences("sesion", MODE_PRIVATE);
            sessionPrefs.edit()
                    .putBoolean("logueado", true)
                    .putString("correo", correo)
                    .apply();

            // Redirigir al perfil
            Intent intent = new Intent(this, PerfilActivity.class);
            intent.putExtra("nombre", nombreGuardado);
            intent.putExtra("correo", correo);
            startActivity(intent);
            finish();
        });

        tvIrARegistro.setOnClickListener(v -> {
            startActivity(new Intent(this, RegistroActivity.class));
        });
    }
}
