package logic;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class Message {
    static int instanceCounter = 0;
    int messageId = 0;
    String message;
    String  readUsers[];
    int nUsers = 0;

    public Message(String message){
        readUsers = new String[20];

        instanceCounter++;
        this.messageId = instanceCounter;
        this.message = message;
    }

    public boolean isReadBy(String clientId){
        for (int i = 0; i < nUsers; i++) {
            if (clientId.equals(readUsers[i]))
                return true;
        }

        return false;
    }

    public void readBy(String clientId){
        readUsers[++nUsers] = clientId;
    }



    public int getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }


}
