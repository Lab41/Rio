package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */
import com.tinkerpop.blueprints.Element;
import org.gephi.graph.store.GraphStore;

import java.util.*;

public class GephiElement implements Element {

    protected GephiGraph graph;

    public GephiElement(GephiGraph graph){
        this.graph = graph;
    }

    public <T> T getProperty(String key){
        return null;
    }
    public Set<String> getPropertyKeys(){
        return null;
    }
    public void setProperty(String key,
                            Object value){

    }
    public <T> T removeProperty(String key){
        return null;
    }
    public void remove(){

    }
    public Object getId(){
        return null;
    }
}
