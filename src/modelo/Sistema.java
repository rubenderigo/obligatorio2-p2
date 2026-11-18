/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ruben Derigo - 287176
 */
public class Sistema implements Serializable {

    private ArrayList<Animal> animales = new ArrayList<>();
    private ArrayList<Familia> familias = new ArrayList<>();
    private ArrayList<Empresa> empresas = new ArrayList<>();

    public ArrayList<Animal> getAnimales() {
        return this.animales;
    }

    public ArrayList<Familia> getFamilias() {
        return this.familias;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public boolean existeAnimal(String codigo) {
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeFamilia(String cedula) {
        for (int i = 0; i < familias.size(); i++) {
            if (familias.get(i).getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeEmpresa(String rut) {
        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getRut().equals(rut)) {
                return true;
            }
        }
        return false;
    }

    public void agregarAnimal(Animal animal) {
        this.animales.add(animal);
    }

    public void agregarFamilia(Familia familia) {
        this.familias.add(familia);
    }

    public void agregarEmpresa(Empresa empresa) {
        this.empresas.add(empresa);
    }

    public int cantidadAnimalesConEstado(String estado) {
        int cantidad = 0;
        for (int i = 0; i < this.animales.size(); i++) {
            if (this.animales.get(i).getEstado().equals(estado)) {
                cantidad++;
            }
        }
        return cantidad;
    }

}
