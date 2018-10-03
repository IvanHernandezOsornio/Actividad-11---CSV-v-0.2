package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

import modelo.Modelos;
import vista.Vistas;

public class Controladores {

    // Creamos un objeto de cada classe para tener su contenido
    Modelos modelos;
    Vistas viestas;

    ActionListener actionlistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viestas.jb_guardar) { // Condicion del boton guardar
                jb_guardar_action_performed();
            } else if (e.getSource() == viestas.jb_primero) { //Condicion del boton primero
                jb_primero_action_performed();
            } else if (e.getSource() == viestas.jb_siguiente) { //Condicion del boton siguiente
                jb_siguiente_action_performed();
            } else if (e.getSource() == viestas.jb_anterior) { // Condicion del boton anterior
                jb_anterior_action_performed();
            } else if (e.getSource() == viestas.jb_ultimo) { // Condicion del boton ultimo
                jb_ultimo_action_performed();
            } else if (e.getSource() == viestas.jb_nuevo) { // Condicion del boton nuevo
                jb_nuevo_action_performed();
            }
        }
    };

    /**
     * Componentes de MVC (conecciones)
     *
     * @param archivo_modelo
     * @param archivo_vista
     */
    public Controladores(Modelos archivo_modelo, Vistas archivo_vista) {
        this.modelos = archivo_modelo;
        this.viestas = archivo_vista;

        this.viestas.jb_guardar.addActionListener(actionlistener);
        this.viestas.jb_nuevo.addActionListener(actionlistener);
        this.viestas.jb_primero.addActionListener(actionlistener);
        this.viestas.jb_anterior.addActionListener(actionlistener);
        this.viestas.jb_siguiente.addActionListener(actionlistener);
        this.viestas.jb_ultimo.addActionListener(actionlistener);
        initComponents();

        // Colocamos el primer registro ingresado en pantalla 
        this.readFirst(modelos.getPath());
    }

    /**
     * METODO del boton GUARDAR
     */
    public void jb_guardar_action_performed() {
        String nombre = viestas.jtf_nombre.getText();
        String email = viestas.jtf_email.getText();
        String registro = nombre + "," + email;
        modelos.setMessage(registro);
        // Comboca al metodo para guardar
        this.writeFile(modelos.getPath(), modelos.getMessage());
        // Colocamos el ultimo registro ingresado 
        this.readLast(modelos.getPath());
    }

    /**
     * METODO para el PRIMERO
     */
    public void jb_primero_action_performed() {
        //Llama a el primer registro ingresado 
        this.readFirst(modelos.getPath());
    }

    /**
     * METODO para el boton SIGUIENTE
     */
    public void jb_siguiente_action_performed() {
        this.readNext(modelos.getPath());
    }

    /**
     * METODO para el boton ANTERIOR
     */
    public void jb_anterior_action_performed() {
        //Llama a el registro anterior a el colocado en pantalla 
        this.readPrevious(modelos.getPath());
    }

    /**
     * METODO para el boton ULTIMO
     */
    public void jb_ultimo_action_performed() {
        //Llma a el ultimo registro ingresado 
        this.readLast(modelos.getPath());
    }

    /**
     * METODO para el boton NUEVO
     */
    public void jb_nuevo_action_performed() {
        //Elimina el contenido escrito en las cajas de texto "JTF" 
        viestas.jtf_nombre.setText("");
        viestas.jtf_email.setText("");
    }

    

    /**
     * 
     * @param path: Indica la ruta del archivo
     * @return
     */
    //Metodo para Mostrar el primer registro almacenado en la pantalla
    public String readFirst(String path) {
        try {
            String row; // indicamos la una fila
            String acumulador_texto = ""; // Creamos un acumulador para los registros 
            try (FileReader archivo = new FileReader(path)) { //Leemos el contenido del archivo
                BufferedReader bufferedreader = new BufferedReader(archivo); 

//                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null) {
//                    num_lineas = num_lineas + 1; //Contador de lineas del archivo
                    acumulador_texto += row + "#"; //acomulador de lineas del archivo
                }
                String registros[] = acumulador_texto.split("#"); 
                modelos.setPosition(0); //Asignamos una posicion de cada registro 
                String registro[] = registros[modelos.getPosition()].split(","); //Separamos la informacion mediante una ","
                viestas.jtf_nombre.setText(registro[0]);
                viestas.jtf_email.setText(registro[1]);
            }
            //Metodo Para La Deteccion de Errores
        } catch (FileNotFoundException err) {
            System.err.println("Archivo no encontrado: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    }
    ;
    /**
     * 
     * @param path: Indica la ruta del archivo
     * @return
     */
    //Metodo para Mostrar el ultimo registro almacenado en la pantalla
    public String readLast(String path) {
       try {
            String row; // indicamos la una fila
            String acumulador_texto = ""; // Creamos un acumulador para los registros 
            try (FileReader archivo = new FileReader(path)) { //Leemos el contenido del archivo
                BufferedReader bufferedreader = new BufferedReader(archivo); 

                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null) {
                    num_lineas = num_lineas + 1;//contador de lineas del archivo
                    acumulador_texto += row + "#"; //acomulador de lineas del archivo
                }
                String registros[] = acumulador_texto.split("#"); 
                modelos.setPosition(num_lineas - 1);//Asignamos una posicion de cada registro 
                String registro[] = registros[modelos.getPosition()].split(","); //Separamos la informacion mediante una ","
                viestas.jtf_nombre.setText(registro[0]);
                viestas.jtf_email.setText(registro[1]);
            }
            //Metodo Para La Deteccion de Errores
        } catch (FileNotFoundException err) {
            System.err.println("Archivo no encontrado: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    }
    ;
    
    
    
    
   /**
     * 
     * @param path: Indica la ruta del archivo
     * @return
     */
    //Metodo para Mostrar el  registro anterior  almacenado en la pantalla
    public String readPrevious(String path) {
       try {
            String row; // indicamos la una fila
            String acumulador_texto = ""; // Creamos un acumulador para los registros 
            try (FileReader archivo = new FileReader(path)) { //Leemos el contenido del archivo
                BufferedReader bufferedreader = new BufferedReader(archivo); 
                
                
//                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null) {
//                    num_lineas = num_lineas + 1; // Contador de lineas del archivo
                    acumulador_texto += row + "#"; // Aacomulador de lineas del archivo
                }
                String registros[] = acumulador_texto.split("#"); 
                modelos.setPosition(modelos.getPosition() - 1); // Asigna el valor de la posición deseada del registro.
                if (modelos.getPosition() >= 0) {
                    String registro[] = registros[modelos.getPosition()].split(","); //Separamos la informacion mediante una ","
                    viestas.jtf_nombre.setText(registro[0]);
                    viestas.jtf_email.setText(registro[1]);
                } else {
                    modelos.setPosition(modelos.getPosition() + 1); 
                    //Condicion para sabar que es el primer registro 
                    JOptionPane.showMessageDialog(null, "Estas en el primer registro");
                }
            }
            //Metodo Para La Deteccion de Errores
        } catch (FileNotFoundException err) {
            System.err.println("Archivo no encontrado: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    }
    ;
    
    
    
    
    
    
    
   /**
     * 
     * @param path: Indica la ruta del archivo
     * @return
     */
    //Metodo para Mostrar el  siguiente registro almacenado en la pantalla
    public String readNext(String path) {
            try {
            String row; // indicamos la una fila
            String acumulador_texto = ""; // Creamos un acumulador para los registros 
            try (FileReader archivo = new FileReader(path)) { //Leemos el contenido del archivo
                BufferedReader bufferedreader = new BufferedReader(archivo); 

                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null) {
                    num_lineas = num_lineas + 1;// Contodor de lineas del archivo
                    acumulador_texto += row + "#"; // acomulador de lineas del archivo
                }
                
                String registros[] = acumulador_texto.split("#"); // Separa los registros por el caracter asignado (#).
                modelos.setPosition(modelos.getPosition() + 1); // Asigna el valor de la posición deseada del registro.
                if (modelos.getPosition() < num_lineas) {
                    String registro[] = registros[modelos.getPosition()].split(","); //Separamos la informacion mediante una ","
                    viestas.jtf_nombre.setText(registro[0]);
                    viestas.jtf_email.setText(registro[1]);
                } else {
                    modelos.setPosition(modelos.getPosition() + 1); 
                    //Condicion para sabar que es el primer registro 
                    JOptionPane.showMessageDialog(null, "Estas en el ultimo registro");
                }
            }
            //Metodo Para La Deteccion de Errores
        } catch (FileNotFoundException err) {
            System.err.println("Archivo no encontrado: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    }
    ;
    
    
    
    
    
    
    
   /**
     * 
     * @param path: Indica la ruta del archivo
     * @return
     */
    //Metodo para Mostrar el  Guardar el registro almacenado en la pantalla
    public void writeFile(String path, String message) {
        try {
            File archivo = new File(path); //Crea un archivo nuevo si no existe y de existir lo abre mediante la ruta asignada 
            FileWriter filewriter = new FileWriter(archivo, true); //Escribe dentro del archivo 

            try (PrintWriter printwriter = new PrintWriter(filewriter)) { //Guarda el contenido en el archivo 
                printwriter.println(message);
                printwriter.close();
            }
            //Metodo Para La Deteccion de Errores
        } catch (FileNotFoundException err) {
            System.err.println("Archivo no encontrado: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
     
    }
    
    /**
     * Metodo para conectar los componentes de las vistas;
     */
    public void initComponents() {
        viestas.setVisible(true);
    }

}
