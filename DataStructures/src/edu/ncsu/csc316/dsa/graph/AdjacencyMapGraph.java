package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * AdjacencyMapGraph class.
 * @author Chase Helton
 *
 * @param <V> the vertex
 * @param <E> the edge
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {

	/** List of vertices. */
    private PositionalList<Vertex<V>> vertexList;
    /** List of edges. */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Constructs an AdjacencyMapGraph.
     */
    public AdjacencyMapGraph() {
        this(false);
    }
    
    /**
     * Constructs an AdjacencyMapGraph with a parameter to determine if it is directed.
     * @param directed true if it is directed, false if not.
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        AMVertex origin = validate(v1);
        AMVertex destination = validate(v2);
        GraphEdge edge = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        origin.getOutgoing().put(v2, edge);
        destination.getIncoming().put(v1, edge);
        return edge;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        for (Edge<E> e : v.getOutgoing().values()) {
        	removeEdge(e);
        }
        for (Edge<E> e : v.getIncoming().values()) {
        	removeEdge(e);
        }
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);
        origin.getOutgoing().remove(dest);
        dest.getIncoming().remove(origin);
        return edgeList.remove(e.getPosition());
    }
    
    /**
     * AMVertex class.
     * @author Chase Helton
     *
     */
    private class AMVertex extends GraphVertex {
        
    	/** Outgoing edges from vertex. */
        private Map<Vertex<V>, Edge<E>> outgoing;
        /** Incoming edges from vertex. */
        private Map<Vertex<V>, Edge<E>> incoming;
        
        /**
         * Constructs a Vertex.
         * @param data of the vertex
         * @param isDirected tells if it is directed or not
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if(isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Returns a map of the outgoing edges.
         * @return a map of the outgoing edges
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Returns a map of the incoming edges.
         * @return a map of the incoming edges
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Validates the vertex.
     * @param v the vertex to validate
     * @return the validated vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex)v;
    }
}