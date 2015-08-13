package stringitem;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo3 extends MIDlet implements CommandListener  {
 
    private Display display; //Pantalla del dispositivo
    private Form form;       //form que vamos a presentar
    private Command salir;   //comando para salirnos
    private Command cambiar; //comando para cambiar de estado
    private StringItem salida; //cadena de salida
    private int cuenta;

    //Constructor
    public Ejemplo3( ) {
      cuenta=0;

      //Cogemos el display
      display=Display.getDisplay(this);

      //Creamos el form 
      form = new Form ("Ejemplo 3");
      form.append("Ejemplos de StringItem\n");

      //Creamos un nuevo StringItem
      salida=new StringItem("CADENA:", "Contador ="+cuenta);
      //lo añadimos al form
      form.append(salida);

      //Creamos el comando de salir
      salir=new Command("Salir",Command.EXIT, 3);
      //Creamos el comando de cambiar
      cambiar=new Command("Cambiar",Command.SCREEN, 1);

      //anadimos los comandos al form y activamos el oyente
      form.addCommand(salir);
      form.addCommand(cambiar);
      form.setCommandListener(this);
    }//Fin constructor

 
    //Metodo que se llama cuando pasamos de Pausado a Activo
    protected void startApp(  ) {
	System.out.println("Midlet Activo\n");
        display.setCurrent(form);
    }//fin método startApp
 

    //Metodo que se llama cuando pasamos de Activo a Pausado
    protected void pauseApp(  ) {
	System.out.println("Midlet Pausado\n");
    }//fin metodo pauseApp
    

    //Metodo que se llama cuando se destruye el midlet
    protected void destroyApp(boolean incondicional) {
	System.out.println("Fin del Midlet\n");
    }//fin metodo destroyApp


    //Metodo para el tratamiento de datos de teclado 
    public void commandAction(Command c, Displayable d) {
	//Miramos si nos salimos o mostramos la alerta
	if (c ==salir) {
	    destroyApp(true);
	    notifyDestroyed();

	//Cambiamos la cadena de salida del form	    
	} else if (c == cambiar) {
	    cuenta++;
	    salida.setText("Contador ="+cuenta);
        } else System.out.println("Otro comando pulsado");
    }//fin del metodo commandAction
}