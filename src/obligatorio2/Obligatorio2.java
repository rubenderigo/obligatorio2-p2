/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio2;

import controlador.Controlador;
import modelo.DatoInvalidoException;
import vista.VentanaPrincipal;

/**
 *
 * @author Ruben Derigo - 287176
 */
public class Obligatorio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        // datos de prueba
        try {
            controlador.altaAnimal("A1", "Otto", "Tranquilo", "12.5", true);
            controlador.altaAnimal("A2", "Maka", "Nerviosa", "8", false);
            controlador.altaAnimal("A3", "Jabi", "Jugueton", "20", true);
            controlador.altaAnimal("A4", "Luna", "Carinosa", "6.3", true);
            controlador.altaAnimal("A5", "Rocky", "Miedoso", "15", false);
            controlador.altaAnimal("A6", "Toby", "Activo", "25", true);

            controlador.altaFamilia("11111111", "Perez", "099111111");
            controlador.altaFamilia("22222222", "Gomez", "099222222");
            controlador.altaFamilia("33333333", "Alvarez", "");

            controlador.altaEmpresa("Pedigree", "1001", "Av. Italia 1234");
            controlador.altaEmpresa("Dog Chow", "1002", "18 de Julio 999");

            controlador.realizarAdopcion(controlador.getAnimalesDisponibles().get(0), controlador.getFamilias().get(0), "Primera adopcion");
            controlador.realizarAdopcion(controlador.getAnimalesDisponibles().get(0), controlador.getFamilias().get(1), "Segunda adopcion");
        } catch (DatoInvalidoException e) {
            System.out.println(e.getMessage());
        }

        VentanaPrincipal ventana = new VentanaPrincipal(controlador);
        ventana.setVisible(true);
    }

}
