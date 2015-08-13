/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.naui.arduinobluetooth;

import com.nutiteq.bluetooth.BluetoothAPIDevice;
import com.nutiteq.bluetooth.BluetoothDevice;
import com.nutiteq.bluetooth.BluetoothHandler;
import com.nutiteq.bluetooth.DiscoveredDevice;
import com.nutiteq.core.MappingCore;
import com.nutiteq.location.providers.BluetoothProvider;
import com.nutiteq.location.providers.LocationDataConnectionProvider;
import com.nutiteq.task.LocalTask;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.MessageConnection;

/**
 *
 * @author humberto
 */
public class ConnectToArduino implements CommandListener, LocalTask,
  BluetoothHandler {

    private BluetoothDevice device;
    private DiscoveredDevice[] found;
    private List deviceList;
    private Client client;
    private Command cmdOK, cmdExit, cmdBack;
    private MIDlet midlet;
    private Display display;
    Form previousForm;
    private final String noDevicesFoundMessage = "NO Devices discovered.";

    public ConnectToArduino(MIDlet midlet, Display display, Form previousForm) {
        this.midlet = midlet;
        this.display = display;
        this.previousForm = previousForm;
        cmdOK = new Command("OK", Command.OK, 1);
        cmdExit = new Command("Exit", Command.EXIT, 1);
        cmdBack = new Command("Back", Command.BACK, 1);
        deviceList = new List("Devices discovered", List.EXCLUSIVE);
        
        MappingCore.getInstance().runAsync(this);
    }

    public void execute() {
        device = new BluetoothAPIDevice(this);
        device.findRemoteDevices();
    }

    public void commandAction(Command c, Displayable d) {
        if (d == deviceList && c == cmdOK) {
            final int index = deviceList.getSelectedIndex();
            final BluetoothProvider bluetoothProvider = new BluetoothProvider(
              midlet, display, previousForm, (LocationDataConnectionProvider) device, found[index].getUrl());

        } else if (c == cmdBack) {
            display.setCurrent(previousForm);
        } else if (c == cmdExit) {
            midlet.notifyDestroyed();
        }
    }

    public void remoteDevicesFound(DiscoveredDevice[] devicesFound) {
        final Vector appended = new Vector();
        deviceList.deleteAll();
        boolean added = false;
        for (int i = 0; i < devicesFound.length; i++) {
            final DiscoveredDevice discoveredDevice = devicesFound[i];
            if (discoveredDevice.getUrl() == null) {
                continue;
            }
            added = true;
            deviceList.append(discoveredDevice.getName(), null);
            appended.addElement(discoveredDevice);
        }

        found = new DiscoveredDevice[appended.size()];
        appended.copyInto(found);

        if (added) {
            deviceList.addCommand(cmdBack);
            deviceList.addCommand(cmdOK);
            deviceList.setCommandListener(this);
        } else {
            deviceList.addCommand(cmdBack);
            deviceList.addCommand(cmdExit);
            deviceList.setCommandListener(this);
        }
        display.setCurrent(deviceList);
    }

}
