package com.app.adt.set;

import com.app.adt.map.HashMap;
import com.app.adt.map.IMap;

public final class DisjointSet<E>
{

    /**
     * The map to store the related {@link DisjointSetNode} for each added element.
     */
    private final IMap<E, DisjointSetNode<E>> disjointSets = new HashMap<E, DisjointSetNode<E>>();

    /**
     * Performs the {@code find} operation applying the <i>path compression</i>.
     *
     * @param e the element has to be find in this {@code DisjointSet} instance
     * @return the value found 
     */
    public E find( E e )
    {
        DisjointSetNode<E> node = find( getNode( e ) );

        if ( node == node.getParent() )
        {
            return node.getElement();
        }

        node.setParent( find( node.getParent() ) );

        return node.getParent().getElement();
    }

    /**
     * Performs the @code{ find} operation by applying the <i>path compression</i>.
     *
     * @param node the input DisjointSet node for the @code{ find} operation
     * @return the root node of the path
     */
    private DisjointSetNode<E> find( DisjointSetNode<E> node )
    {
        if ( node == node.getParent() )
        {
            return node;
        }
        return find( node.getParent() );
    }

    /**
     * Join two subsets into a single subset, performing the merge by applying the <i>union by rank</i>.
     *
     * @param e1 the first element which related subset has to be merged
     * @param e2 the second element which related subset has to be merged
     */
    public void union( E e1, E e2 )
    {
        DisjointSetNode<E> e1Root = find( getNode( e1 ) );
        DisjointSetNode<E> e2Root = find( getNode( e2 ) );

        if ( e1Root == e2Root )
        {
            return;
        }

        int comparison = e1Root.compareTo( e2Root );
        if ( comparison < 0 )
        {
            e1Root.setParent( e2Root );
        }
        else if ( comparison > 0 )
        {
            e2Root.setParent( e1Root );
        }
        else
        {
            e2Root.setParent( e1Root );
            e1Root.increaseRank();
        }
    }

    /**
     * Retrieves the {@code DisjointSetNode} from the {@link #disjointSets},
     * if already previously set, creates a new one and push it in {@link #disjointSets} otherwise.
     *
     * @param e the element which related subset has to be returned
     * @return the input element {@code DisjointSetNode}
     */
    private DisjointSetNode<E> getNode( E e )
    {
        DisjointSetNode<E> node = disjointSets.get( e );

        if ( node == null )
        {
            node = new DisjointSetNode<E>( e );
            disjointSets.put( e, node );
        }

        return node;
    }

}
