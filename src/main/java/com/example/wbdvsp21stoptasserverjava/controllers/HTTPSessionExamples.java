package com.example.wbdvsp21stoptasserverjava.controllers;

import com.example.wbdvsp21stoptasserverjava.models.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HTTPSessionExamples {

    List<User> users = new ArrayList<User>();
    @GetMapping("/api/register/{u}/{p}")
    public User register(
            @PathVariable("u") String username,
            @PathVariable("p") String password,
            HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        session.setAttribute("currentUser", user);
        users.add(user);
        return user;
    }

    @GetMapping("/api/profile")
    public User profile(HttpSession session) {
        User currentUser = (User)
                session.getAttribute("currentUser");
        return currentUser;
    }

    @GetMapping("/api/logout")
    public void logout
            (HttpSession session) {
        session.invalidate();//don't remember anything
    }

    @GetMapping("/api/login/{u}/{p}")
    public User login(
            @PathVariable("u") String username,
            @PathVariable("p") String password,
            HttpSession session) {
        for (User user : users) {
            if( user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                session.setAttribute("currentUser", user);
                return user;
            }
        }
        return null;
    }


    @GetMapping("/api/session/set/{attr}/{value}")
    public String setSessionAttribute(
            @PathVariable("attr") String attr,
            @PathVariable("value") String value,
            HttpSession session) {
        session.setAttribute(attr, value);//we store session as a pair in hashmap
        return attr + " = " + value;
    }

    //retrieve the session that we stored above
    @GetMapping("/api/session/get/{attr}")
    public String getSessionAttribute(
            @PathVariable ("attr") String attr,
            HttpSession session) {
        return (String)session.getAttribute(attr);
    }

    @GetMapping("/hello2")
    public String sayHello(){
        return "Hello World";
    }
}
