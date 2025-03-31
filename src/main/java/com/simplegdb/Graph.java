package com.simplegdb;

import java.util.HashMap;

public class Graph {
    private static int currId = 0;

    static class Entity {
        protected int id;
        protected String type;
        protected HashMap<String, String> attributes;

        public Entity(HashMap<String, String> a) {
            id = currId++;
            attributes = new HashMap<>();
            if (a != null) {
                for (String key : a.keySet()) {
                    attributes.put(key, a.get(key));
                }
            }
        }
    }

    static class Node extends Entity {
        public Node(HashMap<String, String> a) {
            super(a);
            type = "node";
        }
    }

    static class Edge extends Entity {
        public Edge(HashMap<String, String> a) {
            super(a);
            type = "edge";
        }
    }
}
