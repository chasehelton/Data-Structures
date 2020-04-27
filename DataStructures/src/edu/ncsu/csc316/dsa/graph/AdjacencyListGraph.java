package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * AdjacencyListGraph class.
 * @author Chase Helton
 *
 * @param <V> the vertex
 * @param <E> the edge
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {

	/** List of vertices. */
    private PositionalList<Vertex<V>> vertexList;
    /** List of edges. */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Constructor for the AdjacencyListGraph.
     */
    public AdjacencyListGraph() {
        this(false);
    }
    
    /**
     * Constructor for the AdjacencyListGraph that defines if it is directed.
     * @param directed true if the graph is directed, false if not
     */
    public AdjacencyListGraph(boolean directed) {
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
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing();
    }
    
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
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
    public Vertex<V> insertVertex(V vertexData) {
        ALVertex vertex = new ALVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
        
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        ALVertex origin = validate(v1);
        ALVertex destination = validate(v2);       
        ALEdge edge = new ALEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        edge.setOutgoingListPosition(origin.getOutgoing().addLast(edge));
        edge.setIncomingListPosition(destination.getIncoming().addLast(edge));
        return edge;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        ALVertex v = validate(vertex);
        for (Edge<E> e : outgoingEdges(v)) {
        	removeEdge(e);
        }
        for (Edge<E> e : incomingEdges(v)) {
        	removeEdge(e);
        }
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        ALEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        ALVertex origin = validate(ends[0]);
        ALVertex dest = validate(ends[1]);
        dest.getOutgoing().remove(e.getOutgoingListPosition());
        origin.getIncoming().remove(e.getIncomingListPosition());
        return edgeList.remove(e.getPosition());
    }
    
    /**
     * Private inner class for the AdjacencyList vertex.
     * @author Chase Helton
     *
     */
    private class ALVertex extends GraphVertex {
        
    	/** List of outgoing edges from a vertex. */
        private PositionalList<Edge<E>> outgoing;
        /** List of incoming edges to a vertex. */
        private PositionalList<Edge<E>> incoming;
        
        /**
         * Constructs a vertex.
         * @param data the data at a vertex
         * @param isDirected determines if the graph is directed or not
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if(isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Returns the list of outgoing edges.
         * @return the list of outgoing edges
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Returns the list of incoming edges.
         * @return the list of incoming edges
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Private inner class for the AdjacencyList edge.
     * @author Chase Helton
     *
     */
    private class ALEdge extends GraphEdge {    
    	
    	/** List of outgoing edges. */
        private Position<Edge<E>> outgoingListPosition;
        /** List of incoming edges. */
        private Position<Edge<E>> incomingListPosition;
        
        /**
         * Constructs an edge.
         * @param element the element of the edge
         * @param v1 the first vertex to add between
         * @param v2 the second vertex to add between
         */
        public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            super(element, v1, v2);
        }
        
        /**
         * Returns a position of the outgoing edge.
         * @return a position of the outgoing edge
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }
        
        /**
         * Sets the outgoing position of the edge.
         * @param outgoingListPosition the position to set
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }
        
        /**
         * Returns a position of the incoming edge.
         * @return a position of the incoming edge
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }
        
        /**
         * Sets the incoming position of the edge.
         * @param incomingListPosition the position to set
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }
    }

    /**
     * Validates a vertex.
     * @param v the vertex to validate
     * @return the validated vertex
     */
    private ALVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex)v;
    }
    
    /**
     * Validates an edge.
     * @param e the edge to validate
     * @return the validated edge
     */
    protected ALEdge validate(Edge<E> e) {
        if(!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge)e;
    }
}