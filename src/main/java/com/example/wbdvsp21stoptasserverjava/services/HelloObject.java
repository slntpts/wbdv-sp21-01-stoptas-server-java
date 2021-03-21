package com.example.wbdvsp21stoptasserverjava.services;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

public class HelloObject {
    public Integer id;
    public String name;
    public Date today = new Date();

    public HelloObject(Integer id, String name, Date today) {
        this.id = id;
        this.name = name;
        this.today = today;
    }

    public HelloObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}
