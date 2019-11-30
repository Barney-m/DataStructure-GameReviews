package com.app.adt.queue;

public interface IQueue<E>{
	void add(E value);
	
	E remove();
	
	E element();
	
	boolean isEmpty();
	
	int size();
}
