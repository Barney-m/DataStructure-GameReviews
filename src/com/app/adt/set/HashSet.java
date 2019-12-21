package com.app.adt.set;

import com.app.adt.map.HashMap;

public class HashSet<E> implements ISet<E>{
    private transient HashMap<E, Object> map;
    
    private static final Object PRESENT = new Object();
    
    public HashSet(){
        map = new HashMap<>();
    }
    
    public HashSet(int initialCapacity){
        map = new HashMap<>(initialCapacity);
    }
    
    public int length(){
        return map.length();
    }
    
    public boolean isEmpty(){
        return map.isEmpty();
    }
    
    public boolean contains(E o){
        return map.containsKey(o);
    }
    
    public boolean add(E data){
        return map.put(data, PRESENT) == null;
    }
    
    public boolean remove(E o){
        return map.remove(o) == PRESENT;
    }
    
    public void clear(){
        map.clear();
    }
}
