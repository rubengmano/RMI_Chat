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
    int queue = 0;
    Message messages[];
    int nMessages = 0;
    String buffer;
    Object columnNames[] = { "Message Id", "Client Id", "Message", "Time" };
    Object rowData[][] = {{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null}};
    int id = 0;

    public Server(){
        messages = new Message[20];
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

    public void connect(int queue) throws RemoteException, MalformedURLException, AlreadyBoundException {
        try {
            MessageService stub = (MessageService) UnicastRemoteObject.exportObject(this, 0);

            Registry registry = LocateRegistry.createRegistry(9999);
            registry.rebind("server", stub);
            this.queue = queue;
            if (this.queue <= 0 || this.queue > 10) {
                this.queue = 5;
            }
            buffer = "rmiChat::Server Ready";
        } catch (Exception e) {
            buffer = "rmiChat::ERROR";
            e.printStackTrace();
        }
    }

    @Override
    public void newMessage(String clientID, String message) throws RemoteException {
        LocalTime localTime = LocalTime.now();
        rowData[counter++] = new Object[]{id++, clientID, message, Time.valueOf(localTime)};

        messages[nMessages++] = new Message(message);
    }

    @Override
    public String nextMessage(String clientID) throws RemoteException {

        StringJoiner s = new StringJoiner("\n");

        for(int x = 0; x < nMessages ; x++) {
            if (!messages[x].isReadBy(clientID)){
                s.add(messages[x].getMessage());
                messages[x].readBy(clientID);
            } else {
                continue;
            }
        }
        return s.toString();
    }
}
