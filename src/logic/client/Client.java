package logic.client;

import service.MessageService;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import static java.rmi.server.RemoteServer.getClientHost;

public class Client {
    static int clientIterator = 0;
    int client_id = 0;
    MessageService stub;

    public Client() throws RemoteException, NotBoundException, MalformedURLException {
        clientIterator++;
        client_id = clientIterator;
        connect();
    }

    private void connect() throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        stub = (MessageService) registry.lookup("server");
    }

    public String readMessages() throws ServerNotActiveException, RemoteException {
        return stub.nextMessage(String.valueOf(this.client_id));
    }

    public void sendMessage(String message) throws ServerNotActiveException, RemoteException {
        stub.newMessage(String.valueOf(this.client_id),message);
    }

}
