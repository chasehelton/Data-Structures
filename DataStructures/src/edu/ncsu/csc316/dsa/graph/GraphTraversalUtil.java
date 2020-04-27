package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * GraphTraversalUtil class for DFS and BFS.
 * @author Chase Helton
 *
 */
public class GraphTraversalUtil {
    
	/**
	 * Algorithm for DFS traversal.
	 * @param <V> vertex
	 * @param <E> edge
	 * @param graph the graph to traverse
	 * @param start the start of the traversal
	 * @return the depth first search traversal of the graph
	 */
    public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
        Set<Vertex<V>> known = new HashSet<Vertex<V>>();
        Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
        dfsHelper(graph, start, known, forest);
        return forest;
    }
    
    /**
     * Helper method for DFS.
     * @param <V> vertex
     * @param <E> edge
     * @param graph the graph to traverse
     * @param u the vertex
     * @param known the known set of vertices
     * @param forest the map of vertices
     */
    private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(u);
        for (Edge<E> e : graph.outgoingEdges(u)) {
        	Vertex<V> v = graph.opposite(u, e);
        	if (!known.contains(v)) {
        		forest.put(v, e);
        		dfsHelper(graph, v, known, forest);
        	}
        }
    }
    
    /**
     * Algorithm for BFS traversal.
     * @param <V> vertex
     * @param <E> edge
     * @param graph the graph to traverse
     * @param start the start of the traversal
     * @return the breadth first search traversal of the graph
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	Set<Vertex<V>> known = new HashSet<Vertex<V>>();
        Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
        Queue<Vertex<V>> reachableVertices = new ArrayBasedQueue<Vertex<V>>();
        known.add(start);
        reachableVertices.enqueue(start);
        while (!reachableVertices.isEmpty()) {
        	Vertex<V> u = reachableVertices.dequeue();
        	for (Edge<E> e : graph.outgoingEdges(u)) {
        		Vertex<V> w = graph.opposite(u, e);
        		if (!known.contains(w)) {
        			known.add(w);
        			forest.put(w, e);
        			reachableVertices.enqueue(w);
        		}
        	}
        }
        return forest;
    }
}