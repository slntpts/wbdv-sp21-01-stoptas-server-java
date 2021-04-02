package com.example.wbdvsp21stoptasserverjava.services;

import com.example.wbdvsp21stoptasserverjava.models.Widget;
import com.example.wbdvsp21stoptasserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//When we add the @Autowired to the WidgetController, we also need to add @Service in here.
public class WidgetService {

    @Autowired
    WidgetRepository repository;

//    private List<Widget> widgets = new ArrayList<Widget>();
//    {
//        Widget w1 = new Widget(123l, "ABC123", "HEADING", 1, "Widgets for Topic ABC123");
//        Widget w2 = new Widget(234l, "ABC123","PARAGRAPH", 1, "Lorem Ipsum");
//        Widget w3 = new Widget(345l,"ABC234", "HEADING", 2, "Widgets for Topic ABC234");
//        Widget w4 = new Widget(456l,"ABC234", "PARAGRAPH", 1, "Lorem ipsum");
//        Widget w5 = new Widget(567l,"ABC234", "PARAGRAPH", 1, "Lorem ipsum");
//        widgets.add(w1);
//        widgets.add(w2);
//        widgets.add(w3);
//        widgets.add(w4);
//        widgets.add(w5);
//    }
    //Responsibility of Service is implement all CRUD operations.

    //Implement CRUD operations.
    public Widget createWidgetForTopic(String topicId, Widget widget){

        widget.setTopicId(topicId);
        return repository.save(widget);
//        widget.setTopicId(topicId);
//        Long id = (new Date()).getTime();
//        widget.setId(id);
//        widgets.add(widget);
//        return widget;
    }

    public List<Widget> findAllWidgets(){
        return repository.findAllWidgets();
//        return (List<Widget>) repository.findAll();
//        return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId){
        return repository.findWidgetsForTopic(topicId);
//        List<Widget> ws = new ArrayList<Widget>();
//        for(Widget w : widgets){
//            if(w != null && w.getTopicId() != null && w.getTopicId().equals(topicId)){
//                ws.add(w);
//            }
//        }
//        return ws;
    }

    public Widget findWidgetById(Long id){
        return repository.findWidgetById(id);
//            return repository.findById(id).get();

//        for(Widget w : widgets){
//            if(w != null && w.getId().equals(id)){
//                return w;
//            }
//        }
//        return null;
    }

    public Integer deleteWidget(Long id){
        repository.deleteById(id);
        return 1;

//        int index = -1;
//        for(int i=0; i < widgets.size(); i++){
//            if(widgets.get(i)!= null && widgets.get(i).getId().equals(id)){
//                index = i;
//                widgets.remove(index);
//                return 1;
//            }
//        }
//        return -1;
    }

    public Integer updateWidget(Long id, Widget widget) {
        Widget originalWidget = repository.findWidgetById(id);//repository.findWidgetById(id);//get retrieves the actual object

        if(widget.getType() != null) originalWidget.setType(widget.getType());
        if(widget.getSize() != null) originalWidget.setSize(widget.getSize());
        if(widget.getText() != null) originalWidget.setText(widget.getText());
        if(widget.getName() != null) originalWidget.setName(widget.getName());
        if(widget.getWidgetOrder() != null) originalWidget.setWidgetOrder(widget.getWidgetOrder());
        if(widget.getSrc() != null) originalWidget.setSrc(widget.getSrc());
        if(widget.getUrl() != null) originalWidget.setUrl(widget.getUrl());
        if(widget.getHref() != null) originalWidget.setHref(widget.getHref());
        if(widget.getWidth() != null) originalWidget.setWidth(widget.getWidth());
        if(widget.getHeight() != null) originalWidget.setHeight(widget.getHeight());
        if(widget.getCssClass() != null) originalWidget.setCssClass(widget.getCssClass());
        if(widget.getStyle() != null) originalWidget.setStyle(widget.getStyle());
        if(widget.getValue() != null) originalWidget.setValue(widget.getValue());
        if(widget.getOrdered() != null) originalWidget.setOrdered(widget.getOrdered());

        repository.save(originalWidget);

        return 1;

//        for(int i=0; i < widgets.size(); i++){
//            if(widget != null && widgets.get(i) != null && widgets.get(i).getId().equals(id)){
//                widgets.set(i,widget);
//                return 1;
//            }
//        }
//        return -1;
    }
}
