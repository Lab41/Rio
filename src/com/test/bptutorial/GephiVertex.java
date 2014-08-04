package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.VertexQuery;
import com.tinkerpop.blueprints.util.DefaultVertexQuery;
import com.tinkerpop.blueprints.util.MultiIterable;
import com.tinkerpop.blueprints.util.StringFactory;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Edge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class GephiVertex extends GephiElement implements Vertex {

    //private Node node;

    public GephiVertex(Node node, GephiGraph graph){
        //super(graph);
        super(graph, node);
        this.element = node;
        //this.node = node;
    }

    public boolean equals(final Object object){
        return object instanceof GephiVertex && ((GephiVertex) object).getId().equals(this.getId());
    }

    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(Direction direction, String... labels){
        if(direction.equals(Direction.OUT)){
            //return new GephiIncidentEdgeIterable(this.graph,this.node,"OUT",labels);
            return new GephiIncidentEdgeIterable(this.graph,(Node) this.element,"OUT",labels);
        }
        else if(direction.equals(Direction.IN)){
            //return new GephiIncidentEdgeIterable(this.graph,this.node,"IN",labels);
            return new GephiIncidentEdgeIterable(this.graph,(Node) this.element,"IN",labels);
        }
        else{
            //return new MultiIterable(Arrays.asList(new GephiIncidentEdgeIterable(this.graph, this.node, "OUT", labels) , new GephiIncidentEdgeIterable(this.graph, this.node, "IN", labels))  );
            return new MultiIterable(Arrays.asList(new GephiIncidentEdgeIterable(this.graph, (Node) this.element, "OUT", labels) , new GephiIncidentEdgeIterable(this.graph, (Node)this.element, "IN", labels))  );
        }

    }
    public Node getRawVertex(){
        //return this.node;
        return (Node)this.element;
    }

    public String toString(){
        return StringFactory.vertexString(this);
    }

    public Iterable<Vertex> getVertices(Direction direction, String... labels){
        if(direction.equals(Direction.OUT)){
            //return new GephiAdjacentVertexIterable(this.graph,this.node,"OUT",labels);
            return new GephiAdjacentVertexIterable(this.graph,(Node)this.element,"OUT",labels);
        }
        else if(direction.equals(Direction.IN)){
            //return new GephiAdjacentVertexIterable(this.graph,this.node,"IN",labels);
            return new GephiAdjacentVertexIterable(this.graph,(Node)this.element,"IN",labels);
        }
        else{
            //return new MultiIterable(Arrays.asList(new GephiAdjacentVertexIterable(this.graph, this.node, "OUT", labels) , new GephiAdjacentVertexIterable(this.graph, this.node, "IN", labels))  );
            return new MultiIterable(Arrays.asList(new GephiAdjacentVertexIterable(this.graph, (Node)this.element, "OUT", labels) , new GephiAdjacentVertexIterable(this.graph, (Node)this.element, "IN", labels))  );
        }
    }
    public VertexQuery query(){
        return new DefaultVertexQuery(this);
    }
    public com.tinkerpop.blueprints.Edge addEdge(String label, Vertex inVertex){
        return this.graph.addEdge(null,this,inVertex,label);

    }

    private class GephiAdjacentVertexIterable<T extends com.tinkerpop.blueprints.Vertex> implements Iterable<GephiVertex>{
        private GephiGraph graph;
        private Node node;
        private String direction;
        private HashSet<String> labelHashSet;

        public GephiAdjacentVertexIterable(GephiGraph graph, Node node, String direction,String... labels){
            this.graph = graph;
            this.node = node;
            this.direction = direction;
            labelHashSet = new HashSet<String>();
            for(String i:labels) labelHashSet.add(i);
        }

        public Iterator<GephiVertex> iterator(){

            final Iterator<Node> itIn = graph.getGraphStore().getNeighbors(node).iterator();
            final Iterator<Node> itOut = graph.getGraphStore().getNeighbors(node).iterator();

            return new Iterator<GephiVertex>() {
                @Override
                public boolean hasNext() {
                    if (direction.equals("IN")) {
                        while(itIn.hasNext()){
                            if (labelHashSet.contains(itIn.next().getLabel())) {
                                return true;
                            }
                        }
                    }

                    else if(direction.equals("OUT")){
                        while(itOut.hasNext()) {
                            if (labelHashSet.contains(itOut.next().getLabel())) {
                                return true;
                            }
                        }
                    }

                    return false;
                }

                @Override
                public GephiVertex next() {
                    if (direction.equals("IN")) {
                        while(itIn.hasNext()){
                            if (labelHashSet.contains(itIn.next().getLabel())) {
                                return new GephiVertex(itIn.next(),graph);
                            }
                        }
                    }

                    else if(direction.equals("OUT")){
                        while(itOut.hasNext()) {
                            if (labelHashSet.contains(itOut.next().getLabel())) {
                                return new GephiVertex(itOut.next(),graph);
                            }
                        }
                    }

                    return new GephiVertex(null,graph);


                }

                @Override
                public void remove() {
                    if (direction.equals("IN")){
                        itIn.remove();
                    }
                    else if(direction.equals("OUT")){
                        itOut.remove();
                    }
                }
            };
        }


    }


    private class GephiIncidentEdgeIterable<T extends com.tinkerpop.blueprints.Edge> implements Iterable<GephiEdge>{
        private GephiGraph graph;
        private Node node;
        private String direction;
        private HashSet<String> labelHashSet;

        public GephiIncidentEdgeIterable(GephiGraph graph, Node node, String direction,String... labels){
            this.graph = graph;
            this.node = node;
            this.direction = direction;
            labelHashSet = new HashSet<String>();
            for(String i:labels) labelHashSet.add(i);
        }


        public Iterator<GephiEdge> iterator(){

            final Iterator<Edge> itIn = graph.getGraphStore().getEdges(node).iterator();
            final Iterator<Edge> itOut = graph.getGraphStore().getEdges(node).iterator();

            return new Iterator<GephiEdge>() {

                Edge nextEdge = null;
                @Override
                public boolean hasNext() {

                    if (nextEdge != null) {
                        return true;
                    }


                    //Search for the next thing
                    if (direction.equals("IN")) {
                        while (itIn.hasNext()) {
                            Edge temp = itIn.next();
                            if (temp.getTarget().equals(node)) {
                                if (labelHashSet.contains(temp.getLabel())) {
                                    nextEdge = temp;
                                    return true;
                                }
                            }
                        }

                    } else {
                        while (itOut.hasNext()) {
                            Edge temp = itOut.next();
                            if (temp.getSource().equals(node)) {
                                if (labelHashSet.contains(temp.getLabel())) {
                                    nextEdge = temp;
                                    return true;
                                }
                            }
                        }

                    }
                    return false;
                }

                @Override
                public GephiEdge next() {

                    if(nextEdge != null){
                        Edge temp = nextEdge;
                        nextEdge = null;
                        return new GephiEdge(temp,graph);
                    }

                    //Search for the next thing
                    if (direction.equals("IN")) {
                        while (itIn.hasNext()) {
                            Edge temp = itIn.next();
                            if (temp.getTarget().equals(node)) {
                                if (labelHashSet.contains(temp.getLabel())) {

                                    return new GephiEdge(temp,graph);
                                }
                            }
                        }

                    } else {
                        while (itOut.hasNext()) {
                            Edge temp = itOut.next();
                            if (temp.getSource().equals(node)) {
                                if (labelHashSet.contains(temp.getLabel())) {

                                    return new GephiEdge(temp, graph);
                                }
                            }
                        }

                    }

                    throw new NoSuchElementException();

                }

                @Override
                public void remove() {
                    if (direction.equals("IN")){
                        itIn.remove();
                    }
                    else if(direction.equals("OUT")){
                        itOut.remove();
                    }
                }
            };
        }


    }


}
