package com.app.adt.list;

public interface IList<E> {
	boolean isEmpty();

	int length();

	E get(int index);

	E set(int index, E element);

	boolean add(int index, E element);

	boolean add(E element);
        
                  int size();

	E remove(int index);

	void clear();
        
                  Object[] toArray();
}