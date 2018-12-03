
package diccionario3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AVLTree {
    Node root; 
    // A utility function to get the height of the tree 
    int height(Node N) { 
        if (N == null) 
            return 0; 
  
        return N.height; 
    } 
    
     //Obtener el maximo de 2 numeros
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
    
    
    Node rightRotate(Node y) { 
        Node x = y.left; 
        Node T2 = x.right; 
  
        // Rotacion 
        x.right = y; 
        y.left = T2; 
  
        // Incrementar pesos
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        // Retornar la nueva raiz. 
        return x; 
    } 
    
    
    Node leftRotate(Node x) { 
        Node y = x.right; 
        Node T2 = y.left; 
  
        // Rotacion 
        y.left = x; 
        x.right = T2; 
  
        // Incrementar pesos
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
         // Retornar la nueva raiz. 
        return y; 
    } 
  
    // Obtener factor de un nodo.
    int getBalance(Node N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
    
    Node insert(Node node, int key,String pPalabra) { 
  
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new Node(key,pPalabra)); 
  
        if (key < node.key) 
            node.left = insert(node.left, key,pPalabra); 
        else if (key > node.key) 
            node.right = insert(node.right, key,pPalabra); 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Updatear el peso */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
       
        int balance = getBalance(node); 
  
       
        // Validar peso del nodo
        if (balance > 1 && key < node.left.key) 
            return rightRotate(node); 
  
        //Doble Derecha case 
        if (balance < -1 && key > node.right.key) 
            return leftRotate(node); 
  
        // Rotacion izquierda
        if (balance > 1 && key > node.left.key) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // rotacion izquierda derecha
        if (balance < -1 && key < node.right.key) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        /*nuevo nodo  */ 
        return node; 
    } 
    
    
    void preOrder(Node node) { 
        if (node != null) { 
            System.out.println(node.key + " - "+ node.palabra + " - "); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    }
    void inOrder(Node node) { 
        if (node != null) { 
            inOrder(node.left); 
            System.out.println(node.key + " - "+ node.palabra + " - " + node.height +""); 
            inOrder(node.right); 
        } 
    }
    
    void postOrder(Node node) { 
        if (node != null) { 
            postOrder(node.left); 
            postOrder(node.right); 
            System.out.println(node.key + " - "+ node.palabra + " - "); 
            
        } 
    }
    
    //Metodo que recibe un array de String y devuelve un array con la suma de lo caracteres en int
    public int [] devolverNumChar(String [] palabras){
        int [] charPalabras = new int [palabras.length];
        int auxiliar =0;
        for (int index=0;index<palabras.length;index++){
            for(int pindexLetra=0;pindexLetra<palabras[index].length();pindexLetra++){
                auxiliar+=palabras[index].charAt(pindexLetra);
            }
            charPalabras[index]=auxiliar;
            auxiliar=0;
        }
        return charPalabras;
    }
    
    
    
    //Codigo que lee las palabras de un archivo csv o txt y devuelve un arreglo de String con las letras en mayuscula
    public  static String[] muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        int cantWords = 0;
        int index =0;
        String palabras[];    //declaring array
        
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            
            cantWords++;
        }
        
        b.close();
        
        f = new FileReader(archivo);
        b = new BufferedReader(f);
        palabras = new String[cantWords];
        while((cadena = b.readLine())!=null) {
            
            palabras[index]=cadena.toUpperCase();
            index++;
        }
        b.close();
        
        return palabras;
    }
    
    public void  registrarPalabras() throws IOException{
        int [] palabrasCharArray;
        String rutaFile="C:\\Users\\barbozca2\\Desktop\\PalabrasMin2.csv";
        String[] palabras = muestraContenido(rutaFile);
        palabrasCharArray=devolverNumChar(palabras);
        for (int index=0;index<palabras.length;index++){
            root = this.insert(root, palabrasCharArray[index], palabras[index]);
        }
    }
    
    public void mostrarCaminoPalabra(String pPalabra){
        int [] palabrasCharArray;
        String palabras [] = new String [1];
        palabras[0]=pPalabra;
        palabrasCharArray=devolverNumChar(palabras);
        camino(root,palabrasCharArray[0]);
         
    }
    
    public void camino(Node nodo,int charNum){
        if (nodo != null){
            if(charNum<nodo.key){
                System.out.println(nodo.palabra);
                camino(nodo.left,charNum);
            }else if(charNum>nodo.key){
                System.out.println(nodo.palabra);
                camino(nodo.right,charNum);
            }else{
                return;
            }
        }
    }
}
