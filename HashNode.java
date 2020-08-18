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


public class HashNode<K, V> {                       // This is the node used for Chaining collision handling
        K key;                                      // This is the key
        V value;                                    // This is the corresponding value
        
        // Reference to the next node
        HashNode<K, V> next;
        
        // Constructor
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
}
