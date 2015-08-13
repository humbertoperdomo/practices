package com.nutiteq.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connection;
import javax.microedition.io.StreamConnection;

import com.nutiteq.location.providers.LocationDataConnection;
import com.nutiteq.utils.IOUtils;

/**
 * Wrapper around J2ME Connection class.
 */
public class BluetoothAPIConnection implements LocationDataConnection {

    private final Connection connection;

    public BluetoothAPIConnection(final Connection connection) {
        this.connection = connection;
    }

    public void disconnect() {
        IOUtils.closeConnection(connection);
    }

    public InputStream openInputStream() throws IOException {
        return ((StreamConnection) connection).openInputStream();
    }

    public OutputStream openOutputStream() throws IOException {
        return ((StreamConnection) connection).openOutputStream();
    }
}
