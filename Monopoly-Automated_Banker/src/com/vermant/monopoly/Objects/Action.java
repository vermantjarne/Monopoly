package com.vermant.monopoly.Objects;

import com.vermant.monopoly.Utils.ConsoleColors;

public class Action {

    /* 1 */
    public static void buyProperty(Game game, Player player, Property property) {

        // Error: Property owned by a player
        {
            for (Player allPlayer : game.getPlayers()) {
                if (allPlayer.hasProperty(property)) {
                    System.out.println(ConsoleColors.RED_BOLD + "ERROR " + ConsoleColors.RESET + "This property is already owned by a player!");
                }
            }
        }

        // Error: Player balance < property cost
        {
            if (player.getBalance() < property.getCost()) {
                System.out.println(ConsoleColors.RED_BOLD + "ERROR " + ConsoleColors.RESET + "You do not own the money to buy this property!");
                return;
            }
        }

        // Purchase successful
        {
            player.addProperty(property).decreaseBalance(property.getCost());
        }
    }

    /* 4 */
    public static void passByGO(Player player) {
        player.increaseBalance(200);
    }

    /* 5 */
    public static void getOutOfJail(Player player) {
        player.decreaseBalance(50);
    }

}
