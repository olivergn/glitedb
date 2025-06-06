package com.glitedb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private ArrayList<Entity> entities;
    private int currId;

    public Graph() {
        entities = new ArrayList<>();
        currId = 0;
    }

    public Entity getEntity(int id) {
        for (Entity e : entities) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Node getNode(int id) {
        Entity e = getEntity(id);
        if (e != null) {
            if (e.getType().equals("node")) return (Node) e;
        }
        return null;
    }

    public Edge getEdge(int id) {
        Entity e = getEntity(id);
        if (e != null) {
            if (e.getType().equals("edge")) return (Edge) e;
        }
        return null;
    }

    public void addNode(HashMap<String, String> a) {
        Node n = new Node(a);
        entities.add(n);
    }

    public void addEdge(int oId, int dId, HashMap<String, String> a) {
        Node o = getNode(oId);
        Node d = getNode(dId);
        if (o == null || d == null) return;

        Edge e = new Edge(o, d, a);
        o.addEdge(e);
        entities.add(e);
    }
    
    class Entity {
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

        public int getId() {
            return id;
        }

        public String getType() {
            return type;
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

    class Node extends Entity {
        ArrayList<Edge> edges;

        public Node(HashMap<String, String> a) {
            super("node", a);
            edges = new ArrayList<>();
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }
    }

    class Edge extends Entity {
        Node origin;
        Node destination;

        public Edge(Node o, Node d, HashMap<String, String> a) {
            super("edge", a);
            origin = o;
            destination = d;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(type)
            .append(" ")
            .append(id)
            .append(" (")
            .append(origin.getId())
            .append(",")
            .append(destination.getId())
            .append(") { ");
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
}
