package logic.client;

import graphicalInterface.clientUI.ClientUI;

public class ClientMain {
    public static void main(String[] args){
        ClientUI clientUI = new ClientUI(new Client());
    }
}
