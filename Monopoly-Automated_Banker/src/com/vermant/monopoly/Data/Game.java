package com.vermant.monopoly.Data;

import com.vermant.monopoly.Utils.ConsoleColors;

import java.util.*;

public class Game {

    /** @Type Attributes */
    /** @Info Contains a list of sets and their properties */
    private ArrayList<ArrayList<Property>> sets = new ArrayList<>();
    /** @Info Contains a list of players */
    private ArrayList<Player> players = new ArrayList<>();
    /** @Info Has the winner of the game */
    private Player winner;

    /** @Type Constructor */
    /** @Info Constructs a new game instance */
    public Game() {

    }

    /** @Type Setter */
    /** @Info Adds a property to a set */
    public void addProperty(int setNumber, Property property) {

        // Create empty arraylist if it doesn't exist
        if (this.sets.get(setNumber) == null) {
            sets.add((ArrayList<Property>) List.of(property));
            return;
        }

        // Add property to existing arraylist
        this.sets.get(setNumber).add(property);

    }

    /** @Attribute players */
    // Getter: Get players of game
    public List<Player> getPlayers() {
        return this.players;
    }

    /** @Attribute players */
    // Setter: Add players of game
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /** @Attribute winner */
    // Getter: Get winner of game
    public Player getWinner() {
        return this.winner;
    }

    /** @Attribute winner */
    // Setter: Set winner of game
    public void setWinner(Player player) {
        this.winner = player;
    }

    // Method: Start game
    public void startGame() {

        /* Instantiate scanner */
        Scanner scanner = new Scanner(System.in);


        /* Player count */
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "Player Count" + ConsoleColors.RESET);

        int playerCount = 0;
        System.out.print("How many players are you playing with (2-4)? ");
        playerCount = scanner.nextInt();

        while (!(2 <= playerCount && playerCount <= 4)) {
            System.out.print("Enter a valid number between 2-4. ");
            playerCount = scanner.nextInt();
        }


        /* Player info */
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "Player Info" + ConsoleColors.RESET);

        for (int i = 1; i <= playerCount; i++) {
            System.out.println(ConsoleColors.YELLOW_UNDERLINED + "Player " + i + ConsoleColors.RESET);
            Player player = new Player();
            while (player.getName() == null) {

                // Name
                System.out.print("What is the name of player " + i + "? ");
                String name = scanner.next();
                player.setName(name);

                // Tokens
                System.out.println("What is the token player " + i + " uses?");
                int tokenChoice = scanner.nextInt();
                Token token = new Token();
                player.setToken(token);

            }
            this.addPlayer(player);
            System.out.println();
        }

        System.out.println("");
    }

}
