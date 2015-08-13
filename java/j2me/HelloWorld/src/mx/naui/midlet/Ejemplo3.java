package mx.naui.midlet;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo3 extends MIDlet implements CommandListener  {
 
    private Display display;
    private Form form;
    private Command salir;
    private Command cambiar;
    private Command hideLabel;
    private StringItem salida;
    private int cuenta;
    private String label = "CADENA:";

    public Ejemplo3( ) {
      cuenta=0;

      display=Display.getDisplay(this);

      form = new Form ("Ejemplo 3");
      form.append("Ejemplos de StringItem\n");

      salida=new StringItem("CADENA:", "Contador ="+cuenta);
      form.append(salida);

      salir=new Command("Salir",Command.EXIT, 3);
      cambiar=new Command("Cambiar",Command.SCREEN, 1);
      hideLabel = new Command("Hide", Command.SCREEN, 1);

      form.addCommand(salir);
      form.addCommand(cambiar);
      form.addCommand(hideLabel);
      form.setCommandListener(this);
    }

 
    protected void startApp(  ) {
	System.out.println("Midlet Activo\n");
        display.setCurrent(form);
    }
 

    protected void pauseApp(  ) {
	System.out.println("Midlet Pausado\n");
    }
    

    protected void destroyApp(boolean incondicional) {
	System.out.println("Fin del Midlet\n");
    }


    public void commandAction(Command c, Displayable d) {
	if (c ==salir) {
	    destroyApp(true);
	    notifyDestroyed();
	} else if (c == cambiar) {
	    cuenta++;
	    salida.setText("Contador ="+cuenta);
        } else if (c == hideLabel) {
            if (salida.getLabel() == null)
                salida.setLabel(label);
            else
                salida.setLabel(null);
        } 
        else System.out.println("Otro comando pulsado");
    }
}