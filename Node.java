/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diccionario3;

/**
 *
 * @author barbozca2
 */
public class Node {
    int key, height; 
    Node left, right; 
    String palabra;
  
    Node(int d,String pPalabra) { 
        key = d; 
        palabra=pPalabra;
        height = 1; 
    } 
}
