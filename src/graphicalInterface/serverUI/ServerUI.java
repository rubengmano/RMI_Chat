package graphicalInterface.serverUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI extends JFrame {

    JButton startButton;
    JTable table;
    String[] columnNames =  {"ClientId", "Message", "Time"};
    Object[][] data;

    public ServerUI() {
        super("SERVER");

        setupComponents();
        setupLayout();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    private void setupComponents(){
        startButton = new JButton("Connect");

        table = new JTable(data, columnNames);

        startButton.addActionListener(new startButtonListener());

    }

    private void setupLayout(){
        JPanel mainPanel = new JPanel();
        JScrollPane tablePanel = new JScrollPane(table);

        JPanel bottomPanel = new JPanel();

        mainPanel.setBackground(Color.CYAN);
        table.setFillsViewportHeight(true);
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(startButton);


        setLayout(new BorderLayout());

        add(tablePanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    class startButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(startButton.getText().equals("Connect"))
                startButton.setText("Disconnect");
            else
                startButton.setText("Connect");
        }
    }

}
