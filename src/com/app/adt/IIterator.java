package com.app.adt;

//Author: Ng Jun Jet

public interface IIterator<E> {
    boolean hasNext();
    
    E next();
    
    void remove();
}
