package com.example.myrestauranteali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PerfilFragment extends Fragment {

    LinearLayout layoutNoLogueado, layoutLogueado;
    Button btnIniciarSesion, btnRegistrarse, btnCerrarSesion;
    TextView tvNombreUsuario, tvCorreoUsuario;
    SharedPreferences prefs;

    ImageButton btnConfiguracion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        btnConfiguracion = view.findViewById(R.id.btnConfiguracion);

        btnConfiguracion.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), ConfiguracionActivity.class));
        });

        // Inicializar views
        layoutNoLogueado = view.findViewById(R.id.layoutNoLogueado);
        layoutLogueado = view.findViewById(R.id.layoutLogueado);
        tvNombreUsuario = view.findViewById(R.id.tvNombre);
        tvCorreoUsuario = view.findViewById(R.id.tvCorreo);
        btnIniciarSesion = view.findViewById(R.id.btnIrLogin);
        btnRegistrarse = view.findViewById(R.id.btnIrRegistro);
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);

        // SharedPreferences
        prefs = requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE);

        // Botones
        btnCerrarSesion.setOnClickListener(v -> {
            prefs.edit().clear().apply();
            checkSesion();
        });

        btnIniciarSesion.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), LoginActivity.class)));

        btnRegistrarse.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), RegistroActivity.class)));

        // Revisar sesión
        checkSesion();

        return view;
    }

    private void checkSesion() {
        boolean logueado = prefs.getBoolean("logueado", false);

        if (!logueado) {
            layoutNoLogueado.setVisibility(View.VISIBLE);
            layoutLogueado.setVisibility(View.GONE);
        } else {
            layoutNoLogueado.setVisibility(View.GONE);
            layoutLogueado.setVisibility(View.VISIBLE);

            String nombre = prefs.getString("nombre", "Sin nombre");
            String correo = prefs.getString("correo", "Sin correo");

            tvNombreUsuario.setText("Nombre: " + nombre);
            tvCorreoUsuario.setText("Correo: " + correo);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkSesion();
    }
}
