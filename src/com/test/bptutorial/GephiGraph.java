package com.test.bptutorial;



import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.util.DefaultGraphQuery;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.graph.store.NodeImpl;
import org.gephi.graph.store.EdgeImpl;
import java.util.Iterator;

/**
 * Created by aganesh on 7/15/14.
 */
public class GephiGraph implements com.tinkerpop.blueprints.Graph{

    private Graph graphDb;
    private static final Features FEATURES = new Features();
    int counter = 0;

    static{
        FEATURES.ignoresSuppliedIds = true;
        FEATURES.supportsVertexIteration = true;
        FEATURES.supportsEdgeIteration = true;
    }



    public GephiGraph(Graph graphDb){
        this.graphDb = graphDb;
    }

    public Features getFeatures(){
        return FEATURES;
    }
    public Vertex addVertex(Object id){

        this.counter++;
        //Node node = new NodeImpl(id);
        Node node;
        if(id == null)
            node = new NodeImpl(this.counter);
        else
            node = new NodeImpl(id);

        this.graphDb.addNode(node);
        return new GephiVertex(node,this);
    }
    public Vertex getVertex(Object id){
        if (id == null) throw ExceptionFactory.vertexIdCanNotBeNull();
        //return new GephiVertex(new NodeImpl(id),this);
        return new GephiVertex(this.graphDb.getNode(id),this);
    }


    public void removeVertex(Vertex vertex){
        try {

            //Node node = new NodeImpl(vertex.getId());
            Node node = ((GephiVertex) vertex).getRawVertex();

            System.out.println(this.graphDb.getEdgeCount());
            //Need to remove the edges associated with this vertex
            /*Iterator<Edge> itr = this.graphDb.getEdges().iterator();

            while(itr.hasNext()){
                System.out.println(itr.hasNext());
                //this.graphDb.removeEdge(itr.next());
            }*/


            this.graphDb.removeNode(node);

        } catch (IllegalStateException ise){
            throw ExceptionFactory.vertexWithIdDoesNotExist(vertex.getId());
        }

    }
    public Iterable<Vertex> getVertices(){
        return new GephiVertexIterable(graphDb.getNodes(),this);

    }

    public Iterable<Vertex> getVertices(String key, Object value){
        return null;
    }

    public com.tinkerpop.blueprints.Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label){

        if(label == null)
            throw ExceptionFactory.edgeLabelCanNotBeNull();

        //NodeImpl source = new NodeImpl(outVertex.getId());
        //NodeImpl target = new NodeImpl(inVertex.getId());

        Node source = ((GephiVertex)inVertex).getRawVertex();
        Node target = ((GephiVertex)inVertex).getRawVertex();

        if(id == null){
            id = counter;
        }

        Edge gsEdge = new EdgeImpl(id,(NodeImpl)source,(NodeImpl)target,0,0,false);

        this.graphDb.addEdge(gsEdge);
        GephiEdge gephiEdge = new GephiEdge(gsEdge,this);
        gephiEdge.setLabel(label);
        return gephiEdge;
    }
    public com.tinkerpop.blueprints.Edge getEdge(Object id){
        if (id == null) throw ExceptionFactory.edgeIdCanNotBeNull();
        return new GephiEdge(this.graphDb.getEdge(id),this);
    }
    public void removeEdge(com.tinkerpop.blueprints.Edge edge){
        this.graphDb.removeEdge(this.graphDb.getEdge(edge.getId()));
    }
    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(){
        return new GephiEdgeIterable(this.graphDb.getEdges(),this);
    }

    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(String key, Object value){
        return null;
    }

    public GraphQuery query(){
        return new DefaultGraphQuery(this);
    }

    public Graph getGraphStore(){
        return this.graphDb;
    }

    public void shutdown(){
        //this.shutdown();
    }
}
