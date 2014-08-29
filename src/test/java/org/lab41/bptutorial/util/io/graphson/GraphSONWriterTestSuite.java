package org.lab41.bptutorial.util.io.graphson;

import com.tinkerpop.blueprints.Graph;
//import com.tinkerpop.blueprints.TestSuite;
//import com.tinkerpop.blueprints.impls.GraphTest;
import org.lab41.bptutorial.TestSuite;
import org.lab41.bptutorial.impls.GraphTest;
import com.tinkerpop.blueprints.util.io.graphson.GraphSONMode;
import com.tinkerpop.blueprints.util.io.graphson.GraphSONReader;
import com.tinkerpop.blueprints.util.io.graphson.GraphSONWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class GraphSONWriterTestSuite extends TestSuite {
    public GraphSONWriterTestSuite() {
    }

    public GraphSONWriterTestSuite(GraphTest graphTest) {
        super(graphTest);
    }

    public void testGratefulGraphNormalized() throws Exception {
        Graph graph = this.graphTest.generateGraph();
        if (graph.getFeatures().supportsEdgeIteration && !graph.getFeatures().ignoresSuppliedIds) {
            this.stopWatch();
            final String readGraphSON = readFile(GraphSONReader.class.getResourceAsStream("graph-example-2-normalized.json"), Charset.forName("UTF-8"));
            new GraphSONReader(graph).inputGraph(GraphSONReader.class.getResourceAsStream("graph-example-2-normalized.json"));

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new GraphSONWriter(graph).outputGraph(baos, null, null, GraphSONMode.NORMAL, true);
            final String writtenGraphSON = new String(baos.toByteArray());

            assertEquals(readGraphSON, writtenGraphSON);
        }
        graph.shutdown();
    }

    static String readFile(final InputStream inputStream, final Charset encoding) throws IOException {
        byte[] encoded = toByteArray(inputStream);
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    static byte[] toByteArray(final InputStream is) throws IOException{
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();
        while(reads != -1){
            baos.write(reads);
            reads = is.read();
        }

        return baos.toByteArray();
    }
}
