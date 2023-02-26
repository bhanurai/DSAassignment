package Question4;

/*Design and Implement LFU caching

LFU (Least Frequently Used) is a cache eviction algorithm that removes the least frequently used items from the cache. In this algorithm,
each item in the cache has a frequency counter associated with it, which is incremented every time the item is accessed.
When the cache reaches its capacity, the item with the lowest frequency counter is removed.
To implement LFU caching, we can use a combination of a hash map and a doubly linked list. The hash map will store the key-value pairs,
and the doubly linked list will maintain the frequency counts of the items.
We can define a class named LFUCache with the following methods:
LFUCache(int capacity): A constructor that initializes the capacity of the cache.
int get(int key): Returns the value associated with the given key if the key exists in the cache, otherwise returns -1.
This method should also increment the frequency count of the item.
void put(int key, int value): Inserts the key-value pair into the cache. If the cache is already at its capacity,
the least frequently used item should be removed. If multiple items have the same frequency count, the least recently used item should be removed.
Here's the Java code for the LFUCache class:
 */

import java.util.*;

class CacheItem {
    int key;
    int value;
    int frequency;

    public CacheItem(int key, int value, int frequency) {
        this.key = key;
        this.value = value;
        this.frequency = frequency;
    }
}
class LFUCache {
    private int capacity;
    private Map<Integer, CacheItem> cache;
    private Map<Integer, LinkedHashSet<Integer>> frequencyMap;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.minFrequency = 0;
    }

    public int get(int key) {
        CacheItem item = cache.get(key);
        if (item == null) {
            return -1;
        }
        int frequency = item.frequency;
        frequencyMap.get(frequency).remove(key);
        if (frequencyMap.get(frequency).isEmpty()) {
            frequencyMap.remove(frequency);
            if (minFrequency == frequency) {
                minFrequency++;
            }
        }
        item.frequency++;
        frequencyMap.computeIfAbsent(item.frequency, k -> new LinkedHashSet<>()).add(key);
        return item.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        CacheItem item = cache.get(key);
        if (item != null) {
            item.value = value;
            get(key);
            return;
        }
        if (cache.size() == capacity) {
            LinkedHashSet<Integer> keys = frequencyMap.get(minFrequency);
            int leastFrequentKey = keys.iterator().next();
            keys.remove(leastFrequentKey);
            if (keys.isEmpty()) {
                frequencyMap.remove(minFrequency);
            }
            cache.remove(leastFrequentKey);
        }
        CacheItem newItem = new CacheItem(key, value, 1);
        cache.put(key, newItem);
        frequencyMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFrequency = 1;
    }
}
