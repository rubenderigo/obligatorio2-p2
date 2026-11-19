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
    private ArrayList<Adopcion> adopciones = new ArrayList<>();
    private int proximoNumeroAdopcion = 1;

    public ArrayList<Animal> getAnimales() {
        return this.animales;
    }

    public ArrayList<Familia> getFamilias() {
        return this.familias;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public ArrayList<Adopcion> getAdopciones() {
        return this.adopciones;
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

    public int getProximoNumeroAdopcion() {
        return this.proximoNumeroAdopcion;
    }

    public ArrayList<Animal> getAnimalesDisponibles() {
        ArrayList<Animal> disponibles = new ArrayList<>();
        for (int i = 0; i < this.animales.size(); i++) {
            if (this.animales.get(i).getEstado().equals("Disponible para adoptar")) {
                disponibles.add(this.animales.get(i));
            }
        }
        return disponibles;
    }

    public void realizarAdopcion(Animal animal, Familia familia, String comentarios) {
        Adopcion adopcion = new Adopcion(this.proximoNumeroAdopcion, animal, familia, comentarios);
        this.adopciones.add(adopcion);
        this.proximoNumeroAdopcion++;
        animal.setEstado("Entregado a familia");
    }

    public ArrayList<Animal> getAnimalesNoEntregados() {
        ArrayList<Animal> noEntregados = new ArrayList<>();
        for (int i = 0; i < this.animales.size(); i++) {
            if (!this.animales.get(i).getEstado().equals("Entregado a familia")) {
                noEntregados.add(this.animales.get(i));
            }
        }
        return noEntregados;
    }

    public void cambiarEstado(Animal animal) {
        if (animal.getEstado().equals("Disponible para adoptar")) {
            animal.setEstado("No disponible para adoptar");
        } else {
            animal.setEstado("Disponible para adoptar");
        }
    }

    public void anularAdopcion(Adopcion adopcion) {
        adopcion.getAnimal().setEstado("No disponible para adoptar");
        this.adopciones.remove(adopcion);
    }
}
