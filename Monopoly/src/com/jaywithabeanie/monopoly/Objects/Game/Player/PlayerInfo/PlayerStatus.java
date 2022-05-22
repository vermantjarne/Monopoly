package com.jaywithabeanie.monopoly.Objects.Game.Player.PlayerInfo;

public enum PlayerStatus {


    DEFEATED(), // Player is defeated
    IDLE(), // Not the player's turn
    TURN_START(), // Player is pre dice roll
    TURN_MOVED(); // Player is post dice roll

    PlayerStatus() {

    }


}
