package com.package1;

public class Stack {
    private int size;
    private int[] items;
    private int top;
    private int min;

    public Stack(){
        size = 10;
        items = new int[size];
        top = 0;
        min = 100000;
    }

    private void updateSize(){
        var copy = new int [size*2];
        if (size >= 0) System.arraycopy(items, 0, copy, 0, size);
        items = copy;
        size = size*2;
    }

    public boolean isEmpty(){
        return top==0;
    }

    public void push(int item){
        if (top == size) updateSize();
        items[top] = item;
        top++;
    }

    public int pop(){
        if (isEmpty()) {
            System.out.println("empty");
            return 0;
        }
        top--;
        return items[top];
    }

    public int peek(){
        if (isEmpty()) {
            System.out.println("empty");
            return 0;
        }
        return items[top-1];
    }

    public int[] toArray(){
        var result = new int[top];
        System.arraycopy(items, 0, result, 0, top);
        return result;
    }





}
