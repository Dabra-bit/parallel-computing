package chat.window;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import chat.client.ClientInterface;
import chat.client.ComputersConstants;
import chat.server.ServerInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatWindow extends JFrame implements ComputersConstants {
    
    private JPanel mPanel;
    private JPanel inputPanel;
    private JPanel controlsPanel;
    private JPanel outputPanel;
    private JLabel lblInput;
    private JLabel lblOutput;
    private JTextArea tAreaInput;
    private JTextArea tAreaOutput;
    private JButton btnSend;
    private JComboBox<String> cBoxDest;

    public ChatWindow(String title) {
        super(title);

        setSize(new Dimension(800, 400));
        createBaseWindow();
        
        initInputPanel();
        initControlsPanel();
        initOutputPanel();

        mPanel.add(inputPanel);
        mPanel.add(controlsPanel);
        mPanel.add(outputPanel);
    }
    
    private void initInputPanel() {
        FlowLayout flowLayout = new FlowLayout();
        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(300, 300));
        inputPanel.setLayout(flowLayout);

        lblInput = new JLabel("Input");
        tAreaInput = new JTextArea();
        lblInput.setPreferredSize(new Dimension(50, 20));
        tAreaInput.setPreferredSize(new Dimension(280, 250));

        inputPanel.add(lblInput);
        inputPanel.add(tAreaInput);
    }

    private void initControlsPanel() {
        BorderLayout borderLayout = new BorderLayout();
        controlsPanel = new JPanel();
        controlsPanel.setPreferredSize(new Dimension(150, 300));
        controlsPanel.setLayout(borderLayout);

        btnSend = new JButton("Send");
        btnSend.addActionListener(actionEvent -> sendMessage());
        
        int i = 1;
        String[] opts = new String[IPs.length + 1];
        opts[0] = "All";
        for(String ip : IPs) {
            opts[i++] = ip;
        }

        cBoxDest = new JComboBox<String>(opts);
        cBoxDest.setSelectedItem(0);

        controlsPanel.add(cBoxDest, BorderLayout.NORTH);
        controlsPanel.add(btnSend, BorderLayout.SOUTH);
    }

    private void initOutputPanel() {
        FlowLayout flowLayout = new FlowLayout();
        outputPanel = new JPanel();
        outputPanel.setPreferredSize(new Dimension(300, 300));
        outputPanel.setLayout(flowLayout);

        lblOutput = new JLabel("Output");
        tAreaOutput = new JTextArea();
        lblOutput.setPreferredSize(new Dimension(50, 20));
        tAreaOutput.setPreferredSize(new Dimension(280, 250));

        outputPanel.add(lblOutput);
        outputPanel.add(tAreaOutput);
    }

    private void createBaseWindow() {
        mPanel = new JPanel();
        // Close application on window close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mPanel); // Add the panel
        setResizable(false); // Disable window risizing
        setLocationRelativeTo(null); // Open window in the center of the screen
        
        mPanel.setLayout(new FlowLayout());
    }

    private void sendMessage() {
        String ip = (String) cBoxDest.getSelectedItem();
        
        if(ip.equals("All")) {
            try {
                ServerInterface server = (ServerInterface) Naming.lookup("//" + IPs[0] + ":1099/RMIChat");
                server.sendGlobalMessage(tAreaInput.getText());
            } catch (RemoteException | MalformedURLException | NotBoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ClientInterface client = (ClientInterface) Naming.lookup("//" + ip + ":1099/RMIChat");
                client.showMessage(tAreaInput.getText());
            } catch (RemoteException | MalformedURLException | NotBoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setOutputText(String message) {
        tAreaOutput.setText(message);
    }
}