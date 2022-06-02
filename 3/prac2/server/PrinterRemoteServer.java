package prac2.server;

import java.rmi.MarshalledObject;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.rmi.activation.ActivationID;
import java.util.Properties;

import prac2.PrinterRemote;

public class PrinterRemoteServer extends Activatable implements PrinterRemote {

    public PrinterRemoteServer(ActivationID id, MarshalledObject<?> m) throws RemoteException {
        super(id, 0);
    }

    public static void main(String[] args) {
        try {
            String myIP = "192.168.45.115";
            System.out.println(myIP);
            Properties props = new Properties();
            props.put("java.security.policy", "/Users/dberrosp/ceti/parallel-computing/3/prac2/policy");
            props.put("java.rmi.server.hostname", myIP);

            ActivationGroupDesc.CommandEnvironment ace = null;
            ActivationGroupDesc agd = new ActivationGroupDesc(props, ace);

            ActivationGroupID agi = ActivationGroup.getSystem().registerGroup(agd);
            MarshalledObject<?> mObj = null;

            ActivationDesc desc = new ActivationDesc(agi, "prac2.server.PrinterRemoteServer", "file://Users/dberrosp/ceti/parallel-computing/3/prac2/server/", mObj);

            PrinterRemote printerRemote = (PrinterRemote) Activatable.register(desc);

            Naming.rebind("//" + myIP + ":" + args[0] + "/RMITest",
                    printerRemote);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public String print() throws RemoteException {
        System.out.println("Hello, World! from server.");
        return "Hello, World! from server.";
    }
}