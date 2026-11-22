/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.File;
import java.util.ArrayList;
import modelo.Animal;
import modelo.DatoInvalidoException;
import modelo.Sistema;
import modelo.Familia;
import modelo.Empresa;
import modelo.Adopcion;
import modelo.Donacion;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Ruben Derigo - 287176
 */
public class Controlador {

    private Sistema sistema = new Sistema();

    public void altaAnimal(String codigo, String nombre, String temperamento, String textoPeso, boolean disponible) throws DatoInvalidoException {
        if (codigo.equals("") || nombre.equals("") || temperamento.equals("") || textoPeso.equals("")) {
            throw new DatoInvalidoException("Debe completar todos los campos");
        }
        if (this.sistema.existeAnimal(codigo)) {
            throw new DatoInvalidoException("Ya existe un animal con ese codigo");
        }

        double peso;
        try {
            peso = Double.parseDouble(textoPeso);
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("El peso debe ser un numero");
        }
        if (peso <= 0) {
            throw new DatoInvalidoException("El peso debe ser mayor a cero");
        }

        String estado;
        if (disponible) {
            estado = "Disponible para adoptar";
        } else {
            estado = "No disponible para adoptar";
        }

        Animal animal = new Animal(codigo, nombre, temperamento, peso, estado);
        this.sistema.agregarAnimal(animal);
    }

    public void altaFamilia(String cedula, String apellido, String celular) throws DatoInvalidoException {
        if (cedula.equals("") || apellido.equals("")) {
            throw new DatoInvalidoException("Debe completar la cedula y el apellido");
        }
        if (this.sistema.existeFamilia(cedula)) {
            throw new DatoInvalidoException("Ya existe una familia con esa cedula");
        }
        Familia familia = new Familia(cedula, apellido, celular);
        this.sistema.agregarFamilia(familia);
    }

    public void altaEmpresa(String nombre, String rut, String direccion) throws DatoInvalidoException {
        if (nombre.equals("") || rut.equals("") || direccion.equals("")) {
            throw new DatoInvalidoException("Debe completar todos los campos");
        }
        if (this.sistema.existeEmpresa(rut)) {
            throw new DatoInvalidoException("Ya existe una empresa con ese RUT");
        }
        Empresa empresa = new Empresa(nombre, rut, direccion);
        this.sistema.agregarEmpresa(empresa);
    }

    public ArrayList<Animal> getAnimales() {
        return this.sistema.getAnimales();
    }

    public int cantidadAnimales() {
        return this.sistema.getAnimales().size();
    }

    public int cantidadAnimalesConEstado(String estado) {
        return this.sistema.cantidadAnimalesConEstado(estado);
    }

    public int cantidadEmpresas() {
        return this.sistema.getEmpresas().size();
    }

    public ArrayList<Animal> getAnimalesDisponibles() {
        return this.sistema.getAnimalesDisponibles();
    }

    public ArrayList<Familia> getFamilias() {
        return this.sistema.getFamilias();
    }

    public int getProximoNumeroAdopcion() {
        return this.sistema.getProximoNumeroAdopcion();
    }

    public int cantidadAdopciones() {
        return this.sistema.getAdopciones().size();
    }

    public void realizarAdopcion(Animal animal, Familia familia, String comentarios) throws DatoInvalidoException {
        if (animal == null) {
            throw new DatoInvalidoException("Debe seleccionar un animal");
        }
        if (familia == null) {
            throw new DatoInvalidoException("Debe seleccionar una familia");
        }
        this.sistema.realizarAdopcion(animal, familia, comentarios);
    }

    public ArrayList<Animal> getAnimalesNoEntregados() {
        return this.sistema.getAnimalesNoEntregados();
    }

    public void cambiarEstado(Animal animal) throws DatoInvalidoException {
        if (animal == null) {
            throw new DatoInvalidoException("Debe seleccionar un animal");
        }
        this.sistema.cambiarEstado(animal);
    }

    public ArrayList<Adopcion> getAdopciones() {
        return this.sistema.getAdopciones();
    }

    public void anularAdopcion(Adopcion adopcion) throws DatoInvalidoException {
        if (adopcion == null) {
            throw new DatoInvalidoException("Debe seleccionar una adopcion");
        }
        this.sistema.anularAdopcion(adopcion);
    }

    public ArrayList<Empresa> getEmpresas() {
        return this.sistema.getEmpresas();
    }

    public int getProximoNumeroDonacion() {
        return this.sistema.getProximoNumeroDonacion();
    }

    public int stockAlimento(int tipo) {
        return this.sistema.stockAlimento(tipo);
    }

    public void registrarDonacion(Empresa empresa, int tipo, String textoKilos) throws DatoInvalidoException {
        if (empresa == null) {
            throw new DatoInvalidoException("Debe seleccionar una empresa");
        }
        if (textoKilos.equals("")) {
            throw new DatoInvalidoException("Debe ingresar la cantidad de kilos");
        }
        int kilos;
        try {
            kilos = Integer.parseInt(textoKilos);
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("Los kilos deben ser un numero entero");
        }
        if (kilos <= 0) {
            throw new DatoInvalidoException("Los kilos deben ser mayor a cero");
        }
        this.sistema.registrarDonacion(empresa, tipo, kilos);
    }

    public int importarDonaciones(String ruta) {
        return this.sistema.importarDonaciones(ruta);
    }

    public ArrayList<Familia> getFamiliasOrdenadasPorApellido() {
        return this.sistema.getFamiliasOrdenadasPorApellido();
    }

    public ArrayList<Familia> getFamiliasOrdenadasPorAdopciones() {
        return this.sistema.getFamiliasOrdenadasPorAdopciones();
    }

    public int cantidadAdopcionesDeFamilia(Familia familia) {
        return this.sistema.cantidadAdopcionesDeFamilia(familia);
    }

    public ArrayList<Donacion> getDonaciones() {
        return this.sistema.getDonaciones();
    }

    public String generarPagina(String nombre, String ubicacion) throws DatoInvalidoException {
        if (nombre.equals("")) {
            throw new DatoInvalidoException("Debe ingresar el nombre del archivo");
        }
        if (!nombre.endsWith(".html")) {
            nombre = nombre + ".html";
        }

        String ruta = nombre;
        if (!ubicacion.equals("")) {
            File carpeta = new File(ubicacion);
            if (!carpeta.isDirectory()) {
                throw new DatoInvalidoException("La ubicacion no existe");
            }
            ruta = ubicacion + "/" + nombre;
        }

        this.sistema.generarPagina(ruta);
        File archivo = new File(ruta);
        return archivo.getAbsolutePath();
    }

    public void guardarDatos() throws IOException {
        FileOutputStream archivo = new FileOutputStream("datos.ser");
        ObjectOutputStream datos = new ObjectOutputStream(archivo);
        datos.writeObject(this.sistema);
        datos.close();
    }

    public void recuperarDatos() throws IOException, ClassNotFoundException {
        FileInputStream archivo = new FileInputStream("datos.ser");
        ObjectInputStream datos = new ObjectInputStream(archivo);
        this.sistema = (Sistema) datos.readObject();
        datos.close();
    }
}
