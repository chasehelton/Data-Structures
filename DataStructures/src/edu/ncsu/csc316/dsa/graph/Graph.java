package edu.ncsu.csc316.dsa.graph;

/**
 * Graph interface.
 * @author Chase Helton
 *
 * @param <V> the vertex
 * @param <E> the edge
 */
public interface Graph<V, E> {
	
	/**
	 * Returns true if the graph is directed, false if not.
	 * @return true if the graph is directed, false if not
	 */
    boolean isDirected();
    
    /**
     * Returns the number of vertices.
     * @return the number of vertices
     */
    int numVertices();
    
    /**
     * Returns an iterable set of vertices.
     * @return an iterable set of vertices
     */
    Iterable<Vertex<V>> vertices();
    
    /**
     * Returns the number of edges.
     * @return the number of edges
     */
    int numEdges();
    
    /**
     * Returns an iterable set of edges.
     * @return an iterable set of edges
     */
    Iterable<Edge<E>> edges();
    
    /**
     * Returns an edge between two vertices.
     * @param vertex1 first vertex
     * @param vertex2 second vertex
     * @return an edge between two vertices
     */
    Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);
    
    /**
     * Returns the vertices on either end of an edge.
     * @param edge the edge to check
     * @return the vertices on either end of an edge
     */
    Vertex<V>[] endVertices(Edge<E> edge);
    
    /**
     * Returns the opposite vertex from a chosen vertex on the same edge.
     * @param vertex the vertex to check
     * @param edge the edge to check
     * @return the opposite vertex from a chosen vertex on the same edge
     */
    Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);
    
    /**
     * Returns the number of edges going out from a vertex.
     * @param vertex the vertex to check
     * @return the number of edges going out from a vertex
     */
    int outDegree(Vertex<V> vertex);
    
    /**
     * Returns the number of edges going in to a vertex.
     * @param vertex the vertex to check
     * @return the number of edges going in to a vertex
     */
    int inDegree(Vertex<V> vertex);
    
    /**
     * Returns an iterable set of the outgoing edges from a vertex.
     * @param vertex the vertex to check
     * @return an iterable set of the outgoing edges from a vertex
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);
    
    /**
     * Returns an iterable set of the incoming edges to a vertex.
     * @param vertex the vertex to check
     * @return an iterable set of the incoming edges to a vertex
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);
    
    /**
     * Inserts a vertex into the graph.
     * @param vertexData the data to set for the vertex
     * @return the vertex inserted
     */
    Vertex<V> insertVertex(V vertexData);
    
    /**
     * Inserts an edge between two vertices.
     * @param v1 first vertex
     * @param v2 second vertex
     * @param edgeData the data to add at the edge
     * @return the edge inserted
     */
    Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);
    
    /**
     * Removes a vertex from the graph.
     * @param vertex the vertex to remove
     * @return the vertex removed
     */
    Vertex<V> removeVertex(Vertex<V> vertex);
    
    /**
     * Removes an edge from the graph.
     * @param edge the edge to remove
     * @return the edge removed
     */
    Edge<E> removeEdge(Edge<E> edge);
    
    /**
     * Edge interface for the Graph.
     * @author Chase Helton
     *
     * @param <E> the edge
     */
    interface Edge<E> {
    	
    	/**
    	 * Returns the edge.
    	 * @return the edge
    	 */
        E getElement();
    }
    
    /**
     * Vertex interface for the Graph.
     * @author Chase Helton
     *
     * @param <V> the vertex
     */
    interface Vertex<V> {
    	
    	/**
    	 * Returns the vertex.
    	 * @return the vertex
    	 */
        V getElement();
    }
}