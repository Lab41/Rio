package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.impls.GraphTest;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Direction;
import org.gephi.attribute.api.AttributeModel;
import org.gephi.attribute.api.Table;
import org.gephi.graph.api.Element;
import org.gephi.graph.api.Node;
import org.gephi.graph.store.*;
import com.tinkerpop.blueprints.VertexTestSuite;
import com.tinkerpop.blueprints.TestSuite;

import java.lang.reflect.Method;
import java.lang.reflect.*;
import java.lang.Class;
import java.util.*;


public class HellowWorld {




    public static void main(String[] args) {
        //GraphModelImpl gl = new GraphModelImpl();
        //GraphStore gp = new GraphStore(gl);
        //GephiGraph graph = new GephiGraph(gp);
        //Graph graph = new GephiGraph(gp);

        GraphModelImpl gl = new GraphModelImpl();

        //GraphStore gp = new GraphStore(gl);
        //GraphFactoryImpl gf = new GraphFactoryImpl(gp);

        //GephiGraph graph = new GephiGraph(gl.getGraph(),gl.factory());
        //gl.getNodeTable();

        Node node = gl.factory().newNode("2");

        gl.getNodeTable().addColumn("key1",String.class);
        node.setAttribute("key1","value1");
        System.out.println(node.getAttribute("key1"));
        //node.setAttribute("HI","G");



        //Vertex v1 = graph.addVertex("2");
        //v1.setProperty("HI", "G");



        /*for (int i = 0; i < 10; i++) {
            graph.addEdge(null, v1, graph.addVertex(null), "knows");
        }
        //Iterable<Edge> edges = v1.getEdges(Direction.OUT, "knows");
        //System.out.println(graph.getGraphStore().getEdgeCount());

        Iterator<org.gephi.graph.api.Edge> eg = graph.getGraphStore().getEdges(((GephiVertex)v1).getRawVertex()).iterator();


        /*org.gephi.graph.api.Edge[] testArr = graph.getGraphStore().getEdges(((GephiVertex)v1).getRawVertex()).toArray();
        for(int i = 0; i < testArr.length; i++){
            //System.out.println(testArr[i].getSource().getLabel());
            System.out.println(testArr[i].getLabel());
            //System.out.println(testArr[i].getTypeLabel());
        }*/
        //System.out.println(testArr.length);
        /*while(eg.hasNext()){
            //System.out.println(eg.next().getTarget());
            System.out.println(eg.next());
            //System.out.println(eg.next().getLabel());
        }*/



    }



}
