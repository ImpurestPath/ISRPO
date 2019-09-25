package ru.ifmo.isrpo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tournament {
    private int id;
    private List<Game> games;
    private List<Player> players;
    private List<Float> score;
    private List<Player> winners;


    public Tournament(Scanner scanner, PlayerRepository ps, GameRepository gs) {
        load(scanner, ps, gs);
    }

    /**
     * 
     * @param id      id of tournament
     * @param games   list of games that included in tournament
     * @param players list of players that participated in tournament
     * @param score   list of float score for each player
     */
    public Tournament(int id, List<Game> games, List<Player> players, List<Float> score) {
        this.id = id;
        this.games = games;
        this.players = players;
        this.score = score;
        this.winners = new ArrayList<>();
    }

    /**
     * Construcor that get players from games
     * 
     * @param id
     * @param games
     * @param score
     */
    public Tournament(int id, List<Game> games) {
        this.id = id;
        this.games = games;
        this.players = new ArrayList<>();
        this.score = new ArrayList<>();
        for (Game game : games) {
            for (Player player : game.getPlayers()) {
                if (!players.contains(player)) {
                    players.add(player);
                    score.add((float)0);
                }
            }
            for (Player player : game.getWinner()) {
                score.set(players.indexOf(player),score.get(players.indexOf(player)) + 1);
            }
        }
        this.winners = new ArrayList<>();
    }

    public Tournament(int id, List<Game> games, List<Player> players, List<Float> score, List<Player> winners) {
        this.id = id;
        this.games = games;
        this.players = players;
        this.score = score;
        this.winners = winners;
    }

    public List<Player> getWinners() {
        return !this.winners.isEmpty() ? this.winners : calculateWinners();
    }

    public int getId() {
        return this.id;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Float> getScore() {
        return this.score;
    }

    public List<Player> calculateWinners() {
        float max = 0;
        winners.clear();
        for (int i = 0; i < score.size(); i++) {
            if (score.get(i) - max > 0.001) {
                winners.clear();
                winners.add(players.get(i));
                max = score.get(i);
            } else if (score.get(i) == max) {
                winners.add(players.get(i));
            }
        }
        return winners;
    }

    public void addGame(Game game) {
        games.add(game);
        for (Player player : game.getPlayers()) {
            int index = players.indexOf(player);
            if (index != -1) {
                score.set(index, score.get(index) + game.getPlayerScore(player));
            } else {
                players.add(player);
                score.add(game.getPlayerScore(player));
            }
        }
    }

    public void save(PrintWriter pw) {
        pw.print(String.format("%d ", id));
        for (int i = 0; i < games.size(); i++) {
            pw.print(String.format("%d ", games.get(i).getId()));
        }
        pw.print(String.format("%d ", -1));
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

    public void load(Scanner scanner, PlayerRepository ps, GameRepository gs) {
        if (scanner.hasNextInt()) {
            this.id = scanner.nextInt();
            this.games = new ArrayList<>();
            this.players = new ArrayList<>();
            this.score = new ArrayList<>();
            this.winners = new ArrayList<>();
            int number;

            while (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number != -1) {
                    games.add(gs.getGame(number));
                } else
                    break;
            }
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