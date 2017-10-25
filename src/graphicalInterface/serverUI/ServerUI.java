package graphicalInterface.serverUI;

import logic.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI extends JFrame {

    JButton startButton;

    public ServerUI(Server server) {
        super("SERVER");

        setupComponents();
        setupLayout();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    private void setupComponents(){
        startButton = new JButton("Connect");
        startButton.addActionListener(new startButtonListener());
    }

    private void setupLayout(){
        JPanel mainPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        mainPanel.setBackground(Color.CYAN);
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(startButton);

        setLayout(new BorderLayout());

        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    class startButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(startButton.getText().equals("Connect")){
                startButton.setText("Disconnect");
            }

            else
                startButton.setText("Connect");
        }
    }
}
