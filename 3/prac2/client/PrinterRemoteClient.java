package prac2.client;

import java.rmi.Naming;

import prac2.PrinterRemote;

public class PrinterRemoteClient {
    public static void main(String[] args) {
        try {

            PrinterRemote printerRemote = (PrinterRemote) Naming.lookup("//" + args[0] + ":" + args[1] + "/RMITest");
            System.out.println(printerRemote.print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
