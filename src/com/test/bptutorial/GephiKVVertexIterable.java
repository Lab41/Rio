package com.test.bptutorial;

import com.tinkerpop.blueprints.CloseableIterable;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.EdgeIterable;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by aganesh on 8/12/14.
 */

    public class GephiKVVertexIterable <T extends com.tinkerpop.blueprints.Vertex> implements CloseableIterable<GephiVertex> {

        private NodeIterable nodes;
        private GephiGraph graph;
        private String key;
        private Object value;

        public GephiKVVertexIterable(NodeIterable nodes, GephiGraph graph, String key, Object value){
            this.graph = graph;
            this.nodes = nodes;
            this.key = key;
            this.value = value;
        }

        public Iterator<GephiVertex> iterator(){

            final Iterator<Node> it = graph.getGraphModel().getGraph().getNodes().toCollection().iterator();


            return new Iterator<GephiVertex>() {

                Node nextNode = null;

                public boolean hasNext() {

                    if (nextNode != null) {
                        return true;
                    }

                    while(it.hasNext()){
                        Node temp = it.next();
                        if(temp.getAttribute(key).equals(value)){
                            nextNode = temp;
                            return true;
                        }
                    }

                    return false;
                }
                public GephiVertex next(){

                    if(nextNode != null){
                        Node temp = nextNode;
                        nextNode = null;
                        return new GephiVertex(temp,graph);
                    }

                    while(it.hasNext()){
                        Node temp = it.next();
                        if(temp.getAttribute(key).equals(value)){
                            return new GephiVertex(temp,graph);
                        }
                    }
                    throw new NoSuchElementException();
                }
                public void remove(){
                    it.remove();
                }
            };
        }

        public void close(){

        }
    }


