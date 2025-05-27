package com.example.myrestauranteali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatosUsuarioActivity extends AppCompatActivity {

    EditText etNombre, etTelefono;
    Button btnConfirmar;

    String sede, fecha, hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        // Recibir datos de la actividad anterior
        Intent intent = getIntent();
        sede = intent.getStringExtra("sede");
        fecha = intent.getStringExtra("fecha");
        hora = intent.getStringExtra("hora");

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim();
                String telefono = etTelefono.getText().toString().trim();

                if (nombre.isEmpty() || telefono.isEmpty()) {
                    Toast.makeText(DatosUsuarioActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String key = sede + "_" + fecha + "_" + hora;

                SharedPreferences prefs = getSharedPreferences("reservas", MODE_PRIVATE);
                String estado = prefs.getString(key, "libre");

                if (estado.startsWith("ocupado")) {
                    Toast.makeText(DatosUsuarioActivity.this, "Ese horario ya fue reservado por otra persona.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si está libre, guardar los datos
                SharedPreferences.Editor editor = prefs.edit();
                String valor = "ocupado;" + nombre + ";" + telefono;
                editor.putString(key, valor);
                editor.apply();

                Toast.makeText(DatosUsuarioActivity.this, "Reserva confirmada para " + nombre, Toast.LENGTH_SHORT).show();
                finish(); // Opcional: cerrar esta pantalla
            }
        });

    }

    public void guardarReserva(Context context, String sede, String fecha, String hora, String nombre, String telefono) {
        SharedPreferences prefs = context.getSharedPreferences("reservas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String key = sede + "_" + fecha + "_" + hora;
        String datos = "Nombre: " + nombre + ", Tel: " + telefono;
        editor.putString(key, datos);
        editor.apply();
    }
}
