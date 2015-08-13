/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothservicesearch;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
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
public class BluetoothServer implements Runnable, CommandListener {

    private MIDlet midlet;
    private Display display;
    private StreamConnectionNotifier con;
    private Form list;
    Vector conections, inputs;
    Thread t;
    private Command cmdExit;
    private Command cmdBack;
    private int numClientes;

    public BluetoothServer(MIDlet midlet, Display display, Form previousForm,
      int numClientes) {
        cmdExit = new Command("exit", Command.EXIT, 1);
        cmdBack = new Command("back", Command.BACK, 1);
        this.numClientes = numClientes;
        this.midlet = midlet;
        this.display = display;
        this.list = previousForm;
        conections = new Vector();
        inputs = new Vector();
        t = new Thread(this);
        t.start();
    }

    public void run() {
        Form messageForm = new Form("");
        messageForm.append("Waiting...");
        display.setCurrent(messageForm);
        
        createConnections();
        messageForm.deleteAll();
        messageForm.append(receiveMessageFromAll());
        display.setCurrent(messageForm);
    }
    
    private void createConnections(){
        UUID uuid = new UUID(0x0009);
        LocalDevice localDevice;
        try{
            localDevice = LocalDevice.getLocalDevice();
            localDevice.setDiscoverable(DiscoveryAgent.GIAC);
            con = (StreamConnectionNotifier) Connector.open("btspp://localhost:"+uuid 
              +";name=");
            for (int i = 0; i < numClientes; i++){
                
                StreamConnection conn = con.acceptAndOpen();
                DataInputStream in = conn.openDataInputStream();

                conections.addElement(conn);
                inputs.addElement(in);
            }
        } catch (Exception e) {
            System.out.println("lancei exceptions!");

        }
    }

    public String receiveMessageFromAll() {
        String answer = "Mensajes recibidos:\n\n";
        for (int i = 0; i < inputs.size(); i++) {
            try {
                DataInputStream input = (DataInputStream) inputs.elementAt(i);
                answer += input.readUTF() + "\n\n";
            } catch (IOException e) {
            }
        }
        return answer.trim();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack){
            display.setCurrent(list);
        } else if (c == cmdExit){
            midlet.notifyDestroyed();
        }
    }
}
