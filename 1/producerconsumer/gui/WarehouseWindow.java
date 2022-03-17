package producerconsumer.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import producerconsumer.Buffer;
import producerconsumer.Consumer;
import producerconsumer.Producer;

public class WarehouseWindow extends JFrame implements Runnable {
    
    public static final int DEFAULT_SLEEP = 500;
    public static final int MAX_PRODUCERS = 4;
    public static final int MAX_CONSUMERS = 4;

    private JPanel mPanel;
    private JButton btnAddProd;
    private JButton btnAddCons;
    private JPanel pnlBtns;
    private JPanel pnlProds;
    private JPanel pnlCons;

    private int prodCount;
    private int consCount;
    private List<ProducerCard> prodCards;
    private List<ConsumerCard> consCards;
    private Buffer buffer;

    public WarehouseWindow(String windowTitle, Buffer buffer, int width, int height) {
        super(windowTitle);
        prodCount = consCount = 1;
        this.buffer = buffer;

        setSize(width, height); // Set window size
        createBaseWindow();
        
        initButtonsPanel();

        initCards();
        initProdsPanel();
        initConsPanel();

        mPanel.add(pnlBtns, BorderLayout.NORTH);
        mPanel.add(pnlProds, BorderLayout.WEST);
        mPanel.add(pnlCons, BorderLayout.EAST);
    }

    private void initButtonsPanel() {
        pnlBtns = new JPanel();
        pnlBtns.setSize(400, 50);
        pnlBtns.setLayout(new FlowLayout());

        btnAddProd = new JButton("Add producer");
        btnAddCons = new JButton("Add consumer");

        btnAddProd.setLocation(50, 20);
        btnAddProd.setSize(50, 20);

        btnAddCons.setLocation(150, 20);
        btnAddCons.setSize(50, 20);

        btnAddProd.addActionListener(actionEvent -> addProducer());
        btnAddCons.addActionListener(actionEvent -> addConsumer());

        pnlBtns.add(btnAddProd);
        pnlBtns.add(btnAddCons);
    }

    private void initCards() {
        prodCards = new ArrayList<>();
        consCards = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            prodCards.add(new ProducerCard("Producer " + (i+1)));
            consCards.add(new ConsumerCard("Consumer " + (i+1)));
        }
    }

    private void initProdsPanel() {
        pnlProds = new JPanel();
        pnlProds.setSize(200, 600);
        pnlProds.setLayout(new GridLayout(4, 1));

        for(JPanel p : prodCards) {
            p.setVisible(false);
            pnlProds.add(p);
        }
    }

    private void initConsPanel() {
        pnlCons = new JPanel();
        pnlCons.setSize(200, 600);
        pnlCons.setLayout(new GridLayout(4, 1));

        for(JPanel p : consCards) {
            p.setVisible(false);
            pnlCons.add(p);
        }
    }

    private void createBaseWindow() {
        mPanel = new JPanel();
        // Close application on window close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mPanel); // Add the panel
        setResizable(false); // Disable window risizing
        setLocationRelativeTo(null); // Open window in the center of the display
        
        mPanel.setLayout(new BorderLayout());
    }

    private void addProducer() {
        Thread producer = new Producer(
            "Producer " + prodCount,
            buffer,
            prodCards.get(prodCount - 1)
            );
        prodCount++;
        producer.start();
    }

    private void addConsumer() {
        Thread consumer = new Consumer(
            "Consumer " + consCount,
            buffer,
            consCards.get(consCount - 1));
        consCount++;
        consumer.start();
    }

    @Override
    public void run() {
        
    }

}
