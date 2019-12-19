package com.app.adt.list;

import com.app.adt.IIterator;

public interface IListIterator<E> extends IIterator<E> {
    boolean hasNext();
    
    E next();
    
    boolean hasPrevious();
    
    E previous();
    
    int previousIndex();
    
    void remove();
    
    void set(E data);
    
    void add(E data);
}
