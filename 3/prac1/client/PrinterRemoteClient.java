package prac1.client;

import java.rmi.Naming;

import prac1.PrinterRemote;

public class PrinterRemoteClient {
    public static void main(String[] args) {
        try {

            PrinterRemote printerRemote = (PrinterRemote) Naming.lookup("//" + args[0] + ":" + args[1] + "/RMITest");
            printerRemote.print();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
