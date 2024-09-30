package org.inr.tutorial.messenger.database;

import org.inr.tutorial.messenger.model.Message;
import org.inr.tutorial.messenger.model.Profile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Map<Integer, Message> messages=new HashMap<>();
    private static Map<Integer, Profile> profiles=new HashMap<>();

    static {
        messages.put(1, new Message(1,"nagaraja","Content"));
        messages.put(2,new Message(2,"Arumugam","content2"));
    }

    public static Map<Integer, Message> getMessages() {
        return messages;
    }

    public static Map<Integer, Profile> getProfiles() {
        return profiles;
    }
}
