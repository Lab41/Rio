package org.lab41.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.ElementHelper;
import org.gephi.attribute.api.Column;
import org.gephi.graph.api.Element;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

abstract class GephiElement implements com.tinkerpop.blueprints.Element {

    protected final GephiGraph graph;
    protected Element element;
    protected HashSet<String> keyList = new HashSet<String>();

    public GephiElement(GephiGraph graph){
        this.graph = graph;
    }

    public <T> T getProperty(String key) {

        if (this instanceof Vertex) {
            if (this.graph.getGraphModel().getNodeTable().hasColumn(key)) {
                return (T) this.element.getAttribute(key);
            }
        }
        else {

            if(key.equals("weight")){
                if(this.element.getAttribute("weight").equals(-1.0)){
                    return null;
                }
                else{
                    return (T) this.element.getAttribute(key);
                }
            }

            if (this.graph.getGraphModel().getEdgeTable().hasColumn(key)) {
                    return (T) this.element.getAttribute(key);
            }
        }
        return null;
    }

    public Set<String> getPropertyKeys(){
        Set<String> keys = new HashSet<String>();
        Set<String> nodeDefaultCols = new HashSet<String>();
        Set<String> edgeDefaultCols = new HashSet<String>();


        //Keep track of default attributes set in Graphstore
        nodeDefaultCols.add("id");
        nodeDefaultCols.add("timestamp");
        nodeDefaultCols.add("label");

        edgeDefaultCols.add("id");
        edgeDefaultCols.add("timestamp");
        edgeDefaultCols.add("label");


        //TODO Consider edge case where you are actually adding attribute with the names above
        Object[] keyArr = this.element.getAttributeKeys().toArray();
        if(this instanceof Vertex){
            for(int i = 0;  i < keyArr.length; i++){
                //Check if non default attributes are populated
                if(!nodeDefaultCols.contains(keyArr[i])){
                    if(this.element.getAttribute(keyArr[i].toString()) != null){
                        keys.add(keyArr[i].toString());
                    }
                }
            }
        }
        else {

            if(this.element.getAttribute("weight").equals(-1.0)){
                edgeDefaultCols.add("weight");
            }

            for(int i = 0; i < keyArr.length; i++) {
                if (!edgeDefaultCols.contains(keyArr[i])) {
                    if (this.element.getAttribute(keyArr[i].toString()) != null) {
                        keys.add(keyArr[i].toString());
                    }
                }
            }
        }
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
        }
        else{
          if (this.graph.getGraphModel().getEdgeTable().hasColumn(key)) {
            this.keyList.remove(key);
            Object temp;
            if(key.equals("weight")){
                temp = this.element.getAttribute(key);
                this.element.setAttribute("weight",-1.0);
                return (T) temp;
            }
            return (T) this.element.removeAttribute(key);
          }
        }
        return null;

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
