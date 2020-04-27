package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the GraphTraversalUtil class.
 * @author Chase Helton
 *
 */
public class GraphTraversalUtilTest {

	/**
	 * UndirectedGraph to test.
	 */
    private Graph<String, Integer> undirectedGraph;
    
    /**
     * DirectedGraph to test.
     */
    private Graph<String, Integer> directedGraph;
    
    /**
     * Sets up the tests.
     */
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Integer>();
        directedGraph = new AdjacencyListGraph<String, Integer>(true);
    }
    
    /**
     * Builds an undirected sample graph.
     */
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    /**
     * Builds an directed sample graph.
     */
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }
    
    /**
     * Tests DFS.
     */
    @Test
    public void testDFS() {
    	buildUndirectedSample();
    	buildDirectedSample();
    	Vertex<String> v = directedGraph.vertices().iterator().next();
    	Map<Vertex<String>, Edge<Integer>> m = GraphTraversalUtil.depthFirstSearch(directedGraph, v);
    	assertTrue(!m.isEmpty());
    }
    
    /**
     * Tests BFS.
     */
    @Test
    public void testBFS() {
    	buildUndirectedSample();
    	buildDirectedSample();
    	Vertex<String> v = directedGraph.vertices().iterator().next();
    	assertEquals("Raleigh", v.getElement());
    	Map<Vertex<String>, Edge<Integer>> m = GraphTraversalUtil.breadthFirstSearch(directedGraph, v);
    	assertTrue(!m.isEmpty());
    }
	
}
