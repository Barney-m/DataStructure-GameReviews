package com.app.adt;

public interface ICollection<E>{
    
    public void add(E data);
    
    public void clear();
    
    public int hashCode();
    
    boolean contains(Object o);
    
    IIterator<E> iterator();
    
    public boolean isEmpty();
    
    public boolean remove(Object o);
    
    public int length();
    
    public Object[] toArray();
}
