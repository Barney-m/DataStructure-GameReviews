package com.app.adt.list;

import java.util.Arrays;
import com.app.adt.IComparable;
import com.app.adt.IComparator;

public class ArrayList<E> implements IList<E>{
	
	private static final int MINIMUM_CAPACITY = 1024;
	private int size = 0;
	private transient E[] array = (E[])new Object[MINIMUM_CAPACITY];
	
	public boolean add(E value) {
		return add(size,value);
	}
	
	public boolean add(int index, E value) {
        if (size >= array.length) {
            doubleArray();
            /*
            int newSize = size + (size<<1);
            array = Arrays.copyOf(array, newSize);
            */
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
        
        private void doubleArray(){
            E[] tmp = array;
            int tmpSize = array.length;
            
            array = (E[])new Object[2 * tmpSize];
            
            System.arraycopy(tmp, 0, array, 0, tmpSize);
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

    @Override
    public Object[] toArray() {
       return Arrays.copyOf(array, size);
    }
    
    public Object[] sort(Object[] data){
        for(int i = 1;i < data.length;i++){
            Object tmp = data[i];
            int j;
            for(j = i;j > 0 && data[j - 1].toString().compareTo(tmp.toString()) > 0;j--){
                data[j] = data[j - 1];
            }
            data[j] = tmp;
        }
        return data;
    }

        
       
}