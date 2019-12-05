package com.package1;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public int min(){
        return minStack.peek();
    }

    public void push(int item){
        stack.push(item);
        if (minStack.isEmpty() || item<min()){
            minStack.push(item);
        }
    }

    public int pop(){
       int top = stack.pop();
       if (top == min()){
           minStack.pop();
       }
       return top;
    }
}
