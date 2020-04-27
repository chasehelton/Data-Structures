package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * Abstract class for the Graph.
 * @author Chase Helton
 *
 * @param <V> the vertex
 * @param <E> the edge
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    
	/**
	 * Boolean to return if the Graph is directed or not.
	 */
    private boolean isDirected;
    
    /**
     * Constructor for the Graph.
     * @param directed true if directed, false if not
     */
    public AbstractGraph(boolean directed) {
        setDirected(directed);
    }
    
    /**
     * Sets the directed boolean.
     * @param directed the boolean to set
     */
    private void setDirected(boolean directed) {
        isDirected = directed;
    }
    
    /**
     * Getter for the isDirected boolean.
     * @return the isDirected boolean
     */
    public boolean isDirected() {
        return isDirected;
    }
    
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        return validate(edge).getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if(ends[0] == vertex) {
            return ends[1];
        }
        if(ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }
    
    /**
     * Inner class representing a Vertex of a Graph.
     * @author Chase Helton
     *
     */
    protected class GraphVertex implements Vertex<V> {
    	/** The element of the vertex. */
        private V element;
        /** The position of the vertex. */
        private Position<Vertex<V>> position;
        
        /**
         * Constructor for the Vertex.
         * @param element the element of the vertex
         */
        public GraphVertex(V element) {
            setElement(element);
        }
        
        /**
         * Sets the element of a Vertex.
         * @param element the element to set
         */
        public void setElement(V element) {
            this.element = element;
        }
        
        /**
         * Returns the element of the Vertex.
         * @return the element of the Vertex
         */
        public V getElement() {
            return element;
        }
        
        /**
         * Returns the position of the Vertex.
         * @return the position of the Vertex
         */
        public Position<Vertex<V>> getPosition(){
            return position;
        }
        
        /**
         * Sets the position of the Vertex.
         * @param pos the position to set
         */
        public void setPosition(Position<Vertex<V>> pos) {
            position = pos;
        }
    }
    
    /**
     * Inner class representing a Edge of a Graph.
     * @author Chase Helton
     *
     */
    protected class GraphEdge implements Edge<E> {
    	
    	/** Element of the Edge. */
        private E element;
        /** End points of the Edge. */
        private Vertex<V>[] endpoints;
        /** Position of the Edge. */
        private Position<Edge<E>> position;
        
        /**
         * Constructs an Edge between two vertices.
         * @param element the element to set
         * @param v1 first vertex
         * @param v2 second vertex
         */
        @SuppressWarnings("unchecked")
        public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            setElement(element);
            endpoints = (Vertex<V>[])new Vertex[]{v1, v2};
        }
        
        /**
         * Sets the element.
         * @param element the element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * Returns the element.
         * @return the element
         */
        public E getElement() {
            return element;
        }
        
        /**
         * Returns the end points of an edge.
         * @return the end points of an edge
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }
        
        /**
         * Returns the position of an edge.
         * @return the position of an edge
         */
        public Position<Edge<E>> getPosition(){
            return position;
        }
        
        /**
         * Sets the position of an edge.
         * @param pos the position to set
         */
        public void setPosition(Position<Edge<E>> pos) {
            position = pos;
        }
        
        @Override
        public String toString() {
            return "Edge[element=" + element + "]";
        }
    }
    
    /**
     * Validate the edge.
     * @param e edge to validate
     * @return the validated edge
     */
    protected GraphEdge validate(Edge<E> e) {
        if (!(e instanceof AbstractGraph.GraphEdge)) {
            throw new IllegalArgumentException("Edge is not a valid graph edge.");
        }
        return (GraphEdge) e;
    }
}