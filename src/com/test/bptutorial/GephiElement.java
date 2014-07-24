package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import org.gephi.graph.api.Element;

import java.util.*;

public class GephiElement implements com.tinkerpop.blueprints.Element {

    protected GephiGraph graph;
    protected Element element;

    public GephiElement(GephiGraph graph){
        this.graph = graph;
    }

    public <T> T getProperty(String key){
        return (T) this.element.getAttribute(key);
    }

    public Set<String> getPropertyKeys(){
        return this.element.getAttributeKeys();
    }
    public void setProperty(String key,Object value){
        this.element.setAttribute(key,value);
    }
    public <T> T removeProperty(String key){
       return (T) this.element.removeAttribute(key);
    }
    public void remove(){
        if(this instanceof Vertex)
            this.graph.removeVertex((Vertex)this);
        else
            this.graph.removeEdge((Edge) this);
    }
    public Object getId(){
        return element.getId();
    }
}
