package com.app.adt.map;

public class HashMap<K,V> implements Map<K,V> {

    @Override
    public V add(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V get(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    private Node<K,V>[] entry;
//    private int size;
//    private static final int DEFAULT_CAPACITY = 101;
//    private static final double MAX_LOAD_FACTOR = 0.9;
//    
//    public HashMap(){
//        this(DEFAULT_CAPACITY);
//    }
//    
//    public HashMap(int capacity){
//        int primeSize = getNextPrime(capacity);
//        entry = new Node[primeSize];
//        size = 0;
//    }
//    
//    @Override
//    public V add(K key, V value) {
//        V oldValue = null;
//        
//        if(isFull())
//            rehash();
//        
//        int index = getHashIndex(key);
//        
//        if(entry[index] == null){
//            entry[index] = new Node<K,V>(key,value);
//            size++;
//        }
//        else{
//            Node<K,V> cur = entry[index];
//            Node<K,V> prev = null;
//            
//            while((cur != null) && !key.equals(cur.key)){
//                prev = cur;
//                cur = cur.next;
//            }
//            
//            if(cur == null){
//                Node<K,V> newNode = new Node<K,V>(key,value);
//                prev.next = newNode;
//                size++;
//            }
//            else{
//                oldValue = cur.value;
//                cur.value = value;
//            }
//        }
//        return oldValue;
//    }
//
//    @Override
//    public V remove(K key) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public V get(K key) {
//        V result = null;
//        
//        int index = getHashIndex(key);
//        
//        Node<K,V> cur = entry[index];
//        
//        while((cur != null) && !key.equals(cur.key)) cur = cur.next;
//        
//        if(cur != null)
//            result = cur.value;
//        
//        return result;
//    }
//
//    @Override
//    public boolean containsKey(K key) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean isEmpty() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean isFull() {
//        return size > MAX_LOAD_FACTOR * entry.length;
//    }
//    
//    @Override
//    public int length(){
//        return size;
//    }
//
//    @Override
//    public void clear() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    public String toString() {
//    String outputStr = "";
//    for (int index = 0; index < entry.length; index++) {
//      if (entry[index] == null) {
//        outputStr += "null\n";
//      } else {
//        Node<K, V> currentNode = entry[index];
//        while (currentNode != null) {
//          outputStr += currentNode.key + " " + currentNode.value + ", ";
//          currentNode = currentNode.next;
//        } // end while
//
//        outputStr += "\n";
//      } // end if
//    } // end for
//
//    outputStr += "\n";
//    return outputStr;
//  } 
//    
//    private void rehash(){
//        Node<K,V>[] tmp = entry;
//        int oldSize = entry.length;
//        int newSize = getNextPrime(oldSize * 2);
//        
//        entry = new Node[newSize];
//        
//        size = 0;
//        
//        for(int i = 0;i < oldSize;i++){
//            Node<K,V> cur = tmp[i];
//            
//            while(cur != null){
//                add(cur.key,cur.value);
//                cur = cur.next;
//            }
//        }
//    }
//
//    private int getHashIndex(K key){
//        int hashIndex = key.hashCode() % entry.length;
//        
//        if(hashIndex < 0)
//            hashIndex += entry.length;
//        
//        return hashIndex;
//    }
//
//    private int getNextPrime(int i){
//        if(i % 2 == 0)
//            i++;
//        
//        while(!isPrime(i))
//            i += 2;
//        
//        return i;
//    }
//    
//    private boolean isPrime(int i){
//        boolean result;
//        boolean done = false;
//        
//        if((i == 1) || (i % 2 == 0))
//            result = false;
//        else if((i == 2) || (i == 3))
//            result = true;
//        else{
//            result = true;
//            for(int divisor = 3;!done && (divisor * divisor <= i);divisor += 2){
//                if(i % divisor == 0){
//                    result = false;
//                    done = true;
//                }
//            }
//        }
//        return result;
//    }
//
//    
//    
//    public class Node<K,V>implements Serializable{
//        private K key;
//        private V value;
//        private Node<K,V> next;
//        
//        private Node(K key,V value){
//            this.key = key;
//            this.value = value;
//            next = null;
//        }
//        
//        private Node(K key,V value,Node<K,V> next){
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//    }
}
