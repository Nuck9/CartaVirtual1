package com.example.myrestauranteali;

import android.content.Intent;
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
    }
}