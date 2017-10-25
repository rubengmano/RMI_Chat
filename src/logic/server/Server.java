package logic.server;

import service.MessageService;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements MessageService {
    public String getBuffer() {
        return buffer;
    }

    String buffer;

    public Server(){

    }

    public void connect() throws RemoteException, MalformedURLException, AlreadyBoundException {
        try {
            MessageService stub = (MessageService) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("rmiChat", stub);
            buffer = "rmiChat::Server Ready";
        } catch (Exception e) {
            buffer = "rmiChat::ERROR";
            e.printStackTrace();
        }
    }

    public void disconnect(){

    }

    @Override
    public String nextMessage(String clientID) throws RemoteException {
        return null;
    }

    @Override
    public void newMessage(String clientID, String message) throws RemoteException {

    }
}
