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

    //Wrap Graphstore Iterator
    public Iterator<GephiVertex> iterator(){
        return new Iterator<GephiVertex>() {

            private Iterator<Node> it = nodes.toCollection().iterator();

            @Override
            public boolean hasNext() {
                return this.it.hasNext();
            }

            @Override
            public GephiVertex next() {
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
