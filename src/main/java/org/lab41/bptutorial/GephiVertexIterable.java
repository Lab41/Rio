package org.lab41.bptutorial;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Vertex;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by aganesh on 7/17/14.
 */
public class GephiVertexIterable<T extends Vertex> implements CloseableIterable<GephiVertex> {

    private NodeIterable nodes;
    private GephiGraph graph;

    public GephiVertexIterable(NodeIterable nodes, GephiGraph graph){
        this.graph = graph;
        this.nodes = nodes;
    }

    //Instead of checking

    public Iterator<GephiVertex> iterator(){
        return new Iterator<GephiVertex>() {

            private Iterator<Node> it = nodes.toCollection().iterator();
            private Node nextNode = null;

            @Override
            public boolean hasNext() {

                /*if (null != this.nextNode)
                    return true;
                else {
                    while (this.it.hasNext()) {
                        final Node node = this.it.next();
                            this.nextNode = node;
                            return true;
                    }
                    return false;
                }*/
                return this.it.hasNext();
            }

            @Override
            public GephiVertex next() {
                /*if (null != this.nextNode) {
                    final Node temp = this.nextNode;
                    this.nextNode = null;
                    return new GephiVertex(temp, graph);
                } else {
                    while (true) {
                        final Node node = this.it.next();
                            return new GephiVertex(node, graph);
                    }
                }*/
                return new GephiVertex(this.it.next(),graph);

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
