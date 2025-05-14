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
        query = query.trim().toLowerCase();

        if (query.startsWith("help")) {
            printHelp();
            return;
        } else if (query.startsWith("create database") || query.startsWith("create db")) {
            createDatabase();
            return;
        } else {
                System.out.println("That is not a recognized command. For a list of valid commands, use the command `help`.");
        }
    }

    private static void printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tcreate database, create db - creates a new database and sets it as active database\n")
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
}