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
}
