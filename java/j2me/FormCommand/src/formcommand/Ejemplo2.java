package formcommand;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo2 extends MIDlet implements CommandListener {
    private Display display;
    private Form form;
    private Command salir; 

    //Constructor
    public Ejemplo2(  ) {

	//Cogemos el display
	display=Display.getDisplay(this);

        //Creamos el form 
        form = new Form ("Ejemplo 2");
        form.append("Hola Mundo\n");

	//Creamos el comando de salir
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