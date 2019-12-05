package com.package1;

import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private static class Node {
        private String lable;
        public Node(String lable) {
            this.lable = lable;
        }

        @Override
        public String toString() {
            return lable;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, ArrayList<Node>> adjacencyList = new HashMap<>();

    public void addNode(String lable) {
        var node = new Node(lable);
        nodes.putIfAbsent(lable,node);
        adjacencyList.putIfAbsent(node,new ArrayList<>());
    }

    public int size(){
        return nodes.size();
    }

    public void addEdge(String from, String to){
        if (!isEdgeExist(from,to))
            return;

        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        adjacencyList.get(fromNode).add(toNode);
    }
    
    public void print(){
        for (var source : adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            System.out.println(source + " is connected to " + targets);
        }
    }

    public void removeNode(String label){
        var node = nodes.get(label);
        if (node == null){
            System.out.println("node is not exsit");
            return;
        }
        adjacencyList.remove(node);
        for (var source:adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            targets.remove(node);
        }
        nodes.remove(label);
    }

    public void removeEdge(String from, String to){

        if (!isEdgeExist(from,to))
            return;

        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        adjacencyList.get(fromNode).remove(toNode);
    }

    private boolean isEdgeExist(String from, String to){
        if (!isNodeExist(from))
            return false;

        if (!isNodeExist(to))
            return false;

        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (adjacencyList.get(fromNode).contains(toNode)){
            System.out.println(from + " --> " + to + "Edge is not exist");
            return false;
        };

        return true;
    }

    private boolean isNodeExist(String label){
        var node = nodes.get(label);
        if (node != null)
            return true;
        else{
            System.out.println(label+" node is not exist");
            return false;
        }
    }

    public void dfsRecursive(String start){
        HashSet<Node> visited = new HashSet();

        if (nodes.containsKey(start))
            dfsRecursive(nodes.get(start),visited);
        else
            System.out.println(start+"not exist");

    }

    private void dfsRecursive(Node start,HashSet<Node> visited){
        if (visited.size() == size())
            return;

        System.out.println(start.lable);
        visited.add(start);

        for (var neighbor: adjacencyList.get(start)) {
            if (visited.contains(neighbor))
                return;
            dfsRecursive(neighbor,visited);
        }
    }

    public void dfsIterative(String start){
        HashSet<Node> visited = new HashSet<>();
        Stack<Node> call = new Stack<>();

        var current = nodes.get(start);
        if (current != null)
            call.push(current);
        while (!call.empty()){
            current = call.pop();
            System.out.println(current.lable);
            visited.add(current);
            for (var neighbor: adjacencyList.get(current)) {
                if (visited.contains(neighbor))
                    continue;
                call.add(neighbor);
            }
        }
    }

    public void bfs(String start){
        var node = nodes.get(start);
        if (node == null)
            return;

        HashSet<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()){
            var current = queue.remove();
            System.out.println(current.lable);
            visited.add(current);

            for (var neighbor: adjacencyList.get(current)) {
                if (!visited.contains(neighbor))
                    queue.add(neighbor);
            }
        }
    }

    public List<String> topologicSort(){
        LinkedList<String> list = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        Stack<Node> call = new Stack<>();
        for (var node: nodes.values()) {
            topologicSort(node, call, visited);

        }
        while (!call.empty())
            list.add(call.pop().lable);
        return list;
    }

    private void topologicSort(Node root,Stack<Node> stack, HashSet<Node> visited){
        if (visited.size() == size())
            return;

        visited.add(root);
        for (var neighbor: adjacencyList.get(root)) {
            if (!visited.contains(neighbor))
                topologicSort(neighbor,stack,visited);
        }
        stack.push(root);
    }

    public boolean isCycle(){
        HashSet<Node> all = new HashSet<>();
        all.addAll(nodes.values());

        HashSet<Node> visiting = new HashSet<>();

        HashSet<Node> visited = new HashSet<>();


        while (!all.isEmpty()){
            var current = all.iterator().next();
            if (hasCycle(current,all,visiting,visited));
                return true;
        }

        return false;
    }

    public boolean hasCycle(Node node, HashSet<Node> all, HashSet<Node> visiting, HashSet<Node> visited){
        if (all.isEmpty())
            return false;

        all.remove(node);
        visiting.add(node);

        boolean sum = true;

        for (var neighbour: adjacencyList.get(node)) {
            if (visited.contains(node))
                continue;
            if (visiting.contains(node))
                return true;
            if(hasCycle(neighbour, all, visiting,visited))
                return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
}
