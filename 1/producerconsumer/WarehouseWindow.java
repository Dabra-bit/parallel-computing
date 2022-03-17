package producerconsumer;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarehouseWindow extends JFrame implements Runnable {
    
    public static final int DEFAULT_SLEEP = 500;
    public static final int MAX_PRODUCERS = 4;
    public static final int MAX_CONSUMERS = 4;

    private JButton btnAddProd;
    private JButton btnAddCons;
    private JLabel[] lblProds;
    private JLabel[] lblCons;
    private int prodCount;
    private int consCount;
    private Buffer buffer;

    public WarehouseWindow(String windowTitle, Buffer buffer, int width, int height) {
        super(windowTitle);

        prodCount = consCount = 1;
        this.buffer = buffer;

        setSize(width, height); // Set window size
        createBaseWindow();

        initButtons();
        initLabels();
        handleEvents();

        add(btnAddCons);
        add(btnAddProd);
    }

    private void initButtons() {
        btnAddProd = new JButton("Add producer");
        btnAddCons = new JButton("Add consumer");

        btnAddProd.setLocation(50, 20);
        btnAddProd.setSize(50, 20);

        btnAddCons.setLocation(150, 20);
        btnAddCons.setSize(50, 20);
    }

    private void initLabels() {
        lblProds = new JLabel[4];
        lblCons = new JLabel[4];

        int initY = 60;
        
        for(int i = 0; i < 4; i++) {
            lblProds[i] = new JLabel("Producer " + (i+1));
            lblCons[i] = new JLabel("Consumer " + (i+1));

            lblProds[i].setLocation(50, initY);
            lblProds[i].setSize(50, 20);

            lblCons[i].setLocation(150, initY);
            lblCons[i].setSize(50, 20);

            initY += 20;
        }
    }

    private void createBaseWindow() {
        // Close application on window close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new JPanel()); // Add the panel
        setResizable(false); // Disable window risizing
        setLocationRelativeTo(null); // Open window in the center of the display
        
        setLayout(new FlowLayout());
    }

    private void handleEvents(){
        btnAddProd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //if(prodCount == MAX_PRODUCERS) return;

                //add(lblProds[prodCount - 1]);
                Thread producer = new Producer("Producer " + prodCount++, buffer, DEFAULT_SLEEP);
                producer.start();
            }
        });

        btnAddCons.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //if(consCount == MAX_CONSUMERS) return;

                //add(lblCons[consCount - 1]);
                Thread consumer = new Consumer("Consumer " + consCount++, buffer, DEFAULT_SLEEP);
                consumer.start();
            }
            
        });
    }

    @Override
    public void run() {
        
    }

}
