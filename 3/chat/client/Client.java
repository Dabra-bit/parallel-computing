package chat.client;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.SwingUtilities;

import chat.window.ChatWindow;

public class Client extends UnicastRemoteObject implements ClientInterface, ComputersConstants {

    public ChatWindow chatWindow;
    public static void main(String[] args) {
        try {
            boolean hardcoded = true;
            String ip = hardcoded ? IPs[1] : InetAddress.getLocalHost().getHostAddress();
            Client client = new Client();
            SwingUtilities.invokeLater(() -> client.initChatWindow());

            System.setProperty("java.rmi.server.hostname", ip);
            Naming.rebind("//" + ip + ":1099/RMIChat",
                    client);

        } catch (MalformedURLException | UnknownHostException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public Client() throws RemoteException {
        super();
    }

    public void initChatWindow() {
        chatWindow = new ChatWindow("Chat app");
        chatWindow.setVisible(true);
    }

    @Override
    public String showMessage(String message) throws RemoteException {
        chatWindow.setOutputText(message);
        return message;
    }
    
}
