/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.naui.arduinobluetooth;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * BluetoothMidlet MIDlet.
 * 
 * <P>Various attributes of guitars, and related behaviour.
 *  
 * <P>Note that {@link BigDecimal} is used to model the price - not double or float. 
 * See {@link #Guitar(String, BigDecimal, Integer)} for more information.
 *  
 * @author Humberto Perdomo
 * @version 1.0
 */
public class BluetoothMidlet extends MIDlet {
    private Display display;
    OptionsForm form;
    
    public BluetoothMidlet() {
        display = Display.getDisplay(this);
        form = new OptionsForm(this, display);
    }

    public void startApp() {
        display.setCurrent(form);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
