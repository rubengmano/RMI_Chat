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
    final int port = 1080;


    public Server(){

    }

    private void connect() throws RemoteException, MalformedURLException, AlreadyBoundException {
        MessageService stub = (MessageService) UnicastRemoteObject.exportObject( this, 0);

        Registry registry = LocateRegistry.getRegistry();
        registry.bind("rmiChat", stub);
    }

    private void disconnect(){

    }

    @Override
    public String nextMessage(String clientID) throws RemoteException {
        return null;
    }

    @Override
    public void newMessage(String clientID, String message) throws RemoteException {

    }
}
