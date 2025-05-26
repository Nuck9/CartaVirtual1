package com.example.myrestauranteali; // Pon el paquete correcto

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class ReservaAutomaticaActivity extends AppCompatActivity {

    Spinner spinnerSede, spinnerHora;
    DatePicker datePicker;
    Button btnReservar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_automatica);

        spinnerSede = findViewById(R.id.spinnerSede);
        datePicker = findViewById(R.id.datePicker);
        spinnerHora = findViewById(R.id.spinnerHora);
        btnReservar = findViewById(R.id.btnReservar);

        // Listas para los spinners
        String[] sedes = {"Sede Centro", "Sede Norte", "Sede Sur"};
        String[] horarios = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00"};

        // Adaptadores para spinners
        ArrayAdapter<String> adapterSedes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sedes);
        adapterSedes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSede.setAdapter(adapterSedes);

        ArrayAdapter<String> adapterHoras = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horarios);
        adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHora.setAdapter(adapterHoras);

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fecha actual
                Calendar today = Calendar.getInstance();

                // Fecha seleccionada
                int selectedYear = datePicker.getYear();
                int selectedMonth = datePicker.getMonth();
                int selectedDay = datePicker.getDayOfMonth();

                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(selectedYear, selectedMonth, selectedDay);

                if (selectedDate.before(today)) {
                    Toast.makeText(ReservaAutomaticaActivity.this, "No puedes reservar una fecha pasada", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sede = spinnerSede.getSelectedItem().toString();
                String fecha = String.format(Locale.getDefault(), "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                String hora = spinnerHora.getSelectedItem().toString();

                if (estaOcupado(ReservaAutomaticaActivity.this, sede, fecha, hora)) {
                    Toast.makeText(ReservaAutomaticaActivity.this, "Horario no disponible, elige otro.", Toast.LENGTH_SHORT).show();
                } else {
                    // Pasar datos a la siguiente actividad para datos usuario
                    Intent intent = new Intent(ReservaAutomaticaActivity.this, DatosUsuarioActivity.class);
                    intent.putExtra("sede", sede);
                    intent.putExtra("fecha", fecha);
                    intent.putExtra("hora", hora);
                    startActivity(intent);
                }
            }
        });

    }

    // Función para guardar reserva en SharedPreferences
    public void guardarReserva(Context context, String sede, String fecha, String hora) {
        SharedPreferences prefs = context.getSharedPreferences("reservas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String key = sede + "_" + fecha + "_" + hora;
        editor.putString(key, "ocupado");
        editor.apply();
    }

    // Función para consultar si la reserva está ocupada
    public boolean estaOcupado(Context context, String sede, String fecha, String hora) {
        SharedPreferences prefs = context.getSharedPreferences("reservas", Context.MODE_PRIVATE);
        String key = sede + "_" + fecha + "_" + hora;
        String estado = prefs.getString(key, "libre");
        return estado.equals("ocupado");
    }
}
