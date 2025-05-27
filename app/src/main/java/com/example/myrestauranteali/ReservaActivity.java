package com.example.myrestauranteali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ReservaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Activar el botón de retroceso en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        // Datos de las sedes
        String telefonoEnvigado = "+573141234567";
        String telefonoMedellin = "+573102345678";

        // Sede Envigado
        TextView txtEnvigado = findViewById(R.id.txtTelefonoEnvigado);
        txtEnvigado.setText("Teléfono: " + telefonoEnvigado);

        Button btnLlamarEnvigado = findViewById(R.id.btnLlamarEnvigado);
        Button btnWhatsAppEnvigado = findViewById(R.id.btnWhatsAppEnvigado);

        btnLlamarEnvigado.setOnClickListener(v -> llamar(telefonoEnvigado));
        btnWhatsAppEnvigado.setOnClickListener(v -> abrirWhatsApp(telefonoEnvigado, "Hola, quiero hacer una reserva personalizada en la sede de Envigado."));

        // Sede Medellín
        TextView txtMedellin = findViewById(R.id.txtTelefonoMedellin);
        txtMedellin.setText("Teléfono: " + telefonoMedellin);

        Button btnLlamarMedellin = findViewById(R.id.btnLlamarMedellin);
        Button btnWhatsAppMedellin = findViewById(R.id.btnWhatsAppMedellin);

        btnLlamarMedellin.setOnClickListener(v -> llamar(telefonoMedellin));
        btnWhatsAppMedellin.setOnClickListener(v -> abrirWhatsApp(telefonoMedellin, "Hola, quiero hacer una reserva personalizada en la sede de Medellín."));

        // Formulario de contacto
        EditText editNombre = findViewById(R.id.editNombre);
        EditText editCorreo = findViewById(R.id.editCorreo);
        EditText editMensaje = findViewById(R.id.editMensaje);
        Button btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString().trim();
            String correo = editCorreo.getText().toString().trim();
            String mensaje = editMensaje.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || mensaje.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí podrías enviar el mensaje a un servidor o email real
                Toast.makeText(this, "Mensaje enviado correctamente", Toast.LENGTH_LONG).show();
                editNombre.setText("");
                editCorreo.setText("");
                editMensaje.setText("");
            }
        });

        // Botones de redes sociales
        Button btnFacebook = findViewById(R.id.btnFacebook);
        Button btnInstagram = findViewById(R.id.btnInstagram);

        btnFacebook.setOnClickListener(v -> abrirRedSocial("https://www.facebook.com/MirestauranteAli"));
        btnInstagram.setOnClickListener(v -> abrirRedSocial("https://www.instagram.com/MirestauranteAli"));

    }

    private void abrirRedSocial(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // Metodo para hacer llamadas
    private void llamar(String telefono) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + telefono));
        startActivity(intent);
    }

    // Metodo para abrir WhatsApp con un mensaje
    private void abrirWhatsApp(String telefono, String mensaje) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone=" + telefono + "&text=" + Uri.encode(mensaje);
            intent.setData(Uri.parse(url));

            // Verificar si WhatsApp está instalado antes de abrirlo
            if (getPackageManager().resolveActivity(intent, 0) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Manejar el clic en el botón de retroceso
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad y regresa
        return true;
    }



}
