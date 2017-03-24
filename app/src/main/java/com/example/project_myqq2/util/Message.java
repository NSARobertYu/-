package com.example.project_myqq2.util;

/**
 * Created by PC on 2016/10/10.
 */
public class Message {

    private int id;
    private String message;
    private int type;//0表示回复，1表示接收；
    private long time;

    public Message(String message, int type, long time) {
        this.message = message;
        this.type = type;
        this.time = time;
    }

    public Message(int id, String message, long time, int type) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
