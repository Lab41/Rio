package com.test.bptutorial;

//import com.tinkerpop.blueprints.impls.GraphTest;
import com.test.bptest.TestSuite;
import com.test.bptest.VertexTestSuite;
import com.test.bptest.EdgeTestSuite;
import com.test.bptest.*;
import com.test.bptest.util.io.graphml.GraphMLReaderTestSuite;
import com.test.bptest.util.io.graphson.GraphSONReaderTestSuite;
import com.tinkerpop.blueprints.*;
import com.test.bptest.impls.GraphTest;

import java.lang.reflect.Method;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import org.gephi.graph.store.GraphFactoryImpl;
import org.gephi.graph.store.GraphModelImpl;
import org.gephi.graph.store.GraphStore;
//import com.tinkerpop.blueprints.util.io.gml.GMLReaderTestSuite;
//import com.tinkerpop.blueprints.util.io.graphml.GraphMLReaderTestSuite;
//import com.tinkerpop.blueprints.util.io.graphson.GraphSONReaderTestSuite;
import com.test.bptest.util.io.gml.GMLReaderTestSuite;
/**
 * Created by aganesh on 7/24/14.
 */
public class TinkerTestSuite extends GraphTest {
    public void testVertexTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new VertexTestSuite(this));
        printTestPerformance("VertexTestSuite", this.stopWatch());
    }

    public void testEdgeTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new EdgeTestSuite(this));
        printTestPerformance("EdgeTestSuite", this.stopWatch());
    }

    public void testGraphTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GraphTestSuite(this));
        printTestPerformance("GraphTestSuite", this.stopWatch());
    }

    /*public void testKeyIndexableGraphTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new KeyIndexableGraphTestSuite(this));
        printTestPerformance("KeyIndexableGraphTestSuite", this.stopWatch());
    }*/

    /*public void testIndexableGraphTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new IndexableGraphTestSuite(this));
        printTestPerformance("IndexableGraphTestSuite", this.stopWatch());
    }*/

    /*public void testIndexTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new IndexTestSuite(this));
        printTestPerformance("IndexTestSuite", this.stopWatch());
    }*/

    public void testGraphMLReaderTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GraphMLReaderTestSuite(this));
        printTestPerformance("GraphMLReaderTestSuite", this.stopWatch());
    }

    public void testGMLReaderTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GMLReaderTestSuite(this));
        printTestPerformance("GMLReaderTestSuite", this.stopWatch());
    }

    public void testGraphSONReaderTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GraphSONReaderTestSuite(this));
        printTestPerformance("GraphSONReaderTestSuite", this.stopWatch());
    }

    public Graph generateGraph() {
        GraphModelImpl gl = new GraphModelImpl();
        //GraphStore gp = new GraphStore(gl);
        //GraphFactoryImpl gf = new GraphFactoryImpl(gp);
        GephiGraph graph = new GephiGraph(gl);
        return graph;
    }

    public Graph generateGraph(String str){
        return generateGraph();
    }

    public void doTestSuite(final TestSuite testSuite) throws Exception {
        String doTest = System.getProperty("testGephiGraph");
        if (doTest == null || doTest.equals("true")) {
            for (Method method : testSuite.getClass().getDeclaredMethods()) {
                if (method.getName().startsWith("test")) {
                    System.out.println("Testing " + method.getName() + "...");
                    method.invoke(testSuite);
                }
            }
        }
    }
}
