/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datefieldtextfield;

import java.util.Date;
import java.util.Calendar;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class Ejemplo5 extends MIDlet implements CommandListener {
    private Display display;
    private Alert alerta;

    private Form form;
    private TextField nombre;
    private DateField fecha; 
    private StringItem cadena; 

    private Command continuar; 
    private Command salir; 

    //Constructor
    public Ejemplo5(  ) {

	//Cogemos el display
	display=Display.getDisplay(this);

        //Creamos los items Form
 	  //un item para preguntar el nombre
          nombre= new TextField("Nombre:","",10,TextField.ANY);
   	  //un item para preguntar la fecha de nacimiento
          fecha= new DateField("Fecha Nacimiento:",DateField.DATE);
	  //una cadena
	  cadena= new StringItem("","");
  

        //Creamos el form y le anadimos los items
        form = new Form ("Ejemplo 5");
        form.append(nombre); 
        form.append(fecha);
        form.append(cadena);

	//Creamos los comandos del form
	continuar=new Command("Continuar",Command.OK, 0);
	salir=new Command("Salir",Command.EXIT, 3);

        //anadimos los comandos al form y activamos el oyente
	form.addCommand(continuar);
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
        } else if (c == continuar) {
	    
	    //usamos calendar para ver la diferencia con la hora actual
	    Calendar cal1=Calendar.getInstance();
	    Calendar cal2=Calendar.getInstance();
	    Date date=fecha.getDate();
	    if ((date==null) || (nombre.getString().equals(""))) {
		cadena.setText("¡Antes de darle a Continuar mete tus datos!");
		return;
	    }
	    cal2.setTime(date);

	    int edad=cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR);
	    
	    //Quitamos la entrada de datos
	    form.delete(0);
	    form.delete(0);
	    //Quitamos el comando de continuar
	    form.removeCommand(continuar);
	    //Cambiamos la cadena por un mensaje con la edad
	    cadena.setText("Hola "+nombre.getString()+"\n Tu edad es (más o menos)="+edad+" años");
        } else System.out.println("Otro comando pulsado");
        
   } 

}