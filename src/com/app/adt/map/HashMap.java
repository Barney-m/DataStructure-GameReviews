package com.app.adt.map;

public class HashMap<K,V> implements IMap<K,V> {
    
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    transient Entry[] entry;
    transient int size;
    int threshold;
    final float loadFactor;
    
    public HashMap(int initialCapacity,float loadFactor){
        if(initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if(initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if(loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        
        // Find a power of 2 >= initialCapacity
        int capacity = 1;
        while(capacity < initialCapacity)
            capacity <<= 1;
        
        this.loadFactor = loadFactor;
        threshold = (int)(capacity * loadFactor);
        entry = new Entry[capacity];
    }
    
    public HashMap(int initialCapacity){
        this(initialCapacity,DEFAULT_LOAD_FACTOR);
    }
    
    public HashMap(){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        entry = new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        if(key == null)
            return putForNullKey(value);
        int hash = hash(key.hashCode());
        int i = indexFor(hash,entry.length);
        for(Entry<K,V> e = entry[i];e != null;e = e.next){
            Object k;
            if(e.hash == hash && ((k = e.key) == key || key.equals(k))){
                V tmpValue = e.value;
                e.value = value;
                return tmpValue;
            }
        }
        addEntry(hash,key,value,i);
        return null;
    }

    @Override
    public V remove(K key) {
        Entry<K,V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);
    }

    @Override
    public V get(K key) {
        if(key == null)
            return getForNullKey();
        int hash = hash(key.hashCode());
        for(Entry<K,V> e = entry[indexFor(hash,entry.length)];e != null;e = e.next){
            Object k;
            if(e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public void clear() {
        Entry[] tmp = entry;
        for(int i = 0;i < tmp.length;i++)
            tmp[i] = null;
        size = 0;
    }
    
    void resize(int newCapacity){
        Entry[] tmp = entry;
        int tmpCapacity = tmp.length;
        if(tmpCapacity == MAXIMUM_CAPACITY){
            threshold = Integer.MAX_VALUE;
            return;
        }
        
        Entry[] newEntry = new Entry[newCapacity];
        transfer(newEntry);
        entry = newEntry;
        threshold = (int)(newCapacity * loadFactor);
    }
    
    void transfer(Entry[] newEntry){
        Entry[] tmp = entry;
        int newCapacity = newEntry.length;
        for(int i = 0;i < tmp.length;i++){
            Entry<K,V> e = tmp[i];
            if(e != null){
                tmp[i] = null;
                do{
                    Entry<K,V> next = e.next;
                    int x = indexFor(e.hash,newCapacity);
                    e.next = newEntry[i];
                    newEntry[i] = e;
                    e = null;
                }while(e != null);
            }
        }
    }
    
    final Entry<K,V> removeEntryForKey(K key){
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash,entry.length);
        Entry<K,V> prev = entry[i];
        Entry<K,V> e = prev;
        
        while(e != null){
            Entry<K,V> next = e.next;
            Object k;
            if(e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))){
                size--;
                if(prev == e)
                    entry[i] = next;
                else
                    prev.next = next;
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }
    
    final Entry<K,V> getEntry(K key){
        int hash = (key == null) ? 0 : hash(key.hashCode());
        for(Entry<K,V> e = entry[indexFor(hash,entry.length)];e != null;e = e.next){
            Object k;
            if(e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }
    
    private V putForNullKey(V value){
        for(Entry<K,V> e = entry[0];e != null;e = e.next){
            if(e.key == null){
                V tmpValue = e.value;
                e.value = value;
                return tmpValue;
            }
        }
        addEntry(0,null,null,0);
        return null;
    }
    
    private V getForNullKey(){
        for(Entry<K,V> e = entry[0];e != null;e = e.next){
            if(e.key == null)
                return e.value;
        }
        return null;
    }
    
    void addEntry(int hash,K key,V value,int bucketIndex){
        Entry<K,V> e = entry[bucketIndex];
        entry[bucketIndex] = new Entry<>(hash,key,value,e);
        if(size++ >= threshold)
            resize(2 * entry.length);
    }
    
    static int indexFor(int h,int length){
        return h & (length - 1);
    }
    
    static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    
    static class Entry<K,V> implements IMap.Entry<K,V>{
        final K key;
        V value;
        Entry<K,V> next;
        final int hash;
        
        Entry(int hash,K key,V value,Entry<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        public final K getKey(){
            return key;
        }
        
        public final V getValue(){
            return value;
        }
        
        public final V setValue(V value){
            V tmpValue = this.value;
            this.value = value;
            return tmpValue;
        }
        
        public final boolean equals(Object o){
            if(!(o instanceof IMap.Entry))
                return false;
            IMap.Entry e = (IMap.Entry)o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if(k1 == k2 || (k1 != null && k1.equals(k2))){
                Object v1 = getValue();
                Object v2 = e.getValue();
                if(v1 == v2 || (v2 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }
        
        public final int hashCode(){
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }
        
        public final String toString(){
            return getKey() + " = " + getValue();
        }
    }
}
