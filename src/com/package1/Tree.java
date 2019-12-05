package com.package1;

import java.util.ArrayList;

public class Tree {
    private static class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root = null;
    private static final int infi = 100000000;
    private int size;

    public Tree(){
        this.size = 0;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(int value){

       return find(value) != null;
    }

    private Node find(int value){
        var current = root;
        while (current != null){
            if (value == current.value)
                return current;
            if (value < current.value)
                current = current.leftChild;
            else
                current = current.rightChild;
        }
        return null;
    }

    public void insert(int value){
        var node = new Node(value);
        size++;
        if (isEmpty()){
            root = node;
            return;
        }
        var current = root;
        while (current != null){
            if (value < current.value){
                if (current.leftChild == null){
                    current.leftChild = node;
                    return;
                }
                current = current.leftChild;
            }
            else{
                if(current.rightChild == null){
                    current.rightChild = node;
                    return;
                }
                current = current.rightChild;
            }
        }


    }

    public void traversePreOder(){
        traversePreOder(root);
    }

    private void traversePreOder(Node root){
        if (root == null)
            return;
        System.out.println(root.value);
        traversePreOder(root.leftChild);
        traversePreOder(root.rightChild);
    }

    public void traverseInOder(){
        traverseInOder(root);
    }

    private void traverseInOder(Node root){
        if (root == null)
            return;
        traversePreOder(root.leftChild);
        System.out.println(root.value);
        traversePreOder(root.rightChild);
    }

    public void traversePostOder(){
        traversePostOder(root);
    }

    private void traversePostOder(Node root){
        if (root == null)
            return;
        traversePostOder(root.leftChild);
        traversePostOder(root.rightChild);
        System.out.println(root.value);
    }

    public boolean equals(Tree other){
        if (other == null)
            return false;
        return equals(root,other.root);
    }

    private boolean equals(Node first, Node second){
        if (first == null && second == null)
            return true;
        if (first != null && second != null)
            return first.value == second.value &&
                    equals(first.leftChild, second.leftChild) &&
                    equals(first.rightChild, second.rightChild);
        return false;
    }

    public boolean isBinarySearchTree(){
        return isBinarySearchTree(root, -infi, infi);
    }


    public void swap(int a, int b){
        var nodeA = find(a);
        var nodeB = find(b);

        var temp = nodeA.value;
        nodeA.value = nodeB.value;
        nodeB.value = temp;
    }

    private boolean isBinarySearchTree(Node root, int min, int max){
        if (root == null)
            return true;

        if (root.value > min && root.value < max)
            return isBinarySearchTree(root.leftChild, min, root.value) &&
                    isBinarySearchTree(root.rightChild, root.value, max);

        return false;
    }

    public ArrayList<Integer> getNodeAtDistance(int k){
        var list = new ArrayList<Integer>();

        printNodeAtDistance(root,k,list);

        return list;
    }

    private void printNodeAtDistance(Node root,int k, ArrayList<Integer> list){
        if (root == null)
            return;
        if (k == 0){
            list.add(root.value);
            return;
        }
        printNodeAtDistance(root.leftChild,k-1,list);
        printNodeAtDistance(root.rightChild,k-1,list);

    }

    public int height(){
        return height(root);
    }

    private int height(Node root){
        if (root == null)
            return 0;
        return Math.max(height(root.leftChild),height(root.rightChild))+1;
    }

    public void travelLevelOrder() {
        for (int i = 0; i < height(); i++) {
            var list =  getNodeAtDistance(i);
            System.out.println(list);
        }
    }

    public int size(){
        return size;
    }

    public int countLeaves(){
        return countLeaves(root);
    }

    private int countLeaves(Node root){

        if (isLeaf(root))
            return 1;
        return countLeaves(root.leftChild)+ countLeaves(root.rightChild);
    }

    public int max(){
        return max(root);
    }

    private int max(Node root){
        if (isLeaf(root))
            return root.value;
        return max(root.rightChild);
    }

    public boolean areSibling(int a, int b){
        return areSibling(root,a,b);
    }

    private boolean areSibling(Node root, int a, int b){
        if (isLeaf(root))
            return false;
        if (root.rightChild.value == a && root.leftChild.value == b || root.rightChild.value == b && root.leftChild.value == a){
            return true;
        }
        return areSibling(root.leftChild,a,b) || areSibling(root.rightChild,a,b);
    }

    public ArrayList<Integer> getAncestors(int target){
        var list = new ArrayList<Integer>();
        getAncestors(root,target,list);
        return list;
    }

    private boolean getAncestors(Node root, int target, ArrayList<Integer> list){
        if (root == null)
            return false;
        if (root.value == target)
            return true;

        if (getAncestors(root.leftChild,target,list) || getAncestors(root.rightChild,target,list)){
            list.add(root.value);
            return true;
        }
        return false;

    }

    private boolean isLeaf(Node node){
        return node.leftChild == null && node.rightChild == null;
    }


}
