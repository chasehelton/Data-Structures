package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the ShortestPathUtil class.
 * @author Chase Helton
 *
 */
public class ShortestPathUtilTest {

	/**
	 * Private inner class to test dijkstra's algorithm.
	 * @author Chase Helton
	 *
	 */
    private class Highway implements Weighted {
        
    	/** Name of the highway. */
    	private String name;
    	
    	/** Length of the highway. */
        private int length;
        
        /**
         * Constructs a highway.
         * @param n name
         * @param l length
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }

        /**
         * Sets the name.
         * @param name name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Returns the length.
         * @return the length
         */
        public int getLength() {
            return length;
        }

        /**
         * Sets the length.
         * @param length the length to set
         */
        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
    
    /** Graph to test. */
    private Graph<String, Highway> undirectedGraph;
    
    /**
     * Sets up the tests.
     */
    @Before
    public void setUp() {
    	undirectedGraph = new AdjacencyListGraph<String, Highway>(false);
	}
    
    /**
     * Tests Dijkstra's algorithm.
     */
    @Test
    public void testDijkstra() {
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1,  v2,  new Highway("1", 1));
        undirectedGraph.insertEdge(v1,  v2,  new Highway("2", 2));
        undirectedGraph.insertEdge(v3,  v4,  new Highway("3", 3));
        undirectedGraph.insertEdge(v3,  v2,  new Highway("4", 4));
    	undirectedGraph.insertEdge(v1,  v5,  new Highway("5", 5));
    	
    	Map<Vertex<String>, Integer> distances = ShortestPathUtil.dijkstra(undirectedGraph, v1);
    	
    	assertFalse(ShortestPathUtil.dijkstra(undirectedGraph, v1).isEmpty());
    	assertFalse(ShortestPathUtil.shortestPathTree(undirectedGraph, v1, distances).isEmpty());
    }
	
	
}
