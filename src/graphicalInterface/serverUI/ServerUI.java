package graphicalInterface.serverUI;

import logic.server.Server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class ServerUI extends JFrame {

    Server server;
    JButton startButton;
    JTable table;

    public ServerUI(Server server) {
        super("SERVER");
        this.server = server;

        setupComponents();
        setupLayout();

        setSize(600,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    private void setupComponents(){
        startButton = new JButton("Connect");
        table = new JTable(server.getRowData(), server.getColumnNames());
        startButton.addActionListener(new startButtonListener());
    }

    private void setupLayout(){
        JPanel bottomPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);

        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(startButton);
        table.setFillsViewportHeight(true);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    class startButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(startButton.getText().equals("Connect")){
                try {
                    server.connect();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (AlreadyBoundException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, server.getBuffer(), "InfoBox: " + "MiniRogue", JOptionPane.INFORMATION_MESSAGE);
                startButton.setText("Disconnect");
            }

            else
                startButton.setText("Connect");
        }
    }
}
