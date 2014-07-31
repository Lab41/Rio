package com.test.bptutorial;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import com.tinkerpop.blueprints.util.StringFactory;
import org.gephi.graph.api.Edge;

/**
 * Created by aganesh on 7/16/14.
 */
public class GephiEdge extends GephiElement implements com.tinkerpop.blueprints.Edge  {

    //private Edge edge;

    public GephiEdge(Edge edge, GephiGraph graph){
        //super(graph,edge);
        super(graph);
        this.element = edge;
        //this.edge = edge;
    }

    public Vertex getVertex(Direction direction) throws IllegalArgumentException{
        if(direction.equals(Direction.OUT)){
            //return new GephiVertex((this.edge).getSource(),this.graph);
            return new GephiVertex(((Edge) this.element).getSource(),this.graph);
        }
        else if(direction.equals(Direction.IN)){
            return new GephiVertex(((Edge)this.element).getTarget(),this.graph);
        }
        else{
            throw ExceptionFactory.bothIsNotSupported();
        }
    }

    public Edge getRawEdge(){
        return (Edge) this.element;
    }

    public String toString(){
        return StringFactory.edgeString(this);
    }

    public void setLabel(String str){
        //this.edge.setLabel(str);
        ((Edge)this.element).setLabel(str);
    }

    public String getLabel(){
        //return this.edge.getLabel();
        return ((Edge)this.element).getLabel();
    }

    public boolean equals(final Object object) {
        return object instanceof GephiEdge && ((GephiEdge) object).getId().equals(this.getId());
    }

}
