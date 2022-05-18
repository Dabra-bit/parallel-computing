package prac1.server;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import prac1.PrinterRemote;

public class PrinterRemoteServer extends UnicastRemoteObject implements PrinterRemote {

    public static void main(String[] args) {
        try {
            PrinterRemote printerRemote = new PrinterRemoteServer();

            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());

            Naming.rebind("//" + InetAddress.getLocalHost().getHostAddress() + ":" + args[0] + "/RMITest",
                    printerRemote);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrinterRemoteServer() throws RemoteException {
        super();
    }

    @Override
    public void print() throws RemoteException {
        System.out.println("Hello, World! from server.");
    }
}