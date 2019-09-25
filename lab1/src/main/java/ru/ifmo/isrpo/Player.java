package ru.ifmo.isrpo;

import java.io.PrintWriter;
import java.util.Scanner;

public class Player {

    private int id;
    private String name;

    public Player(Scanner scanner) {
        load(scanner);
    }

    public Player() {
        id = 0;
        name = "error";
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void save(PrintWriter ps) {
        ps.println(String.format("%d %s", id, name));
    }

    public void load(Scanner scanner) {
        if (scanner.hasNextInt()) {
            this.id = scanner.nextInt();
            if (scanner.hasNextLine())
                this.name = scanner.nextLine();
        }
    }
}