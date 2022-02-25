package com.vermant.monopoly;

import com.vermant.monopoly.Data.Game;
import com.vermant.monopoly.Data.Player;
import com.vermant.monopoly.Methods.Game.initiateProperties;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {

        // Initiate new game
        Game game = new Game();

        game.startGame();

        new initiateProperties();

        game.startGame();

    }

}
