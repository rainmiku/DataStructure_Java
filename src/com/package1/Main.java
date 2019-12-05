package com.package1;


import java.text.NumberFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        int[] a = {3,2,4,1,5};

        var sort = new Sort();

        sort.mergeSort(a);

        System.out.println(Arrays.toString(a));

        System.out.println("done");

    }
}