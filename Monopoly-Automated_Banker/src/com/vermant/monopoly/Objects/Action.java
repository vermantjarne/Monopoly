package com.vermant.monopoly.Objects;

public class Action {

    public static String endGame() {
        return "END";
    }

    // 4
    public static Player passByGO(Player player) {
        player.increaseBalance(200);
        return player;
    }

    // 5
    public static Player getOutOfJail(Player player) {
        player.decreaseBalance(50);
        return player;
    }

}
