package com.example.myrestauranteali;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private static final List<Plato> platos = new ArrayList<>();

    // Catalogo.java
    static {
        platos.add(new Plato("Sopa de tomate", "Entradas", "Sopa cremosa de tomate con hierbas frescas", 12000));
        platos.add(new Plato("Ensalada César", "Entradas", "Lechuga romana, aderezo césar, crutones y queso parmesano", 13500));
        platos.add(new Plato("Bruschetta", "Entradas", "Pan tostado con tomate, albahaca y ajo", 11000));

        platos.add(new Plato("Coca-Cola", "Bebidas", "Refresco clásico servido frío", 5000));
        platos.add(new Plato("Jugo de naranja", "Bebidas", "Jugo natural de naranja exprimida", 6000));
        platos.add(new Plato("Limonada", "Bebidas", "Refrescante limonada casera", 5500));

        platos.add(new Plato("Pasta Alfredo", "Platos", "Pasta en salsa cremosa de queso parmesano", 22000));
        platos.add(new Plato("Filete de res", "Platos", "Corte de carne a la parrilla con guarnición", 30000));
        platos.add(new Plato("Pollo a la plancha", "Platos", "Pechuga de pollo asada con ensalada", 21000));

        platos.add(new Plato("Tiramisú", "Postres", "Postre italiano de café y queso mascarpone", 14000));
        platos.add(new Plato("Flan", "Postres", "Flan de huevo casero con caramelo", 10000));
        platos.add(new Plato("Brownie con helado", "Postres", "Brownie de chocolate caliente con bola de helado", 16000));

        platos.add(new Plato("Vino tinto", "Licores", "Copa de vino tinto de la casa", 15000));
        platos.add(new Plato("Whisky", "Licores", "Trago de whisky importado", 18000));
        platos.add(new Plato("Tequila", "Licores", "Shot de tequila con sal y limón", 16000));
    }



    public static List<Plato> obtenerPlatos() {
        return platos;
    }

    public static Plato buscarPorNombre(String nombre) {
        for (Plato p : platos) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }
}

