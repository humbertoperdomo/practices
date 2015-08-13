/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bluetoothservicesearch;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.MessageConnection;

/**
 *
 * @author humberto
 */
public class MessageForm implements CommandListener{
    private Command cmdOK, cmdExit;
    private MIDlet midlet;
    private TextBox text;
    private Display display;
    Form previousForm;

    public MessageForm(MIDlet midlet, Display display, Form previousForm) {
        this.midlet = midlet;
        this.display = display;
        this.previousForm = previousForm;
        text = new TextBox("Mensaje", "", 100, TextField.ANY);
        
        cmdOK = new Command("OK", Command.OK, 0);
        cmdExit = new Command("Exit", Command.EXIT, 0);
        
        text.addCommand(cmdOK);
        text.addCommand(cmdExit);
        text.setCommandListener(this);
        display.setCurrent(text);
    }
    
    
    
    public void commandAction(Command c, Displayable d) {
        if (c == cmdOK){
            if (text.getString().length() > 0){
                new Client(midlet, display, previousForm, text.getString());
            } else {
                display.setCurrent(new Alert("Inserte un mensaje"));
            }
        } else if(c == cmdExit){
            midlet.notifyDestroyed();
        }
    }
    
}
