package logic;

import java.sql.Time;
import java.time.LocalTime;

public class Message {
    static int instanceCounter = 0;
    int messageId = 0;
    String message;


    public Message(String message){
        instanceCounter++;
        this.messageId = instanceCounter;
        this.message = message;
    }


    public int getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }


}
