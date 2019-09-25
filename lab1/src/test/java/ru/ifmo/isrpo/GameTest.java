package ru.ifmo.isrpo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GameTest 
{
   
    @Test
    public void Id()
    {
        Game game = new Game(1, null ,null);
        assertEquals(game.getId(), 1);
    }
    @Test
    public void Winners(){
        Player player1 = new Player(1,"a");
        Player player2 = new Player(2,"a");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        
        List<Float> score = new ArrayList<>();
        score.add((float) 1);
        score.add((float) 0);
        Game game = new Game(1,players,score);
        assertEquals(player1, game.getWinner().get(0)); 
    }
}
