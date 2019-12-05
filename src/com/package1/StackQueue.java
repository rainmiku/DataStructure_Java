package com.package1;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Stack;

public class StackQueue {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public boolean isEmpty(){
        return s1.isEmpty();
    }

    public void enqueue(int item){
        s1.push(item);
    }
    public int dequeue(){
        if(s2.isEmpty()){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();

    }

}
