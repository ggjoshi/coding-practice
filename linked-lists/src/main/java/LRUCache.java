import java.util.HashMap;
import java.util.Map;

/**
 * LRU cache for storing strings and ints.
 */
public class LRUCache<K, V> {
    private class LRUCacheNode<K, V> {
        public K key;
        public V value;
        public LRUCacheNode<K, V> next, prev;

        public LRUCacheNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    private Map<K, LRUCacheNode<K, V>> map;
    // head and tail of linkedlist running through the LRUCacheNodes
    private LRUCacheNode head;
    private LRUCacheNode tail;
    private int maxRetentionCapacity;

    public LRUCache(int maxRetentionCapacity) {
        map = new HashMap<K, LRUCacheNode<K, V>>();
        head = tail = null;
        this.maxRetentionCapacity = maxRetentionCapacity;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public V put(K key, V value) {
        V existingValue = null;
        LRUCacheNode<K, V> cacheNode;
        if(!map.containsKey(key)) {
            if(map.size() >= maxRetentionCapacity) {
                removeNodeFromLinkedList(tail);
                map.remove(tail.key);
            }
            cacheNode = new LRUCacheNode<K, V>(key, value);
            map.put(key, cacheNode);
        } else {
            cacheNode = map.get(key);
            existingValue = cacheNode.value;
            removeNodeFromLinkedList(cacheNode);
        }
        addToFront(cacheNode);
        return existingValue;
    }

    public V get(K key) {
        LRUCacheNode<K, V> cacheNode = map.get(key);
        if(cacheNode != null) {
            removeNodeFromLinkedList(cacheNode);
            addToFront(cacheNode);
            return cacheNode.value;
        }
        return null;
    }

    public V remove(K key) {
        LRUCacheNode<K, V> cacheNode = map.remove(key);
        removeNodeFromLinkedList(cacheNode);
        return cacheNode.value;
    }

    private void removeNodeFromLinkedList(LRUCacheNode<K, V> cacheNode) {
        if(cacheNode == null) {
            return;
        }
        if(cacheNode.prev != null) {
            cacheNode.prev.next = cacheNode.next;
        } else {
            // cache node is head
            head = cacheNode.next;
        }
        if(cacheNode.next != null) {
            cacheNode.next.prev = cacheNode.prev;
        } else {
            tail = cacheNode.prev;
        }
        cacheNode.next = null;
        cacheNode.prev = null;
    }

    private void addToFront(LRUCacheNode<K, V> cacheNode) {
        cacheNode.next = head;
        if(head != null) {
            head.prev = cacheNode;
        } else {
            tail = cacheNode;
        }
        head = cacheNode;
    }

    @Override
    public String toString() {
        if(head == null) {
            return "LRUCache is empty";
        }
        StringBuilder output = new StringBuilder();
        LRUCacheNode<K, V> cacheNode = head;
        while (cacheNode != null) {
            output.append("(" +
                cacheNode.key + "," +
                cacheNode.value + ") ");
            cacheNode = cacheNode.next;
        }
        return output.toString();
    }

    public static void main(String [] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<String, Integer>(3);
        lruCache.put("a", 1);lruCache.put("b", 2);lruCache.put("c", 3);
        System.out.println(lruCache);
        lruCache.get("c");
        System.out.println(lruCache);
        lruCache.get("b");
        System.out.println(lruCache);
        lruCache.put("d", 4);
        System.out.println(lruCache);
        lruCache.remove("a");
        System.out.println(lruCache);
        lruCache.remove("d");
        System.out.println(lruCache);
        lruCache.put("e", 6);
        System.out.println(lruCache);
        lruCache.put("f", 7);
        System.out.println(lruCache);
    }
}
