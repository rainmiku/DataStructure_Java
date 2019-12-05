package com.package1;


import java.util.Arrays;

public class ListQueue {

    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    private Node first;
    private Node last;

    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(int value){

        var node = new Node(value);

        if (isEmpty())
            first = last = node;
        else{
            last.next = node;
            last = node;
        }
    }

    public int dequeue(){
        if (isEmpty()) return 0;

        int result = first.value;
        if (first == last){
            first = last = null;
        }
        else{
            first = first.next;
        }
        return result;
    }

    public int peek(){
        if (isEmpty()) return 0;
        return first.value;
    }

    public int size(){
        var node = first;
        int result = 0;
        while (node!=null){
            result++;
            node = node.next;
        }
        return result;
    }

    public void print(){
        int [] copy = new int[size()];
        var node = first;
        int i = 0;
        while (node!=null){
            copy[i] = node.value;
            i++;
            node = node.next;
        }
        System.out.println(Arrays.toString(copy));
    }
}
