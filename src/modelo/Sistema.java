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
    private ArrayList<Donacion> donaciones = new ArrayList<>();
    private int proximoNumeroAdopcion = 1;
    private int proximoNumeroDonacion = 1;

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

    public ArrayList<Donacion> getDonaciones() {
        return this.donaciones;
    }

    public int getProximoNumeroDonacion() {
        return this.proximoNumeroDonacion;
    }

    public void registrarDonacion(Empresa empresa, int tipo, int kilos) {
        Donacion donacion = new Donacion(this.proximoNumeroDonacion, empresa, tipo, kilos);
        this.donaciones.add(donacion);
        this.proximoNumeroDonacion++;
    }

    public int stockAlimento(int tipo) {
        int total = 0;
        for (int i = 0; i < this.donaciones.size(); i++) {
            if (this.donaciones.get(i).getTipo() == tipo) {
                total = total + this.donaciones.get(i).getKilos();
            }
        }
        return total;
    }

    public Empresa buscarEmpresa(String rut) {
        for (int i = 0; i < this.empresas.size(); i++) {
            if (this.empresas.get(i).getRut().equals(rut)) {
                return this.empresas.get(i);
            }
        }
        return null;
    }

    private String procesarLineaDonacion(String linea) {
        String[] partes = linea.split("#");
        if (partes.length != 3) {
            return "formato incorrecto";
        }

        String error = "";

        Empresa empresa = this.buscarEmpresa(partes[0].trim());
        if (empresa == null) {
            error = error + "empresa no registrada ";
        }

        int tipo = 0;
        try {
            tipo = Integer.parseInt(partes[1].trim());
            if (tipo < 1 || tipo > 4) {
                error = error + "tipo incorrecto ";
            }
        } catch (NumberFormatException e) {
            error = error + "tipo incorrecto ";
        }

        int kilos = 0;
        try {
            kilos = Integer.parseInt(partes[2].trim());
            if (kilos <= 0) {
                error = error + "cantidad incorrecta ";
            }
        } catch (NumberFormatException e) {
            error = error + "cantidad incorrecta ";
        }

        if (error.equals("")) {
            this.registrarDonacion(empresa, tipo, kilos);
        }
        return error.trim();
    }

    public int importarDonaciones(String ruta) {
        ArrayList<String> errores = new ArrayList<>();
        ArchivoLectura archivo = new ArchivoLectura(ruta);
        int numeroLinea = 0;

        while (archivo.hayMasLineas()) {
            numeroLinea++;
            String error = this.procesarLineaDonacion(archivo.linea());
            if (!error.equals("")) {
                errores.add("Linea " + numeroLinea + ": " + error);
            }
        }
        archivo.cerrar();

        if (!errores.isEmpty()) {
            this.grabarErrores(ruta, errores);
        }
        return errores.size();
    }

    private void grabarErrores(String ruta, ArrayList<String> errores) {
        String rutaError = ruta;
        int posicionPunto = ruta.lastIndexOf(".");
        if (posicionPunto >= 0) {
            rutaError = ruta.substring(0, posicionPunto);
        }
        rutaError = rutaError + "Error.txt";

        ArchivoGrabacion archivo = new ArchivoGrabacion(rutaError);
        for (int i = 0; i < errores.size(); i++) {
            archivo.grabarLinea(errores.get(i));
        }
        archivo.cerrar();
    }

    public int cantidadAdopcionesDeFamilia(Familia familia) {
        int cantidad = 0;
        for (int i = 0; i < this.adopciones.size(); i++) {
            if (this.adopciones.get(i).getFamilia() == familia) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public ArrayList<Familia> getFamiliasOrdenadasPorApellido() {
        ArrayList<Familia> lista = new ArrayList<>(this.familias);
        lista.sort((a, b) -> a.getApellido().compareToIgnoreCase(b.getApellido()));
        return lista;
    }

    public ArrayList<Familia> getFamiliasOrdenadasPorAdopciones() {
        ArrayList<Familia> lista = new ArrayList<>(this.familias);
        lista.sort((a, b) -> this.cantidadAdopcionesDeFamilia(b) - this.cantidadAdopcionesDeFamilia(a));
        return lista;
    }
}
