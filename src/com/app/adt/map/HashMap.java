package com.app.adt.map;

import com.app.adt.IIterator;
import com.app.adt.set.ISet;
import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;



public class HashMap<K,V> implements IMap<K,V> {
//    
//    static final int DEFAULT_INITIAL_CAPACITY = 16;
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//    transient Entry[] entry;
//    transient int size;
//    int threshold;
//    final float loadFactor;
//    
//    public HashMap(int initialCapacity,float loadFactor){
//        if(initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
//        if(initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if(loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
//        
//        // Find a power of 2 >= initialCapacity
//        int capacity = 1;
//        while(capacity < initialCapacity)
//            capacity <<= 1;
//        
//        this.loadFactor = loadFactor;
//        threshold = (int)(capacity * loadFactor);
//        entry = new Entry[capacity];
//    }
//    
//    public HashMap(int initialCapacity){
//        this(initialCapacity,DEFAULT_LOAD_FACTOR);
//    }
//    
//    public HashMap(){
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
//        entry = new Entry[DEFAULT_INITIAL_CAPACITY];
//    }
//
//    @Override
//    public V put(K key, V value) {
//        if(key == null)
//            return putForNullKey(value);
//        int hash = hash(key.hashCode());
//        int i = indexFor(hash,entry.length);
//        for(Entry<K,V> e = entry[i];e != null;e = e.next){
//            Object k;
//            if(e.hash == hash && ((k = e.key) == key || key.equals(k))){
//                V tmpValue = e.value;
//                e.value = value;
//                return tmpValue;
//            }
//        }
//        addEntry(hash,key,value,i);
//        return null;
//    }
//    /*
//    @Override
//    public void putAll(IMap<? extends K, ? extends V> m) {
//        int numKeysToBeAdded = m.length();
//        if(numKeysToBeAdded == 0)
//            return;
//        
//        if(numKeysToBeAdded > threshold){
//            int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
//            if(targetCapacity > MAXIMUM_CAPACITY)
//                targetCapacity = MAXIMUM_CAPACITY;
//            
//            int newCapacity = entry.length;
//            
//            while(newCapacity < targetCapacity)
//                newCapacity <<= 1;
//            
//            if(newCapacity > entry.length)
//                resize(newCapacity);
//        }
//        
//        for(IMap.Entry<? extends K,? extends V> e : m.entrySet())
//            put(e.getKey(),e.getValue());
//    }
//*/
//    @Override
//    public V remove(K key) {
//        Entry<K,V> e = removeEntryForKey(key);
//        return (e == null ? null : e.value);
//    }
//
//    @Override
//    public V get(K key) {
//        if(key == null)
//            return getForNullKey();
//        int hash = hash(key.hashCode());
//        for(Entry<K,V> e = entry[indexFor(hash,entry.length)];e != null;e = e.next){
//            Object k;
//            if(e.hash == hash && ((k = e.key) == key || key.equals(k)))
//                return e.value;
//        }
//        return null;
//    }
//
//    @Override
//    public boolean containsKey(K key) {
//        return getEntry(key) != null;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public int length() {
//        return size;
//    }
//
//    @Override
//    public void clear() {
//        Entry[] tmp = entry;
//        for(int i = 0;i < tmp.length;i++)
//            tmp[i] = null;
//        size = 0;
//    }
//    
//    void resize(int newCapacity){
//        Entry[] tmp = entry;
//        int tmpCapacity = tmp.length;
//        if(tmpCapacity == MAXIMUM_CAPACITY){
//            threshold = Integer.MAX_VALUE;
//            return;
//        }
//        
//        Entry[] newEntry = new Entry[newCapacity];
//        transfer(newEntry);
//        entry = newEntry;
//        threshold = (int)(newCapacity * loadFactor);
//    }
//    
//    void transfer(Entry[] newEntry){
//        Entry[] tmp = entry;
//        int newCapacity = newEntry.length;
//        for(int i = 0;i < tmp.length;i++){
//            Entry<K,V> e = tmp[i];
//            if(e != null){
//                tmp[i] = null;
//                do{
//                    Entry<K,V> next = e.next;
//                    int x = indexFor(e.hash,newCapacity);
//                    e.next = newEntry[i];
//                    newEntry[i] = e;
//                    e = null;
//                }while(e != null);
//            }
//        }
//    }
//    
//    final Entry<K,V> removeEntryForKey(K key){
//        int hash = (key == null) ? 0 : hash(key.hashCode());
//        int i = indexFor(hash,entry.length);
//        Entry<K,V> prev = entry[i];
//        Entry<K,V> e = prev;
//        
//        while(e != null){
//            Entry<K,V> next = e.next;
//            Object k;
//            if(e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))){
//                size--;
//                if(prev == e)
//                    entry[i] = next;
//                else
//                    prev.next = next;
//                return e;
//            }
//            prev = e;
//            e = next;
//        }
//        return e;
//    }
//    
//    final Entry<K,V> getEntry(K key){
//        int hash = (key == null) ? 0 : hash(key.hashCode());
//        for(Entry<K,V> e = entry[indexFor(hash,entry.length)];e != null;e = e.next){
//            Object k;
//            if(e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
//                return e;
//        }
//        return null;
//    }
//    
//    private V putForNullKey(V value){
//        for(Entry<K,V> e = entry[0];e != null;e = e.next){
//            if(e.key == null){
//                V tmpValue = e.value;
//                e.value = value;
//                return tmpValue;
//            }
//        }
//        addEntry(0,null,null,0);
//        return null;
//    }
//    
//    private V getForNullKey(){
//        for(Entry<K,V> e = entry[0];e != null;e = e.next){
//            if(e.key == null)
//                return e.value;
//        }
//        return null;
//    }
//    
//    void addEntry(int hash,K key,V value,int bucketIndex){
//        Entry<K,V> e = entry[bucketIndex];
//        entry[bucketIndex] = new Entry<>(hash,key,value,e);
//        if(size++ >= threshold)
//            resize(2 * entry.length);
//    }
//    
//    static int indexFor(int h,int length){
//        return h & (length - 1);
//    }
//    
//    static int hash(int h){
//        h ^= (h >>> 20) ^ (h >>> 12);
//        return h ^ (h >>> 7) ^ (h >>> 4);
//    }
//    
//    static class Entry<K,V> implements IMap.Entry<K,V>{
//        final K key;
//        V value;
//        Entry<K,V> next;
//        final int hash;
//        
//        Entry(int hash,K key,V value,Entry<K,V> next){
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//        
//        public final K getKey(){
//            return key;
//        }
//        
//        public final V getValue(){
//            return value;
//        }
//        
//        public final V setValue(V value){
//            V tmpValue = this.value;
//            this.value = value;
//            return tmpValue;
//        }
//        
//        public final boolean equals(Object o){
//            if(!(o instanceof IMap.Entry))
//                return false;
//            IMap.Entry e = (IMap.Entry)o;
//            Object k1 = getKey();
//            Object k2 = e.getKey();
//            if(k1 == k2 || (k1 != null && k1.equals(k2))){
//                Object v1 = getValue();
//                Object v2 = e.getValue();
//                if(v1 == v2 || (v2 != null && v1.equals(v2)))
//                    return true;
//            }
//            return false;
//        }
//        
//        public final int hashCode(){
//            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
//        }
//        
//        public final String toString(){
//            return getKey() + " = " + getValue();
//        }
//    }
//    /*
//    private abstract class HashIterator<E> implements IIterator<E>{
//        Entry<K,V> next;
//        int index;
//        Entry<K,V> cur;
//        
//        HashIterator(){
//            if(size > 0){
//                Entry[] e = entry;
//                while(index < e.length && (next = e[index++]) == null);
//            }
//        }
//        
//        public final boolean hasNext(){
//            return next != null;
//        }
//        
//        final Entry<K,V> nextEntry(){
//            Entry<K,V> e = next;
//            if(e == null) throw new NoSuchElementException();
//            
//            if((next = e.next) == null){
//                Entry[] tmp = entry;
//                while(index < tmp.length && (next = tmp[index++]) == null);
//            }
//            
//            cur = e;
//            return e;
//        }
//        
//        public void remove(){
//            if(cur == null) throw new IllegalStateException();
//            
//            K k = cur.key;
//            cur = null;
//            HashMap.this.removeEntryForKey(k);
//        }
//        
//        private final class EntryIterator extends HashIterator<IMap.Entry<K,V>>{
//            public IMap.Entry<K,V> next(){
//                return nextEntry();
//            }
//        }
//        
//        IIterator<IMap.Entry<K,V>> newEntryIterator(){
//            return new EntryIterator();
//        }
//    }
//    
//    
//    public ISet<IMap.Entry<K,V>> entrySet(){
//        return entrySet0();
//    }
//    
//    private ISet<IMap.Entry<K,V>> entrySet0(){
//        ISet<IMap.Entry<K,V>> es = entrySet();
//        return es != null ? es : (entrySet = new EntrySet());
//    }
//    
//    private final class EntrySet extends java.util.AbstractSet<IMap.Entry<K,V>>{
//        public IIterator<IMap.Entry<K,V>> iterator(){
//            return newEntryIterator();
//        }
//        
//        public boolean contains(Object o){
//            if(!(o instanceof IMap.Entry))
//                return false;
//            IMap.Entry<K,V> e = (IMap.Entry<K, V>) o;
//            Entry<K,V> candidate = getEntry(e.getKey());
//            return candidate != null && candidate.equals(e);
//        }
//        
//        public boolean remove(Object o){
//            return removeMapping(o) != null;
//        }
//        
//        public int length(){
//            return size;
//        }
//        
//        public void clear(){
//            HashMap.this.clear();
//        }
//    }
//    */
    private Node<K,V>[] entry;
    private int size;
    private static final int DEFAULT_CAPACITY = 101;
    private static final double MAX_LOAD_FACTOR = 0.9;
    
