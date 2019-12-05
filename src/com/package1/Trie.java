package com.package1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Trie {
    private static class Node {
        private char value;
        private HashMap<Character,Node> childeren = new HashMap<>();
        boolean isTheEndOfTheWord = false;

        public Node(char value) {
            this.value = value;
        }

        public boolean hasChild(char ch){
            return childeren.containsKey(ch);
        }

        public void addChild(char ch){
            childeren.put(ch, new Node(ch));
        }

        public Node getChild(char ch){
            return childeren.get(ch);
        }

        public Node[] getChildren(){
           return childeren.values().toArray(new Node[0]);
        }

        public boolean hasChildren(){
            return !childeren.isEmpty();
        }

        public void removeChildren(char ch){
            childeren.remove(ch);
        }

        public int countChildren(){
            return childeren.size();
        }


    }

    private Node root = new Node('\0');

    public void insert(String word){

        var current = root;

        for (var ch: word.toCharArray()) {
            if (!current.hasChild(ch)){
                current.addChild(ch);
            }
            current = current.getChild(ch);

        }
        current.isTheEndOfTheWord = true;

    }

    public boolean contains(String word){

        if (word == null)
            return false;

        var current = root;
        for (var ch: word.toCharArray()){
            if (current.hasChild(ch))
                current = current.getChild(ch);
            else
                return false;
        }

        return current.isTheEndOfTheWord;
    }

    public boolean containsRecursive(String word){
        return containsRecursive(word,root,0);
    }

    private boolean containsRecursive(String word, Node root, int index){
        if (root == null)
            return false ;

        if (index == word.length())
        {
            return root.isTheEndOfTheWord;
        }

        var ch = word.charAt(index);
        if (root.hasChild(ch))
            return containsRecursive(word,root.getChild(ch),index+1);
        return false;
    }

    public void remove(String word){
        if (word == null)
            return;
        remove(word,root,-1);
    }

    private void remove(String word, Node current, int step){

        if (step == word.length()-1)
            return;

        var ch = word.charAt(step+1);
        var child = current.getChild(ch);

        if (current.hasChild(ch))
           remove(word,child,step+1);

        if (step == word.length()-2){
            child.isTheEndOfTheWord = false;
        }

        if (!child.isTheEndOfTheWord && !child.hasChildren())
            current.removeChildren(child.value);
    }



    public List<String> findWords(String prefix){
        List<String> list = new LinkedList<>();

        var current = root;
        for (var ch : prefix.toCharArray()) {
            if (current.hasChild(ch))
                current = current.getChild(ch);
            else
                return list;
        }

        for (var child : current.getChildren()) {
            findWords(prefix+child.value, child, list);
        }

        return list;
    }


    private void findWords(String prefix, Node root, List<String> list){

        if (root == null)
            return;

        if (root.isTheEndOfTheWord)
            list.add(prefix);

        for (var child : root.getChildren()) {
            findWords(prefix+child.value, child, list);
        }

    }

    public int countWords(){
        return countWords(root);
    }

    private int countWords(Node root){
        if (root == null)
            return 0;

        var sum = 0;
        if(root.isTheEndOfTheWord)
            sum++;

        for (var child:root.getChildren()) {
            sum += countWords(child);
        }
        return sum;
    }

    public String longestCommonPrefix(List<String> strings){
        for (var string: strings) {
            insert(string);
        }

        String result = "";
        int index = 0;
        var current = root;
        while(current.countChildren()==1)
        {
            if (current.isTheEndOfTheWord)
                return result;
            for (var child: current.getChildren()) {
                result += child.value;
                current = child;
                break;
            }
        }
        return result;
    }
}

