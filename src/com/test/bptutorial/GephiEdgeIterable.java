package com.test.bptutorial;

/**
 * Created by aganesh on 7/17/14.
 */
import com.tinkerpop.blueprints.CloseableIterable;
import org.gephi.graph.api.Edge;
import java.util.Iterator;

public class GephiEdgeIterable <T extends com.tinkerpop.blueprints.Edge> implements CloseableIterable<GephiEdge> {

    private Iterable<Edge> edges;
    private GephiGraph graph;

    public GephiEdgeIterable(Iterable<Edge> edges, GephiGraph graph){
        this.graph = graph;
        this.edges = edges;
    }

    public Iterator<GephiEdge> iterator(){
        return new Iterator<GephiEdge>() {

            private Iterator<Edge> it = edges.iterator();
            private Edge nextEdge = null;

            @Override
            public boolean hasNext() {
                /*if (this.it.hasNext()) {
                    return true;
                }
                else{
                    return false;
                }*/
                if (null != this.nextEdge)
                    return true;
                else {
                    while (this.it.hasNext()) {
                        final Edge edge = this.it.next();
                        try {

                            this.nextEdge = edge;
                            return true;
                        } catch (final IllegalStateException e) {
                        }
                    }
                    return false;
                }

            }

            @Override
            public GephiEdge next() {

                /*if(this.it.next() == null){
                    return new GephiEdge(null,graph);
                }
                else{
                    return new GephiEdge(this.it.next(),graph);
                }*/
                if (null != this.nextEdge) {
                    final Edge temp = this.nextEdge;
                    this.nextEdge = null;
                    return new GephiEdge(temp, graph);
                } else {
                    while (true) {
                        final Edge edge = this.it.next();
                        try {
                            return new GephiEdge(edge, graph);
                        } catch (final IllegalStateException e) {
                            // tried to access a relationship not available to the transaction
                        }
                    }
                }

            }

            @Override
            public void remove() {
                this.it.remove();
            }
        };
    }

    public void close(){

    }
}
