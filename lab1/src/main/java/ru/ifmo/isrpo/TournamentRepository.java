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

public class TournamentRepository {
    private List<Tournament> tournaments;
    private int lastId;
    private PrintWriter pw;

    TournamentRepository(File file, PlayerRepository pr, GameRepository gr) {
        tournaments = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                tournaments.add(new Tournament(scanner, pr, gr));
            }
            if (!tournaments.isEmpty())
                lastId = tournaments.get(tournaments.size() - 1).getId();
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

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    /**
     * 
     * @param games
     * @param players
     * @param score
     * @return
     */
    public int createTournament(List<Game> games, List<Player> players, List<Float> score) {
        Tournament t = new Tournament(lastId + 1, games, players, score);
        t.save(pw);
        tournaments.add(t);
        return ++lastId;
    }

    public void close() {
        pw.close();
    }

    public List<Tournament> getPlayerGames(Player player){
        List<Tournament> tournaments = new ArrayList<>();
        for (Tournament game : tournaments){
            if (game.getPlayers().contains(player)){
                tournaments.add(game);
            }
        }
        return tournaments;
    }
    public List<Tournament> getPlayerWinGames(Player player){
        List<Tournament> win = new ArrayList<>();
        for (Tournament tournament : tournaments){
            if (tournament.getWinners().contains(player)){
                win.add(tournament);
            }
        }
        return win;
    }
}