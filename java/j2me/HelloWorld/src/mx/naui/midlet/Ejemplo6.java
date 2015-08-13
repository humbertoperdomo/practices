package mx.naui.midlet;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo6 extends MIDlet implements CommandListener {

    private Display display;
    private Alert alerta;

    private Form form;
    private TextField nombre;
    private ChoiceGroup sexo;
    private ChoiceGroup aficiones;

    private Command salir;

    public Ejemplo6() {

        display = Display.getDisplay(this);

        nombre = new TextField("Nombre:", "", 10, TextField.ANY);
        sexo = new ChoiceGroup("Sexo:", ChoiceGroup.EXCLUSIVE);
        sexo.append("Hombre", null);
        sexo.append("Mujer", null);
        aficiones = new ChoiceGroup("Aficiones:", ChoiceGroup.MULTIPLE);
        aficiones.append("Lectura", null);
        aficiones.append("Cine", null);
        aficiones.append("Deportes", null);
        aficiones.append("Amigos", null);
        aficiones.append("Television", null);
        aficiones.append("Otras", null);

        form = new Form("Ejemplo 6");
        form.append(nombre);
        form.append(sexo);
        form.append(aficiones);

        //Creamos el comando del form
        salir = new Command("Salir", Command.EXIT, 3);

        //anadimos el comando al form y activamos el oyente
        form.addCommand(salir);
        form.setCommandListener(this);
    }

    //Metodo que se llama cuando pasamos de Pausado a Activo
    protected void startApp() {
        display.setCurrent(form);
    }

    //Metodo que se llama cuando pasamos de Activo a Pausado
    protected void pauseApp() {
    }

    //Metodo que se llama cuando se destruye el midlet
    protected void destroyApp(boolean incondicional) {
    }

    //Metodo para el tratamiento de datos de teclado 
    public void commandAction(Command c, Displayable d) {
        //Miramos si nos salimos o mostramos la alerta
        if (c == salir) {
            destroyApp(true);
            notifyDestroyed();
        } else {
            System.out.println("Otro comando pulsado");
        }

    }

}
