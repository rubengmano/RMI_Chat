package logic.client;

import service.MessageService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

public class Client {
    MessageService stub;

    public Client(){

    }

    public void connect() throws  RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(1099);
        stub = (MessageService) registry.lookup("rmiChat");
    }

    public String readMessages() throws ServerNotActiveException, RemoteException {
        return stub.nextMessage(RemoteServer.getClientHost());
    }

    public void sendMessage(String message) throws ServerNotActiveException, RemoteException {
        stub.newMessage(RemoteServer.getClientHost(),message);
    }

}
