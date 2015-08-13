package com.nutiteq.location.providers;

import java.io.IOException;
import java.io.OutputStream;

import com.nutiteq.log.Log;
import com.nutiteq.utils.IOUtils;
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
 * Provider reading NMEA data from given connection source.
 */
public class BluetoothProvider implements Runnable, CommandListener, ItemCommandListener {

    private static final int STATUS_CONNECTING = 1;
    private static final int STATUS_CONNECTED = 2;
    private static final int STATUS_CONNECTION_LOST = 4;
    private static final int STATUS_CANT_LOCATE = 8;
    private static final int FLAG_LIGHT_ON = 1;
    private static final int FLAG_LIGHT_OFF = 0;
    private boolean running;
    private final String url;
    private OutputStream os;
    private boolean connected;
    private final LocationDataConnectionProvider connectionProvider;
    private LocationDataConnection connection;
    private boolean firstTry = true;
    Thread t;
    private MIDlet midlet;
    private Display display;
    private Form form;
    private Form previousForm;
    private Command exitCommand, onCommand, offCommand, backCommand;
    private StringItem cmdON, cmdOFF;

    /**
     * Create provider
     *
     * @param connectionProvider object for connection handling
     * @param url url for connection
     */
    public BluetoothProvider(MIDlet midlet, Display display, Form previousForm,
      final LocationDataConnectionProvider connectionProvider,
      final String url) {
        this.midlet = midlet;
        this.display = display;
        this.previousForm = previousForm;
        this.connectionProvider = connectionProvider;
        this.url = url;
        form = new Form("Control", new Item[]{getCmdON(),getCmdOFF()});
        form.addCommand(getBackCommand());
        form.addCommand(getExitCommand());
        form.setCommandListener(this);

        t = new Thread(this);
        t.start();
    }

    public int getStatus() {
        return connected ? STATUS_CONNECTED : STATUS_CONNECTING;
    }

    public void run() {
        running = true;
        display.setCurrent(form);
        while (running) {
            connect();
            while (running && connected) {
                
                sleepSomeTime(10);
            }
            sleepSomeTime(5000);
        }
        Log.debug("BT done");
    }

    private void sleepSomeTime(final long howLong) {
        if (!running) {
            return;
        }
        synchronized (this) {
            try {
                Log.debug("" + howLong);
                wait(howLong);
            } catch (final InterruptedException ignore) {
            }
        }
    }

    public void disconnect() {
        Log.info("BT disconnect");
        IOUtils.closeStream(os);
        connection.disconnect();
        connected = false;
    }

    private void connect() {
        Log.info("Connect");
        try {
            connection = connectionProvider.openConnection(url);
            os = connection.openOutputStream();
            connected = true;
        } catch (final IOException e) {
            Log.error("BT connect " + e.getMessage());
            Log.printStackTrace(e);
            if (firstTry) {
                quit();
            }
        }
        firstTry = false;
        Log.info("End connect");
    }

    public void quit() {
        if (!running) {
            return;
        }
        running = false;
        disconnect();
        synchronized (this) {
            notify();
        }
    }

    /**
     * Returns an initiliazed instance of onCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOnCommand() {
        if (onCommand == null) {
            // write pre-init user code here
            onCommand = new Command("OK", Command.OK, 0);
            // write post-init user code here
        }
        return onCommand;
    }

    /**
     * Returns an initiliazed instance of cmdON component.
     *
     * @return the initialized component instance
     */
    public StringItem getCmdON() {
        if (cmdON == null) {
            // write pre-init user code here
            cmdON = new StringItem("On", "", Item.BUTTON);
            cmdON.addCommand(getOnCommand());
            cmdON.setItemCommandListener(this);
            // write post-init user code here
        }
        return cmdON;
    }

    /**
     * Returns an initiliazed instance of offCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOffCommand() {
        if (offCommand == null) {
            // write pre-init user code here
            offCommand = new Command("OK", Command.OK, 0);
            // write post-init user code here
        }
        return offCommand;
    }

    /**
     * Returns an initiliazed instance of cmdOFF component.
     *
     * @return the initialized component instance
     */
    public StringItem getCmdOFF() {
        if (cmdOFF == null) {
            // write pre-init user code here
            cmdOFF = new StringItem("Off", "", Item.BUTTON);
            cmdOFF.addCommand(getOffCommand());
            cmdOFF.setItemCommandListener(this);
            // write post-init user code here
        }
        return cmdOFF;
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
     * Returns an initiliazed instance of backCommand component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);
            // write post-init user code here
        }
        return backCommand;
    }

    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular displayable.
     *
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
        // write pre-action user code here
        if (displayable == form) {
             if (command == backCommand) {
                display.setCurrent(previousForm);
            } else if (command == exitCommand) {
                // write pre-action user code here
                midlet.notifyDestroyed();
                // write post-action user code here
            }
        }
        // write post-action user code here
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
        int data = -1;
        if (item == cmdON) {
            if (command == onCommand) {
                // write pre-action user code here
                data = FLAG_LIGHT_ON;
                // write post-action user code here
            }
        } else if (item == cmdOFF) {
            if (command == offCommand) {
                // write pre-action user code here
                data = FLAG_LIGHT_OFF;
                // write post-action user code here
            }
        }
        try {
            os.write(FLAG_LIGHT_ON);
            os.flush();
        } catch (final Exception ex) {
            Log.error("Error sending data");
            Log.printStackTrace(ex);
            // disconnect if an error occurs
            disconnect();
        }
        
    }
}
