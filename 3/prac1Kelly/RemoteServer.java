package prac1Kelly;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServer extends UnicastRemoteObject implements P_Remote{

    protected RemoteServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException {
        try {
            P_Remote printeRemote = new RemoteServer();

                java.rmi.Naming.rebind("//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":" + args[0] + "/P_RMI", printeRemote);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void metodo1() throws RemoteException {
        System.out.println("Hola cliente, vengo del 1099 :) ... Quiereme!");
    }
}
