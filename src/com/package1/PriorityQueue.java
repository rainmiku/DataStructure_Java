package com.package1;

import java.util.Arrays;

public class PriorityQueue {
    private int size = 4;
    private int[] array = new int[size];
    private int F = 0;
    private int length = 0;


    public boolean isEmpty(){
        return length == 0;
    }

    public boolean isFull(){
        return length == size;
    }

    private int getArrayIndex(int i){
        return (F+i) % size;
    }

    public void enqueue(int item){
        if (isFull()) {
            System.out.println("Queue is Full!");
            return;
        }

        int insertIndex = length;
        for (int i = 0; i < length; i++) {
            if(item < array[getArrayIndex(i)] ){
                insertIndex = i;
                break;
            }
        }
        for (int i = length-1; i >= insertIndex ; i--){
            array[getArrayIndex(i+1)] = array[getArrayIndex(i)];
        }
        array[getArrayIndex(insertIndex)] = item;
        length++;
    }

    public int dequeue(){
        if (isEmpty()){
            System.out.println("Queue is empty!");
            return 0;
        }
        int head = array[F];
        F = (F + 1) % size;
        length--;
        return head;
    }

    public int peek(){
        if (isEmpty()){
            System.out.println("Queue is empty!");
            return 0;
        }
        return array[F];
    }

    public void print(){
        var copy = new int[length];
        for (int i = 0; i < length; i++) {
            copy[i] = array[getArrayIndex(i)];
        }
        System.out.println(Arrays.toString(copy));
    }
    
}
