package com.example.wbdvsp21stoptasserverjava.controllers;

import com.example.wbdvsp21stoptasserverjava.models.Widget;
import com.example.wbdvsp21stoptasserverjava.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//We want to provide the functionality to the client so that it can retrieve the data.
//The way we are doing it is annotating this as a REST Controller.
//Controller means that we are going to expose this as an HTTP set of APIs, URLs.
//Rest is a naming convention how we represent that those URLs.
@RestController
@CrossOrigin(origins = "*")//we added that because we want to fetch the url from localhost:8080 ("http://localhost:8080/api/topics/ABC234/widgets") in client-react app. This is not allowed. allows JavaScript downloaded from another domain to communicate with this domain
//to reach from one domain ti other without adding this. * means all domain is allowed.
public class WidgetController {//Controller allow us to access the what inside the class.
    @Autowired//Instantiate the service and provide a reference to this variable. tells the server to create an instance of the class and provide a reference through the variable. This is instead of using the "new" keyword
    WidgetService service;// = new WidgetService();//Controller is not responsible for finding all widget, servise does it. So we
    //are creating a service instance and delegate the finding all widgets to the service.

    @PostMapping("/api/topics/{tid}/widgets")//this has to match with the url in createWidget on widget-list.js in client-react app.
    public Widget createWidgetForTopic(
            @PathVariable("tid") String topicId,
            @RequestBody Widget widget){//from the request we want to parse the body and map it into this widget object. @RequestBody parses the body and instantiate it
        //into Java object.
        widget.setTopicId(topicId);
        widget.setId(new Date().getTime());
        return service.createWidgetForTopic(topicId, widget);
    }

    @GetMapping("/api/topics/{tid}/widgets")//tid means topicId, meaning being in {} is it'll change over time.
    public List<Widget> findWidgetsForTopic(
            @PathVariable("tid") String topicId){
        return service.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets")//we can call it data instead of api.
    public List<Widget> findAllWidgets(){
        return service.findAllWidgets();
    }

    @GetMapping("/api/widgets/{wid}")//we added the id here as a parameter instead of hardcoded.
    public Widget findWidgetById(
            @PathVariable("wid") Long id){//by adding @PathVariable here, it parses the url path and get the specific wid and passes it along to the id.
        return service.findWidgetById(id);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public Integer deleteWidget(
            @PathVariable("wid") Long id){
        return service.deleteWidget(id);
    }

    @PutMapping("/api/widgets/{wid}")
    public Integer updateWidget(
            @PathVariable("wid") Long id,
            @RequestBody Widget widget){
        return service.updateWidget(id, widget);
    }
}
