package com.example.wbdvsp21stoptasserverjava.services;

import com.example.wbdvsp21stoptasserverjava.models.Widget;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service//When we add the @Autowired to the WidgetController, we also need to add @Service in here.
public class WidgetService {
    private List<Widget> widgets = new ArrayList<Widget>();
    {
        Widget w1 = new Widget(123l, "ABC123", "HEADING", 1, "Widgets for Topic ABC123");
        Widget w2 = new Widget(234l, "ABC123","PARAGRAPH", 1, "Lorem Ipsum");
        Widget w3 = new Widget(345l,"ABC234", "HEADING", 2, "Widgets for Topic ABC234");
        Widget w4 = new Widget(456l,"ABC234", "PARAGRAPH", 1, "Lorem ipsum");
        Widget w5 = new Widget(567l,"ABC234", "PARAGRAPH", 1, "Lorem ipsum");
        widgets.add(w1);
        widgets.add(w2);
        widgets.add(w3);
        widgets.add(w4);
        widgets.add(w5);
    }
    //Responsibility of Service is implement all CRUD operations.

    //Implement CRUD operations.
    public Widget createWidgetForTopic(String topicId, Widget widget){

        widget.setTopicId(topicId);
        Long id = (new Date()).getTime();
        widget.setId(id);
        widgets.add(widget);
        return widget;
    }

    public List<Widget> findAllWidgets(){
        return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId){
        List<Widget> ws = new ArrayList<Widget>();
        for(Widget w : widgets){
            if(w != null && w.getTopicId() != null && w.getTopicId().equals(topicId)){
                ws.add(w);
            }
        }
        return ws;
    }

    public Widget findWidgetById(Long id){

        for(Widget w : widgets){
            if(w != null && w.getId().equals(id)){
                return w;
            }
        }
        return null;
    }

    public Integer updateWidget(Long id){
        return -1;
    }

    public Integer deleteWidget(Long id){
        int index = -1;
        for(int i=0; i < widgets.size(); i++){
            if(widgets.get(i)!= null && widgets.get(i).getId().equals(id)){
                index = i;
                widgets.remove(index);
                return 1;
            }
        }
        return -1;
    }

    public Integer updateWidget(Long id, Widget widget) {
        for(int i=0; i < widgets.size(); i++){
            if(widget != null && widgets.get(i) != null && widgets.get(i).getId().equals(id)){
                widgets.set(i,widget);
                return 1;
            }
        }
        return -1;
    }
}
