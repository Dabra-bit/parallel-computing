package chat.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public String showMessage(String message) throws RemoteException;
}