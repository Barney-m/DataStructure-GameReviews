package com.app.adt.set;

final class DisjointSetNode<E>
    implements Comparable<DisjointSetNode<E>>
{

    /**
     * The stored node value.
     */
    private final E element;

    /**
     * The {@code DisjointSetNode} parent node, {@code this} by default.
     */
    private DisjointSetNode<E> parent = this;

    /**
     * The current node rank.
     */
    private Integer rank = 0;

    /**
     * Creates a new {@link DisjointSet} node with the given value.
     *
     * @param element the node value has to be stored.
     */
    public DisjointSetNode( E element )
    {
        this.element = element;
    }

    /**
     * Returns the adapted element by this node.
     *
     * @return the adapted element by this node.
     */
    public E getElement()
    {
        return element;
    }

    /**
     * Returns the reference to the parent node, the node itself by default.
     *
     * @return the reference to the parent node, the node itself by default.
     */
    public DisjointSetNode<E> getParent()
    {
        return parent;
    }

    /**
     * Sets the reference to a new parent node.
     *
     * @param parent the reference to a new parent node.
     */
    public void setParent( DisjointSetNode<E> parent )
    {
        this.parent = parent;
    }

    /**
     * Returns this node rank.
     *
     * @return this node rank
     */
    public Integer getRank()
    {
        return rank;
    }

    /**
     * Increases this node rank.
     */
    public void increaseRank()
    {
        rank++;
    }

    /**
     * Sets a new different rank.
     *
     * @param rank the new rank to this node.
     */
    public void setRank( int rank )
    {
        this.rank = rank;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo( DisjointSetNode<E> o )
    {
        return rank.compareTo( o.getRank() );
    }

}

