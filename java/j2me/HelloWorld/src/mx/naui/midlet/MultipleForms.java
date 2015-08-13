package mx.naui.midlet;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class MultipleForms extends MIDlet implements CommandListener {

    private Display display;
    private Form form;
    private Form form2;
    private Form form3;
    private Command salir;
    private Command back;
    private Command forward;
    private Command start;
    private TextField textField;
    private StringItem stringItem;
    private ImageItem imagen;

    public MultipleForms() {
        display = Display.getDisplay(this);
        
        form = new Form("One");
        textField = new TextField("Name:", "", 10, TextField.ANY);
        form.append(textField);
        form2 = new Form("Two");
        stringItem = new StringItem("Name: ", null);
        form2.append(stringItem);
        form3 = new Form("Three");
        try {
            imagen = new ImageItem("", Image.createImage("/logo.png"), ImageItem.LAYOUT_CENTER, "Logo de Java");
            form3.append(imagen);
        } catch (java.io.IOException e) {
            form.append(" Ha habido un problema al leer el fichero logo.gif\nMotivo:" + e);
        }

        salir = new Command("Exit", Command.EXIT, 0);
        back = new Command("Back", Command.BACK, 1);
        forward = new Command("Forward", Command.SCREEN, 2);
        start = new Command("Start", Command.SCREEN, 3);
        
        form.addCommand(salir);
        form.addCommand(forward);
        form2.addCommand(back);
        form2.addCommand(forward);
        form3.addCommand(back);
        form3.addCommand(start);

        form.setCommandListener(this);
        form2.setCommandListener(this);
        form3.setCommandListener(this);
    }

    public void startApp() {
        display.setCurrent(form);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        Form currentForm = (Form) d;
        System.out.println(""+ ((Form)d).getTitle());
        if (c == salir) {
            destroyApp(true);
            notifyDestroyed();
        } else if (c == forward) {
            if (currentForm == form){
                currentForm = form2;
                if (textField != null && !textField.getString().equals(""))
                    stringItem.setText(textField.getString());
            } else 
                currentForm = form3;
            display.setCurrent(currentForm);
        } else if (c == back) {
            if (currentForm == form2)
                currentForm = form;
            else {
                currentForm = form2;
                if (textField != null && !textField.getString().equals(""))
                    stringItem.setText(textField.getString());
            }
            display.setCurrent(currentForm);
        } else if (c == start) {
            currentForm = form;
            display.setCurrent(currentForm);
        } else {
            System.out.println("Otro comando pulsado");
        }

    }
}
