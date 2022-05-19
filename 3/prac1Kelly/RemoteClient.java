package prac1Kelly;

import java.rmi.Naming;

public class RemoteClient {
    public static void main(String[] args) {
        try {

            P_Remote printeRemote = (P_Remote) Naming.lookup("//" + args[0] + ":" + args[1] + "P_RMI");
            printeRemote.metodo1();
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
