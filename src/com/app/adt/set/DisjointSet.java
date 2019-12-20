package com.app.adt.set;

import com.app.adt.map.HashMap;

public class DisjointSet<E> {
    
    private DisjointSet(){}
    
    private final HashMap<E,Node> objectsToNodes = new HashMap<E,Node>();
    
    public void makeSet(E data){
        final Node node = new Node(null,data);
        node.parent = node;
        objectsToNodes.put(data, node);
    }
    
    public E findSet(E data){
        DisjointSet.Node node = objectsToNodes.get(data);
        Node tmp = findSet(node);
        return (E) tmp.value;
    }
    
    private Node findSet(Node node){
        if(node == null)
            return null;
        if(node != node.parent)
            node.parent = findSet(node.parent);
        return node.parent;
    }
    
    public void removeSet(E data){
        E set = findSet(data);
        if(set == null)
            return;
        objectsToNodes.remove(data);
    }
    
    
            
    
    
    public class Node<E>{
        private Node<E> parent;
        private E value;
        private int rank;
        
        public Node(Node<E> parent,E value){
            this.parent = parent;
            this.value = value;
            this.rank = 0;
        }
        
        public E getValue(){
            return value;
        }
        
        public int getRank(){
            return rank;
        }
        
        public boolean equals(Object o){
            if(!(o instanceof Node))
                return false;
            
            final Node<E> i = (Node<E>)o;
            if((i.parent != null && parent != null) && !(i.parent.value.equals(parent.value)))
                return false;
            if((i.value != null && value != null) && !(i.value.equals(value)))
                return false;
            return true;
        }
        
        public String toString(){
            return "parent = " + (parent != null ? parent.value : null) + " " + (rank > 0 ? "rank = " + rank + " " : " ") + "value = " + (value != null ? value : null);
        }
    }
}
