package com.app.adt.set;


public interface ISet<E>{
    public int length();
    
    public boolean isEmpty();
    
    public boolean contains(E o);
    
    public boolean add(E data);
    
    public boolean remove(E o);
    
    public void clear();
}
