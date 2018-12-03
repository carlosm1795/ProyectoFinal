
package diccionario3;

import java.io.IOException;


public class Diccionario3 {

    public static void main(String[] args) throws IOException {
        AVLTree tree = new AVLTree();
                
        tree.registrarPalabras();
        
        System.out.println("--------------------Preorder:-----------------"); 
        System.out.println("CharNUM - Palabra - Altura");
        tree.preOrder(tree.root); 
        
        System.out.println("--------------------In Order -----------------"); 
        System.out.println("CharNUM - Palabra - Altura");
        tree.inOrder(tree.root);
        
        System.out.println("-------------------- Post Order---------------"); 
        System.out.println("CharNUM - Palabra - Altura");
        tree.postOrder(tree.root);
        
        System.out.println("-------------------- Mostrar camino---------------"); 
        tree.mostrarCaminoPalabra("fringilla");
        
    }
    
}
