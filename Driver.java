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
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DH_HashMap<String, Integer> map = new DH_HashMap<>();

        map.add("This", 1);
        map.add("is", 1);
        map.add("line", 1);
        System.out.println("Size of the HashMap: " + map.getSize());

        map.add("This", 9999);
        System.out.println("Size of the HashMap: " + map.getSize());
        map.remove("xyz");
        System.out.println("Size of the HashMap: " + map.getSize());
        System.out.println("Removing Value " + map.remove("This"));
        System.out.println("Size of the HashMap: " + map.getSize());
        System.out.println("Returned Value: " + map.get("line"));
        map.add("line", 100);
        System.out.println("Returned Value: " + map.get("line"));

        map.add("test", 42);
        System.out.println("Print: " + map.get("test"));
        
        
    }

}
