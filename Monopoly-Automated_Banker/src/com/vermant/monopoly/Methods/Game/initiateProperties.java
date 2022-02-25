package com.vermant.monopoly.Methods.Game;

import com.vermant.monopoly.Data.Game;
import com.vermant.monopoly.Data.Property;

public class initiateProperties {

    public Game initiateProperties(Game game) {

        // Street 1
        game.addProperty(1, new Property(60));
        game.addProperty(1, new Property(60));
        // Street 2
        game.addProperty(2, new Property(100));
        game.addProperty(2, new Property(100));
        // Street 3
        game.addProperty(3, new Property(140));
        game.addProperty(3, new Property(140));
        // Street 4
        game.addProperty(4, new Property(180));
        game.addProperty(4, new Property(180));
        // Street 5
        game.addProperty(5, new Property(220));
        game.addProperty(5, new Property(220));
        // Street 6
        game.addProperty(6, new Property(260));
        game.addProperty(6, new Property(260));
        // Street 7
        game.addProperty(7, new Property(300));
        game.addProperty(7, new Property(300));
        // Street 8
        game.addProperty(8, new Property(350));
        game.addProperty(9, new Property(400));

        // Return game
        return game;

    }

}
