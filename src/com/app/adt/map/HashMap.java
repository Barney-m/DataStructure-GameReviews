package com.app.adt.map;

import java.util.Objects;

public class HashMap<K, V> implements IMap<K, V> {

    transient Entry<K, V>[] entry;
    static final float loadFactor = 0.75f;
    private int threshold;
    private int size;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    

    static final int hash(Object o) {
        return (o == null) ? o.hashCode() : 0;
    }

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public HashMap(int capacity) {
        this.threshold = tableSizeFor(capacity);
    }

    @Override
    public int length() {
        return size;
    }

    final void resize() {

        // Place everything to a temp variable
        Entry<K, V>[] tmp = entry;
        int tmpCapacity = (tmp == null) ? 0 : tmp.length;
        int tmpThreshold = threshold;

        // New variable
        int newCapacity, newThreshold;

        // Three cases
        // 1. Table is not empty.
        // 2. Table is empty, but have a specified threshold.
        // 3. Table is empty, and does not have a specified threshold.
        if (tmpCapacity > 0) {
            newCapacity = tmpCapacity << 1;
            newThreshold = tmpThreshold << 1;
        } else if (tmpThreshold > 0) {
            newCapacity = tmpThreshold;
            newThreshold = (int) (newCapacity * loadFactor);
        } else {
            newCapacity = 16;
            newThreshold = (int) (newCapacity * loadFactor);
        }

        Entry<K, V>[] newEntry = (Entry<K, V>[]) new Entry[newCapacity];
        threshold = newThreshold;
        entry = newEntry;

        /*   
        If oldTable is not empty
            For every index of the table
                If it's not null
                    place head to temp
                    delete temp
                    case 1 : it's the only node
                        place temp in newTable[temp.hash AND with newCapacity -1]
                    case 2 : it's not the only node
                        do
                            case 1 : resizing will not cause repositioning of node
                                add to low list
                            case 2 : resizing will cause repositioning
                                add to high list
                            temp = temp.next
                        while temp != null
        
                        if low list exists
                            clean lowTail.next
                            move it to lower half of the table (back to original index)
                        if high list exists
                            clean highTail.next
                            move it to higher half of the table (new index)
         */
        if (tmp != null) {
            for (int j = 0; j < tmpCapacity; ++j) {
                Entry<K, V> temp = tmp[j];
                if (temp != null) {
                    tmp[j] = null;
                    if (temp.next == null) {
                        newEntry[temp.hash & (newCapacity - 1)] = temp;
                    } else {
                        Entry<K, V> btmHTra = null, btmTTra = null,topHTra = null, topTTra = null;
                        do {
                            if ((temp.hash & tmpCapacity) == 0) {
                                if (btmTTra == null) {
                                    btmHTra = temp;
                                } else {
                                    btmTTra.next = temp;
                                    temp.prev = btmTTra;
                                }
                                btmTTra = temp;
                            } else {
                                if (topTTra == null) {
                                    topHTra = temp;
                                } else {
                                    topTTra.next = temp;
                                    temp.prev = topTTra;
                                }
                                topTTra = temp;
                            }
                            temp = temp.next;
                        } while (temp != null);
                        if (btmTTra != null) {
                            btmTTra.next = null;
                            newEntry[j] = btmHTra;
                        }
                        if (topTTra != null) {
                            topTTra.next = null;
                            newEntry[j + tmpCapacity] = topHTra;
                        }
                    }
                }
            }
        }
        entry = newEntry;
    }

