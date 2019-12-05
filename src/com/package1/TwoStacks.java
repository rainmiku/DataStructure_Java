package com.package1;

public class TwoStacks {
    private int size = 3;
    private int top1 = 0;
    private int top2 = 0;
    private int[] items = new int[size];

    public boolean isEmpty1(){
        return top1 == 0;
    }
    public boolean isEmpty2(){
        return top2 == 0;
    }

    public boolean isFull1(){
        return top1 == size-top2;
    }
    public boolean isFull2(){
        return isFull1();
    }

    public void push1(int item){
       if (isFull1()){
           System.out.println("stackoverflow");
           return;
       }
       items[top1] = item;
       top1++;
    }

    public void push2(int item){
        if (isFull2()){
            System.out.println("stackoverflow");
            return;
        }
        items[size-top2-1] = item;
        top2++;
    }

    public int pop1(){
        if(isEmpty1()){
            System.out.println("empty1");
            return 0;
        }
        top1--;
        return items[top1];
    }

    public int pop2(){
        if(isEmpty2()){
            System.out.println("empty2");
            return 0;
        }
        top2--;
        return items[size-top2-1];
    }
}
