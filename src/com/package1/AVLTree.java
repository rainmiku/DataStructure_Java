package com.package1;

public class AVLTree {

    private static class AVLNode {
        private int value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        public AVLNode(int value) {
            this.value = value;
        }
    }

    private AVLNode root = null;

    public void insert(int value){
        root = insert(root,value);
    }

    private AVLNode insert(AVLNode root, int value){
        if (root == null){
            return new AVLNode(value);
        }

        if (value < root.value)
            root.leftChild = insert(root.leftChild,value);
        else
            root.rightChild = insert(root.rightChild,value);

        root.height = getHeight(root);

        root = balance(root);

        return root;

    }

    private AVLNode balance(AVLNode node){
        if (isLeftHeavy(node))
        {
            if (balanceFactor(node.leftChild) < 0)
                node.leftChild = leftRotate(node.leftChild);
            node = rightRotate(node);
        }
        else
            if (isRightHeavy(node))
            {
                if (balanceFactor(node.rightChild) > 0)
                    node.rightChild = rightRotate(node.rightChild);
                node = leftRotate(node);
            }

        return node;
    }


    private AVLNode leftRotate(AVLNode root){
        var newRoot = root.rightChild;
        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;

        root.height = getHeight(root);
        newRoot.height = getHeight(newRoot);

        return newRoot;
    }

    private AVLNode rightRotate(AVLNode root){
        var newRoot = root.leftChild;
        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;

        root.height = getHeight(root);
        newRoot.height = getHeight(newRoot);

        return newRoot;
    }

    private int getHeight(AVLNode node){
        return Math.max(height(node.leftChild), height(node.rightChild))+1;
    }

    private boolean isLeftHeavy(AVLNode node){
        return balanceFactor(node) > 1;
    }
    private boolean isRightHeavy(AVLNode node){
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode node){
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    private int height(AVLNode node){
        return (node == null) ? -1 : node.height;
    }




}
