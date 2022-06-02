package chat.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageInterface extends Remote {
    public String showMessage(String message) throws RemoteException;
}