package logic.server;

import logic.Message;
import service.MessageService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.Timer;

public class Server extends RemoteServer implements MessageService {
    int counter = 0;
    private HashMap messages;
    String buffer;
    Object columnNames[] = { "Message Id", "Client Id", "Message", "Time" };
    Object rowData[][] = {{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null}};

    public Server(){
        messages = new HashMap<>();
    }

    public String getBuffer() {
        return buffer;
    }

    public Object[][] getRowData() {
        return rowData;
    }

    public Object[] getColumnNames() {
        return columnNames;
    }

    public void connect() throws RemoteException, MalformedURLException, AlreadyBoundException {
        try {
            MessageService stub = (MessageService) UnicastRemoteObject.exportObject(this, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("server", stub);

            buffer = "rmiChat::Server Ready";
        } catch (Exception e) {
            buffer = "rmiChat::ERROR";
            e.printStackTrace();
        }
    }

    @Override
    public void newMessage(String clientID, String message) throws RemoteException {
        System.out.println(clientID + " : " + message);
        messages.put(clientID, new Message(message));
    }

    @Override
    public String nextMessage(String clientID) throws RemoteException {
        Set set = messages.entrySet();
        Iterator i = set.iterator();

        while (i.hasNext()) {
            Map.Entry map = (Map.Entry) i.next();
            Message m = (Message) map.getValue();

            System.out.print(map.getKey() + " : " + map.getValue());

            if (map.getKey().equals(clientID)) {
                if (counter == 5)
                    counter = 0;

                LocalTime localTime = LocalTime.now();
                rowData[counter++] = new Object[]{m.getMessageId(), map.getKey(), m.getMessage(), Time.valueOf(localTime)};

                return " [Server]" + m.getMessage();
            }
        }

        return "NO MESSAGES";
    }
}
