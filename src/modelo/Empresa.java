/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Ruben Derigo - 287176
 */
public class Empresa implements Serializable {

    private String nombre;
    private String rut;
    private String direccion;

    public Empresa(String nombre, String rut, String direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRut() {
        return this.rut;
    }

    public String getDireccion() {
        return this.direccion;
    }

    @Override
    public String toString() {
        return this.nombre + " (" + rut + ")";
    }
}
