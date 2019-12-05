package com.package1;

import java.util.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class WeightedGraph {
    private static class Node {
        private String lable;
        private List<Edge> edges = new ArrayList<>();

        public Node(String lable) {
            this.lable = lable;
        }

        @Override
        public String toString() {
            return lable;
        }

        public void addEdge(Node to, int weight){
            edges.add(new Edge(this,to,weight));
        }

        public List<Edge> getEdges(){
            return edges;
        }


    }

    private static class Edge {
        private Node from;
        private Node to;
        private int weight;


        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " ---> " + to + " with " + weight ;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();

    public void addNode(String lable) {
        nodes.putIfAbsent(lable,new Node(lable));
    }

    public void addEdge(String from, String to, int weight){
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null){
            System.out.println("Node not exist");
            return;
        }

        fromNode.addEdge(toNode,weight);
        toNode.addEdge(fromNode,weight);
    }

    public void print(){
        for (var node: nodes.values()) {
            var edges = node.getEdges();
            for (var edge:edges) {
                System.out.println(edge);
            }
        }
    }

    private static class NodeEntry{
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    public int getShortestDistance(String from, String to){
        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.priority));
        HashMap<Node,Integer> distances = new HashMap<>();
        HashMap<Node,Node> previousNodes = new HashMap<>();
        HashSet<Node> visited = new HashSet<>();

        for (var node:nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        var fromNode = nodes.get(from);
        distances.replace(fromNode,0);
        queue.add(new NodeEntry(fromNode,0));

        while (!queue.isEmpty()){
            var current = queue.remove().node;
            visited.add(current);
            for (var neighbourEdege:current.edges) {
                var neighbour = neighbourEdege.to;
                if (visited.contains(neighbour))
                    continue;

                var newDistance = neighbourEdege.weight + distances.get(current);
                if (newDistance < distances.get(neighbour)){
                    distances.replace(neighbour, newDistance);
                    previousNodes.put(neighbour, current);
                    queue.add(new NodeEntry(neighbour,newDistance));
                }
            }
        }

        ArrayList<Node> path = new ArrayList<>();

        var current = nodes.get(to);
        while(current != null){
            path.add(current);
            current = previousNodes.get(current);
        }
        System.out.println(path);

        return distances.get(nodes.get(to));
    }

    public boolean hasCycle(){

        var visited = new HashSet<Node>();

        for (var node:nodes.values()) {
            if (visited.contains(node))
                continue;
            if (hasCycle(node,node,visited))
                return true;
        }
        return false;
    }

    private boolean hasCycle(Node root, Node parent,HashSet<Node> visited){

        visited.add(root);
        for (var edge:root.edges) {
            if (edge.to == parent)
                continue;
            if (visited.contains(edge.to) || hasCycle(edge.to, root, visited)){
                return true;
            }
        }
        return false;
    }

    public WeightedGraph MinimumSpanningTree(){
        var visited = new HashSet<Node>();
        var queue = new PriorityQueue<Edge>(Comparator.comparingInt(e->e.weight));
        var tree = new WeightedGraph();

        Node first = nodes.values().iterator().next();
        tree.addNode(first.lable);
        queue.addAll(first.edges);
        visited.add(first);
        while (tree.nodes.size() < nodes.size()){
            var minEdge = queue.remove();
            var next = minEdge.to;
            if (visited.contains(next))
                continue;
            tree.addNode(next.lable);
            tree.addEdge(minEdge.from.lable, next.lable, minEdge.weight);
            visited.add(next);
            for (var edge:next.edges) {
                if (visited.contains(edge.to))
                    continue;
                queue.add(edge);
            }
        }
        return tree;
    }
}
