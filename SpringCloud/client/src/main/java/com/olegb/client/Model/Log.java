package com.olegb.client.Model;

import javax.persistence.*;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Log(){}

    @Column(name = "Action",nullable = false)
    private String Action;

    public Log(String action){
        this.Action=action;
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

