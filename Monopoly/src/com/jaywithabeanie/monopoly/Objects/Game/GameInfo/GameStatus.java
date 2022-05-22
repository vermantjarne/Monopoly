package com.jaywithabeanie.monopoly.Objects.Game.GameInfo;

import com.jaywithabeanie.monopoly.Objects.Game.Game;
import com.jaywithabeanie.monopoly.Objects.Game.Player.Player;


/**
 * Contains the states a {@link Game} can be in.<br>
 * <br>
 * Possible values:<br>
 * {@link #IDLE}, {@link #INITIALIZING}, {@link #RUNNING}, {@link #FINISHED}
 */
public enum GameStatus {


    /**
     * The {@link Game} remains in this {@link GameStatus Status} until it starts {@link #INITIALIZING initializing}.<br>
     * It loads all assets and prepares to boot up.
     */
    IDLE("Idle"),
    /**
     * The {@link Game} remains in this {@link GameStatus Status} until it starts {@link #RUNNING running}.<br>
     * Its settings are determined here.
     */
    INITIALIZING("Initializing"),
    /**
     * The {@link Game} remains in this {@link GameStatus Status} until it's {@link #FINISHED finished}.<br>
     * It lets the {@link Player players} take turns to claim their monopoly.
     */
    RUNNING("Running"),
    /**
     * The {@link Game} remains in this {@link GameStatus Status} until a {@link Player winner} is determined.<br>
     * Once finished, it will remain in this state.
     */
    FINISHED("Finished");

    private final String gameStatus;

    GameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }


}
