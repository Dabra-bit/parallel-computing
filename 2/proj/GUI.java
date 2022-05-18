package proj;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import javax.swing.WindowConstants;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI extends JFrame {

    String[] array;
    int val, n;
    boolean isFileLoaded = false;

    private JButton btnLoadFile;
    private JButton btnConvert;
    private JButton btnForkJoin;
    private JButton btnExecutorService;
    private JButton btnClear;
    private JLabel lblExecutionTime;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;

    public GUI() {
        initComponents();
        lblExecutionTime.setText("");
    }

    public class TaskIP extends RecursiveAction {
        private String[] ips;
        private boolean end;

        public TaskIP(String[] ips) {
            this.ips = ips;
            this.end = false;
        }

        public TaskIP(String[] ips, boolean end) {
            this.ips = ips;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (!end) {
                TaskIP tarea = new TaskIP(ips, true);
                invokeAll(tarea);
                for (int i = 0; i < ips.length; i++) {
                    JLabel j = new JLabel(Utils.IP2Hex(ips[i]));
                    jPanel2.add(j);
                }

            } else {
                return;
            }
        }
    }

    private void initComponents() {

        btnLoadFile = new JButton();
        jScrollPane1 = new JScrollPane();
        jPanel1 = new JPanel();
        btnConvert = new JButton();
        btnForkJoin = new JButton();
        btnExecutorService = new JButton();
        btnClear = new JButton();
        jScrollPane2 = new JScrollPane();
        jPanel2 = new JPanel();
        lblExecutionTime = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnLoadFile.setText("Archivo");
        btnLoadFile.addActionListener((ActionEvent evt) -> {
            try {
                btnLoadFileActionPerformed(evt);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jPanel1.setLayout(new GridLayout(0, 5));
        jScrollPane1.setViewportView(jPanel1);

        btnConvert.setText("Convertir ");
        btnConvert.addActionListener((ActionEvent evt) -> {
            try {
                btnConvertActionPerformed(evt);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnForkJoin.setText("Fork/Join");
        btnForkJoin.addActionListener((ActionEvent evt) -> {
            try {
                btnForkJoinActionPerformed(evt);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnExecutorService.setText("ExecutorService");
        btnExecutorService.addActionListener((ActionEvent evt) -> {
            btnExecutorServiceActionPerformed(evt);
        });

        btnClear.setText("Limpiar");
        btnClear.addActionListener((ActionEvent evt) -> {
            btnClearActionPerformed(evt);
        });

        jPanel2.setLayout(new GridLayout(0, 5));
        jScrollPane2.setViewportView(jPanel2);

        lblExecutionTime.setText("Tiempo (ms): ");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnClear)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnLoadFile, GroupLayout.PREFERRED_SIZE,
                                                                        84, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnConvert)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnForkJoin)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnExecutorService))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 570,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGap(34, 34, 34)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblExecutionTime, GroupLayout.PREFERRED_SIZE, 140,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 570,
                                                                GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(23, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnClear)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnLoadFile)
                                        .addComponent(btnConvert)
                                        .addComponent(btnForkJoin)
                                        .addComponent(btnExecutorService))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblExecutionTime))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 300,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)));

        pack();
    }

    private void btnLoadFileActionPerformed(ActionEvent evt) throws Exception {

        val = n;
        array = new String[n + 1];
        BufferedReader fileBufferedReader = FileUtils.getReadableFile();

        String line;
        String splitted[];
        jPanel1.removeAll();

        while ((line = fileBufferedReader.readLine()) != null) {
            splitted = line.split("\\s+");
            for (String ip : splitted) {
                JLabel j = new JLabel(ip);
                jPanel1.add(j);
            }
        }
        fileBufferedReader.close();
        System.out.println("IPs agregadas");
        jPanel1.updateUI();
        isFileLoaded = true;

    }

    private void btnClearActionPerformed(ActionEvent evt) {
        jPanel1.removeAll();
        jPanel2.removeAll();
        lblExecutionTime.setText("");
        jPanel1.updateUI();
        jPanel2.updateUI();
        isFileLoaded = false;
    }

    private void btnConvertActionPerformed(ActionEvent evt) throws Exception {
        if (isFileLoaded) {
            // Variables for time
            long tStart, tEnd, time;
            jPanel2.removeAll();

            String line;
            String ips[];

            tStart = System.currentTimeMillis();
            BufferedReader filBufferedReader = FileUtils.getReadableFile();
            while ((line = filBufferedReader.readLine()) != null) {
                ips = line.split("\\s+");
                for (String ip : ips) {
                    JLabel j = new JLabel(Utils.IP2Hex(ip));
                    jPanel2.add(j);
                }
            }
            filBufferedReader.close();
            jPanel2.updateUI();
            System.out.println("A HEXADECIMAL NORMAL");
            tEnd = System.currentTimeMillis(); // Tomamos la hora en que finalizó el algoritmo y la almacenamos en la
                                               // variable T
            time = tEnd - tStart; // Calculamos los milisegundos de diferencia
            System.out.println("Tiempo de ejecución en milisegundos: " + time);
            lblExecutionTime.setText("Tiempo (ms): " + time);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Presiona el boton de archivo para que se lean los datos");
        }
    }

    private void btnForkJoinActionPerformed(ActionEvent evt) throws FileNotFoundException, IOException {

        if (isFileLoaded) {
            jPanel2.removeAll();
            long tStart, tEnd, tiempo; // Variables para determinar el tiempo de ejecución
            ForkJoinPool forkJoinPool = new ForkJoinPool();

            BufferedReader filBufferedReader = FileUtils.getReadableFile();

            String line;
            String splitted[];
            StringBuilder strBuilder = new StringBuilder();
            String ipsMergedInASingleString;
            String listOfIps[] = {};

            while ((line = filBufferedReader.readLine()) != null) {
                splitted = line.split("\\s+");
                for (String ip : splitted) {
                    strBuilder.append(ip + '\n');
                }
            }

            filBufferedReader.close();
            ipsMergedInASingleString = strBuilder.toString();
            listOfIps = ipsMergedInASingleString.split("\n");
            tStart = System.currentTimeMillis();

            forkJoinPool.invoke(new TaskIP(listOfIps));

            tEnd = System.currentTimeMillis();
            System.out.println("A HEXADECIMAL FORK/JOIN");
            jPanel2.updateUI();
            tiempo = tEnd - tStart;

            System.out.println("Tiempo de ejecución en milisegundos:" + tiempo);
            lblExecutionTime.setText("Tiempo (ms): " + tiempo);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Presiona el boton de archivo para que se lean los datos");
        }
    }

    private void btnExecutorServiceActionPerformed(ActionEvent evt) {// GEN-FIRST:event_btnExecutorServiceActionPerformed
        if (isFileLoaded) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Runnable command = new Runnable() {
                @Override
                public void run() {
                    long tStart, tEnd, tiempo; // Variables para determinar el tiempo de ejecución
                    tStart = System.currentTimeMillis();

                    BufferedReader filBufferedReader = FileUtils.getReadableFile();
                    String line;
                    String splitted[];

                    try {
                        while ((line = filBufferedReader.readLine()) != null) {
                            splitted = line.split("\\s+");
                            for (String ip : splitted) {
                                JLabel j = new JLabel(Utils.IP2Hex(ip));
                                jPanel2.add(j);
                            }
                        }
                        filBufferedReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jPanel2.updateUI();
                    System.out.println("HEXADECIMAL con ExecutorService");
                    tEnd = System.currentTimeMillis(); // Tomamos la hora en que finalizó el algoritmo y la almacenamos
                                                       // en la variable T
                    tiempo = tEnd - tStart; // Calculamos los milisegundos de diferencia
                    System.out.println("Tiempo de ejecución en milisegundos:" + tiempo);
                    lblExecutionTime.setText("Tiempo (ms): " + String.valueOf(tiempo));
                }
            };
            jPanel2.removeAll();
            executorService.execute(command);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Presiona el boton de archivo para que se lean los datos");
        }
    }

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

}
