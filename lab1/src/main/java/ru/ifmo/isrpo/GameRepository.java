package ru.ifmo.isrpo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameRepository {
    private List<Game> games;
    private int lastId;
    private PrintWriter pw;

    GameRepository(File file, PlayerRepository ps) {
        games = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                games.add(new Game(scanner, ps));
            }
            if (!games.isEmpty())
                lastId = games.get(games.size() - 1).getId();
            else
                lastId = 0;
            scanner.close();
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            pw = new PrintWriter(br);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Game> getGames() {
        return games;
    }

    public int createGame(List<Player> players, List<Float> score) {
        Game g = new Game(lastId + 1, players, score);
        games.add(g);
        g.save(pw);
        
        return ++lastId;
    }

    public Game getGame(int id) {
        for (Game game : games) {
            if (game.getId() == id)
                return game;
        }
        return null;
    }

    public void close() {
        pw.close();
    }
}