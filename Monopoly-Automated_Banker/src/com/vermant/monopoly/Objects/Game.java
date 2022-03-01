package com.vermant.monopoly.Objects;

import com.vermant.monopoly.Utils.ConsoleColors;

import java.util.*;

public class Game {

    /** @Type Attributes */
    /** @Info Contains a list of the sets and their properties */
    private ArrayList<ArrayList<Property>> sets = new ArrayList<>();
    /** @Info Contains a list of the players */
    private ArrayList<Player> players = new ArrayList<>();
    /** @Info Contains the winner of the game */
    private Player winner;

    /** @Type Constructor */
    /** @Info Constructs a new Game instance */
    public Game() {

    }

    /** @Type Getter */
    /** @Info Gets the properties of the game */
    public ArrayList<ArrayList<Property>> getSets() {
        return sets;
    }

    /** @Type Setter */
    /** @Info Adds a property to a set */
    public Game addProperty(int setNumber, Property property) {

        // Create empty list if it doesn't exist
        if (this.sets.size() < setNumber) {
            sets.add(new ArrayList<>(Arrays.asList(property)));
        }
        // Add property to existing list
        else {
            this.sets.get(setNumber - 1).add(property);
        }

        return this;

    }

    /** @Type Getter */
    /** @Info Gets the players of the game */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /** @Type Setter */
    /** @Info Adds a player to the game */
    public Game addPlayer(Player player) {
        this.players.add(player);
        return this;
    }

    /** @Type Getter */
    /** @Info Gets the winner of the game */
    public Player getWinner() {
        return this.winner;
    }

    /** @Type Setter */
    /** @Info Sets the winner of the game */
    public Game setWinner(Player player) {
        this.winner = player;
        return this;
    }

    /** @Type Method */
    /** @Info Initiates the game */
    public Game startGame() {

        // Initiate scanner
        Scanner scanner = new Scanner(System.in);

        // Initiate properties
        {
            // Street 1
            this.addProperty(1, new Property("Mediterranean Avenue", 60))
                .addProperty(1, new Property("Baltic Avenue", 60));

            // Street 2
            this.addProperty(2, new Property("Oriental Avenue", 100))
                .addProperty(2, new Property("Vermont Avenue", 100))
                .addProperty(2, new Property("Connecticut Avenue", 120));

            // Street 3
            this.addProperty(3, new Property("St. Charles Place", 140))
                .addProperty(3, new Property("States Avenue", 140))
                .addProperty(3, new Property("Virginia Avenue", 160));

            // Street 4
            this.addProperty(4, new Property("St. James Place", 180))
                .addProperty(4, new Property("Tennessee Avenue", 180))
                .addProperty(4, new Property("New York Avenue", 200));

            // Street 5
            this.addProperty(5, new Property("Kentucky Avenue", 220))
                .addProperty(5, new Property("Indiana Avenue", 220))
                .addProperty(5, new Property("Illinois Avenue", 240));

            // Street 6
            this.addProperty(6, new Property("Atlantic Avenue", 260))
                .addProperty(6, new Property("Ventnor Avenue", 260))
                .addProperty(6, new Property("Marvin Gardens", 280));

            // Street 7
            this.addProperty(7, new Property("Pacific Avenue", 300))
                .addProperty(7, new Property("North Carolina Avenue", 300))
                .addProperty(7, new Property("Pennsylvania Avenue", 320));

            // Street 8
            this.addProperty(8, new Property("Park Place", 350))
                .addProperty(8, new Property("Boardwalk", 400));
        }
        
        /* Settings */
        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Settings" + ConsoleColors.RESET);

        /* Player Count */
        int playerCount = 0;
        {
            // Separator
            System.out.println();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "• Player Count" + ConsoleColors.RESET);

            // Request player count
            System.out.print("How many players are you playing with (2-4)? ");
            playerCount = Integer.parseInt(scanner.nextLine());

            // Request correct player count
            while ( !(2 <= playerCount && playerCount <= 4) ) {
                System.out.print("Enter a valid number between 2-4. ");
                playerCount = Integer.parseInt(scanner.nextLine());
            }
        }

        /* Player info */
        {
            // Separator
            System.out.println();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "• Player Info" + ConsoleColors.RESET);

            // Request info for each player
            for ( int i = 1; i <= playerCount; i++ ) {
                System.out.println(ConsoleColors.CYAN_BOLD + "Player " + i + ConsoleColors.RESET);
                Player player = new Player();
                while ( player.getName() == null ) {
                    // Name
                    System.out.print("What is the name of player " + i + "? ");
                    String name = scanner.nextLine();
                    player.setName(name);
                }
                this.addPlayer(player);
            }

            // Separator
            System.out.println();
        }

        /* Game started */
        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Game Started" + ConsoleColors.RESET);

        /* Actions */
        int input = 0;
        while (winner == null) {

            for (Player player : getPlayers()) {

                // Separator
                System.out.println();
                System.out.println(ConsoleColors.YELLOW_UNDERLINED + player.getName() + "'s turn" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW_BRIGHT + "Which action would you like to perform (1-6)?" + ConsoleColors.RESET);
                System.out.println("1. Buy a property");
                System.out.println("2. Sell a property");
                System.out.println("3. Pay rent");
                System.out.println("4. Pass by GO");
                System.out.println("5. Get out of jail");
                System.out.println("6. Declare bankruptcy");
                System.out.println(ConsoleColors.WHITE + "0. Skip turn" + ConsoleColors.RESET);
                System.out.println();

                // Request action input
                input = Integer.parseInt(scanner.nextLine());

                // Determine action
                while (!(0 <= input && input <= 6)) {
                    System.out.print("Enter a valid number between 0-6. ");
                }
                switch (input) {
                    case 1 -> {

                        // Request property position
                        System.out.print("Which set does your property belong to (1-" + getSets().size() + ")? ");
                        int property_set = Integer.parseInt(scanner.nextLine());
                        System.out.print("Which street in the set does your property belong to (1-" + getSets().get(property_set - 1).size() + ")? ");
                        int property_street = Integer.parseInt(scanner.nextLine());

                        // Purchase property
                        Property property = getSets().get(property_set - 1).get(property_street - 1);
                        Action.buyProperty(this, player, property);

                    }
                    case 4 -> Action.passByGO(player);
                    case 5 -> Action.getOutOfJail(player);
                }

                // Show player income and properties
                for (Player allPlayer : getPlayers()) {
                    System.out.println(allPlayer.getName() + ":\n\t$" + allPlayer.getBalance() + "\n\t" + allPlayer.getProperties());
                }

                // Reset input
                input = 0;

            }

        }

        return this;
    }

}
