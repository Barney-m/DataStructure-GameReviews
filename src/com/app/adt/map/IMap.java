package com.app.adt.map;


public interface IMap<K,V> {
    public V put(K key,V value);
    
    /*
        void putAll(IMap<? extends K, ? extends V> m);
    */
    public V remove(K key);
    
    public V get(K key);
    
    public boolean containsKey(K key);
    
    public boolean isEmpty();
    
    public int length();
    
    public boolean containsValue(Object value);
    
    public void clear();
    
    interface Entry<K,V>{
        K getKey();
        V getValue();
        V setValue(V value);
        boolean equals(Object o);
        int hashCode();
    }
}
