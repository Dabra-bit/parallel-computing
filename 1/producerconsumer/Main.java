package producerconsumer;

import producerconsumer.gui.WarehouseWindow;

public class Main {
    public static final int DEFAULT_SLEEP = 50;
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        WarehouseWindow warehouseWindow = new WarehouseWindow("Almacén", buffer, 400, 500);
        Thread tWindow = new Thread(warehouseWindow);
        tWindow.start();
        warehouseWindow.setVisible(true);
    }
}