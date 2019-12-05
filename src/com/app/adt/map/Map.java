package com.app.adt.map;

public interface Map<K,V> {
    
    public V add(K key,V value);
    
    public V remove(K key);
    
    public V get(K key);
    
    public boolean containsKey(K key);
    
    public boolean isEmpty();
    
    public boolean isFull();
    
    public int length();
    
    public void clear();
}
