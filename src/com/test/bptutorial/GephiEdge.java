package com.test.bptutorial;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import org.gephi.graph.store.EdgeImpl;
import org.gephi.graph.store.NodeImpl;

/**
 * Created by aganesh on 7/16/14.
 */
public class GephiEdge extends GephiElement implements Edge {

    private EdgeImpl edge;


    public GephiEdge(EdgeImpl edge, GephiGraph graph){
        super(graph);
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

    public void setLabel(String str){
        this.edge.setLabel(str);
    }

    public String getLabel(){
        return this.edge.getLabel();
    }
}
