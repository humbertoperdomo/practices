package bluetoothservicesearch;

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;

public final class BluetoothDemo extends MIDlet {
    private Display display;
    OptionsForm form;

    public BluetoothDemo() {
        display = Display.getDisplay(this);
        form = new OptionsForm(this, display);
    }

    protected void startApp() {
        display.setCurrent(form);
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional){
    }
   
}