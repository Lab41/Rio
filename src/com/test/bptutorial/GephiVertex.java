package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.VertexQuery;
import org.gephi.graph.api.EdgeIterable;
import org.gephi.graph.api.Node;
import org.gephi.graph.store.*;

public class GephiVertex extends GephiElement implements Vertex {

    private NodeImpl node;

    public GephiVertex(NodeImpl node, GephiGraph graph){
        super(graph);
        this.node = node;
    }

    public Iterable<Edge> getEdges(Direction direction, String... labels){
        if(direction.equals(Direction.OUT)){
            //return graph.getGraphStore().getOutEdges(this.node);
            return null;
        }
        else if(direction.equals(Direction.IN)){
            return null;
        }
        else{
            return null;
        }

    }
    public Iterable<Vertex> getVertices(Direction direction, String... labels){
        return null;
    }
    public VertexQuery query(){
        return null;
    }
    public Edge addEdge(String label, Vertex inVertex){
        return null;
    }

    /*private class GephiVertexEdgeIterable<T extends Edge> implements Iterable<GephiEdge>{
        private GephiGraph graph;
        private NodeImpl node;
        private Dire


    }*/


}
