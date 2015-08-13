/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imageitem;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class Ejemplo4 extends MIDlet implements CommandListener {
    private Display display;
    private Alert alerta;

    private Form form;
    private ImageItem imagen;

    private Command salir; 

    //Constructor
    public Ejemplo4(  ) {

	//Cogemos el display
	display=Display.getDisplay(this);

        //Creamos el form y le anadimos el item
        form = new Form ("Ejemplo 4");

	try {
	    //Creamos el item del Form
	    imagen= new ImageItem("", Image.createImage("/icons/logo.png"), ImageItem.LAYOUT_CENTER, "Logo de Java");
	    
	    form.append(imagen); 
	} catch (java.io.IOException e) {
	    form.append(" Ha habido un problema al leer el fichero logo.gif\nMotivo:"+e); 
	}

	//Creamos el comando del form
	salir=new Command("Salir",Command.EXIT, 3);

        //anadimos el comando al form y activamos el oyente
	form.addCommand(salir);
        form.setCommandListener(this);
    }
 
    //Metodo que se llama cuando pasamos de Pausado a Activo
    protected void startApp(  ) {
        display.setCurrent(form);
    }
 
    //Metodo que se llama cuando pasamos de Activo a Pausado
    protected void pauseApp(  ) {
    }
 
    //Metodo que se llama cuando se destruye el midlet
    protected void destroyApp(boolean incondicional) {
    }

    //Metodo para el tratamiento de datos de teclado 
    public void commandAction(Command c, Displayable d) {
	//Miramos si nos salimos o mostramos la alerta
	if (c ==salir) {
	   destroyApp(true);
	   notifyDestroyed();
        } else System.out.println("Otro comando pulsado");
        
   } 

}