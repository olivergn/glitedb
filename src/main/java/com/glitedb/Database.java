package com.glitedb;

import java.util.HashMap;

public class Database {
    private HashMap<String, Graph> graphs;

    public Database() {
        graphs = new HashMap<>();
    }

    public void createGraph(String name, Graph graph) throws Exception {
        if (graphs.containsKey(name)) {
            throw new Exception("A graph with this name already exists.");
        }
        else {
            graphs.put(name, graph);
        }
    }

    public Graph getGraph(String name) throws Exception {
        if (!graphs.containsKey(name)) {
            throw new Exception("No such graph with this name exists.");
        } else {
            return graphs.get(name);
        }
    }

    public void deleteGraph(String name) throws Exception {
        if (!graphs.containsKey(name)) {
            throw new Exception("No such graph with this name exists.");
        } else {
            graphs.remove(name);
        }
    }
}
