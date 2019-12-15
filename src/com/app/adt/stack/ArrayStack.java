package com.app.adt.stack;

import java.collection.Arrays;

public class ArrayStack<T> implements IStack<T>{
	
	private static final int DEFAULT_CAPACITY = 10;
	private T[] store;
	private int size = 0;
	private int capacity;
	
	public ArrayStack() {
		capacity = DEFAULT_CAPACITY;
		store = (T[])new Object[DEFAULT_CAPACITY];
	}
	
	public ArrayStack(int capacity) {
		this.capacity = capacity;
		store = (T[])new Object[capacity];
	}

	@Override
	public boolean push(T value) {
		if(size >= store.length) {
                    doubleArray();
                    /*
			int newSize = size + (size >> 1);
			store = Arrays.copyOf(store,newSize);
                    */
		}
		
		store[size++] = value;
		return true;
	}

	@Override
	public T pop() {
		if(size <= 0)
			return null;
		
		T value = store[--size];
		store[size] = null;
		
		int reducedSize = size;
		
		if(size >= capacity && size < (reducedSize + (reducedSize << 1))) {
			System.arraycopy(store, 0, store, 0, size);
		}
		return value;
	}

	@Override
	public boolean contains(T value) {
		for(int i = 0;i < size;i++) {
			T element = store[i];
			
			if(element.equals(value))
				return true;
		}
		
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for(int i = 0;i < size;i++) {
			store[i] = null;
		}
		
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
        
        private void doubleArray(){
            T[] tmp = store;
            int tmpSize = store.length;
            
            store = (T[])new Object[2 * tmpSize];
            
            System.arraycopy(tmp, 0, store, 0, tmpSize);
        }
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
		
		for(int i = size - 1;i >= 0;i--) {
			builder.append(store[i]);
			
			if(i > 0)
				builder.append(", ");
		}
		
		builder.append("]");
		
		return builder.toString();
	}
}
