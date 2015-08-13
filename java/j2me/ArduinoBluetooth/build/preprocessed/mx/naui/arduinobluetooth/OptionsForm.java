/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.naui.arduinobluetooth;

import com.nutiteq.log.Log;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author humberto
 */
public class OptionsForm extends Form implements CommandListener, ItemCommandListener {

    private Form form;
    private Display display;
    private MIDlet midlet;
    private Command exitCommand, okCommand;
    private StringItem cmdOK, cmdExit;

    public OptionsForm(MIDlet midlet, Display display) {
        super("");
        this.midlet = midlet;
        this.display = display;

        setTitle("Connect to Arduino");
        append(getCmdOK());
        this.addCommand(getExitCommand());
        setCommandListener(this);

    }

    public void commandAction(Command command, Displayable d) {
        if (command == exitCommand) {
            midlet.notifyDestroyed();
        }
    }

    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular item.
     *
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {
        // write pre-action user code here
        if (item == cmdOK) {
            if (command == okCommand) {
                // write pre-action user code here
                new ConnectToArduino(midlet, display, this);
                // write post-action user code here
            }
        }
    }

    /**
     * Returns an initiliazed instance of offCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {
            // write pre-init user code here
            okCommand = new Command("OK", Command.OK, 0);
            // write post-init user code here
        }
        return okCommand;
    }

    /**
     * Returns an initiliazed instance of cmdOFF component.
     *
     * @return the initialized component instance
     */
    public StringItem getCmdOK() {
        if (cmdOK == null) {
            // write pre-init user code here
            cmdOK = new StringItem("", "Search", Item.BUTTON);
            cmdOK.addCommand(getOkCommand());
            cmdOK.setItemCommandListener(this);
            // write post-init user code here
        }
        return cmdOK;
    }

    /**
     * Returns an initiliazed instance of exitCommand component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);
            // write post-init user code here
        }
        return exitCommand;
    }
    
    /**
     * Returns an initiliazed instance of cmdOFF component.
     *
     * @return the initialized component instance
     */
    public StringItem getCmdEXIT() {
        if (cmdExit == null) {
            // write pre-init user code here
            cmdExit = new StringItem("", "Exit", Item.BUTTON);
            cmdExit.addCommand(getExitCommand());
            cmdExit.setItemCommandListener(this);
            // write post-init user code here
        }
        return cmdExit;
    }
}
