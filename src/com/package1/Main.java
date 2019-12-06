package com.package1;


import java.text.NumberFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        int[] a = {1,2,3,4,5};

        Search search =  new Search();

        var index = search.binarySearchIterative(a, 6);

        System.out.println(index);

        System.out.println("done");

    }
}