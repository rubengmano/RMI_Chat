package logic;

import java.sql.Time;
import java.time.LocalTime;

public class Message {
    static int instanceCounter = 0;
    int messageId = 0;
    String message;
    Time messageTime;

    public Message(String message){
        instanceCounter++;
        this.messageId = instanceCounter;
        this.message = message;

        LocalTime localTime = LocalTime.now();
        this.messageTime = Time.valueOf(localTime);
    }

    public static int getInstanceCounter() {
        return instanceCounter;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public Time getMessageTime() {
        return messageTime;
    }

}
