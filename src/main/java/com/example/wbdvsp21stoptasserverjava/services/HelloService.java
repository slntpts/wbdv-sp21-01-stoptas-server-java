package com.example.wbdvsp21stoptasserverjava.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController//mean I am going to map a url / http request
public class HelloService {
    @GetMapping("/hello")//reading data from server
    public String sayHi(){
        return "Hello World!";
    }

    @GetMapping("/addAandB/{A}/{B}")//defined name whatever we want and sending parameter
    //we want to be able to read these vars, parse them pass and convert to integers.
    public Integer add(
            @PathVariable("A") Integer a,
            @PathVariable("B") Integer b) {
        return a + b;
    }
    //Here when we call the url: http://localhost:8080/addAandB/2/3, it returns 5

    @GetMapping("/my/hello/object")
    public HelloObject getHelloObject(){
        HelloObject h = new HelloObject();
        h.setId(123);
        h.setName("My hello object");
        return h;
    }
}
