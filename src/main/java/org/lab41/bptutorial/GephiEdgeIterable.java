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
