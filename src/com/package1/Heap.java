package com.package1;

import java.util.Arrays;

public class Heap {

    private int size = 10;
    private int[] numbers = new int[size];

    private int count = 0;



    public void insert(int value){

        var index = count;
        numbers[index] = value;
        count++;

        while (index > 0 && numbers[index] > numbers[parent(index)]){
            swap(index,parent(index));
            index = parent(index);
        }
    }

    private int parent(int index){
        return  (index-1)/2;
    }


    public int remove(){

        var result = numbers[0];
        count--;
        numbers[0] = numbers[count];
        var index = 0;
        while (index < count && !isValidParent(index)){
            var largeChildIndex = largerChildIndex(index);
            swap(index,largeChildIndex);
            index = largeChildIndex;
        }

        return result;
    }

    private boolean isValidParent(int index){
        if(!hasLeftChild(index))
            return true;
        if(!hasRightChild(index))
            return numbers[index] >= leftChild(index);

        return (numbers[index] >= leftChild(index) && numbers[index] >= rightChild(index));
    }

    private boolean hasLeftChild(int index){
        return leftChildIndex(index) < count;
    }

    private boolean hasRightChild(int index){
        return rightChildIndex(index) < count;
    }

    private int largerChildIndex(int index){

        if (!hasLeftChild(index))
            return index;
        if (!hasRightChild(index))
            return leftChild(index);


        return (leftChild(index) >= rightChild(index)) ? leftChildIndex(index) : rightChildIndex(index);
    }

    private int leftChild(int index){
        return numbers[leftChildIndex(index)];
    }

    private int rightChild(int index){
        return numbers[rightChildIndex(index)];
    }

    private int leftChildIndex(int index){
        return index * 2 + 1;
    }

    private int rightChildIndex(int index){
        return index * 2 + 2;
    }

    public void print(){
        System.out.println(Arrays.toString(numbers));
    }
    private void swap(int i, int j){
        var temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }


}
