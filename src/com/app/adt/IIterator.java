package com.app.adt;

public interface IIterator<E> {
    boolean hasNext();
    
    E next();
    
    void remove();
}
