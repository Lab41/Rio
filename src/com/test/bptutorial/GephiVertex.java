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

import java.util.*;


public class GephiVertex extends GephiElement implements Vertex {

    public GephiVertex(Node node, GephiGraph graph){
        super(graph);
        this.element = node;
    }

    public boolean equals(final Object object){
        return object instanceof GephiVertex && ((GephiVertex) object).getId().equals(this.getId());
    }

    public Iterable<com.tinkerpop.blueprints.Edge> getEdges(Direction direction, String... labels){
        if(direction.equals(Direction.OUT)){
            return new GephiIncidentEdgeIterable(this.graph,(Node) this.element,"OUT",labels);
        }
        else if(direction.equals(Direction.IN)){
            return new GephiIncidentEdgeIterable(this.graph,(Node) this.element,"IN",labels);
        }
        else{
            return new MultiIterable(Arrays.asList(new GephiIncidentEdgeIterable(this.graph, (Node) this.element, "OUT", labels) , new GephiIncidentEdgeIterable(this.graph, (Node)this.element, "IN", labels))  );
        }

    }
    public Node getRawVertex(){
        return (Node)this.element;
    }

    public String toString(){
        return StringFactory.vertexString(this);
    }

    public Iterable<Vertex> getVertices(Direction direction, String... labels){
        if(direction.equals(Direction.OUT)){
            return new GephiAdjacentVertexIterable(this.graph,(Node)this.element,"OUT",labels);
        }
        else if(direction.equals(Direction.IN)){
            return new GephiAdjacentVertexIterable(this.graph,(Node)this.element,"IN",labels);
        }
        else{
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
            for(String x : labels) labelHashSet.add(x);
        }

        public Iterator<GephiVertex> iterator(){

            //TODO Cut this down
            final Iterator<Node> itIn = graph.getGraphModel().getDirectedGraph().getPredecessors(node).toCollection().iterator();
            final Iterator<Node> itOut = graph.getGraphModel().getDirectedGraph().getSuccessors(node).toCollection().iterator();
            final Iterator<Edge> itEdgeIn = graph.getGraphModel().getDirectedGraph().getInEdges(node).toCollection().iterator();
            final Iterator<Edge> itEdgeOut = graph.getGraphModel().getDirectedGraph().getOutEdges(node).toCollection().iterator();

            return new Iterator<GephiVertex>() {
                Edge nextEdge = null;
                @Override
                public boolean hasNext() {

                    if(labelHashSet.size() == 0){
                        if(direction.equals("IN")){
                            return itIn.hasNext();
                        }
                        else if(direction.equals("OUT")){
                            return itOut.hasNext();
                        }
                    }

                    if (nextEdge != null) {
                        return true;
                    }

                    //Search for the next thing
                    if (direction.equals("IN")) {
                        while (itEdgeIn.hasNext()) {
                            Edge temp = itEdgeIn.next();
                                if (labelHashSet.contains(temp.getTypeLabel())) {
                                    nextEdge = temp;
                                    return true;
                                }
                        }

                    } else {
                        while (itEdgeOut.hasNext()) {

                            Edge temp = itEdgeOut.next();
                                if (labelHashSet.contains(temp.getTypeLabel())) {
                                    nextEdge = temp;
                                    return true;
                                }
                        }

                    }
                    return false;

                }

                @Override
                public GephiVertex next() {
                    if(labelHashSet.size() == 0){
                        if(direction.equals("IN")){
                            while(itIn.hasNext()) {
                                return new GephiVertex(itIn.next(), graph);
                            }
                        }
                        else if(direction.equals("OUT")){
                            while(itOut.hasNext()) {
                                return new GephiVertex(itOut.next(), graph);
                            }
                        }
                    }

                    if(nextEdge != null){
                        Edge temp = nextEdge;
                        nextEdge = null;

                        //TODO Rethink this case
                        if(temp.getSource().equals(node)) {
                            return new GephiVertex(temp.getTarget(), graph);
                        }
                        else{
                            return new GephiVertex(temp.getSource(), graph);
                        }
                    }

                    //Search for the next thing
                    if (direction.equals("IN")) {
                        while (itEdgeIn.hasNext()) {
                            Edge temp = itEdgeIn.next();
                                if (labelHashSet.contains(temp.getTypeLabel())) {
                                    return new GephiVertex(temp.getSource(), graph);
                                }
                        }

                    } else {
                        while (itEdgeOut.hasNext()) {
                            Edge temp = itEdgeOut.next();
                                if (labelHashSet.contains(temp.getTypeLabel())) {
                                    return new GephiVertex(temp.getTarget(), graph);
                                }
                        }

                    }

                    throw new NoSuchElementException();

                }

                @Override
                public void remove() {
                    if (direction.equals("IN")){
                        if(labelHashSet.size() == 0) {
                            itIn.remove();
                        }
                        else {
                            itEdgeIn.remove();
                        }
                    }
                    else if(direction.equals("OUT")){
                        if(labelHashSet.size() == 0){
                            itOut.remove();
                        }
                        else {
                            itEdgeOut.remove();
                        }
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

            final Iterator<Edge> itIn = graph.getGraphModel().getDirectedGraph().getInEdges(node).toCollection().iterator();
            final Iterator<Edge> itOut = graph.getGraphModel().getDirectedGraph().getOutEdges(node).toCollection().iterator();

            return new Iterator<GephiEdge>() {

                Edge nextEdge = null;
                @Override
                public boolean hasNext() {

                    if(labelHashSet.size() == 0){
                        if(direction.equals("IN")){
                            return itIn.hasNext();
                        }
                        else if(direction.equals("OUT")){
                            return itOut.hasNext();
                        }
                    }

                    if (nextEdge != null) {
                        return true;
                    }


                    //Search for the next thing
                    if (direction.equals("IN")) {
                        while (itIn.hasNext()) {

                            Edge temp = itIn.next();

                            if (labelHashSet.contains(temp.getTypeLabel())) {
                                nextEdge = temp;
                                return true;
                            }

                        }


                    } else {
                        while (itOut.hasNext()) {

                                Edge temp = itOut.next();

                                    if (labelHashSet.contains(temp.getTypeLabel())) {
                                        nextEdge = temp;
                                        return true;
                                    }
                        }

                    }
                    return false;
                }

                @Override
                public GephiEdge next() {



                    if(labelHashSet.size() == 0){
                        if(direction.equals("IN")){
                            while(itIn.hasNext()) {
                                return new GephiEdge(itIn.next(), graph);

                            }
                        }
                        else if(direction.equals("OUT")){
                            while(itOut.hasNext()) {
                                return new GephiEdge(itOut.next(), graph);

                            }
                        }
                    }

                    if(nextEdge != null){
                        Edge temp = nextEdge;
                        nextEdge = null;
                        return new GephiEdge(temp,graph);

                    }

                    //Search for the next thing
                    if (direction.equals("IN")) {
                        while (itIn.hasNext()) {
                            Edge temp = itIn.next();
                                    if (labelHashSet.contains(temp.getTypeLabel())) {
                                        return new GephiEdge(temp, graph);

                                    }
                        }

                    } else {
                        while (itOut.hasNext()) {
                            Edge temp = itOut.next();
                                    if (labelHashSet.contains(temp.getTypeLabel())) {
                                        return new GephiEdge(temp, graph);

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
