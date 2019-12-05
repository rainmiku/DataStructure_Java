package com.package1;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {
    private Queue<Integer> queue1 = new ArrayDeque<>();
    private Queue<Integer> queue2 = new ArrayDeque<>();
    private int top;

    public boolean isEmpty(){
        return queue1.isEmpty();
    }

    public void swapQueues(){
        var temp = queue1;
        queue1 = queue2;
        queue2 = queue1;
    }

    public void push(int item){
       top = item;
       queue1.add(top);
    }

    public int pop(){
        if (isEmpty()) {
            System.out.println("empty");
            return 0;
        }
        while (queue1.size()>1) {
            top = queue1.remove();
            queue2.add(top);
        }
        swapQueues();
        return queue2.remove();
    }

    public int peek(){
        if (isEmpty()) {
            System.out.println("empty");
            return 0;
        }
        return top;
    }

    public int size(){
        return queue1.size();
    }

    public void print(){
        System.out.println(queue1);
    }
}
