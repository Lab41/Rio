package com.test.bptutorial;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Vertex;
import org.gephi.graph.store.NodeImpl;

import java.util.Iterator;

/**
 * Created by aganesh on 7/17/14.
 */
public class GephiVertexIterable<T extends Vertex> implements CloseableIterable<GephiVertex> {

    private Iterable<NodeImpl> nodes;
    private GephiGraph graph;

    public GephiVertexIterable(Iterable<NodeImpl> nodes, GephiGraph graph){
        this.graph = graph;
        this.nodes = nodes;
    }

    public Iterator<GephiVertex> iterator(){
        return new Iterator<GephiVertex>() {

            private Iterator<NodeImpl> it = nodes.iterator();

            @Override
            public boolean hasNext() {
                if(this.it.hasNext()) {

                    return true;
                }
                else{
                    return false;
                }
            }

            @Override
            public GephiVertex next() {
                if(this.it.next() == null){
                    return new GephiVertex(null,graph);
                }
                else{
                    return new GephiVertex(this.it.next(),graph);
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
