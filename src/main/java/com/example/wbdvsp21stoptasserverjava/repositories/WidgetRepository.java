package com.example.wbdvsp21stoptasserverjava.repositories;

import com.example.wbdvsp21stoptasserverjava.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository
        extends CrudRepository<Widget, Long>{

    //Query("SELECT widget FROM Widget widget")
    @Query(value="SELECT * FROM widgets", nativeQuery = true)
    public List<Widget> findAllWidgets();

    //native means we want to use the query exactly as it is.
    @Query(value="SELECT * FROM widgets WHERE id=:wid", nativeQuery = true)
    public Widget findWidgetById(@Param("wid") Long widgetId);//We map the parameter in the query that we called "wid"

    @Query(value="SELECT * FROM widgets WHERE topic_id=:tid", nativeQuery = true)
    public List<Widget> findWidgetsForTopic(@Param("tid")String topicId);

}