    @Override
    public V get(Object key) {
        int hash = hash(key);
        int index = (entry.length - 1) & hash;
        Entry<K, V> tmp;

        /*
        case 1 : table is not empty and have valid length
            case 1.1 : the index contain nodes
                loop through the nodes
         */
        if (entryExists()) {
            if (entry[index] != null) {

                tmp = getNode(hash, key, entry[index]);
                
                if (tmp != null) {
                    return tmp.value;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> tmp;
        V tmpValue = null;
        int hash = hash(key);
        int index;

        if (!entryExists()) {
            resize();
        }

        /*
        Use the length of the table (power of two) minus one
        eg (1000 will become 0111)
        AND the hash of the key to determine the location of the node
         */
        index = hash & (entry.length - 1);
        tmp = entry[index];

        /*
        case 1 : there's no node here
        case 2 : there's node here
            loop through the nodes
            case 2.1 : there's no matching nodes. (new node)
            case 2.2 : temp is the matching nodes. (replace node)
         */
        if (tmp == null) {
            entry[index] = new Entry(hash, key, value);
        } else {
            tmp = getNode(hash, key, tmp);
            if (tmp == null) {
                Entry<K, V> newNode = new Entry(hash, key, value);
                entry[index].prev = newNode;
                newNode.next = entry[index];
                entry[index] = newNode;
            } else {
                tmpValue = tmp.value;
                tmp.value = value;
            }
        }
        if (size + 1 > threshold) {
            resize();
        }
        return tmpValue;
    }

    @Override
    public V remove(Object key) {
        V tmpValue;
        int hash = hash(key);
        int index = (entry.length - 1) & hash;
        Entry<K, V> tmp;

        /*
        case 1 : table is not empty and have valid length
            case 1.1 : the index contain nodes
                case 1.1.1 : it's the first node
                    case 1.1.1.1 : the only node in the index
                        empty out the index
                    case 1.1.1.2 : at least two nodes in the index
                        update next node
                    reduce size
                    return old value
                case 1.1.2 : its the middle or last node
                    update next pointer (if there's any)
                    update prev pointer
                    
         */
        if (entryExists()) {
            if (entry[index] != null) {
                tmp = entry[index];

                if (compareKey(hash, key, tmp)) {
                    if (tmp.next == null) {
                        entry[index] = null;
                    } else {
                        tmp.next.prev = tmp.prev;
                        tmp.next = null;
                    }

                    --size;
                    tmpValue = tmp.value;
                    tmp.value = null;
                    return tmpValue;
                }

                tmp = getNode(hash, key, tmp.next);
                
                if (tmp != null) {
                    if (tmp.next != null) {
                        tmp.next.prev = tmp.prev;
                        tmp.next = null;
                    }

                    tmp.prev.next = tmp.next;
                    tmp.prev = null;

                    --size;
                    tmpValue = tmp.value;
                    tmp.value = null;
                    return tmpValue;
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        V value = get(key);
        return (value != null);
    }

    @Override
    public boolean containsValue(Object value) {
        Entry<K, V>[] tmp;
        V tmpValue;

        /*
        case 1 : table is not empty and have at least one node
            for every index in the table
                for every node in the index
                    case 1.1 : values match
                        return true
         */
        if ((tmp = entry) != null && size > 0) {
            for (int i = 0; i < tmp.length; ++i) {
                for (Entry<K, V> e = tmp[i]; e != null; e = e.next) {
                    if ((tmpValue = e.value) == value
                            || (value != null && value.equals(tmpValue))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        if (entryExists()) {
            size = 0;
            for (int i = 0; i < entry.length; i++) {
                entry[i] = null;
            }
        }

    }

    private boolean entryExists() {
        return entry != null && entry.length > 0;
    }

    /*
     * https://stackoverflow.com/questions/51118300
     * Description  : Returns x where (x > cap) and x is a power of 2
     * Return       : x where (x > cap) and x is a power of 2
     */
    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    /*
     * Description  : Find the matching node through the node list 
     *                begin from the specified node
     * Return       : true  - A match!
     *                false - No matching nodes
     */
    private Entry<K, V> getNode(int hash, Object key, Entry<K, V> tmpEntry) {
        while (compareKey(hash, key, tmpEntry) == false) {
            if (tmpEntry.next == null) {
                return null;
            }
            tmpEntry = tmpEntry.next;
        }
        return tmpEntry;
    }

    /*
     * Description  : Compares the node with the specified key and hash
     * Return       : true  - Match
     *                false - Does not match
     */
    private boolean compareKey(int hash, Object key, Entry<K, V> tmpEntry) {
        K entryKey = tmpEntry.key;

        return tmpEntry.hash == hash
                && (entryKey == key || (key != null && key.equals(entryKey)));
    }
    
    static class Entry<K, V>{

        final int hash;
        final K key; 
        V value;
        Entry<K, V> prev;
        Entry<K, V> next;

        public Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof Entry) {
                Entry<?, ?> tmp = (Entry<?, ?>) object;
                if (Objects.equals(this.key, tmp.getKey())
                        && Objects.equals(this.value, tmp.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }
}
