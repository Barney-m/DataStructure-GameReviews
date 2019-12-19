package com.app.adt.set;

import com.app.adt.map.IMap;
import java.util.Objects;

public class HashSet<K,V> implements IMap<K,V> {

    Node<K,V>[] entry;
    private final float loadFactor;
    private int threshold;
    private int size;
    
    public HashSet(){
        this.loadFactor = 0.75f;
    }
    
    public HashSet(int capacity){
        this.loadFactor = 0.75f;
        this.threshold = tableSizeFor(capacity);
    }
    
    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V get(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private final void resize(){
        Node<K,V>[] tmp = entry;
        int tmpCapacity = (tmp == null) ? 0 : tmp.length;
        int tmpThreshold = threshold;
        
        int capacity;
        
        if(tmpCapacity > 0){
            capacity = tmpCapacity << 1;
            threshold = tmpThreshold << 1;
        }
        else if(tmpThreshold > 0){
            capacity = tmpCapacity;
            threshold = (int)(capacity * loadFactor);
        }
        else{
            capacity = 16;
            threshold = (int)(capacity * loadFactor);
        }
    }
    
    private static final int hash(Object key){
        return (key == null) ? 0 : key.hashCode();
    }
    
    private static class Node<K,V>{
        
        private final int hash;
        private final K key;
        private V value;
        private Node<K,V> prev;
        private Node<K,V> next;
        
        public Node(int hash,K key,V value){
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
        
        public final K getKey(){
            return key;
        }
        
        public final V getValue(){
            return value;
        }
        
        public final boolean duplicateCheck(Object o){
            if(o == this)
                return true;
            
            if(o instanceof Node){
                Node<?,?> cur = (Node<?,?>)o;
                if(Objects.equals(this.key, cur.getKey()) && Objects.equals(this.value, cur.getValue()))
                    return true;
            }
            return false;
        }
    }
    
}
