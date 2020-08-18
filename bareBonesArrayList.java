/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc331hashmap;

/**
 *
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mac
 */
public interface bareBonesArrayList <E>{
    
    public void add(E a);
    public void add(int index, E a);
    
    public E remove(int index);
    
    public E get(int index);
    public void set(int index, E a);
    
    public int getSize();
    public int indexOf(E a);

}


