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
public class Donacion implements Serializable {

    private int numero;
    private Empresa empresa;
    private int tipo;
    private int kilos;

    public Donacion(int numero, Empresa empresa, int tipo, int kilos) {
        this.numero = numero;
        this.empresa = empresa;
        this.tipo = tipo;
        this.kilos = kilos;
    }

    public int getNumero() {
        return this.numero;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public int getTipo() {
        return this.tipo;
    }

    public int getKilos() {
        return this.kilos;
    }

    public String getNombreTipo() {
        String nombre;
        if (this.tipo == 1) {
            nombre = "Perros grandes";
        } else if (this.tipo == 2) {
            nombre = "Perros chicos";
        } else if (this.tipo == 3) {
            nombre = "Perros adultos";
        } else {
            nombre = "Otra";
        }
        return nombre;
    }

}
