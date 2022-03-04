package com.vermant.monopoly.Objects;

import com.vermant.monopoly.Enums.Action;
import com.vermant.monopoly.Enums.ActionType;
import com.vermant.monopoly.Utils.ConsoleColors;

import java.awt.*;
import java.awt.font.TextLayout;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;

import static com.vermant.monopoly.Enums.ActionType.BUY_PROPERTY;


public class Game {

    /** @Type Attributes */
    /** @Info Contains a list of the sets of the game and their properties */
    private ArrayList<Set> sets = new ArrayList<>();
    /** @Info Contains a list of the players of the game */
    private ArrayList<Player> players = new ArrayList<>();
    /** @Info Contains the current player of the game */
    private Player currentPlayer;
    /** @Info Contains the winner of the game */
    private Player winner;

    /** @Type Constructor */
    /** @Info Constructs a new Game instance */
    public Game() {

    }

    /** @Type Getter */
    /** @Info Gets the properties of the game */
    public ArrayList<Set> getSets() {
        return sets;
    }

    /** @Type Setter */
    /** @Info Adds a property to a set */
    public Game addSet(Set set) {
        sets.add(set);
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
    /** @Info Runs the game */
    public Game run() {

        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        // Create sets
        {
            // Set 1
            this.addSet(new Set("brown", List.of(
                    new Property("Mediterranean Avenue", 60),
                    new Property("Baltic Avenue", 60))));

            // Set 2
            this.addSet(new Set("light blue", List.of(
                    new Property("Oriental Avenue", 100),
                    new Property("Vermont Avenue", 100),
                    new Property("Connecticut Avenue", 120))));

            // Set 3
            this.addSet(new Set("pink", List.of(
                    new Property("St. Charles Place", 140),
                    new Property("States Avenue", 140),
                    new Property("Virginia Avenue", 160))));

            // Set 4
            this.addSet(new Set("orange", List.of(
                    new Property("St. James Place", 180),
                    new Property("Tennessee Avenue", 180),
                    new Property("New York Avenue", 200))));

            // Set 5
            this.addSet(new Set("red", List.of(
                    new Property("Kentucky Avenue", 220),
                    new Property("Indiana Avenue", 220),
                    new Property("Illinois Avenue", 240))));

            // Set 6
            this.addSet(new Set("yellow", List.of(
                    new Property("Atlantic Avenue", 260),
                    new Property("Ventnor Avenue", 260),
                    new Property("Marvin Gardens", 280))));

            // Set 7
            this.addSet(new Set("green", List.of(
                    new Property("Pacific Avenue", 300),
                    new Property("North Carolina Avenue", 300),
                    new Property("Pennsylvania Avenue", 320))));

            // Set 8
            this.addSet(new Set("dark blue", List.of(
                    new Property("Park Place", 350),
                    new Property("Boardwalk", 400))));
        }


        // Separator: 'Settings'
        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Settings" + ConsoleColors.RESET);

        // Request player count
        int playerCount = 0;
        {
            // Separator: 'Player Count'
            System.out.println();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "• Player Count" + ConsoleColors.RESET);

            // Request player count
            System.out.print("How many players are you playing with (2-4)? ");
            playerCount = Integer.parseInt(scanner.nextLine());

            // Re-request incorrect player count
            while ( !(2 <= playerCount && playerCount <= 4) ) {
                System.out.print("Enter a valid number between 2-4. ");
                playerCount = Integer.parseInt(scanner.nextLine());
            }
        }

        // Request player info
        {
            // Separator: 'Player Info'
            System.out.println();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "• Player Info" + ConsoleColors.RESET);

            // Request player info per player
            for ( int i = 1; i <= playerCount; i++ ) {

                // Separator: 'Player x'
                System.out.println(ConsoleColors.CYAN_BOLD + "Player " + i + ConsoleColors.RESET);

                // Request player name
                System.out.print("What is the name of player " + i + "? ");
                String name = scanner.nextLine();

                // Add player to game
                Player player = new Player(name);
                this.addPlayer(player);

            }

            // Separator
            System.out.println();
        }


        // Separator: 'Game Started'
        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Game Started" + ConsoleColors.RESET);

