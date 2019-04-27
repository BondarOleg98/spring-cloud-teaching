package com.olegb.service.Model;

public class Log {

    private Integer id;

    private String Action;

    public Log(){

    }
    public Log(String action){
        Action=action;
    }

    public Integer getId() {
        return id;
    }

    public String getAction() {
        return Action;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAction(String action) {
        Action = action;
    }
}
