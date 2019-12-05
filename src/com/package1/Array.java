package com.package1;

import java.util.Arrays;

public class Array {
    private int[] numbers;
    private int end;
    private int length;
    public Array(int length) {
        numbers = new int[length];
        this.length = length;
        end = 0;
    }


    public int[] getArray(){
        return numbers;
    }

    public int getEnd(){
        return end;
    }

    public void insert(int item){
        if (end < length){
            numbers[end] = item;
        }
        else{
            int[] copy = new int[length*2];
            if (length >= 0) System.arraycopy(numbers, 0, copy, 0, length);
            copy[length] = item;
            numbers = copy;
            length = length*2;
        }
        end++;
    }

    public void insertAt(int item, int idex){
        if (end >= length){
            int[] copy = new int[length*2];
            length = length*2;
            if (end >= 0) System.arraycopy(numbers, 0, copy, 0, end);
            numbers = copy;
        }
        for (int i = end; i >=idex ; i--) {
            numbers[i]=numbers[i-1];
        }
        numbers[idex] = item;
        end++;
    }

    public void removeAt(int index){
        int[] copy = new int[length-1];
        if (length - 1 - index >= 0) System.arraycopy(numbers, index + 1, numbers, index, length - 1 - index);
        if (length - 1 >= 0) System.arraycopy(numbers, 0, copy, 0, length - 1);
        numbers = copy;
        length--;
    }

    public int max(){
        int result = -100000;
        for (int i = 0; i < end; i++) {
            if (numbers[i]>result){
                result = numbers[i];
            }
        }
        return result;
    }

    public int[] intersect(Array other){
        int[] otherNumber = other.getArray();
        int otherEnd = other.getEnd();
        int[] result = new int[end];
        int resultEnd = 0;

        for (int i = 0; i < end; i++) {
            for (int j = 0; j < otherEnd; j++) {
                if(numbers[i] == otherNumber[j]){
                    result[resultEnd] = numbers[i];
                    resultEnd++;
                    break;
                }
            }
        }
        int[] copy = new int[resultEnd];
        System.arraycopy(result, 0, copy, 0, resultEnd);
        return copy;

    }

    public void reverse(){
        for (int i = 0; i < end / 2; i++) {
            int t;
            t = numbers[i];
            numbers[i] = numbers[end-i-1];
            numbers[end-i-1] = t;
        }
    }



    public void print(){
        for (int i = 0; i < end; i++) {
            System.out.println(numbers[i]);
        }
    }

    public int indexOf(int item){
        for (int i = 0; i < end; i++) {
            if (numbers[i] == item){
                return i;
            }
        }
        return -1;
    }
}
