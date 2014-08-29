package org.lab41.bptutorial;

import com.tinkerpop.blueprints.*;
//import com.tinkerpop.blueprints.impls.GraphTest;
import org.lab41.bptutorial.impls.GraphTest;


import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public abstract class TestSuite extends BaseTest {

    protected GraphTest graphTest;

    public TestSuite() {
    }

    public TestSuite(final GraphTest graphTest) {
        this.graphTest = graphTest;
    }

    protected void vertexCount(final Graph graph, int expectedCount) {
        if (graph.getFeatures().supportsVertexIteration) {
            assertEquals(count(graph.getVertices()), expectedCount);
        }
    }

    protected void containsVertices(final Graph graph, final Collection<Vertex> vertices) {
        for (Vertex v : vertices) {
            Vertex vp = graph.getVertex(v.getId());
            if (vp == null || !vp.getId().equals(v.getId())) {
                fail("Graph does not contain vertex: '" + v + "'");
            }
        }
    }

    protected void edgeCount(final Graph graph, int expectedCount) {
        if (graph.getFeatures().supportsEdgeIteration) assertEquals(count(graph.getEdges()), expectedCount);
    }


}
