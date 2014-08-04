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

        GraphModelImpl gl = new GraphModelImpl();
        GephiGraph graph = new GephiGraph(gl);

        Set<Vertex> vertices = new HashSet<Vertex>();
        for (int i = 0; i < 50; i++) {
            Vertex vertex = graph.addVertex(null);
            for (int j = 0; j < 15; j++) {
                vertex.setProperty(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            }
            vertices.add(vertex);
        }
        for (Vertex vertex : vertices) {
            //System.out.println(vertex.getPropertyKeys().size());

        }



        /*Node node = gl.factory().newNode("2");

        gl.getNodeTable().addColumn("key1",String.class);
        node.setAttribute("key1","value1");
        System.out.println(node.getAttribute("key1"));

        System.out.println(gl.getNodeTable().hasColumn("key1"));*/




    }



}
