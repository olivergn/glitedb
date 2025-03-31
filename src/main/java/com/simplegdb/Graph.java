package com.simplegdb;

import java.util.HashMap;

public class Graph {
    private int currId;

    static class Entity {
        private int id;
        private HashMap<String, String> attributes;
    }

    static class Node extends Entity {

    }

    static class Edge extends Entity {

    }
}
