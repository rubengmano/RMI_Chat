package graphicalInterface.clientUI;

import logic.client.Client;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientUI extends JFrame {

    Client client;
    JTextArea inputMSG;
    JButton sendButton, readButton;

    public ClientUI(Client client){
        super("CLIENT");
        this.client = client;

        setupComponents();
        setupLayout();

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    private void setupComponents(){
        sendButton = new JButton("Send Message");
        sendButton.addActionListener(new sendButtonListener());
        readButton = new JButton("Read Messages");
        readButton.addActionListener(new readButtonListener());
        inputMSG = new JTextArea(60, 20);
        inputMSG.setWrapStyleWord(true);
        inputMSG.setLineWrap(true);

    }

    private void setupLayout(){
        JPanel mainPanel = new JPanel();
        mainPanel.add(inputMSG);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(sendButton);
        bottomPanel.add(Box.createVerticalGlue());
        bottomPanel.add(readButton);

        setLayout(new BorderLayout());

        add(mainPanel, BorderLayout.WEST);
        add(Box.createVerticalGlue());

        add(bottomPanel, BorderLayout.SOUTH);

    }

    class sendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    class readButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
