package com.package1;

public class LinkedList {

    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    private Node first;
    private Node last;
    private int length;

    public LinkedList(){
        length = 0;
    }

    private boolean isEmpty(){
        return first == null;
    }

    public void print(){
        Node node = first;
        while(node!=null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public void addLast(int value){
        length++;
        var node = new Node(value);
        if(isEmpty()){
            first = node;
            last = node;
        }
        else{
            last.next = node;
            last = node;
        }
    }

    public void addFirst(int value){
        length++;
        var node = new Node(value);
        if (isEmpty()){
            first = node;
            last = node;
        }
        else{
            node.next = first;
            first = node;
        }
    }

    public int indexOf(int value){
        Node node = first;
        int result = -1;
        while(node != null) {
            if (node.value == value){
                return result;
            }
            node = node.next;
            result++;
        }
        return result;
    }

    public boolean contians(int value){
        return indexOf(value) != -1;
    }

    public void removeFirst(){
        if(!isEmpty()){
            if(first == last){
                first = last = null;
                return;
            }
            first = first.next;
        }
    }

    public void removeLast(){
        if(!isEmpty()){
            if(first == last){
                first = last = null;
                return;
            }
            Node node = first;
            while (node.next!=last){
                node = node.next;
            }
            last = node;
            node.next = null;
        }
    }

    public void reverse(){
        if(isEmpty()) return;

        var before = first;
        var current = first.next;

        while (current!=null){
            var next = current.next;
            current.next = before;
            before = current;
            current = next;
        }

        last = first;
        last.next = null;
        first = before;
    }

    public int getKthFromTheEnd(int k){

        var node = first;
        var end = first;

        for (int i = 0; i <k; i++) {
            end = end.next;
        }
        while (end!=null){
            node = node.next;
            end = end.next;
        }
        return node.value;

    }


}
