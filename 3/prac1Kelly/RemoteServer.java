package prac1Kelly;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import prac1.PrinterRemote;

public class RemoteServer extends UnicastRemoteObject implements PrinterRemote{

    public static void main(String[] args) throws RemoteException {
        try {
            PrinterRemote printeRemote = new RemoteServer();

                java.rmi.Naming.rebind("//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":" + args[0] + "/P_RMI", printeRemote);

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void print() throws RemoteException {
        // TODO Auto-generated method stub
        System.out.println("Hola cliente, vengo del 1099 :) ... Quiereme!");
        
    }
    protected RemoteServer() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }
}
