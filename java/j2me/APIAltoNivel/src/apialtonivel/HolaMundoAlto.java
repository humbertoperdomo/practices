/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
   *#5239870*# Reset
   *#9998*5282# Profile
   *#0228# ó *#9998*228# Bateria info
   *#9998*842# vibrador opciones
   *#06# IMEI
   *#9998*289# Alarma?
   *#9998*377# Texto error EEPROM
   *#9998*746# Tamaño del fichero SIM
   *#9998*778# Tablas de servicio SIM
   *#9998*#
 */ 

package apialtonivel;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


/**
 * @author humberto
 */
public class HolaMundoAlto extends MIDlet implements CommandListener {
    private Display display;
    private Form form;
    private Command salir;

    // Constructor
    public HolaMundoAlto() {
        // Obtenemos el display
        display = Display.getDisplay(this);
        
        // Creamos el form
        form = new Form("Ejemplo");
        form.append("Hola Mundo!");
        
        // Creamos el comando salir
        salir = new Command("Salir", Command.EXIT, 3);
        
        // Agregamos el comando salir al form y activamos el listener
        form.addCommand(salir);
        form.setCommandListener(this);
    }
    
    // Método que se llama cuando pasamos de Pausado a Activo
    public void startApp() {
        display.setCurrent(form);
    }
    
    // Método que se llama cuando pasamos de Activo a Pasado
    public void pauseApp() {
    }
    
    // Método que se llama cuando se destruye el midlet
    public void destroyApp(boolean unconditional) {
    }
    
    // Método para el tratamiento de datos de teclado
    public void commandAction(Command c, Displayable d) {
        // Vemos si salimos o mostramos la alerta
        if (c == salir) {
            destroyApp(true);
            notifyDestroyed();
        } else {
            System.out.println("Otro comando tecleado");
        }
    }
    
    
}
