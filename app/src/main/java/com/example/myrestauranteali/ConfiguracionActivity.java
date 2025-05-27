package com.example.myrestauranteali;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class ConfiguracionActivity extends AppCompatActivity {

    Switch switchTema, switchNotificaciones;
    Button btnProbarNotificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Cargar preferencias de tema antes de mostrar UI
        SharedPreferences prefs = getSharedPreferences("configuracion", MODE_PRIVATE);
        boolean modoOscuro = prefs.getBoolean("temaOscuro", false);
        if (modoOscuro) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        switchTema = findViewById(R.id.switchTema);
        switchNotificaciones = findViewById(R.id.switchNotificaciones);

        // Estado guardado
        switchTema.setChecked(modoOscuro);
        switchNotificaciones.setChecked(prefs.getBoolean("notificaciones", true));

        switchTema.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("temaOscuro", isChecked);
            editor.apply();

            // Cambiar tema y reiniciar actividad
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
            recreate();
        });

        switchNotificaciones.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("notificaciones", isChecked);
            editor.apply();
        });

        btnProbarNotificacion = findViewById(R.id.btnProbarNotificacion);

        btnProbarNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("configuracion", MODE_PRIVATE);
                boolean notificacionesActivadas = prefs.getBoolean("notificaciones", true);

                if (notificacionesActivadas) {
                    mostrarNotificacionDePrueba();
                } else {
                    Toast.makeText(ConfiguracionActivity.this, "Las notificaciones están desactivadas.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    private void mostrarNotificacionDePrueba() {
        // Verifica permisos en Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso para notificaciones no concedido", Toast.LENGTH_SHORT).show();
                // Puedes pedir el permiso aquí si quieres que se solicite automáticamente:
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
        }

        // Crear canal de notificación en Android 8+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "canal_reservas",
                    "Reservas",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        // Crear y mostrar la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "canal_reservas")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notificación de Prueba")
                .setContentText("Esto es solo una prueba de notificación.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify((int) (System.currentTimeMillis() % 10000), builder.build());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de notificaciones concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se puede mostrar la notificación sin permiso", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
