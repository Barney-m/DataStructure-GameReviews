package com.app.adt.list;

import java.collection.Arrays;

public class ArrayList<E> implements IList<E>{
	
	private static final int MINIMUM_CAPACITY = 1024;
	private int size = 0;
	private E[] array = (E[])new Object[MINIMUM_CAPACITY];
	
	public boolean add(E value) {
		return add(size,value);
	}
	
	public boolean add(int index, E value) {
        if (size >= array.length) {
        	int newSize = size + (size<<1);
            array = Arrays.copyOf(array, newSize);
        }
        
        if (index==size) {
            array[size] = value;
        } else {
            // Shift the array down one spot
            System.arraycopy(array, index, array, index+1, size - index);
            array[index] = value;
        }
        size++;
        return true;
    }
	
	public boolean remove(E value) {
		for(int i = 0;i < size;i++) {
			E obj = array[i];
			if(obj.equals(value)) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public E remove(int index) {
		if(index < 0 || index >= size) return null;
		
		E value = array[index];
		
		if(index != --size) {
			System.arraycopy(array, index + 1, array, index, size - index);
		}
		array[size] = null;
		
		int shrinkSize = array.length >> 1;
		if(shrinkSize >= MINIMUM_CAPACITY && size < shrinkSize) {
			int newSize = array.length>> 1;
        	array = Arrays.copyOf(array, newSize);
		}
		
		return value;	
	}
	
	public E set(int index,E value) {
		if(index < 0 || index >= size) return null;
		E element = array[index];
		array[index] = value;
		return element;
	}
	
	public E get(int index) {
		if(index < 0 || index >= size) return null;
		return array[index];
	}
        
        public int length(){
            return this.size;
        }
        
        public boolean isEmpty(){
            return this.size == 0;
        }
	
	public void clear() {
		size = 0;
	}
	
	public boolean contains(E value) {
		for (int i = 0; i < size; i++) {
            E obj = array[i];
            if (obj.equals(value)) return true;
        }
        return false;
	}
	
	public int size() {
        return size;
    }
	
	public boolean validate() {
        int localSize = 0;
        for (int i = 0;i < array.length;i++) {
            E element = array[i];
            if (i<size) {
                if (element == null) return false;
                localSize++;
            } else {
                if (element != null) return false;
            }
        }
        return (localSize == size);
    }
	
	public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(array[i]).append(", ");
        }
        return builder.toString();
    }
	
}