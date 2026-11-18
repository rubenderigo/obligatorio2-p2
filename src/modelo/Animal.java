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
public class Animal implements Serializable {

    private String codigo;
    private String nombre;
    private String temperamento;
    private double peso;
    private String estado;

    public Animal(String codigo, String nombre, String temperamento, double peso, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.temperamento = temperamento;
        this.peso = peso;
        this.estado = estado;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTemperamento() {
        return this.temperamento;
    }

    public double getPeso() {
        return this.peso;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
