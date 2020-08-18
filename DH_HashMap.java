/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc331hashmap;

/**
 *
 * @author Hector Felix
 */
public class DH_HashMap<K, V> {                 // Class for creating the actual HashMap
    // An ArrayList to store the chains

    private DHArrayList<HashNode<K, V>> bucketArray;

    // Current Capacity of the AL
    private int numberOfBuckets;

    // Number of Spaces actually occupied
    private int size;

    // Constructor to initialize the AL, size, and chains
    public DH_HashMap() {
        numberOfBuckets = 5;                    // There are 5 spaces available

        bucketArray = new DHArrayList<HashNode<K, V>>(numberOfBuckets);
        size = 0;                               // Currently there are 0 chains

        // Create the empty chains
        for (int i = 0; i < numberOfBuckets; i++) {
            bucketArray.add(null);
        }
    }

    // Method to find the index given the key
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();          // Using Java's built-in HashFn
        int index = hashCode % numberOfBuckets; // Map it to index in table
        return index;                           // Return the index
    }

    // Helper isEmpy method
    private boolean isEmpty() {
        return (size == 0);
    }

    // Helper getSize Method
    public int getSize() {
        return size;
    }

    // Deleting an Element from the HashMap
    // Given a key, it removes the entry and 
    // returns the corresponding value if found
    public V remove(K key) {
        //private V remove(K key) {
        // Sanity checking, if there are no elements, return null
        if (isEmpty()) {
            return null;
        }

        // So, we need to find the index where the element might be present
        int bucketIndex = getBucketIndex(key);
        // Get the head of the chain
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // Search for the key in its chain
        // This is similar to finding and deleting an element in a linkedlist
        // Let's keep track of the previous node, it helps to delete
        HashNode<K, V> prev = null;             // Assign it to null at the beginning

        while (head != null) {
            // Check the data in the head
            if (head.key.equals(key)) {
                // We found the data
                break;          // Get out of the loop
            }
            // If we don't find it yet, then keep moving in the chain
            prev = head;
            head = head.next;   // Moved to the next Node
        }
        // WE COME out of the loop if we either found the data
        // or we reached the end of the chain and the data was not there

        // if the data is not there
        if (head == null) {
            return null;        // you reached the end of the chain
        }

        // Otherwise you found the data, now delete it and return the value
        if (prev != null) {
            prev.next = head.next;          // Normal deletion
        } else {
            // found the date at the head
            // So delete the head data and point to the next
            // This is done in the AL
            bucketArray.set(bucketIndex, head.next);
        }

        // Decrement the size of the elements
        size--;

        // return the corresponding value
        return head.value;
    }

    // Method to insert data into the HashMap
    public void add(K key, V value) {
        // Find the head of the chain of the given key
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // Check if the value is already present
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;             // Update the value
                // Cannot insert the key
                return;
            }
            head = head.next;             // Go to next node to check
        }

        // The value is not present, so we will insert it
        // At the beginning
        size++;                         // Update the number of keys
        head = bucketArray.get(bucketIndex);
        // Create the new HashNode
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        bucketArray.set(bucketIndex, newNode);

        // After the new value is inserted, 
        // we need to check the load factor of the HashMap
        double load = size * 1.0 / numberOfBuckets;
        if (load >= 0.7) {
            // There is too much data in the HashMap
            // We need to double the size of the HashMap
            // And then we need to rehash and insert all data
            // again
            DHArrayList<HashNode<K, V>> temp = bucketArray;
            numberOfBuckets = 2 * numberOfBuckets;
            bucketArray = new DHArrayList<>(numberOfBuckets);
            // Reset the HashMap
            size = 0;
            for (int i = 0; i < numberOfBuckets; i++) {
                bucketArray.add(null);
            }
            // Remove each entry from the old HashMap and add to new HashMap
            for (int i = 0; i < temp.getSize(); i++) {
                HashNode<K, V> headNode = temp.remove(i);
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
    // Returns value for a key 

    public V get(K key) {
        // Find head of chain for given key 
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // Search key in chain 
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        // If key not found 
        return null;
    }
}
