package com.test.bptutorial;



import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.util.DefaultGraphQuery;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import org.gephi.graph.api.*;
import org.gephi.graph.store.NodeImpl;
import org.gephi.graph.store.EdgeImpl;

import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

/**
 * Created by aganesh on 7/15/14.
 */
public class GephiGraph implements com.tinkerpop.blueprints.Graph{

    private GraphModel graphModel;

    private static final Features FEATURES = new Features();
    int counter = 0;

    static{
        FEATURES.ignoresSuppliedIds = true;
        FEATURES.supportsVertexIteration = true;
        FEATURES.supportsEdgeIteration = true;
        FEATURES.supportsVertexProperties = true;
        FEATURES.supportsStringProperty = true;
        FEATURES.supportsIntegerProperty = true;
        FEATURES.supportsEdgeRetrieval = true;
        FEATURES.supportsDuplicateEdges = false;
        FEATURES.supportsSelfLoops = true;
        FEATURES.supportsEdgeProperties = true;
        FEATURES.supportsDoubleProperty = false;


    }

    public GephiGraph(GraphModel graphModel){
        this.graphModel = graphModel;

    }

    public Features getFeatures(){
        return FEATURES;
    }
    public Vertex addVertex(Object id){

        this.counter++;
        Node node;

        if(id == null)
            //node = this.graphModel.factory().newNode(UUID.randomUUID());
            node = this.graphModel.factory().newNode(counter);
        else
            node = this.graphModel.factory().newNode(id);

        this.graphModel.getGraph().addNode(node);
        return new GephiVertex(node,this);
    }
    public Vertex getVertex(Object id){
        if (id == null) throw ExceptionFactory.vertexIdCanNotBeNull();

        if(this.graphModel.getGraph().getNode(id) != null ){
            return new GephiVertex(this.graphModel.getGraph().getNode(id),this);
        }
        else{
            return null;
        }
    }


    public void removeVertex(Vertex vertex){
        try {
            this.graphModel.getGraph().readUnlockAll();
            Node node = ((GephiVertex) vertex).getRawVertex();

            //Need to remove the edges associated with this vertex
            //Iterator<Edge> itr = this.graphModel.getGraph().getEdges().toCollection().iterator();
            Iterator<Edge> itrIn = this.graphModel.getDirectedGraph().getInEdges(node).toCollection().iterator();
            Iterator<Edge> itrOut = this.graphModel.getDirectedGraph().getOutEdges(node).toCollection().iterator();

            //TODO: Figure out correcting locking mechanism
            while(itrIn.hasNext()){
                this.graphModel.getGraph().removeEdge(itrIn.next());
            }
            while(itrOut.hasNext()){
                this.graphModel.getGraph().removeEdge(itrOut.next());
            }

            this.graphModel.getGraph().removeNode(node);

        } catch (IllegalStateException ise){
            throw ExceptionFactory.vertexWithIdDoesNotExist(vertex.getId());
        }

    }
    public Iterable<Vertex> getVertices(){
        return new GephiVertexIterable(this.graphModel.getGraph().getNodes(),this);
    }

    public Iterable<Vertex> getVertices(String key, Object value){
        //throw new UnsupportedOperationException();
        return new GephiKVVertexIterable(this.graphModel.getGraph().getNodes(),this,key,value);
    }

    public com.tinkerpop.blueprints.Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label){

        if(label == null)
            throw ExceptionFactory.edgeLabelCanNotBeNull();

        Node source = ((GephiVertex)outVertex).getRawVertex();
        Node target = ((GephiVertex)inVertex).getRawVertex();

        Edge gsEdge;

        /*if(id != null) {
            //gsEdge = this.graphModel.factory().newEdge(source, target, this.graphModel.addEdgeType(label), true);
            gsEdge = this.graphModel.factory().newEdge(id,source,target,this.graphModel.addEdgeType(label),0,true);
        }
        else{
            //gsEdge = this.graphModel.factory().newEdge(source, target, this.graphModel.addEdgeType(label), true);
            gsEdge = this.graphModel.factory().newEdge(UUID.randomUUID(),source,target,this.graphModel.addEdgeType(label),0,true);
        }*/
        gsEdge = this.graphModel.factory().newEdge(source, target, this.graphModel.addEdgeType(label), true);
        gsEdge.setAttribute("label",label);
        this.graphModel.getGraph().addEdge(gsEdge);


        return new GephiEdge(gsEdge,this);
    }
    public com.tinkerpop.blueprints.Edge getEdge(Object id){
        if (id == null) throw ExceptionFactory.edgeIdCanNotBeNull();
        //return new GephiEdge(this.graphModel.getGraph().getEdge(id),this);
        if(this.graphModel.getGraph().getEdge(id) != null ){
            return new GephiEdge(this.graphModel.getGraph().getEdge(id),this);
        }
        else{
            return null;
        }
    }
    public void removeEdge(com.tinkerpop.blueprints.Edge edge){
        //TODO Figure out if this locking mechanism is correct
        this.graphModel.getGraph().readUnlockAll();
        this.graphModel.getGraph().removeEdge(this.graphModel.getGraph().getEdge(edge.getId()));
        //this.graphModel.getGraph().removeEdge( ((GephiEdge)edge).getRawEdge() );
    }
    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(){
        return new GephiEdgeIterable(this.graphModel.getGraph().getEdges(),this);
    }

    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(String key, Object value){
        //throw new UnsupportedOperationException();
        return new GephiKVEdgeIterable(this.graphModel.getGraph().getEdges(),this,key,value);
    }

    public GraphQuery query(){
        return new DefaultGraphQuery(this);
    }

    public Graph getGraphStore(){
        return this.graphModel.getGraph();
    }

    public GraphModel getGraphModel(){
        return this.graphModel;
    }

    public void shutdown(){
        //this.shutdown();
    }
}
