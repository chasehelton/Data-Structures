package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * ShortestPathUtil class.
 * @author Chase Helton
 *
 */
public class ShortestPathUtil {
    
	/**
	 * Dijkstra's pathfinding algorithm.
	 * @param <V> vertex
	 * @param <E> edge
	 * @param g the graph
	 * @param src the vertex to start at
	 * @return a map of Dijkstra's algorithm
	 */
	public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> g, Vertex<V> src) {
		Map<Vertex<V>, Integer> d = new LinearProbingHashMap<>();
		Map<Vertex<V>, Integer> cloud = new LinearProbingHashMap<>();
		AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens = new LinearProbingHashMap<>();
		for (Vertex<V> v : g.vertices()) {
			if (v == src) {
				d.put(v, 0);
			} else {
				d.put(v, Integer.MAX_VALUE);
			}
			pqTokens.put(v, pq.insert(d.get(v), v));
		}
		while (!pq.isEmpty()) {
			Entry<Integer, Vertex<V>> entry = pq.deleteMin();
			int key = entry.getKey();
			Vertex<V> u = entry.getValue();
			cloud.put(u, key);
			pqTokens.remove(u);
			for (Edge<E> e : g.outgoingEdges(u)) {
				Vertex<V> v = g.opposite(u, e);
				if (cloud.get(v) == null) {
					int weight = e.getElement().getWeight();
					if (d.get(u) + weight < d.get(v)) {
						d.put(v, d.get(u) + weight);
						pq.replaceKey(pqTokens.get(v), d.get(v));
					}
				}
			}
		}
		return cloud;
    }
    
    /**
     * The shortest path found with Dijkstra's algorithm.
     * @param <V> vertex
     * @param <E> edge
     * @param g the graph to traverse
     * @param s the starting vertex
     * @param distances a map of distances
     * @return the map
     */
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> g, Vertex<V> s, Map<Vertex<V>, Integer> distances) {
    	Map<Vertex<V>, Edge<E>> map = new LinearProbingHashMap<>();
    	for (Vertex<V> v : distances) {
    		if (v != s) {
    			for (Edge<E> e : g.incomingEdges(v)) {
    				Vertex<V> u = g.opposite(v, e);
    				if (distances.get(v).equals(distances.get(u) + e.getElement().getWeight())) {
    					map.put(v, e);
    				}
    			}
    		}
    	}
    	return map;
    }
}
