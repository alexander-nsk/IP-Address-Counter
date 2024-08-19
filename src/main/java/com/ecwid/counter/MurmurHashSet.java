package com.ecwid.counter;

public class MurmurHashSet {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int[] buckets;
    private boolean[] occupied;
    private int size;
    private int threshold;

    /**
     * Constructs an empty MurmurHashSet
     */
    public MurmurHashSet() {
        buckets = new int[DEFAULT_CAPACITY];
        occupied = new boolean[DEFAULT_CAPACITY];
        size = 0;
        threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    /**
     * Adds the specified key to this set.
     *
     * @param key the key to be added.
     */
    public void add(int key) {
        if (contains(key)) {
            return;
        }

        int bucketIndex = hash(key);
        while (occupied[bucketIndex]) {
            bucketIndex = (bucketIndex + 1) % buckets.length;
        }
        buckets[bucketIndex] = key;
        occupied[bucketIndex] = true;
        size++;

        if (size >= threshold) {
            resize();
        }
    }

    /**
     * Returns true if this set contains the specified key.
     *
     * @param key is the key whose presence in this set is to be tested.
     * @return true if this set contains the specified key.
     */
    public boolean contains(int key) {
        int bucketIndex = hash(key);
        while (occupied[bucketIndex]) {
            if (buckets[bucketIndex] == key) {
                return true;
            }
            bucketIndex = (bucketIndex + 1) % buckets.length;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Calculates the hash code for the specified key.
     *
     * @param key the key to be hashed
     * @return the hash code
     */
    private int hash(int key) {
        return fmix32(key) & (buckets.length - 1);
    }

    /**
     * MurmurHash3's fmix function to hase the key.
     *
     * @param h the hash code to be finalized
     * @return the hash code
     */
    private static int fmix32(int h) {
        h ^= h >>> 16;
        h *= 0x85ebca6b;
        h ^= h >>> 13;
        h *= 0xc2b2ae35;
        h ^= h >>> 16;
        return h;
    }

    /**
     * Resizes the set when the current size exceeds the threshold.
     */
    private void resize() {
        int[] oldBuckets = buckets;
        boolean[] oldOccupied = occupied;
        int newCapacity = oldBuckets.length * 2;
        buckets = new int[newCapacity];
        occupied = new boolean[newCapacity];
        threshold = (int) (newCapacity * LOAD_FACTOR);
        size = 0;

        for (int i = 0; i < oldBuckets.length; i++) {
            if (oldOccupied[i]) {
                add(oldBuckets[i]);
            }
        }
    }
}
