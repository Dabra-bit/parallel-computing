package prac1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrinterRemote extends Remote {
    public void print() throws RemoteException;
}