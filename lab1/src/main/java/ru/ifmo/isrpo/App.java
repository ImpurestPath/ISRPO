package ru.ifmo.isrpo;

import java.io.File;


public class App {
    public static PlayerRepository pr;
    public static  GameRepository gr;
    public static TournamentRepository tr;
    public static void init(){
        try {
        File playersFile = new File("players.txt");
        File gamesFile = new File("games.txt");
        File tourFile = new File("tour.txt");

        pr = new PlayerRepository(playersFile);
        gr = new GameRepository(gamesFile, pr);
        tr = new TournamentRepository(tourFile, pr, gr);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
    public static void main(String[] args) {
        try {
            init();
           Cli.main(null);
            pr.close();
            gr.close();
            tr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
