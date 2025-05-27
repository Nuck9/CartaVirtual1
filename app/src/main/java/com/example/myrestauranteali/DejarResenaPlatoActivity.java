package com.example.myrestauranteali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DejarResenaPlatoActivity extends AppCompatActivity {

    TextView tvInfoPlato;
    RatingBar ratingBarPlato;
    EditText etComentarioPlato;
    Button btnEnviarResenaPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dejar_resena_plato);

        tvInfoPlato = findViewById(R.id.tvInfoPlato);
        ratingBarPlato = findViewById(R.id.ratingBarPlato);
        etComentarioPlato = findViewById(R.id.etComentarioPlato);
        btnEnviarResenaPlato = findViewById(R.id.btnEnviarResenaPlato);

        String categoria = getIntent().getStringExtra("categoria");
        String plato = getIntent().getStringExtra("plato");

        tvInfoPlato.setText("Reseña para: " + plato + " (" + categoria + ")");

        btnEnviarResenaPlato.setOnClickListener(v -> {
            float estrellas = ratingBarPlato.getRating();
            String comentario = etComentarioPlato.getText().toString().trim();

            // Buscar el plato en el catálogo
            Plato platoSeleccionado = null;
            for (Plato p : Catalogo.obtenerPlatos()) {
                if (p.getNombre().equals(plato)) {
                    platoSeleccionado = p;
                    break;
                }
            }

            if (platoSeleccionado != null) {
                platoSeleccionado.agregarCalificacion(estrellas);
            }

            Toast.makeText(this, "¡Gracias por tu reseña!\n" +
                    estrellas + " estrellas\nComentario: " + comentario, Toast.LENGTH_LONG).show();

            // Devolver resultado para actualizar la UI
            Intent resultado = new Intent();
            resultado.putExtra("plato", plato);
            resultado.putExtra("nuevaCalificacion", estrellas);
            setResult(RESULT_OK, resultado);

            finish();
        });

    }
}
