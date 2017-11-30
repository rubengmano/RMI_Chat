package logic.client;

import service.MessageService;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.Random;

import static java.rmi.server.RemoteServer.getClientHost;

public class Client extends Thread{
    String buffer;
    static int clientIterator = 0;
    int client_id = 0;
    //String client_id;
    String ip;
    MessageService stub;
    boolean connected = false;

    public Client() throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException {
        Random randomGenerator = new Random();


        this.client_id =  randomGenerator.nextInt(100);;
        //this.client_id = InetAddress.getLocalHost().getHostAddress();
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

    public void connect(String ip) throws RemoteException, NotBoundException, ConnectException{
        Registry registry = LocateRegistry.getRegistry(ip,9999);
        stub = (MessageService) registry.lookup("server");
    }

    public String readMessages() throws ServerNotActiveException, RemoteException {
        //return stub.nextMessage(getClientHost());
       // return stub.nextMessage(ip);
       return stub.nextMessage(String.valueOf(this.client_id));
    }

    public void sendMessage(String message) throws ServerNotActiveException, RemoteException {
        //stub.newMessage(getClientHost(),message);
        //stub.newMessage(ip,message);
        stub.newMessage(String.valueOf(this.client_id),message);
    }

     /*   @Override
    public void run() {
        while (true){
            try {
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
    }*/
}
