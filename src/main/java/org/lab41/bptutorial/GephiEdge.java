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

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.util.ExceptionFactory;
import com.tinkerpop.blueprints.util.StringFactory;
import org.gephi.graph.api.Edge;

/**
 * Created by aganesh on 7/16/14.
 */
public class GephiEdge extends GephiElement implements com.tinkerpop.blueprints.Edge  {

    public GephiEdge(Edge edge, GephiGraph graph){
        super(graph);
        this.element = edge;
    }

    public Vertex getVertex(Direction direction) throws IllegalArgumentException{


        //Graphstore source = Blueprints OUT
        if(direction.equals(Direction.OUT)){
            return new GephiVertex(((Edge) this.element).getSource(),this.graph);
        }
        else if(direction.equals(Direction.IN)){
            return new GephiVertex(((Edge)this.element).getTarget(),this.graph);
        }
        else{
            throw ExceptionFactory.bothIsNotSupported();
        }
    }

    public Edge getRawEdge(){
        return (Edge) this.element;
    }

    public String toString(){
        return StringFactory.edgeString(this);
    }

    public String getLabel(){
        return ((Edge)this.element).getTypeLabel().toString();
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof GephiEdge && ((GephiEdge) object).getId().equals(this.getId());
    }

}
