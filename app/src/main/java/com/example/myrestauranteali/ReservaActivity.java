package com.example.myrestauranteali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReservaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        // Datos de las sedes
        String telefonoEnvigado = "+573001234567";
        String telefonoMedellin = "+573002345678";

        // Sede Envigado
        TextView txtEnvigado = findViewById(R.id.txtTelefonoEnvigado);
        txtEnvigado.setText("Teléfono: " + telefonoEnvigado);

        Button btnLlamarEnvigado = findViewById(R.id.btnLlamarEnvigado);
        Button btnWhatsAppEnvigado = findViewById(R.id.btnWhatsAppEnvigado);

        btnLlamarEnvigado.setOnClickListener(v -> llamar(telefonoEnvigado));
        btnWhatsAppEnvigado.setOnClickListener(v -> abrirWhatsApp(telefonoEnvigado, "Hola, quiero hacer una reserva en la sede de Envigado."));

        // Sede Medellín
        TextView txtMedellin = findViewById(R.id.txtTelefonoMedellin);
        txtMedellin.setText("Teléfono: " + telefonoMedellin);

        Button btnLlamarMedellin = findViewById(R.id.btnLlamarMedellin);
        Button btnWhatsAppMedellin = findViewById(R.id.btnWhatsAppMedellin);

        btnLlamarMedellin.setOnClickListener(v -> llamar(telefonoMedellin));
        btnWhatsAppMedellin.setOnClickListener(v -> abrirWhatsApp(telefonoMedellin, "Hola, quiero hacer una reserva en la sede de Medellín."));
    }

    // Metodo para hacer llamadas
    private void llamar(String telefono) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + telefono));
        startActivity(intent);
    }

    // Metodo para abrir WhatsApp con un mensaje predefinido
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

}
