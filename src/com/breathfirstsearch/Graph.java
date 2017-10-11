package com.breathfirstsearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
    private HashMap<Integer, Node> nodeLookup = new HashMap<>();
    public static void main(String[] args) {

    }

    public static class Node {
        private int id;
        LinkedList<Node>  adjacent = new LinkedList<>();
        private Node(int id) {
            this.id = id;
        }
    }
    private Node getNode(int id) {
        if(nodeLookup.containsKey(id))
            return nodeLookup.get(id);
            Node node = new Node(id);
            nodeLookup.put(id, node);
            return node;
    }

    private void addEdge(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        s.adjacent.add(d);
    }

    public boolean hasPathDFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDFS(s, d, visited);
    }

    private boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited) { // uses a stack -- pop visited
        if(visited.contains(source.id)) {
            return false;
        }
        visited.add(source.id);
        if (source.equals(destination)) {
            return true;
        }
        for (Node child : source.adjacent) {
            if (hasPathDFS(child, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPathBFS(int source, int destination) {
        return hasPathBFS(getNode(source), getNode(destination));
    } //use id's and call recusive method

    private boolean hasPathBFS(Node source, Node destination) { // uses a queue -- dequeue
        LinkedList<Node> nextToVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        nextToVisit.add(source);
        while(!nextToVisit.isEmpty()) {
            Node node = nextToVisit.remove();
            if (node == destination) {
                return true;
            }
            if (visited.contains(node.id)) {
                continue; // if there's a path
            }
            visited.add(node.id); // mark as visited

            for (Node child: node.adjacent) {
                nextToVisit.add(child);// add next to visit once
            }
        }
        return false;
    }
}
