package com.example.myrestauranteali;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InicioFragment extends Fragment {

    Button btnMenu, btnSedes, btnContacto, btnDejarResena, btnReservaA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        btnMenu = view.findViewById(R.id.btnMenu);
        btnSedes = view.findViewById(R.id.btnSedes);
        btnContacto = view.findViewById(R.id.btnContacto);
        btnDejarResena = view.findViewById(R.id.btnDejarResena);
        btnReservaA = view.findViewById(R.id.btnReservaA);

        btnMenu.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), MenuActivity.class);
            startActivity(i);
        });

        btnSedes.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), SedesActivity.class);
            startActivity(i);
        });

        btnContacto.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), ReservaActivity.class);
            startActivity(i);
        });

        btnDejarResena.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), ElegirResenaActivity.class);
            startActivity(i);
        });

        btnReservaA.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), ReservaAutomaticaActivity.class);
            startActivity(i);
        });

        return view;

    }
}
