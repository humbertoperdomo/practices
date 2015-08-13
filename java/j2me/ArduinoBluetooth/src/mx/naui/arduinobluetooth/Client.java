package mx.naui.arduinobluetooth;

import java.io.DataOutputStream;

import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

import javax.microedition.midlet.MIDlet;

import java.io.IOException;

/**
 *
 * @author humberto
 */
public class Client implements Runnable, CommandListener, DiscoveryListener {

    public static final int BLUETOOTH_TIMEOUT = 30000;
    private boolean isBTSearchComplete;
    private LocalDevice local;
    private MIDlet midlet;
    private String deviceName;
    private Display display;
    private UUID[] uuid;
    private DiscoveryAgent discoveryAgent;
    private Vector btDevicesFound;
    private Command cmdOK, cmdCancel;
    private List deviceList;
    private ServiceRecord btServicesFound;
    private RemoteDevice otherDevice;
    DataOutputStream output;
    private boolean deviceChosen;
    private boolean connect;
    Form messageForm;
    private Form list;
    Thread t;
    String url;
    StreamConnection conn;
    private Command cmdExit;
    private Command cmdBack;
    private String message;
    private int transactionID;

    public Client(MIDlet midlet, Display display, Form previousForm, String message) {
        this.message = message;
        this.midlet = midlet;
        this.display = display;
        this.list = previousForm;

        btDevicesFound = new Vector();
        deviceChosen = false;
        messageForm = new Form("");
        connect = false;

        deviceList = new List("Devices discovered", List.EXCLUSIVE);
        cmdOK = new Command("ok", Command.OK, 1);
        cmdCancel = new Command("cancel", Command.CANCEL, 1);
        cmdExit = new Command("exit", Command.EXIT, 1);
        cmdBack = new Command("back", Command.BACK, 1);
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            // cleans previous elements
            btDevicesFound.removeAllElements();
            isBTSearchComplete = false;
            local = LocalDevice.getLocalDevice();
            messageForm.append("Esperando...\n");
            display.setCurrent(messageForm);
            messageForm.append("MAC address:\n" + local.getBluetoothAddress() + "\n");
            messageForm.append("Nombre: " + local.getFriendlyName());
            display.setCurrent(messageForm);
            discoveryAgent = local.getDiscoveryAgent();
            //local.setDiscoverable(DiscoveryAgent.GIAC);
            // discover new devices
            discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
            while (!isBTSearchComplete) {
                // waits for a fixed time, to avoid long search
                synchronized (this) {
                    this.wait(BLUETOOTH_TIMEOUT);
                }
                // check if search is completed
                if (!isBTSearchComplete) {
                    // search no yet completed so let's cancel it
                    discoveryAgent.cancelInquiry(this);
                }
            }
        } catch (BluetoothStateException bse) {
            messageForm.deleteAll();
            messageForm.append(bse.getMessage());
            display.setCurrent(messageForm);
        } catch (InterruptedException ie) {
            messageForm.deleteAll();
            messageForm.append(ie.getMessage());
            display.setCurrent(messageForm);
        }

    }

    public void deviceDiscovered(final RemoteDevice remoteDevice, final DeviceClass dClass) {
        btDevicesFound.addElement(remoteDevice);
    }

    public void servicesDiscovered(final int param, final ServiceRecord[] serviceRecord) {
        btServicesFound = serviceRecord[0];
    }

    public void serviceSearchCompleted(final int transId, final int respCode) {
        switch (respCode) {
            case SERVICE_SEARCH_TERMINATED:
                display.setCurrent(new Alert("La busqueda de servicios ha sido cancelada!"),
                  new OptionsForm(midlet, display));
                break;
            case SERVICE_SEARCH_ERROR:
                display.setCurrent(new Alert("Ha ocurrido un error en la busqueda del servidor!"),
                  new OptionsForm(midlet, display));
                break;
            case SERVICE_SEARCH_NO_RECORDS:
                display.setCurrent(new Alert("En el dispositivo remoto no se encuentra la aplicación!"),
                  new OptionsForm(midlet, display));
                break;
            case SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
                display.setCurrent(new Alert("El dispositivo remoto ha rechazado!"),
                  new OptionsForm(midlet, display));
                break;
            case SERVICE_SEARCH_COMPLETED:
                try {
                    createConnection();
                    output.writeUTF(message);
                    output.flush();
                    messageForm.deleteAll();
                    messageForm.append("Message sent");
                    display.setCurrent(messageForm);
                    output.close();
                    conn.close();
                } catch (Exception e) {
                    messageForm.deleteAll();
                    messageForm.append(e.getMessage());
                    display.setCurrent(messageForm);
                }
                break;
        }
    }

    private void createConnection() {
        try {
            url = btServicesFound.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            conn = (StreamConnection) Connector.open(url);
            output = conn.openDataOutputStream();
        } catch (Exception e) {
            messageForm.deleteAll();
            messageForm.append(e.getMessage());
            display.setCurrent(messageForm);
        }
    }

    public void inquiryCompleted(final int discType) {
        isBTSearchComplete = true;
        switch (discType) {
            case DiscoveryListener.INQUIRY_COMPLETED:
                if (btDevicesFound.size() > 0) {
                    deviceList.deleteAll();
                    for (int i = 0; i < btDevicesFound.size(); i++) {
                        try {
                            RemoteDevice rd = (RemoteDevice) btDevicesFound.elementAt(i);
                            deviceList.append(rd.getFriendlyName(connect), null);
                        } catch (IOException e) {
                            messageForm.deleteAll();
                            messageForm.append(e.getMessage());
                            display.setCurrent(messageForm);
                        }
                    }
                    deviceList.addCommand(cmdCancel);
                    deviceList.addCommand(cmdOK);
                    deviceList.setCommandListener(this);
                    display.setCurrent(deviceList);
                } else {
                    deviceChosen = true;
                    display.setCurrent(new Alert("No se encontro dispositivo."),
                      new OptionsForm(midlet, display));
                }
                break;
            case DiscoveryListener.INQUIRY_TERMINATED:
                display.setCurrent(new Alert("La busqueda de dispositivos ha sido terminada!"),
                  new OptionsForm(midlet, display));
                break;
            case DiscoveryListener.INQUIRY_ERROR:
                display.setCurrent(new Alert("Ha ocurrido un error en la busqueda de dispositivos."),
                  new OptionsForm(midlet, display));
                break;
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (d == deviceList && c == cmdOK) {
            otherDevice = (RemoteDevice) btDevicesFound.elementAt(deviceList.getSelectedIndex());
            deviceChosen = true;
            connect = true;
            messageForm.deleteAll();
            messageForm.append("Waiting...");
            display.setCurrent(messageForm);
        } else if (c == cmdBack) {
            display.setCurrent(list);
        } else if (c == cmdExit) {
            midlet.notifyDestroyed();
        } else if (c == cmdCancel) {
            midlet.notifyPaused();
        }
    }

}
