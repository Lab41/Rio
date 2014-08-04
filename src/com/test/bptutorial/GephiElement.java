package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.ElementHelper;
import org.gephi.attribute.api.AttributeModel;
import org.gephi.attribute.api.Table;
import org.gephi.graph.api.Element;

import java.util.*;

abstract class GephiElement implements com.tinkerpop.blueprints.Element {

    protected final GephiGraph graph;
    protected Element element;
    //protected Table table;
    //protected AttributeModel atr;

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

        ElementHelper.validateProperty(this, key, value);
        //this.table.addColumn(key,value.getClass());
        //this.atr.getNodeTable().addColumn(key,value.getClass());
        if(this instanceof Vertex){
            if(this.graph.getGraphModel().getNodeTable().hasColumn(key)){
                this.element.setAttribute(key,value);
            }
            else{
                this.graph.getGraphModel().getNodeTable().addColumn(key,value.getClass());
                this.element.setAttribute(key,value);
            }
        }
        else{
            if(this.graph.getGraphModel().getEdgeTable().hasColumn(key)){
                this.element.setAttribute(key,value);
            }
            else{
                this.graph.getGraphModel().getEdgeTable().addColumn(key,value.getClass());
                this.element.setAttribute(key,value);
            }

        }

        //this.element.setAttribute(key , value);

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
