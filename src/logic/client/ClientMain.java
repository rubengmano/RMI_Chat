package logic.client;

import graphicalInterface.clientUI.ClientUI;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMain {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Client client = new Client();
        ClientUI clientUI = new ClientUI(client);
        client.start();
    }
}
