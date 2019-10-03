package ru.ifmo.isrpo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.ifmo.isrpo.Tournament.Level;

public class TournamentTest {

    @Test
    public void check() {
        Player player1 = new Player(1, "Player1");
        Player player2 = new Player(2, "Player2");
        List<Player> players = new ArrayList<>();
        List<Float> score = new ArrayList<>();
        players.add(player1);
        score.add((float) 1);
        players.add(player2);
        score.add((float) 0);
        Game game = new Game(1, players, score);
        List<Game> games = new ArrayList<>();
        games.add(game);
        Tournament tournament = new Tournament(1, Level.BEGINNER, games);
        assertEquals(player1, tournament.getPlayers().get(0));
        assertEquals(player1, tournament.getWinners().get(0));
    }
}