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

    //Wrap Graphstore Iterator
    public Iterator<GephiEdge> iterator(){
        return new Iterator<GephiEdge>() {

            private Iterator<Edge> it = edges.toCollection().iterator();

            @Override
            public boolean hasNext() {
                return this.it.hasNext();
            }

            @Override
            public GephiEdge next() {
                return new GephiEdge(this.it.next(), graph);
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
