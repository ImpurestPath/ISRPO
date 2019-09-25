package ru.ifmo.isrpo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cli {
    private static Scanner scanner = new Scanner(System.in);

    Cli() {
    }

    public static void main(String[] args) {
        System.out.println("Select option:");
        System.out.println("1. Register new player");
        System.out.println("2. Register new game");
        System.out.println("3. Register new tournament");
        System.out.println("4. Exit");
        switch (scanner.nextInt()) {
        case 1:
            newPlayer();
            main(null);
            break;
        case 2:
            newGame();
            main(null);
            break;
        case 3:
            newTournament();
            main(null);
            break;
        case 4:
            break;
        }

    }

    public static void newPlayer() {
        System.out.println("Enter name:");
        System.out.println("Your id: " + Integer.toString(App.pr.createPlayer(scanner.nextLine())));
    }

    public static void newGame() {
        System.out.println("Enter amount of players and their id (\"3 1 2 3\"):");
        List<Player> players = new ArrayList<>();
        int amount = scanner.nextInt();
        for (int i = 0; i < amount; i++) {
            players.add(App.pr.getPlayer(scanner.nextInt()));
        }
        System.out.println("Enter score for each player(\" 1 0 0\")");
        List<Float> score = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            score.add(scanner.nextFloat());
        }
        System.out.println("Game id: " + App.gr.createGame(players, score));
    }

    public static void newTournament() {
        System.out.println("Enter amount of games and their id (\"3 1 2 3\")");
        List<Game> games = new ArrayList<>();
        int amount = scanner.nextInt();
        for (int i = 0; i < amount; i++) {
            games.add(App.gr.getGame(scanner.nextInt()));
        }
        System.out.println("Enter amount of players and their id (\"3 1 2 3\"):");
        List<Player> players = new ArrayList<>();
        amount = scanner.nextInt();
        for (int i = 0; i < amount; i++) {
            players.add(App.pr.getPlayer(scanner.nextInt()));
        }
        System.out.println("Enter score for each player(\" 1 0 0\")");
        List<Float> score = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            score.add(scanner.nextFloat());
        }
        System.out.println("Tournament id: " + App.tr.createTournament(games, players, score));
    }
}