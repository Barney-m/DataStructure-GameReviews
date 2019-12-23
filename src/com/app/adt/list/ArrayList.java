package com.app.adt.list;

import java.util.Arrays;
import com.app.adt.IComparable;
import com.app.adt.IComparator;
import com.app.adt.IIterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList<E> implements IList<E>{
	
	private static final int MINIMUM_CAPACITY = 1024;
	private int size;
	private transient E[] array;
        
        public ArrayList(){
            this(25);
        }
        
        public ArrayList(int initialCapacity){
            size = 0;
            array = (E[])new Object[initialCapacity];
        }
	
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
//		if(index < 0 || index >= size) return null;
//		
//		E value = array[index];
//		
//		if(index != --size) {
//			System.arraycopy(array, index + 1, array, index, size - index);
//		}
//		array[size] = null;
//		
//		int shrinkSize = array.length >> 1;
//		if(shrinkSize >= MINIMUM_CAPACITY && size < shrinkSize) {
//			int newSize = array.length>> 1;
//        	array = Arrays.copyOf(array, newSize);
//		}
//		
//		return value;	
                    E result = null;

                        if ((index >= 1) && (index <= size)) {
                          result = array[index - 1];

                          if (index < size) {
                            removeGap(index);
                          }

                          size--;
                        }

                        return result;
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
            for(int i = 0;i < array.length;i++)
                array[i] = null;
            size = 0;
	}
	
	public boolean contains(E value) {
            for (int i = 0; i < size; i++) {
                E obj = array[i];
                if (obj.equals(value)) return true;
            }
            return false;
	}
        
        private void doubleArray(){
            E[] tmp = array;
            int tmpSize = array.length;
            
            array = (E[])new Object[2 * tmpSize];
            
            System.arraycopy(tmp, 0, array, 0, tmpSize);
        }
        
        private void removeGap(int index) {
	  // move each entry to next lower position starting at entry after the
    // one removed and continuing until end of list
            int removedIndex = index - 1;
            int lastIndex = size - 1;

            for (int i = removedIndex; i < lastIndex; i++) {
              array[i] = array[i + 1];
            }
          }
	
	
	public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(array[i]);
			
			if(i + 1 < size)
				builder.append(",");
        }
        return builder.toString();
    }

    @Override
    public Object[] toArray() {
       return Arrays.copyOf(array, size);
    }
    
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] data) {
        if(data.length < size)
            return (E[])Arrays.copyOf(array, size, data.getClass());
        System.arraycopy(array, 0, data, 0, size);
        if(data.length > size)
            data[size] = null;
        return data;
    }
    
    @Override
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
    
    public IListIterator<E> listIterator(int index){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);
        return new ListItr(index);
    }
    
    public IListIterator<E> listIterator(){
        return new ListItr(0);
    }
    
    public IIterator<E> iterator(){
        return new Itr();
    }

    
    
    private class Itr implements IIterator<E>{
        int cursor; //index of next element to return
        int lastReturn = -1; // index of last element returned; -1 if no such
        
        public boolean hasNext(){
            return cursor != size;
        }
        
        @SuppressWarnings("unchecked")
        public E next(){
            int i = cursor;
            if(i >= size)
                throw new NoSuchElementException();
            Object[] array = ArrayList.this.array;
            if(i >= array.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) array[lastReturn = i];
        }
        
        public void remove(){
            if(lastReturn < 0)
                throw new IllegalStateException();
            
            try{
                ArrayList.this.remove(lastReturn);
                cursor = lastReturn;
                lastReturn = -1;
            }catch(IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }
    }
    
    private class ListItr extends Itr implements IListIterator<E>{
        
        ListItr(int index){
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @SuppressWarnings("unchecked")
        public E previous() {
            int i = cursor - 1;
            
            if(i < 0)
                throw new NoSuchElementException();
            Object[] array = ArrayList.this.array;
            if(i >= array.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) array[lastReturn = i];
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E data) {
            if(lastReturn < 0)
                throw new IllegalStateException();
            try{
                ArrayList.this.set(lastReturn, data);
            }catch(IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E data) {
            try{
                int i = cursor;
                ArrayList.this.add(i , data);
                cursor = i + 1;
                lastReturn = -1;
            }catch(IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }
        
    }
}