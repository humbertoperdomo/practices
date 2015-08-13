package bluetoothservicesearch;

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

    private LocalDevice localDevice;
    private MIDlet midlet;
    private String deviceName;
    private Display display;
    private UUID[] uuid;
    private DiscoveryAgent discoveryAgent;
    private Vector devices;
    private Command cmdOK, cmdCancel;
    private List deviceList;
    private ServiceRecord service;
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

        devices = new Vector();
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
            messageForm.append("Esperando...\n");
            display.setCurrent(messageForm);
            localDevice = LocalDevice.getLocalDevice();
            messageForm.append("MAC address:\n" + localDevice.getBluetoothAddress() + "\n");
            messageForm.append("Nombre: " + localDevice.getFriendlyName());
            display.setCurrent(messageForm);
            discoveryAgent = localDevice.getDiscoveryAgent();
            localDevice.setDiscoverable(DiscoveryAgent.GIAC);
            discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
            uuid = new UUID[1];
            uuid[0] = new UUID("0003", false);

            while (!deviceChosen) {
            }

            if (connect) {
                //new int[]{0x0100}
                transactionID = discoveryAgent.searchServices(null, uuid,
                  otherDevice, this);
            }
        } catch (BluetoothStateException e) {
            messageForm.deleteAll();
            messageForm.append(e.getMessage());
            display.setCurrent(messageForm);
        } //catch (IOException f) {
        //messageForm.append("IOException: " + f);
        //}

    }

    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        devices.addElement(btDevice);
    }

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        service = servRecord[0];
    }

    public void serviceSearchCompleted(int transID, int respCode) {
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
            url = service.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            conn = (StreamConnection) Connector.open(url);
            output = conn.openDataOutputStream();
        } catch (Exception e) {
            messageForm.deleteAll();
            messageForm.append(e.getMessage());
            display.setCurrent(messageForm);
        }
    }

    public void inquiryCompleted(int discType) {
        switch (discType) {
            case DiscoveryListener.INQUIRY_COMPLETED:
                if (devices.size() > 0) {
                    deviceList.deleteAll();
                    for (int i = 0; i < devices.size(); i++) {
                        try {
                            RemoteDevice rd = (RemoteDevice) devices.elementAt(i);
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
            otherDevice = (RemoteDevice) devices.elementAt(deviceList.getSelectedIndex());
            deviceChosen = true;
            connect = true;
            messageForm.deleteAll();
            messageForm.append("Waiting...");
            display.setCurrent(messageForm);
        } else if (c == cmdBack) {
            display.setCurrent(list);
        } else if (c == cmdExit){
            midlet.notifyDestroyed();
        } else if (c == cmdCancel){
            midlet.notifyPaused();
        }
    }

}
