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
 * Created by aganesh on 7/15/14.
 */

import org.gephi.graph.api.Node;
import org.gephi.graph.store.*;

import java.util.Set;

public class HellowWorld {

    //Testing area for subset of tests

    public static void main(String[] args) {

        GraphModelImpl gl = new GraphModelImpl();
        //GephiGraph graph = new GephiGraph(gl);
        org.gephi.graph.api.Graph test = gl.getGraph();



        //Vertex a = graph.addVertex(null);
        //Vertex b = graph.addVertex(null);
        //Edge edge = graph.addEdge(null,a,b, "knows");



        //a.setProperty("keyString","value");
        //graph.removeEdge(edge);

        Node a = gl.factory().newNode("1");
        Node b = gl.factory().newNode("2");
        test.addNode(a);
        test.addNode(b);

        org.gephi.graph.api.Edge x = gl.factory().newEdge("1",a,b,gl.addEdgeType("knows"),-1,true);
        test.addEdge(x);

        System.out.println(a.getAttribute("label"));

        //x.setAttribute("weight",Double.parseDouble("2.3"));
        //System.out.println("HI");

        Double temp = 1.3;
        //Float we = temp.floatValue();


        //Float d = Float.parseFloat("2.12f");


        //Double xy = d.doubleValue();

        //System.out.println(we);
        //System.out.println(we);
        //a.setAttribute("weight",2.3);

        //gl.getNodeTable().addColumn("boolean",Boolean.class);
        //gl.getNodeTable().addColumn("key", "value".getClass());

        //a.setAttribute("boolean", true);
        //a.setAttribute("key","value");

        //Set<String> set = a.getAttributeKeys();
        //Object[] arr = set.toArray();

       /* for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i].toString());
        }

        System.out.println("   ");*/

        /*Object[] arr2 = a.getAttributes();
        for(int i = 0; i < arr2.length; i++){
            System.out.println(arr2[i] + "" + arr[i].toString());
        }*/

        //a.removeAttribute("key");
        //a.removeAttribute("boolean");

        //Boolean x = (Boolean) a.removeAttribute("ooolean");
        //System.out.println(a.getAttribute("boolean"));
        //System.out.println(x);



        /*Node a = gl.factory().newNode("1");
        Node b = gl.factory().newNode("2");
        Node c = gl.factory().newNode("3");

        org.gephi.graph.api.Graph test = gl.getGraph();
        test.addNode(a);
        test.addNode(b);*/
        //test.addNode(c);

        //org.gephi.graph.api.Edge x = gl.factory().newEdge(a,b,gl.addEdgeType("knows"),true);
        //org.gephi.graph.api.Edge y = gl.factory().newEdge(a,b,gl.addEdgeType("knows"),true);
        /*org.gephi.graph.api.Edge z = gl.factory().newEdge(a,c,gl.addEdgeType("hates"),true);
        org.gephi.graph.api.Edge q = gl.factory().newEdge(a,b,gl.addEdgeType("hates"),true);
        org.gephi.graph.api.Edge w = gl.factory().newEdge(c,c,gl.addEdgeType("hates"),true);*/

        //test.addEdge(x);
        //test.addEdge(y);


        //System.out.println(test.getEdgeCount());
        /*test.addEdge(z);
        test.addEdge(q);
        test.addEdge(w);*/
        //System.out.println(q.getTypeLabel());
        //System.out.println(gl.getDirectedGraph().getInEdges(a).toArray().length);
        //System.out.println(gl.getDirectedGraph().getSuccessors(a,q.getType()).toArray().length);
        //System.out.println(gl.getDirectedGraph().getSuccessors(a,gl.getEdgeType("hates")).toArray().length);

        //System.out.print(gl.getDirectedGraph().getEdge(a,b).getId());
        //Iterator<org.gephi.graph.api.Edge> it = gl.getDirectedGraph().getOutEdges(a).iterator();
        //System.out.println(gl.getDirectedGraph().getOutEdges(a).toArray().length);
        //Node tempN = null;
        //System.out.println(it.next());
        //while(it.hasNext()){
        //    System.out.println(it.next().getTypeLabel());
        //}

        /*while(it.hasNext()){
            temp = it.next().getTarget();
            System.out.println(temp.getTypeLabel());
            System.out.println(temp.getLabel());
        }*/

        //System.out.print(gl.getGraph().getEdge(a,b,gl.getEdgeType("hates")));
        //System.out.println(gl.getDirectedGraph().getSuccessors(a,gl.getEdgeType("hates")).toArray().length   );
        //System.out.println(gl.getDirectedGraph().getSuccessors(a).toArray().length      );
        //System.out.println(it.hasNext());

        /*Vertex a = graph.addVertex(null);
        Vertex b = graph.addVertex(null);
        Vertex c = graph.addVertex(null);
        Edge w = graph.addEdge(null, a, b, "knows");
        Edge x = graph.addEdge(null, b, c, "knows");
        Edge y = graph.addEdge(null, a, c, "hates");
        Edge z = graph.addEdge(null, a, b, "hates");
        Edge zz = graph.addEdge(null, c, c, "hates");*/

        //System.out.println(Double.parseDouble("0.5"));


    }



}
