package com.package1;


public class Sort {

    public void bubleSort(int[] numbers){
        boolean isSorted;
        for (int i = 0; i < numbers.length ; i++) {
            isSorted = true;
            for (int j = 0; j < numbers.length-i-1; j++) {
                if (numbers[j] > numbers[j+1]) {
                    swap(numbers, j, j + 1);
                    isSorted = false;
                }
            }
            if (isSorted)
                return;
        }
    }

    public void selectSort(int[] numbers){
        for (int i=0;i<numbers.length-1; i++){
            int min = numbers[i];
            int minIndex = i;
            for (int j = i+1; j <numbers.length;j++) {

                if (numbers[j] < min) {
                    min = numbers[j];
                    minIndex = j;
                }
            }
            swap(numbers,i,minIndex);
        }
    }

    public void insertSort(int[] numbers){
        for (int i = 1; i < numbers.length; i++) {
            var current = numbers[i];
            int j;
            for (j = i; j >= 1; j--) {
                if (numbers[j-1] > current)
                    numbers[j] = numbers[j-1];
                else
                    break;
            }
            numbers[j] = current;
        }
    }

    public void mergeSort(int[] numbers){


        var middle = numbers.length/2;

        if (middle == 0) return;

        var left = new int[middle];
        var right = new int[numbers.length-middle];

        for (int i = 0; i < middle; i++) {
            left[i] = numbers[i];
        }
        for (int i = middle; i < numbers.length; i++){
            right[i-middle] = numbers[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(left,right,numbers);

    }

    private void merge(int[] left, int[] right, int[] merged){

        int i=0,j=0,k=0;
        while (i < left.length && j < right.length){
            if (left[i] < right[j])
                merged[k++] = left[i++];
            else
                merged[k++] = right[j++];
        }
        while (i < left.length)
            merged[k++] = left[i++];
        while (j < right.length)
            merged[k++] = left[i++];
    }


    private void swap(int[] numbers, int a, int b){
        var temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }

}
