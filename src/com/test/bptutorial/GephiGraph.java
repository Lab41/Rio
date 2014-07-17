package com.test.bptutorial;

import com.tinkerpop.blueprints.IndexableGraph;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.MetaGraph;
import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import org.gephi.graph.store.EdgeImpl;
import org.gephi.graph.store.GraphStore;
import org.gephi.graph.store.NodeImpl;

/**
 * Created by aganesh on 7/15/14.
 */
public class GephiGraph implements Graph{

    private GraphStore graphDb;

    public GephiGraph(GraphStore graphDb ){
        this.graphDb = graphDb;
    }

    public Features getFeatures(){
        return null;
    }
    public Vertex addVertex(Object id){
        NodeImpl node = new NodeImpl(id);
        graphDb.addNode(node);
        return new GephiVertex(node,this);
    }
    public Vertex getVertex(Object id){
        if (id == null) throw ExceptionFactory.vertexIdCanNotBeNull();
        return new GephiVertex(new NodeImpl(id),this);
    }
    public void removeVertex(Vertex vertex){

    }
    public Iterable<Vertex> getVertices(){
        return null;
    }
    public Iterable<Vertex> getVertices(String key, Object value){
        return null;
    }
    public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label){
        NodeImpl source = new NodeImpl(outVertex.getId());
        NodeImpl target = new NodeImpl(inVertex.getId());
        EdgeImpl gsEdge = new EdgeImpl(id,source,target,0,0,false);

        graphDb.addEdge(gsEdge);
        GephiEdge gephiEdge = new GephiEdge(gsEdge,this);
        gephiEdge.setLabel(label);
        return gephiEdge;
    }
    public Edge getEdge(Object id){
        return null;
    }
    public void removeEdge(Edge edge){

    }
    public Iterable<Edge> getEdges(){
        return null;
    }
    public Iterable<Edge> getEdges(String key, Object value){
        return null;
    }
    public GraphQuery query(){
        return null;
    }

    public void shutdown(){

    }
}
