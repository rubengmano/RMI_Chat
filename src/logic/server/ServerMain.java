package logic.server;

import graphicalInterface.serverUI.ServerUI;

public class ServerMain {
    public static void main(String[] args)
    {
        ServerUI serverUI = new ServerUI(new Server());
    }
}
