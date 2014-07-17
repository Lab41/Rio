package com.test.bptutorial;

/**
 * Created by aganesh on 7/15/14.
 */

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Direction;
import org.gephi.graph.store.GraphStore;
import org.gephi.graph.store.GraphModelImpl;


public class HellowWorld {
    public static void main(String[] args) {
        GraphModelImpl gl = new GraphModelImpl();
        GraphStore gp = new GraphStore(gl);
        GephiGraph graph = new GephiGraph(gp);
        graph.addVertex("HI");
        System.out.print(gp.getNodeCount());
    }
}
