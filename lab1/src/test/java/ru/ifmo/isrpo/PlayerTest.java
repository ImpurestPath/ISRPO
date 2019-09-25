package ru.ifmo.isrpo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PlayerTest 
{

 
    @Test
    public void checkRegisterPlayer()
    {
        App.init();
        int number = App.pr.createPlayer("Name");
        assertEquals(number+1, App.pr.createPlayer("Name"));
    }
}