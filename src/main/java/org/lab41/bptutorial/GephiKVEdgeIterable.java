/*
*
* Copyright (c) 2014 In-Q-Tel, Inc/Lab41, All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.lab41.bptutorial;

import com.tinkerpop.blueprints.CloseableIterable;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.EdgeIterable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by aganesh on 8/12/14.
 */
public class GephiKVEdgeIterable <T extends com.tinkerpop.blueprints.Edge> implements CloseableIterable<GephiEdge> {

    private EdgeIterable edges;
    private GephiGraph graph;
    private String key;
    private Object value;

    public GephiKVEdgeIterable(EdgeIterable edges, GephiGraph graph, String key, Object value){
        this.graph = graph;
        this.edges = edges;
        this.key = key;
        this.value = value;
    }

    public Iterator<GephiEdge> iterator(){

        final Iterator<Edge> it = graph.getGraphModel().getGraph().getEdges().toCollection().iterator();

        return new Iterator<GephiEdge>() {

            Edge nextEdge = null;

            public boolean hasNext() {

                if (nextEdge != null) {
                    return true;
                }

                while(it.hasNext()){
                    Edge temp = it.next();
                    if(temp.getAttribute(key).equals(value)){
                        nextEdge = temp;
                        return true;
                    }
                }

                return false;
            }
            public GephiEdge next(){

                if(nextEdge != null){
                    Edge temp = nextEdge;
                    nextEdge = null;
                    return new GephiEdge(temp,graph);
                }

                while(it.hasNext()){
                    Edge temp = it.next();
                    if(temp.getAttribute(key).equals(value)){
                        return new GephiEdge(temp,graph);
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
