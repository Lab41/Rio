package com.test.bptutorial;

/**
 * Created by aganesh on 7/17/14.
 */
import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Edge;
import org.gephi.graph.store.EdgeImpl;

import java.util.Iterator;

public class GephiEdgeIterable <T extends Edge> implements CloseableIterable<GephiEdge> {

    private Iterable<EdgeImpl> edges;
    private GephiGraph graph;

    public GephiEdgeIterable(Iterable<EdgeImpl> edges, GephiGraph graph){
        this.graph = graph;
        this.edges = edges;
    }

    public Iterator<GephiEdge> iterator(){
        return new Iterator<GephiEdge>() {

            private Iterator<EdgeImpl> it = edges.iterator();

            @Override
            public boolean hasNext() {
                if (this.it.hasNext()) {
                    return true;
                }
                else{
                    return false;
                }
            }

            @Override
            public GephiEdge next() {
                if(this.it.next() == null){
                    return new GephiEdge(null,graph);
                }
                else{
                    return new GephiEdge(this.it.next(),graph);
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
