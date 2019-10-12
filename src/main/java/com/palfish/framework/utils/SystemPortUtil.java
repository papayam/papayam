package com.palfish.framework.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SystemPortUtil {
    private Socket socket;

    public SystemPortUtil() {
        socket = new Socket();
        InetSocketAddress inetAddress = new InetSocketAddress(0);
        try {
            socket.bind(inetAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void freePort() {
        if (null == socket || socket.isClosed()) {
            return;
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        if (null == socket || socket.isClosed()) {
            return -1;
        }
        return socket.getLocalPort();
    }

    public int getPortAndFree() {
        if (null == socket || socket.isClosed()) {
            return -1;
        }
        int port = socket.getLocalPort();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }
}
