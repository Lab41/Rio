package com.test.bptutorial;



import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.util.DefaultGraphQuery;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import com.tinkerpop.blueprints.util.StringFactory;
import org.gephi.graph.api.*;


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
        FEATURES.ignoresSuppliedIds = false;
        FEATURES.isPersistent = false;
        FEATURES.isWrapper = false;
        FEATURES.supportsBooleanProperty = true;
        FEATURES.supportsDoubleProperty = true;
        FEATURES.supportsDuplicateEdges = false;
        FEATURES.supportsEdgeIndex = true;
        FEATURES.supportsEdgeIteration = true;
        FEATURES.supportsEdgeKeyIndex = false;
        FEATURES.supportsEdgeProperties = true;
        FEATURES.supportsEdgeRetrieval = true;
        FEATURES.supportsFloatProperty = true;
        FEATURES.supportsIndices = false;
        FEATURES.supportsIntegerProperty = true;
        FEATURES.supportsKeyIndices = false;
        FEATURES.supportsLongProperty = true;
        FEATURES.supportsMapProperty = false;
        FEATURES.supportsMixedListProperty = false;
        FEATURES.supportsPrimitiveArrayProperty = true;
        FEATURES.supportsSelfLoops = true;
        FEATURES.supportsSerializableObjectProperty = false;
        FEATURES.supportsStringProperty = true;
        FEATURES.supportsThreadedTransactions = false;
        FEATURES.supportsTransactions = false;
        FEATURES.supportsUniformListProperty = false;
        FEATURES.supportsVertexIndex = true;
        FEATURES.supportsVertexIteration = true;
        FEATURES.supportsVertexKeyIndex = false;
        FEATURES.supportsVertexProperties = true;

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
            node = this.graphModel.factory().newNode(Integer.toString(counter));
        else
            node = this.graphModel.factory().newNode(id.toString());

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
            Node node = ((GephiVertex) vertex).getRawVertex();

            //Need to remove the edges associated with this vertex
            Iterator<Edge> itrIn = this.graphModel.getDirectedGraph().getInEdges(node).toCollection().iterator();
            Iterator<Edge> itrOut = this.graphModel.getDirectedGraph().getOutEdges(node).toCollection().iterator();

            //TODO: Figure out correcting locking mechanism if needed
            while(itrIn.hasNext()){
                this.graphModel.getGraph().removeEdge(itrIn.next());
            }
            while(itrOut.hasNext()){
                this.graphModel.getGraph().removeEdge(itrOut.next());
            }

            this.graphModel.getGraph().removeNode(node);

        } catch (IllegalStateException ise){
            throw ExceptionFactory.vertexWithIdDoesNotExist(vertex.getId());
        } catch(IllegalArgumentException iae){
            throw ExceptionFactory.vertexWithIdDoesNotExist(vertex.getId());
        }

    }
    public Iterable<Vertex> getVertices(){
        return new GephiVertexIterable(this.graphModel.getGraph().getNodes(),this);
    }

    public Iterable<Vertex> getVertices(String key, Object value){

        return new GephiKVVertexIterable(this.graphModel.getGraph().getNodes(),this,key,value);
    }

    public com.tinkerpop.blueprints.Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label){

        if(label == null)
            throw ExceptionFactory.edgeLabelCanNotBeNull();

        Node source = ((GephiVertex)outVertex).getRawVertex();
        Node target = ((GephiVertex)inVertex).getRawVertex();

        Edge gsEdge;

        gsEdge = this.graphModel.factory().newEdge(source, target, this.graphModel.addEdgeType(label), true);
        gsEdge.setAttribute("label",label);
        this.graphModel.getGraph().addEdge(gsEdge);

        return new GephiEdge(gsEdge,this);
    }
    public com.tinkerpop.blueprints.Edge getEdge(Object id){
        if (id == null) throw ExceptionFactory.edgeIdCanNotBeNull();

        if(this.graphModel.getGraph().getEdge(id) != null ){
            return new GephiEdge(this.graphModel.getGraph().getEdge(id),this);
        }
        else{
            return null;
        }
    }

    public void removeEdge(com.tinkerpop.blueprints.Edge edge){
        //TODO Figure out if locking mechanism needed
        this.graphModel.getGraph().removeEdge(this.graphModel.getGraph().getEdge(edge.getId()));
    }

    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(){
        return new GephiEdgeIterable(this.graphModel.getGraph().getEdges(),this);
    }

    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(String key, Object value){
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

    public String toString(){
        return StringFactory.graphString(this,this.graphModel.getGraph().toString());
    }
}
