/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apibajonivel;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author humberto
 */
public class HolaMundoBajo extends MIDlet implements CommandListener {
    private Display display;
    private Command salir;
    private Canvas canvas;
    
    public HolaMundoBajo() {
        display = Display.getDisplay(this);
        
        canvas = new Canvas() {
            private int width;
            private int height;

            protected void paint(Graphics g) {
                width = getWidth();
                height = getHeight();
                
                g.setColor(0,0,0);
                g.fillRect(0, 0, width, height);
                
                g.setColor(255,255,255);
                g.setStrokeStyle(Graphics.SOLID);
                g.drawString("Hola Caracola", width/2, height/2, (Graphics.BASELINE | Graphics.HCENTER));
            }
        };
        
        salir = new Command("Salir", Command.EXIT, 3);
        canvas.addCommand(salir);
        canvas.setCommandListener(this);
    }
    
    public void startApp() {
        display.setCurrent(canvas);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == salir) {
            destroyApp(true);
            notifyDestroyed();
        } else {
            System.out.println("Otro comando tecleado");
        }
    }
}
