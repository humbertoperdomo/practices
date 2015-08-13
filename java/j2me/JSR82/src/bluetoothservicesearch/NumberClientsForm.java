package bluetoothservicesearch;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author humberto
 */
public class NumberClientsForm extends Form implements CommandListener{
    MIDlet midlet;
    Display display;
    Form previousForm;
    private ChoiceGroup numClientes;
    Command cmdOK;
    Command cmdExit;
    Command cmdBack;

    public NumberClientsForm(MIDlet midlet, Display display, Form gameOptionsForm) {
        super("");
        this.midlet = midlet;
        this.display = display;
        previousForm = gameOptionsForm;
        numClientes = new ChoiceGroup("Numero de clientes", ChoiceGroup.EXCLUSIVE);
        numClientes.append("1", null);
        numClientes.append("2", null);
        numClientes.append("3", null);
        numClientes.append("4", null);
        cmdOK = new Command("OK", Command.OK, 0);
        cmdExit = new Command("Salir", Command.EXIT, 0);
        cmdBack = new Command("Volver", Command.BACK, 0);
        this.addCommand(cmdOK);
        this.addCommand(cmdExit);
        this.addCommand(cmdBack);
        this.append(numClientes);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdOK){
            new BluetoothServer(midlet, display, previousForm, numClientes.getSelectedIndex()+1);
        } else if(c == cmdBack) {
            display.setCurrent(previousForm);
        } else if(c == cmdExit){
            midlet.notifyDestroyed();
        }
    }
    
    
}
