package chat.server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.SwingUtilities;

import chat.client.ClientInterface;
import chat.client.ComputersConstants;
import chat.window.ChatWindow;

public class Server extends UnicastRemoteObject implements ServerInterface, ClientInterface, ComputersConstants {

    public ChatWindow chatWindow;

    public static void main(String[] args) {
        try {
            boolean hardcoded = true;
            String ip = hardcoded ? "192.168.100.254" : InetAddress.getLocalHost().getHostAddress();
            Server server = new Server();
            SwingUtilities.invokeLater(() -> server.initChatWindow());

            System.setProperty("java.rmi.server.hostname", ip);
            Naming.rebind("//" + ip + ":1099/RMIChat",
                    server);

        } catch (MalformedURLException | UnknownHostException | RemoteException e) {
            e.printStackTrace();
        }
    }

    protected Server() throws RemoteException {
        super();
    }

    public void initChatWindow() {
        chatWindow = new ChatWindow("Chat app");
        chatWindow.setVisible(true);
    }

    @Override
    public String sendGlobalMessage(String message) throws RemoteException {
        for (String ip : IPs) {
            try {
                ClientInterface client = (ClientInterface) Naming.lookup("//" + ip + ":1099/RMIChat");
                client.showMessage(message);
            } catch (MalformedURLException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String showMessage(String message) throws RemoteException {
        chatWindow.setOutputText(message);
        return message;
    }

}
