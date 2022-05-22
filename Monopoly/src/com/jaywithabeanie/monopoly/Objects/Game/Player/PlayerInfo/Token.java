package com.jaywithabeanie.monopoly.Objects.Game.Player.PlayerInfo;

import com.jaywithabeanie.monopoly.Objects.Game.Player.Player;

/**
 * Contains the tokens the {@link Player} can use.<br>
 * Possible values:<br>
 * {@link #BATTLESHIP}, {@link #BOOT}, {@link #CAR}, {@link #CAT}, {@link #DOG}, {@link #THIMBLE}, {@link #TOP_HAT}, {@link #WHEELBARROW}
 */
public enum Token {


    /**
     * The Battleship {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Battleship.png" width=auto height=50/>
     */
    BATTLESHIP("Battleship"),
    /**
     * The Boot {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Boot.png" width=auto height=50/>
     */
    BOOT("Boot"),
    /**
     * The Car {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Car.png" width=auto height=50/>
     */
    CAR("Car"),
    /**
     * The Cat {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Cat.png" width=auto height=50/>
     */
    CAT("Cat"),
    /**
     * The Dog {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Dog.png" width=auto height=50/>
     */
    DOG("Dog"),
    /**
     * The Thimble {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Thimble.png" width=auto height=50/>
     */
    THIMBLE("Thimble"),
    /**
     * The Top Hat {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Top Hat.png" width=auto height=50/>
     */
    TOP_HAT("Top Hat"),
    /**
     * The Wheelbarrow {@link Token}<br>
     * <img src="../../../../../../../resources/Tokens/Wheelbarrow.png" width=auto height=50/>
     */
    WHEELBARROW("Wheelbarrow");


    private final String name;

    Token(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}
