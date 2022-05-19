package prac1Kelly;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface P_Remote extends Remote{
    public void metodo1() throws RemoteException;
}
