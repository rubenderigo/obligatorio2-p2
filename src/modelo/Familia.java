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
public class Familia implements Serializable {

    private String cedula;
    private String apellido;
    private String celular;

    public Familia(String cedula, String apellido, String celular) {
        this.cedula = cedula;
        this.apellido = apellido;
        this.celular = celular;
    }

    public String getCedula() {
        return this.cedula;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getCelular() {
        return this.celular;
    }

    @Override
    public String toString() {
        return apellido + " (" + cedula + ")";
    }
}
