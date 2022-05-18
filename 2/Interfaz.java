package com.mycompany.ms;

/**
 *
 * @author zonar
 */


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import  java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Interfaz extends javax.swing.JFrame {
    String []array;
    int val,n;
    boolean opcion=false;

    public Interfaz() {
        initComponents();
        jLabel1.setText("");
    }
    
    public class TaskIP extends RecursiveAction{
    private String []arrays;
    private int num;
    private boolean fin;
        public TaskIP(String[] arrays,boolean fin) {
            this.arrays = arrays;
            this.fin=fin;
        }

        public TaskIP(String[] arrays, int num) {
            this.arrays = arrays;
            this.num = num;
        }
        @Override
        protected void compute(){
            if(!fin){
            TaskIP tarea= new TaskIP(arrays,true);
            invokeAll(tarea);
            for(int i=0;i<arrays.length;i++){
                JLabel j=new JLabel(""+ip(arrays[i]));
                jPanel2.add(j);
            }
            
            }
            else{
            return;
            }
        }
        public String ip(String  reqIpAddr){
            String hex = "";
            
            String[] part = reqIpAddr.split("[\\.,]");
            if (part.length < 4) {
                System.out.println("00000000");
            }
            for (int i = 0; i < 4; i++) {
                int decimal = Integer.parseInt(part[i]);
                if (decimal < 16){
                    hex += "0" + String.format("%01X", decimal);
                }else {hex += String.format("%01X", decimal);
                }
            }
            return hex;
        }
    }
   
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Archivo");
        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton1ActionPerformed(evt);
            } catch (Exception ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(0, 5));
        jScrollPane1.setViewportView(jPanel1);

        jButton2.setText("Convertir ");
        jButton2.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton2ActionPerformed(evt);
            } catch (Exception ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jButton3.setText("Fork/Join");
        jButton3.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton3ActionPerformed(evt);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jButton4.setText("ExecutorService");
        jButton4.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton4ActionPerformed(evt);
        });

        jButton5.setText("Limpiar");
        jButton5.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton5ActionPerformed(evt);
        });

        jPanel2.setLayout(new java.awt.GridLayout(0, 5));
        jScrollPane2.setViewportView(jPanel2);

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
        
        val=n;
        array = new String[n+1];
        File doc = new File("C:\\Archivo\\IPS.txt");
        BufferedReader obj = new BufferedReader(new FileReader(doc));
         
        String strng;
        String splited [];
        jPanel1.removeAll();
        while ((strng = obj.readLine()) != null) {
            splited = strng.split("\\s+");
            for (String part : splited) {
                String ip = part;
                JLabel j=new JLabel(""+ip);
                jPanel1.add(j);
            }
        }
        obj.close();
        System.out.println("IPs agregadas");
        jPanel1.updateUI();
        opcion=true;
        
       
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
      jPanel1.removeAll();
      jPanel2.removeAll();
      jLabel1.setText("");
      jPanel1.updateUI();
      jPanel2.updateUI();
      opcion=false;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
        if(opcion){
            long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            jPanel2.removeAll();

            File doc = new File("C:\\Archivo\\IPS.txt");
            BufferedReader obj = new BufferedReader(new FileReader(doc));
            String strng;
            String splited [];
            TInicio = System.currentTimeMillis();
            while ((strng = obj.readLine()) != null) {
                splited = strng.split("\\s+");
                for (String part : splited) {
                    String ip = part;
                    JLabel j=new JLabel(""+ip(ip));
                    jPanel2.add(j);
                }
            }
            obj.close();
            jPanel2.updateUI();
            System.out.println("A HEXADECIMAL NORMAL");
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
            System.out.println("Tiempo de ejecución en milisegundos:" + tiempo);
            jLabel1.setText("milisegundos:" + tiempo);
        }else{
            JOptionPane.showMessageDialog(rootPane,"Presiona el boton de archivo para que se lean los datos");
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException {
        
        if(opcion){
            jPanel2.removeAll();
            long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            File doc = new File("C:\\Archivo\\IPS.txt");
            BufferedReader obj = new BufferedReader(new FileReader(doc));
            String strng;
            String splited [];
            StringBuilder test = new StringBuilder();
            String test2;
            String test3[]={};


            while ((strng = obj.readLine()) != null) {
                splited = strng.split("\\s+");
                for (String part : splited) {
                    test.append(part+'\n');
                }
            }
            obj.close();
            test2 = test.toString();
            test3 = test2.split("\n");
            TInicio = System.currentTimeMillis();
            forkJoinPool.invoke(new TaskIP(test3,false));

            
            jPanel2.updateUI();
            System.out.println("Mergesort con forkJoin");
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
            System.out.println("Tiempo de ejecución en milisegundos:" + tiempo);
            jLabel1.setText("milisegundos:" + tiempo);
        }else{
       JOptionPane.showMessageDialog(rootPane,"Presiona el boton de archivo para que se lean los datos");
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(opcion){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Runnable command = new Runnable() {
                @Override
                public void run() {
                    long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
                    TInicio = System.currentTimeMillis();
                    
                    File doc = new File("C:\\Archivo\\IPS.txt");
                    BufferedReader obj = null;
                    try {
                        obj = new BufferedReader(new FileReader(doc));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String strng;
                    String splited [];
                    
                    try {
                        while ((strng = obj.readLine()) != null) {
                            splited = strng.split("\\s+");
                            for (String part : splited) {
                                String ip = part;
                                JLabel j=new JLabel(""+ip(ip));
                                jPanel2.add(j);
                            }
                        }
                        obj.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jPanel2.updateUI();
                    System.out.println("HEXADECIMAL con ExecutorService");
                    TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
                    tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
                    System.out.println("Tiempo de ejecución en milisegundos:" + tiempo);
                    jLabel1.setText("milisegundos:" + tiempo);
                }
            };  
            jPanel2.removeAll();
            executorService.execute(command);     
        }else{
       JOptionPane.showMessageDialog(rootPane,"Presiona el boton de archivo para que se lean los datos");
         }
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    public String ip(String reqIpAddr){
        String hex = "";
        String[] part = reqIpAddr.split("[\\.,]");
        if (part.length < 4) {
            System.out.println("00000000");
        }
        for (int i = 0; i < 4; i++) {
            int decimal = Integer.parseInt(part[i]);
            if (decimal < 16){
                hex += "0" + String.format("%01X", decimal);
            }else {hex += String.format("%01X", decimal);
            }
        }
	return hex;
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
}
