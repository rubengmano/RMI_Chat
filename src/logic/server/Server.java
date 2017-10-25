package logic.server;

import logic.Message;
import service.MessageService;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Server implements MessageService {
    private HashMap messages;
    String buffer;
    Object columnNames[] = { "Message Id", "Client Id", "Message", "Time" };
    Object rowData[][] = {};

    public Server(){

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

            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("rmiServer", stub);

            buffer = "rmiChat::Server Ready";
        } catch (Exception e) {
            buffer = "rmiChat::ERROR";
            e.printStackTrace();
        }
    }

    public void disconnect(){

    }


    @Override
    public void newMessage(String clientID, String message) throws RemoteException {
        System.out.println(clientID + " : " + message);
        this.messages.put(clientID, new Message(message));
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
                return "" + m.getMessageId() + " :: " + map.getKey() + " : " + m.getMessage() + "::" + m.getMessageTime();
            }

            rowData[rowData.length - 1] = new Object[]{m.getMessageId(), clientID, m.getMessage(), m.getMessageTime()};

        }
        return "NO MESSAGES";
    }
}
