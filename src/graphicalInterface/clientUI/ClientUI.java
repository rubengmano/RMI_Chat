package graphicalInterface.clientUI;

import logic.client.Client;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public class ClientUI extends JFrame {
    Timer timer;
    Client client;
    JTextArea inputMSG ,otherPanel;
    JButton sendButton, readButton, space;


    public ClientUI(Client client){
        super("CLIENT");
        this.client = client;

        setupComponents();
        setupLayout();

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        notifyUser();
    }

    private void setupComponents(){
        sendButton = new JButton("Send Message");
        sendButton.addActionListener(new sendButtonListener());
        readButton = new JButton("Read Messages");
        readButton.addActionListener(new readButtonListener());
        inputMSG = new JTextArea(60, 20);
        inputMSG.setWrapStyleWord(true);
        inputMSG.setLineWrap(true);
        space = new JButton("                 ");
        space.setOpaque(false);
        space.setContentAreaFilled(false);
        space.setBorderPainted(false);

    }

    private void notifyUser(){
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(client.getBuffer().equals(null))) {
                    JOptionPane.showMessageDialog(null, client.getBuffer(), "InfoBox: " + "rmi::Chat", JOptionPane.INFORMATION_MESSAGE);
                    client.setBuffer(null);
                }
            }});

        timer.setRepeats(true);
        timer.setDelay(17);
        timer.start();
    }

    private void setupLayout(){
        JPanel big = new JPanel();
        JPanel mainPanel = new JPanel();
        mainPanel.add(inputMSG);
        otherPanel = new JTextArea();
        otherPanel.setBackground(Color.PINK);
        otherPanel.setPreferredSize(new Dimension(300, 900));
        otherPanel.setVisible(true);
        otherPanel.setEditable(false);


        JPanel bottomPanel = new JPanel();
        bottomPanel.add(sendButton);
        bottomPanel.add(Box.createVerticalGlue());
        bottomPanel.add(space);
        bottomPanel.add(readButton);

        setLayout(new BorderLayout());

        big.add(mainPanel, BorderLayout.WEST);
        big.add(Box.createVerticalGlue());
        big.add(otherPanel, BorderLayout.EAST);

        add(big, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    class sendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                client.sendMessage(inputMSG.getText());
            } catch (ServerNotActiveException e) {
                client.setConnected(false);
                e.printStackTrace();
            } catch (RemoteException e) {
                client.setConnected(false);
                e.printStackTrace();
            }
        }
    }

    class readButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                otherPanel.setText(client.readMessages());
            } catch (ServerNotActiveException e) {
                client.setConnected(false);
                e.printStackTrace();
            } catch (RemoteException e) {
                client.setConnected(false);
                e.printStackTrace();
            }
        }
    }
}