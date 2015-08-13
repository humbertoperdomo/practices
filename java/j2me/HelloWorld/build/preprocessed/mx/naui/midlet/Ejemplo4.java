package mx.naui.midlet;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo4 extends MIDlet implements CommandListener {

    private Display display;
    private Alert alerta;

    private Form form;
    private ImageItem imagen;

    private Command salir;

    public Ejemplo4() {

        display = Display.getDisplay(this);

        form = new Form("Ejemplo 4");

        try {
            imagen = new ImageItem("", Image.createImage("/logo.png"), ImageItem.LAYOUT_CENTER, "Logo de Java");

            form.append(imagen);
        } catch (java.io.IOException e) {
            form.append(" Ha habido un problema al leer el fichero logo.gif\nMotivo:" + e);
        }

        salir = new Command("Salir", Command.EXIT, 3);

        form.addCommand(salir);
        form.setCommandListener(this);
    }

    protected void startApp() {
        display.setCurrent(form);
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean incondicional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == salir) {
            destroyApp(true);
            notifyDestroyed();
        } else {
            System.out.println("Otro comando pulsado");
        }

    }

}
