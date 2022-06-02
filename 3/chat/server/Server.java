package chat.server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import chat.common.MessageInterface;
import chat.common.ComputersConstants;

public class Server extends UnicastRemoteObject implements MessageInterface, ComputersConstants {

    public static void main(String[] args) {
        try {
            boolean hardcoded = true;
            String ip = hardcoded ? SERVER_IP : InetAddress.getLocalHost().getHostAddress();
            MessageInterface server = new Server();

            System.setProperty("java.rmi.server.hostname", ip);
            Naming.rebind("rmi://" + ip + ":1099/RMIChat", server);

        } catch (MalformedURLException | UnknownHostException | RemoteException e) {
            e.printStackTrace();
        }
    }

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public String showMessage(String message) throws RemoteException {
        System.out.println("Broadcasting: " + message);
        for (String ip : CLIENTS_IPS) {
            try {
                MessageInterface client = (MessageInterface) Naming.lookup("rmi//" + ip + ":1099/RMIChat");
                client.showMessage(message);
            } catch (MalformedURLException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
