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
public class Adopcion implements Serializable {

    private int numero;
    private Animal animal;
    private Familia familia;
    private String comentarios;

    public Adopcion(int numero, Animal animal, Familia familia, String comentarios) {
        this.numero = numero;
        this.animal = animal;
        this.familia = familia;
        this.comentarios = comentarios;
    }

    public int getNumero() {
        return this.numero;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public Familia getFamilia() {
        return this.familia;
    }

    public String getComentarios() {
        return this.comentarios;
    }

}
