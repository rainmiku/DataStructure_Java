package com.package1;

public class Search {

    public int linearSearch(int[] numbers, int target){
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target)
                return i;
        }

        return -1;
    }

    public int binarySearch(int[] numbers, int target){
        return binarySearch(numbers, target, 0, numbers.length-1);
    }

    private int binarySearch(int[] numbers, int target, int start, int end){

        if (start > end) return -1;
        var middle = (start + end) / 2;
        if (numbers[middle] == target)
            return middle;

        if (numbers[middle] > target)
            return binarySearch(numbers, target, start, middle-1);
        else
            return binarySearch(numbers, target, middle+1,end);
    }

    public int binarySearchIterative(int[] numbers, int target){
        int start = 0, end = numbers.length-1;

        while (start <= end){
            var middle = (start + end) / 2;
            if (numbers[middle] == target)
                return middle;
            if (numbers[middle] > target)
                end = middle - 1;
            else
                start = middle + 1;
        }

        return -1;
    }

    public int jumpSearch(int[] numbers, int target){
        int blockSize =(int) Math.sqrt(numbers.length);
        int start = 0;
        int next = blockSize;

        while (start <= numbers.length -1){
            if (numbers[next-1] < target){
                start = next;
                next = Math.min(start + blockSize, numbers.length);
            }
            else {
                for (int i = start; i < next; i++) {
                    if (numbers[i] == target )
                        return i;
                }
                return -1;
            }
        }
        return -1;
    }

    public int exponentialSearch(int[] numbers, int target){

        if (numbers[numbers.length-1] < target)
            return -1;
        int last = 0;
        int bound = 2;
        while (numbers[bound] < target && bound < numbers.length){
            last = bound;
            bound = Math.min(bound*2, numbers.length-1);
        }
        return binarySearch(numbers,target,last,bound);
    }
}
