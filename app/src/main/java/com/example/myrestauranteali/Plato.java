package com.example.myrestauranteali;

public class Plato {
    private String nombre;
    private String tipo;
    private float promedioEstrellas;
    private int cantidadCalificaciones;
    private String descripcion;
    private int precio;

    public Plato(String nombre, String tipo, String descripcion, int precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.promedioEstrellas = 5.0f; // Por defecto
        this.cantidadCalificaciones = 0;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public float getPromedioEstrellas() {
        return promedioEstrellas;
    }

    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    // Calificaciones
    public void agregarCalificacion(float estrellas) {
        float total = promedioEstrellas * cantidadCalificaciones;
        cantidadCalificaciones++;
        promedioEstrellas = (total + estrellas) / cantidadCalificaciones;
    }
}
