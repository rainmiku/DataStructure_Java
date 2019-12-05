package com.package1;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BalanceExpressionDetector {
    private final List<Character> leftBlanket = Arrays.asList('(','<','{','[');
    private final List<Character> rightBlanket = Arrays.asList(')','>','}',']');

    public boolean ifBalanced(String str){
        Stack<Character> stack = new Stack<>();
        for (char ch:str.toCharArray()) {
            if(isLeftBlanket(ch))
                stack.push(ch);
            if(isRightBlanket(ch)){
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if (!matchBlanket(top,ch)) return false;
            }
        }
        return stack.isEmpty();
    }
    private boolean isLeftBlanket(char ch){
        return leftBlanket.contains(ch);
    }
    private boolean isRightBlanket(char ch){
        return rightBlanket.contains(ch);
    }
    private boolean matchBlanket(char left, char right){
        return (leftBlanket.indexOf(left) == rightBlanket.indexOf(right));
    }
}


