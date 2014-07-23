package com.test.bptutorial;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Vertex;
import org.gephi.graph.api.Node;

import java.util.Iterator;

/**
 * Created by aganesh on 7/17/14.
 */
public class GephiVertexIterable<T extends Vertex> implements CloseableIterable<GephiVertex> {

    private Iterable<Node> nodes;
    private GephiGraph graph;

    public GephiVertexIterable(Iterable<Node> nodes, GephiGraph graph){
        this.graph = graph;
        this.nodes = nodes;
    }

    public Iterator<GephiVertex> iterator(){
        return new Iterator<GephiVertex>() {

            private Iterator<Node> it = nodes.iterator();

            @Override
            public boolean hasNext() {
                return this.it.hasNext();
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
