package org.lab41.bptutorial;

/**
 * Created by aganesh on 7/17/14.
 */
import com.tinkerpop.blueprints.CloseableIterable;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.EdgeIterable;

import java.util.Iterator;

public class GephiEdgeIterable <T extends com.tinkerpop.blueprints.Edge> implements CloseableIterable<GephiEdge> {

    private EdgeIterable edges;
    private GephiGraph graph;

    public GephiEdgeIterable(EdgeIterable edges, GephiGraph graph){
        this.graph = graph;
        this.edges = edges;
    }

    public Iterator<GephiEdge> iterator(){
        return new Iterator<GephiEdge>() {

            private Iterator<Edge> it = edges.toCollection().iterator();
            private Edge nextEdge = null;

            @Override
            public boolean hasNext() {

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
