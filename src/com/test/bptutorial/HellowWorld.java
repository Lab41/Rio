package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.impls.GraphTest;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import org.gephi.attribute.api.AttributeModel;
import org.gephi.attribute.api.Table;
import org.gephi.graph.api.Element;
import org.gephi.graph.api.Node;
import org.gephi.graph.store.*;

import java.lang.reflect.Method;
import java.lang.reflect.*;
import java.lang.Class;
import java.util.*;


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
        test.addNode(a);
        gl.getNodeTable().addColumn("boolean",Boolean.class);
        gl.getNodeTable().addColumn("key", "value".getClass());

        a.setAttribute("boolean", true);
        a.setAttribute("key","value");
        //a.removeAttribute("key");
        a.removeAttribute("boolean");

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




    }



}
