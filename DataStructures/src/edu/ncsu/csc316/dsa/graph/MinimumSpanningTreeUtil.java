package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.disjoint_set.DisjointSetForest;
import edu.ncsu.csc316.dsa.disjoint_set.UpTreeDisjointSetForest;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * MinimumSpanningTreeUtil class.
 * @author Chase Helton
 *
 */
public class MinimumSpanningTreeUtil {
    
	/**
	 * Kruskal's algorithm.
	 * @param <V> vertex
	 * @param <E> edge
	 * @param g the graph to traverse
	 * @return the minimum spanning tree for Kruskal
	 */
    public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
    	PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
    	PriorityQueue<Integer, Edge<E>> pq = new HeapPriorityQueue<>();
    	DisjointSetForest<Vertex<V>> forest = new UpTreeDisjointSetForest<>();
    	Map<Vertex<V>, Position<Vertex<V>>> positions = new LinearProbingHashMap<>();
    	for (Vertex<V> v: g.vertices()) {
    		positions.put(v, forest.makeSet(v));
    	}
    	for (Edge<E> e : g.edges()) {
    		pq.insert(e.getElement().getWeight(), e);
    	}
    	int size = g.numVertices();
    	while (tree.size() != size - 1 && !pq.isEmpty()) {
    		Entry<Integer, Edge<E>> entry = pq.deleteMin();
    		Edge<E> edge = entry.getValue();
    		Vertex<V>[] endpoints = g.endVertices(edge);
    		Position<Vertex<V>> a = forest.find(positions.get(endpoints[0]).getElement());
    		Position<Vertex<V>> b = forest.find(positions.get(endpoints[1]).getElement());
    		if (a != b) {
    			tree.addLast(edge);
    			forest.union(a, b);
    		}
    	}
    	return tree;
    }
    
    /**
     * Prim-Jarnik's algorithm.
     * @param <V> vertex
     * @param <E> edge
     * @param g the graph to traverse
     * @return the minimum spanning tree for Prim-Jarnik
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
        AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();
        
        PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
        
        Vertex<V> src = g.vertices().iterator().next();
        
        for(Vertex<V> v : g.vertices()) {
            if(v == src) {
                weights.put(v, 0);
            } else {
                weights.put(v, Integer.MAX_VALUE);
            }
            pqEntries.put( v, q.insert(weights.get(v), v));
        }
        while(!q.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = q.deleteMin();
            Vertex<V> u = entry.getValue();
            if(connectingEdges.get(u) != null) {
                tree.addLast(connectingEdges.get(u));
            }
            known.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight();
                if(!known.contains(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    connectingEdges.put(z, e);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return tree;
    }

}