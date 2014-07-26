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
            private Node nextNode = null;

            @Override
            public boolean hasNext() {
                //return this.it.hasNext();
                if (null != this.nextNode)
                    return true;
                else {
                    while (this.it.hasNext()) {
                        final Node node = this.it.next();
                        try {
                            //node.hasProperty(DUMMY_PROPERTY);
                            this.nextNode = node;
                            return true;
                        } catch (final IllegalStateException e) {
                        }
                    }
                    return false;
                }
            }

            @Override
            public GephiVertex next() {
                /*if(this.it.next() == null){
                    return new GephiVertex(null,graph);
                }
                else{
                    return new GephiVertex(this.it.next(),graph);
                }*/
                if (null != this.nextNode) {
                    final Node temp = this.nextNode;
                    this.nextNode = null;
                    return new GephiVertex(temp, graph);
                } else {
                    while (true) {
                        final Node node = this.it.next();
                        try {
                            return new GephiVertex(node, graph);
                        } catch (final IllegalStateException e) {
                            // tried to access a node not available to the transaction
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
