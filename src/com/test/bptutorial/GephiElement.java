package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import org.gephi.graph.api.Element;

import java.util.*;

abstract class GephiElement implements com.tinkerpop.blueprints.Element {

    protected final GephiGraph graph;
    protected Element element;

    public GephiElement(GephiGraph graph){
        this.graph = graph;
    }

    /*public GephiElement(GephiGraph graph, org.gephi.graph.api.Edge edge){
        this.graph = graph;
        this.element = edge;
    }
    public GephiElement(GephiGraph graph, org.gephi.graph.api.Node node){
        this.graph = graph;
        this.element = node;
    }*/

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
