package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MessageDao {
    private Map<Integer, Message> messages = Database.getMessages();

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public Message getMessageById(int id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message editMessage(int id, Message message) {
        if (messages.containsKey(id)) {
            messages.put(id, message);
            return message;
        }
        return null;
    }

    public Message deleteMessage(int id) {
        return messages.remove(id);
    }

//    public List<Message> getMessageOfYear(int year){
//
//    }

}