    public HashMap(){
        this(DEFAULT_CAPACITY);
    }
    
    public HashMap(int capacity){
        int primeSize = getNextPrime(capacity);
        entry = new Node[primeSize];
        size = 0;
    }
    
    @Override
    public V put(K key, V value) {
        V oldValue = null;
        
        if(isFull())
            rehash();
        
        int index = getHashIndex(key);
        
        if(entry[index] == null){
            entry[index] = new Node<K,V>(key,value);
            size++;
        }
        else{
            Node<K,V> cur = entry[index];
            Node<K,V> prev = null;
            
            while((cur != null) && !key.equals(cur.key)){
                prev = cur;
                cur = cur.next;
            }
            
            if(cur == null){
                Node<K,V> newNode = new Node<K,V>(key,value);
                prev.next = newNode;
                size++;
            }
            else{
                oldValue = cur.value;
                cur.value = value;
            }
        }
        return oldValue;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V get(K key) {
        V result = null;
        
        int index = getHashIndex(key);
        
        Node<K,V> cur = entry[index];
        
        while((cur != null) && !key.equals(cur.key)) cur = cur.next;
        
        if(cur != null)
            result = cur.value;
        
        return result;
    }

    @Override
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean isFull() {
        return size > MAX_LOAD_FACTOR * entry.length;
    }
    
    @Override
    public int length(){
        return size;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString() {
    String outputStr = "";
    for (int index = 0; index < entry.length; index++) {
      if (entry[index] == null) {
        outputStr += "null\n";
      } else {
        Node<K, V> currentNode = entry[index];
        while (currentNode != null) {
          outputStr += currentNode.key + " " + currentNode.value + ", ";
          currentNode = currentNode.next;
        } // end while

        outputStr += "\n";
      } // end if
    } // end for

    outputStr += "\n";
    return outputStr;
  } 
    
    private void rehash(){
        Node<K,V>[] tmp = entry;
        int oldSize = entry.length;
        int newSize = getNextPrime(oldSize * 2);
        
        entry = new Node[newSize];
        
        size = 0;
        
        for(int i = 0;i < oldSize;i++){
            Node<K,V> cur = tmp[i];
            
            while(cur != null){
                put(cur.key,cur.value);
                cur = cur.next;
            }
        }
    }

    private int getHashIndex(K key){
        int hashIndex = key.hashCode() % entry.length;
        
        if(hashIndex < 0)
            hashIndex += entry.length;
        
        return hashIndex;
    }

    private int getNextPrime(int i){
        if(i % 2 == 0)
            i++;
        
        while(!isPrime(i))
            i += 2;
        
        return i;
    }
    
    private boolean isPrime(int i){
        boolean result;
        boolean done = false;
        
        if((i == 1) || (i % 2 == 0))
            result = false;
        else if((i == 2) || (i == 3))
            result = true;
        else{
            result = true;
            for(int divisor = 3;!done && (divisor * divisor <= i);divisor += 2){
                if(i % divisor == 0){
                    result = false;
                    done = true;
                }
            }
        }
        return result;
    }

    
    
    public class Node<K,V>implements Serializable{
        private K key;
        private V value;
        private Node<K,V> next;
        
        private Node(K key,V value){
            this.key = key;
            this.value = value;
            next = null;
        }
        
        private Node(K key,V value,Node<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
