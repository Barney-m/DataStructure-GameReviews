package com.app.adt.list;

public interface ILinkedList<E>{
	boolean isEmpty();
	
	void addFirst(E value);
	
	E getFirst();
	
	E removeFirst();
	
	void addLast(E value);
	
	E getLast();
	
	void clear();
	
	boolean contains(E value);
	
	E get(int value);
	
	void insertAfter(E key,E value);
	
	void insertBefore(E key,E value);
	
	void remove(E value);
}
