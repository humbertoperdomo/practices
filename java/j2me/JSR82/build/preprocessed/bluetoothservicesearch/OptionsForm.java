/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothservicesearch;

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
public class OptionsForm extends Form implements CommandListener{

    private ChoiceGroup list;
    private Display display;
    private MIDlet midlet;
    Command cmdOK;

    public OptionsForm(MIDlet midlet, Display display) {
        super("");
        this.midlet = midlet;
        this.display = display;

        list = new ChoiceGroup("Seleccione", ChoiceGroup.EXCLUSIVE);
        list.append("Servidor", null);
        list.append("Enviar mensaje", null);

        cmdOK = new Command("ok", Command.OK, 1);
        this.addCommand(cmdOK);
        this.append(list);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdOK) {
            switch (list.getSelectedIndex()) {
                case 0:
                    display.setCurrent(new NumberClientsForm(midlet, display, this));
                    break;
                case 1:
                    new MessageForm(midlet, display, this);
                    break;
            }
        }
    }

}
