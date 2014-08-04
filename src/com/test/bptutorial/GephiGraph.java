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

/**
 * Created by aganesh on 7/15/14.
 */
public class GephiGraph implements com.tinkerpop.blueprints.Graph{

    //private Graph graphDb;
    //private GraphFactory graphFactory;
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
    }

    /*public GephiGraph(Graph graphDb, GraphFactory graphFactory){
        this.graphDb = graphDb;
        this.graphFactory = graphFactory;
    }*/
    public GephiGraph(GraphModel graphModel){
        this.graphModel = graphModel;
    }


    public Features getFeatures(){
        return FEATURES;
    }
    public Vertex addVertex(Object id){

        this.counter++;
        //Node node = new NodeImpl(id);
        Node node;

        if(id == null)
            //node = new NodeImpl(this.counter);
            node = this.graphModel.factory().newNode(this.counter);
        else
            //node = new NodeImpl(id);
            node = this.graphModel.factory().newNode(id);

        this.graphModel.getGraph().addNode(node);
        return new GephiVertex(node,this);
    }
    public Vertex getVertex(Object id){
        if (id == null) throw ExceptionFactory.vertexIdCanNotBeNull();
        //return new GephiVertex(new NodeImpl(id),this);
        if(this.graphModel.getGraph().getNode(id) != null ){
            return new GephiVertex(this.graphModel.getGraph().getNode(id),this);
        }
        else{
            return null;
        }
    }


    public void removeVertex(Vertex vertex){
        try {
            //Node node = new NodeImpl(vertex.getId());
            Node node = ((GephiVertex) vertex).getRawVertex();

            //Need to remove the edges associated with this vertex
            //Iterator<Edge> itr = this.graphDb.getEdges().iterator();

            //TODO: Figure out correcting locking mechanism
            Edge [] edgeArray = this.graphModel.getGraph().getEdges().toArray();

            /*while(itr.hasNext()){
                this.graphDb.removeEdge(itr.next());
            }*/
            for(Edge x : edgeArray){
                this.graphModel.getGraph().removeEdge(x);
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
        return null;
    }

    public com.tinkerpop.blueprints.Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label){

        if(label == null)
            throw ExceptionFactory.edgeLabelCanNotBeNull();

        //NodeImpl source = new NodeImpl(outVertex.getId());
        //NodeImpl target = new NodeImpl(inVertex.getId());

        //Node source = ((GephiVertex)inVertex).getRawVertex();
        //Node target = ((GephiVertex)outVertex).getRawVertex();

        Node source = ((GephiVertex)outVertex).getRawVertex();
        Node target = ((GephiVertex)inVertex).getRawVertex();

        /*if(id == null){
            id = counter;
        }*/

        //Edge gsEdge = new EdgeImpl(id,(NodeImpl)source,(NodeImpl)target,0,0,false);
        Edge gsEdge = this.graphModel.factory().newEdge(source,target,true);
        //gsEdge.setLabel(label);

        this.graphModel.getGraph().addEdge(gsEdge);
        GephiEdge gephiEdge = new GephiEdge(gsEdge,this);
        gephiEdge.setLabel(label);
        return gephiEdge;
    }
    public com.tinkerpop.blueprints.Edge getEdge(Object id){
        if (id == null) throw ExceptionFactory.edgeIdCanNotBeNull();
        return new GephiEdge(this.graphModel.getGraph().getEdge(id),this);
    }
    public void removeEdge(com.tinkerpop.blueprints.Edge edge){
        this.graphModel.getGraph().removeEdge(this.graphModel.getGraph().getEdge(edge.getId()));
    }
    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(){
        return new GephiEdgeIterable(this.graphModel.getGraph().getEdges(),this);
    }

    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(String key, Object value){
        return null;
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
