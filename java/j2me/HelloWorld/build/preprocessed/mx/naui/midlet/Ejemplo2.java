package mx.naui.midlet;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author humberto
 */
public class Ejemplo2 extends MIDlet implements CommandListener {

    private Display display;
    private Form form;
    private Command salir, change;
//    private Canvas miCanvas;
    private String stringOne;
    private String stringTwo;  

    public Ejemplo2() {
        display = Display.getDisplay(this);
        
        form = new Form("Ejemplo 2");
        stringOne = "Hola ";
        stringTwo = "Mundo ";
        form.append(stringOne);
        form.append(stringTwo);
//        miCanvas = new Canvas() {
//            private int width; 
//            private int height;
//            
//            public void paint(Graphics g) {
//                width = getWidth();
//                height = getHeight();
//                
//                g.setColor(0, 0, 0);
//                g.fillRect(0, 0, width, height);
//                
//                g.setColor(255, 255, 255);
//                g.setStrokeStyle(Graphics.SOLID);
//                g.drawString("Hello Planet", width/2, height/2, (Graphics.BASELINE | Graphics.HCENTER));
//            }
//        };

        salir = new Command("Exit", Command.EXIT, 3);
        change = new Command("Change", Command.SCREEN, 1);
        
        
        form.addCommand(change);
        form.addCommand(salir);
        form.setCommandListener(this);

//        miCanvas.addCommand(salir);
//        miCanvas.setCommandListener(this);
    }

    public void startApp() {
        display.setCurrent(form);
//        display.setCurrent(miCanvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == salir) {
            destroyApp(true);
            notifyDestroyed();
        } else if (c == change) {
            String tmp = stringOne;
            ((StringItem) form.get(1)).setText(stringOne);
            stringOne = stringTwo;
            stringTwo = tmp;
            ((StringItem) form.get(0)).setText(stringOne);
        } else {
            System.out.println("Otro comando pulsado.");
        }
    }
}
