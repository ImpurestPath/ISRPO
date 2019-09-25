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

public class PlayerRepository {
    private List<Player> players;
    private int lastId;
    private PrintWriter pw;

    PlayerRepository(File file) {
        players = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                players.add(new Player(scanner));
            }
            if (!players.isEmpty())
                lastId = players.get(players.size() - 1).getId();
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

    public List<Player> getPlayers() {
        return players;
    }

    public int createPlayer(String name) {
        Player p = new Player(lastId + 1, name);
        players.add(p);
        p.save(pw);
        return ++lastId;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Player getPlayer(int id) {
        for (Player player : players) {
            if (player.getId() == id)
                return player;
        }
        return null;
    }

    public void close() {
        pw.close();
    }
}