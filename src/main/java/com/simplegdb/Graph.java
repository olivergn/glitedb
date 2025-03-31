package com.simplegdb;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private static int currId = 0;

    static class Entity {
        protected int id;
        protected String type;
        protected HashMap<String, String> attributes;

        public Entity(String t, HashMap<String, String> a) {
            id = currId++;
            type = t;
            attributes = new HashMap<>();
            if (a != null) {
                for (String key : a.keySet()) {
                    attributes.put(key, a.get(key));
                }
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(type)
            .append(" ")
            .append(id)
            .append(" { ");
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                sb.append(entry.getKey())
                .append(": \"")
                .append(entry.getValue())
                .append("\" ");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    static class Node extends Entity {
        public Node(HashMap<String, String> a) {
            super("node", a);
        }
    }

    static class Edge extends Entity {
        public Edge(HashMap<String, String> a) {
            super("edge", a);
        }
    }
}
