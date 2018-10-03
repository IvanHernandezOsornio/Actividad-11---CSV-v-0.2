
package main;

import controlador.Controladores;
import modelo.Modelos;
import vista.Vistas;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Modelos modelo = new Modelos();
        Vistas vista = new Vistas();
        Controladores controllercsv = new Controladores(modelo, vista); 
        
    }
    
}
