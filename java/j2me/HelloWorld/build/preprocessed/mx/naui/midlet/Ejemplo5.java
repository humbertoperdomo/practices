package mx.naui.midlet;

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

    public Ejemplo5() {

        display = Display.getDisplay(this);

        nombre = new TextField("Nombre:", "", 10, TextField.ANY);
        fecha = new DateField("Fecha Nacimiento:", DateField.DATE);
        cadena = new StringItem("", "");

        form = new Form("Ejemplo 5");
        form.append(nombre);
        form.append(fecha);
        form.append(cadena);

        continuar = new Command("Continuar", Command.OK, 0);
        salir = new Command("Salir", Command.EXIT, 3);

        form.addCommand(continuar);
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
        } else if (c == continuar) {

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            Date date = fecha.getDate();
            if ((date == null) || (nombre.getString().equals(""))) {
                cadena.setText("¡Antes de darle a Continuar mete tus datos!");
                return;
            }
            cal2.setTime(date);

            int edad = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);

            form.delete(0);
            form.delete(0);
            form.removeCommand(continuar);
            cadena.setText("Hola " + nombre.getString() + "\n Tu edad es (más o menos)=" + edad + " años");
        } else {
            System.out.println("Otro comando pulsado");
        }

    }

}
