package com.glitedb;

import java.util.Scanner;

public class Main {
    private static Database currentDatabase;
    private static Graph currentGraph;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to GliteDB!");
        System.out.println("To create a database, use the command `create db`.");
        System.out.print("> ");

        while (sc.hasNextLine()) {
            processQuery(sc.nextLine());
            System.out.print("> ");
        }
    }

    private static void processQuery(String query) {
        query = query.trim();
        String queryLC = query.toLowerCase();

        if (queryLC.startsWith("help")) {
            printHelp();
        } else if (queryLC.startsWith("create database") || queryLC.startsWith("create db")) {
            createDatabase();
        } else if (queryLC.startsWith("create graph")) {
            createGraph(queryLC.substring(12).trim());
        } else if (queryLC.startsWith("delete graph")) {
            deleteGraph(queryLC.substring(12).trim());
        } else if (queryLC.startsWith("use graph")) {
            useGraph(queryLC.substring(9).trim());
        } else {
            System.out.println("That is not a recognized command. For a list of valid commands, use the command `help`.");
        }
    }

    private static void printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tcreate database, create db - creates a new database and sets it as active database\n")
        .append("\tcreate graph <name> - creates a graph with name <name> within the current database\n")
        .append("\tdelete graph <name> - deletes the graph with the name <name> within the current database, if one exists\n")
        .append("\tuse graph <name> - sets the active graph to the graph with the name <name> within the current database, if one exists\n")
        .append("\thelp - prints a list of available commands in GliteDB");
        System.out.println(sb.toString());
    }

    private static void createDatabase() {
        if (currentDatabase == null) {
            currentDatabase = new Database();
            System.out.println("Database created.");
        } else {
            System.out.println("A database is already open. Unsaved data may be lost. Proceed? (y/n)");
            if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                currentDatabase = new Database();
                System.out.println("Database created.");
            } else {
                System.out.println("Database creation aborted.");
            }
        }
    }

    private static void createGraph(String name) {
        if (currentDatabase == null) {
            System.out.println("No database is currently in use.");
        } else {
            try {
                currentDatabase.createGraph(name);
                System.out.println("Graph \"" + name + "\" created.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void deleteGraph(String name) {
        if (currentDatabase == null) {
            System.out.println("No database is currently in use.");
        } else {
            try {
                currentDatabase.deleteGraph(name);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void useGraph(String name) {
        if (currentDatabase == null) {
            System.out.println("No database is currently in use.");
        } else {
            try {
                Graph g = currentDatabase.getGraph(name);
                if (currentGraph == g) {
                    System.out.println("Graph \"" + name + "\" already in use.");
                    return;
                }
                currentGraph = g;
                System.out.println("Graph \"" + name + "\" in use.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}