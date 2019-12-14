package com.app.adt.set;

import com.app.adt.ICollection;

public interface ISet<E> extends ICollection<E>{
    public void add(E data);
    
    public void addAll(ICollection<? extends E> data);
    
    public boolean remove(Object o);
    
    public void clear();
    
    public boolean contains(Object o);
    
    public boolean isEmpty();
    
    public int length();
    
    public Object[] toArray();
}
