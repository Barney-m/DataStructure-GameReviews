package com.app.adt.list;

import java.util.Iterator;

public interface ILinkedList<E>{
	public E add(E data, int index);


	public E add(E data);

	public E remove(int index);


	public E remove();


	public void removeDuplicateNodes();


	public E get(int k);

	public void reverse();

	public boolean isEmpty();


	public String toString();

	

	public int size();
        
        Iterator<E> iterator();
}
