package com.example.myrestauranteali;

public class MenuItemInfo {
    private String nombre;
    private String descripcion;
    private int precio;
    private int imagenResId;

    public MenuItemInfo(String nombre, String descripcion, int precio, int imagenResId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenResId = imagenResId;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getPrecio() { return precio; }
    public int getImagenResId() { return imagenResId; }

    private float estrellas;

    public float getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }

}