        // Roll dice to determine starting player
        {
            // Separator: 'Determining starting player by rolling the dice...'
            System.out.println(ConsoleColors.YELLOW + "Determining starting player by rolling the dice..." + ConsoleColors.RESET);

            // Initialize rolls
            HashMap<Player, Integer> rolls = new HashMap<>() {{
                for (Player player : getPlayers()) {
                    put(player, 0);
                }
            }};

            // Roll dice until a starting player is determined
            while (rolls.size() != 1) {

                // Roll dice for each player
                for (Player player : this.getPlayers() ) {

                    // Ensure player can roll dice
                    if (!rolls.containsKey(player)) continue;

                    // Roll dice for player
                    Action action = new Action(player, ActionType.ROLL_DICE);
                    int currentRoll = (int) player.performAction(action);
                    rolls.put(player, currentRoll);
                    System.out.println(player.getName() + " rolled a " + currentRoll);

                }

                // Determine highest value
                int highestRoll = Collections.max(rolls.values());

                // Remove players with lower rolls
                HashMap<Player, Integer> highestRolls = new HashMap<>();
                for (Player player : this.getPlayers()) {
                    if (!rolls.containsKey(player)) continue;
                    if (rolls.get(player) == highestRoll) highestRolls.put(player, highestRoll);
                }
                rolls = highestRolls;

                // Separator: 'It's a tie! Re-rolling the dice...'
                if (rolls.size() > 1) {
                    System.out.println(ConsoleColors.YELLOW + "It's a tie! Re-rolling the dice..." + ConsoleColors.RESET);
                }

            }

            // Set starting player
            rolls.keySet().forEach(player -> {
                currentPlayer = player;
                System.out.println(ConsoleColors.BLUE + player.getName() + " rolled the highest!" + ConsoleColors.RESET);
            });
        }

        int input = 0;
        // Play game as long as there isn't a winner
        while (winner == null) {

            // Loop through turns of players
            for (Player player : this.getPlayers()) {

                // Check for a winner
                if (winner != null) break;

                // TODO Determine possible actions for player
                // Add action types to map
                HashMap<Integer, ActionType> actionTypes = new HashMap<>();
                for ( ActionType actionType : ActionType.values() ) {
                    actionTypes.put(actionTypes.size(), actionType);
                }

                // Request input: 'Which action would you like to perform?'
                {

                    // Separator
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_UNDERLINED + player.getName() + "'s turn");

                    // Request input: 'Which action would you like to perform?'
                    System.out.println(ConsoleColors.YELLOW_BRIGHT + "Which action would you like to perform (1-" + actionTypes.size() + ")?" + ConsoleColors.RESET);
                    for ( int i = 0; i < actionTypes.size(); i++ ) {
                        System.out.println((i + 1) + ". " + actionTypes.get(i).getText());
                    }
                    System.out.println();

                    input = Integer.parseInt(scanner.nextLine()) - 1;
                    while ( !(0 <= input && input < actionTypes.size()) ) {
                        System.out.print("Enter a valid number between 1-" + actionTypes.size() + ". ");
                        input = Integer.parseInt(scanner.nextLine()) - 1;
                    }

                }

                // Determine action
                switch ( actionTypes.get(input) ) {

                    case BUY_PROPERTY -> {

                        // Request property position
                        System.out.print("Which set does your property belong to (1-" + getSets().size() + ")? ");
                        int property_set = Integer.parseInt(scanner.nextLine());
                        System.out.print("Which street in the set does your property belong to (1-" + getSets().get(property_set - 1).getProperties().size() + ")? ");
                        int property_street = Integer.parseInt(scanner.nextLine());

                        // Purchase property
                        Property property = getSets().get(property_set - 1).getProperties().get(property_street - 1);
                        Action.buyProperty(player, property);

                    }

                    case PAY_JAIL_BAIL -> {}

                }

                // Show player income and properties
                for ( Player allPlayer : getPlayers() ) {
                    System.out.println(allPlayer.getName() + ":\n\t$" + allPlayer.getBalance() + "\n\t" + allPlayer.getProperties());
                }

                // Reset input
                input = 0;

            }

        }

        return this;

    }

}
