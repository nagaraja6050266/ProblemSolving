package org.inr.tutorial.messenger.model;


import jakarta.json.bind.annotation.JsonbProperty;

import java.util.Date;

public class Message {
//    @JsonbProperty("message_id")
    private int id;

//    @JsonbProperty("author_name")
    private String author;

//    @JsonbProperty("content")
    private String content;
    private Date date;

    public Message(){
    }

    public Message(int id, String author, String content){
        this.id=id;
        this.author=author;
        this.content=content;
        this.date=new Date();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
