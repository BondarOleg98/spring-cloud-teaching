package com.olegb.consumer.Models;

import javax.persistence.*;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Action",nullable = false)
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
