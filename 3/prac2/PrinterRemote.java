package prac2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrinterRemote extends Remote {
    public String print() throws RemoteException;
}