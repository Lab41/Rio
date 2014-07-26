package com.test.bptutorial;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import org.gephi.graph.api.Edge;

/**
 * Created by aganesh on 7/16/14.
 */
public class GephiEdge extends GephiElement implements com.tinkerpop.blueprints.Edge  {

    private Edge edge;

    public GephiEdge(Edge edge, GephiGraph graph){
        super(graph,edge);
        this.edge = edge;
    }

    public Vertex getVertex(Direction direction) throws IllegalArgumentException{
        if(direction.equals(Direction.OUT)){
            return new GephiVertex((this.edge).getSource(),this.graph);
        }
        else if(direction.equals(Direction.IN)){
            return new GephiVertex((this.edge).getTarget(),this.graph);
        }
        else{
            throw ExceptionFactory.bothIsNotSupported();
        }
    }

    public Edge getRawEdge(){
        return this.edge;
    }

    public void setLabel(String str){
        this.edge.setLabel(str);
    }

    public String getLabel(){
        return this.edge.getLabel();
    }
}
