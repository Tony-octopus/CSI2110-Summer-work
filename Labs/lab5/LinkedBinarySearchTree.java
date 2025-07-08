/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 * LinkedBinarySearchTree for tree traversal lab
 * @author Lachlan Plant
 * @param <E>
 */
public class LinkedBinarySearchTree<E extends Comparable> implements Iterable<E>{
    
    private class Node<E>{
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        public Node(E e,Node<E> p, Node<E> l, Node<E> r){
            elem = e;
            parent= p;
            left = l;
            right = r;
        }
    }
    
    private Node<E> root;
    private int size;
    
    /**
     *
     */
    public LinkedBinarySearchTree(){
        root = null;
        size = 0;
    }
    
    /**
     * Adds elem into BST
     * @param elem
     * @return
     */
    public boolean add(E elem){
        if(root == null){
            root = new Node<>(elem, null, null, null);
            size ++;
            return true;
        }
        else{
            root = insert(elem, root, null);
            return true;
        }
    }
    
    /**
     * Recursive BST insertion
     * @param elem
     * @param curr
     * @param from
     * @return
     */
    private Node<E> insert(E elem, Node<E> curr, Node<E> from){
        if(curr == null){
            curr = new Node<>(elem, from, null, null);
            size ++;
            return curr;
        }
        if( elem.compareTo(curr.elem)<0){
            curr.left = insert(elem, curr.left, curr);
        }
        if( elem.compareTo(curr.elem)>0){
            curr.right = insert(elem, curr.right, curr);
        }
        return curr;
    }
    

    
    /*****************************************************************
     *
     * Recursive Printing Functions
     *
     *
     *****************************************************************/
    
    /**
     * Caller method for preorder recursive printing
     */
    public void printPreorderRecursive(){
        System.out.print("Recursive Preorder Printing: ");
        preorderRecursive(root);
    }
    
    /**
     * preorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void preorderRecursive(Node<E> curr){
        //Implement Here
        if (curr.left == null && curr.right == null){//is external node
            System.out.print(curr.elem + ", ");      //print root
            return;
        }
        if (curr.left != null || curr.right != null){
            System.out.print(curr.elem + ", ");      //print root
            if (curr.left != null)
                preorderRecursive(curr.left);
            if (curr.right != null)
                preorderRecursive(curr.right);
        }
    }
    
    /**
     * Caller method for inorder recursive printing
     */
    public void printInorderRecursive(){
        System.out.print("Recursive Inorder Printing: ");
        inorderRecursive(root);
    }
    
    /**
     * inorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void inorderRecursive(Node<E> curr){
        //Implement Here
        if (curr.left == null && curr.right == null){//is external node
            System.out.print(curr.elem + ", ");      //print root
            return;
        }
        if (curr.left != null || curr.right != null){
            if (curr.left != null)
                inorderRecursive(curr.left);

            System.out.print(curr.elem + ", ");      //print root

            if (curr.right != null)
                inorderRecursive(curr.right);
        }
    }
    
    
    /**
     * Caller method for postorder recursive printing
     */
    public void printPostorderRecursive(){
        System.out.print("Recursive Postorder Printing: ");
        postorderRecursive(root);
    }
    
    /**
     * postorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void postorderRecursive(Node<E> curr){
        //Implement Here       
        if (curr.left == null && curr.right == null){//is external node
            System.out.print(curr.elem + ", ");
            return;
        }
        if (curr.left != null || curr.right != null){
            if (curr.left != null)
                postorderRecursive(curr.left);

            if (curr.right != null)
                postorderRecursive(curr.right);


            System.out.print(curr.elem + ", ");      //print roo
        }
    }

    
    
     /*****************************************************************
     *
     * Iterator Functions
     *
     *
     *****************************************************************/
    
    
    public Iterator iterator(){
        return new InorderIterator();
    }
    
    public Iterator inorderIterator(){
        return new InorderIterator();
    }
    
    public Iterator preorderIterator(){
        return new PreorderIterator();
    }
    

    
     /*****************************************************************
     *
     * Iterators 
     *
     *
     *****************************************************************/ 
    
    /*
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
    */
    
    
    /**
     * Tree Iterator using preorder traversal for ordering
     */
    private class PreorderIterator implements Iterator<E>{
        Node<E> next;
        
        public PreorderIterator(){      //constructor
            //Implement Here
            next = root;
        }
        
        public boolean hasNext(){
            return (this.next != null);
            //Implement Here
        }
        
        public E next(){

            E temp = next.elem;
            
            //if has left child
            if (next.left != null){
                next = next.left;
            }

            //if has right child
            else if (next.right != null){
                next = next.right;
            }

            //if has no children (leaf)
            else{
                while (next.parent != null){//go up
                    if (next.parent.right != null && next == next.parent.left){//if came from left and has right child
                        next = next.parent.right;
                        return temp;
                    }
                    next = next.parent;//move up
                }
                next = null;//done
            }


            return temp;
            //Implement Here
        }
        


        public void remove(){                       //lab instruction said to leave blank
            // not implemented
        }
    }
    
    /**
     * Tree Iterator using inorder traversal for ordering
     */
    private class InorderIterator implements Iterator<E>{
        
        Node<E> next;
        
        public InorderIterator(){           //constructor
            //Implement Here
            next = root;
            while (next.left != null){  //go left until very left node
                next = next.left;
            }
        }
        
        public boolean hasNext(){
            return (this.next != null);
            //Implement Here
        }
        
        public E next(){            //should do left, root, right
            
            E temp = next.elem;

            if (next.right != null) {
                // Go to leftmost node in right subtree
                next = next.right;
                while (next.left != null) {
                    next = next.left;
                }
            }else{
                while (next.parent != null && next == next.parent.right){
                    next = next.parent;
                }
                next = next.parent;     //code was geekin out bc gotta move up after we find the node to right of where we wan go
            }






            

            return temp;
            //Implement Here
        }
        
        public void remove(){
            // not implemented
        }
    }
}
