package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.ElementHelper;
import org.gephi.attribute.api.Column;
import org.gephi.graph.api.Element;

import java.util.*;

abstract class GephiElement implements com.tinkerpop.blueprints.Element {

    protected final GephiGraph graph;
    protected Element element;
    protected HashSet<String> keyList = new HashSet<String>();

    public GephiElement(GephiGraph graph){
        this.graph = graph;
    }

    public <T> T getProperty(String key){


        if(this instanceof Vertex){
            if(this.graph.getGraphModel().getNodeTable().hasColumn(key)){
                return (T)this.element.getAttribute(key);
            }
            else{
                return null;
            }
        }
        else{
            if(this.graph.getGraphModel().getEdgeTable().hasColumn(key)){

                return (T)this.element.getAttribute(key);
            }
            else{
                return null;
            }
        }
    }

    public Set<String> getPropertyKeys(){
        Set<String> keys = new HashSet<String>(this.keyList);
        return keys;
    }

    public void setProperty(String key,Object value){

        key = key.toLowerCase();
        ElementHelper.validateProperty(this, key, value);

        if(key.equals("weight")){
            value = (Double.parseDouble(value.toString()));
        }

        if(this instanceof Vertex){
            if(this.graph.getGraphModel().getNodeTable().hasColumn(key)){
                this.element.setAttribute(key,value);
                this.keyList.add(key);

            }
            else{
                this.graph.getGraphModel().getNodeTable().addColumn(key, value.getClass());
                this.keyList.add(key);
                this.element.setAttribute(key,value);
            }
        }
        else{
            if(this.graph.getGraphModel().getEdgeTable().hasColumn(key)){
                this.element.setAttribute(key,value);
                this.keyList.add(key);
            }
            else{
                this.graph.getGraphModel().getEdgeTable().addColumn(key, value.getClass());
                this.keyList.add(key);
                this.element.setAttribute(key,value);
            }

        }

    }
    public <T> T removeProperty(String key){

        if(this instanceof Vertex){
            if(this.graph.getGraphModel().getNodeTable().hasColumn(key)){
                this.keyList.remove(key);
                return (T) this.element.removeAttribute(key);
            }
            else{
                return null;
            }
        }
        else{
            if(this.graph.getGraphModel().getEdgeTable().hasColumn(key)){
                this.keyList.remove(key);
                return (T) this.element.removeAttribute(key);
            }
            else{
                return null;
            }

        }

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

    public int hashCode(){
        return this.getId().hashCode();
    }

}
