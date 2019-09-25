package ru.ifmo.isrpo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int id;
    private List<Player> players;
    private List<Float> score;
    private List<Player> winners;

    public Game(int id, List<Player> players, List<Float> score, List<Player> winners) {
        this.id = id;
        this.players = players;
        this.score = score;
        this.winners = winners;
    }
    public Game(int id, List<Player> players, List<Float> score) {
        this.id = id;
        this.players = players;
        this.score = score;
        this.winners = new ArrayList<>();
    }


    public Game(Scanner scanner, PlayerRepository ps) {
        load(scanner, ps);
    }

    public int getId() {
        return this.id;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Float> getScore() {
        return this.score;
    }

    public List<Player> getWinner() {
        return !this.winners.isEmpty() ? this.winners : calculateWinners();
    }

    public List<Player> calculateWinners() {
        float max = 0;
        winners.clear();
        for (int i = 0; i < score.size(); i++) {
            if (score.get(i) > max) {
                winners.clear();
                winners.add(players.get(i));
            } else if (score.get(i) == max) {
                winners.add(players.get(i));
            }
        }
        return winners;
    }

    public float getPlayerScore(Player player) {
        for (Player iter_player : players) {
            if (iter_player.equals(player)) {
                return score.get(players.indexOf(iter_player));
            }
        }
        return -1;
    }

    public void save(PrintWriter pw) {
        pw.print(String.format("%d ", id));
        for (int i = 0; i < players.size(); i++) {
            pw.print(String.format("%d ", players.get(i).getId()));
        }
        pw.print(String.format("%d ", -1));
        for (int i = 0; i < score.size(); i++) {
            pw.print(String.format("%f ", score.get(i)));
        }
        pw.print(String.format("%d \n", -1));
        pw.flush();
    }
    /**
     * 
     * @param scanner scanner where take data
     * @param ps need to find players
     */
    private void load(Scanner scanner, PlayerRepository ps) {
        if (scanner.hasNextInt()) {
            this.id = scanner.nextInt();
            this.players = new ArrayList<>();
            this.score = new ArrayList<>();
            this.winners = new ArrayList<>();
            int number;

            while (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number != -1) {
                    players.add(ps.getPlayer(number));
                } else
                    break;
            }
            float fnumber;
            while (scanner.hasNextFloat()) {
                fnumber = scanner.nextFloat();
                if (fnumber + 1 > -0.0001 && fnumber + 1 > 0.0001) {
                    score.add(fnumber);
                } else
                    break;
            }
        }
    }
}