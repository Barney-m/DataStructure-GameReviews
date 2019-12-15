/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.adt.queue;

import java.collection.Arrays;

public class ArrayQueue<E> implements IQueue<E>{
	
	private static final int DEFAULT_CAPACITY = 10;
	private E store[];
	private int front,rear;
	private int size = 0;
	
	public ArrayQueue() {
		front = 0;
		rear = -1;
		store = (E[])new Object[DEFAULT_CAPACITY];
	}
	
	public ArrayQueue(int capacity) {
		store = (E[])new Object[capacity];
	}

	@Override
	public void add(E value) {
		if(size >= store.length) {
                    doubleArray();
                    /*
			int newSize = size + (size >> 1);
			store = Arrays.copyOf(store,newSize);
                    */
		}
		
		if(rear == store.length) {
			int x = 0;
			
			for(int i = front;i <= rear;i++) {
				store[x++] = store[i];
			}
			
			front = 0;
			rear = size -1;
		}
		
		store[++rear] = value;
		size++;
	}

	@Override
	public E remove() {
		if(isEmpty())
			return null;
		
		E values = store[front];
		store[front] = null;
		front++;
		size--;


		return values;
	}
	
	@Override
	public E element() {
		if(isEmpty())
			return null;
		
		return store[front];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
        
        private void doubleArray(){
            E[] tmp = store;
            int tmpSize = store.length;
            
            store = (E[])new Object[2 * tmpSize];
            
            System.arraycopy(tmp, 0, store, 0, tmpSize);
        }
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
		
		for(int i = 0;i < store.length;i++) {
			if(store[i] != null) {
				builder.append(store[i]);
				
				builder.append(", ");
			}
		}
		
		builder.append("]");
		
		return builder.toString();
	}
	
}

