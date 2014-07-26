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
import org.gephi.graph.store.GraphStore;
import org.gephi.graph.store.GraphModelImpl;
import com.tinkerpop.blueprints.VertexTestSuite;
import com.tinkerpop.blueprints.TestSuite;

import java.lang.reflect.Method;
import java.lang.reflect.*;
import java.lang.Class;
import java.util.*;


public class HellowWorld {




    public static void main(String[] args) {
        GraphModelImpl gl = new GraphModelImpl();
        GraphStore gp = new GraphStore(gl);
        //GephiGraph graph = new GephiGraph(gp);

        Graph graph = new GephiGraph(gp);

        Vertex v1 = graph.addVertex("1");
        Vertex v2 = graph.addVertex("2");
        graph.addEdge(null, v1, v2,"knows");

        graph.removeVertex(v1);
        //System.out.println(graph.getVertices());


        //Iterator<Vertex> it = graph.getVertices().iterator();
        //while(it.hasNext()) { System.out.println(it.next()); }


    }



}
