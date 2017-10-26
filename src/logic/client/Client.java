package logic.client;

import service.MessageService;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import static java.rmi.server.RemoteServer.getClientHost;

public class Client extends Thread{
    String buffer;
    static int clientIterator = 0;
    int client_id = 0;
    MessageService stub;
    boolean connected = false;

    public Client() throws RemoteException, NotBoundException, MalformedURLException {
        this.client_id = clientIterator;
        clientIterator++;

    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public void setConnected(boolean connected){
        this.connected = connected;
    }

    private void connect() throws RemoteException, NotBoundException, ConnectException{
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        stub = (MessageService) registry.lookup("server");
    }

    public String readMessages() throws ServerNotActiveException, RemoteException {
        return stub.nextMessage(String.valueOf(this.client_id));
    }

    public void sendMessage(String message) throws ServerNotActiveException, RemoteException {
        stub.newMessage(String.valueOf(this.client_id),message);
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.print("Trying");
                connect();
                connected = true;
                buffer = "rmiChat::Connected to server";
            } catch (ConnectException e) {
                e.printStackTrace();
                buffer = "rmiChat::Couldn't connect to server";
            }catch (NotBoundException e) {
                e.printStackTrace();
                buffer = "rmiChat::Couldn't connect to server";
            }catch (RemoteException e) {
                e.printStackTrace();
                buffer = "rmiChat::Couldn't connect to server";
            }
        }
    }
}
