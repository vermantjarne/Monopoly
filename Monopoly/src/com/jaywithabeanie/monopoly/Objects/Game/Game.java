package com.jaywithabeanie.monopoly.Objects.Game;

import com.jaywithabeanie.monopoly.Objects.Game.Action.Action;
import com.jaywithabeanie.monopoly.Objects.Game.Action.ActionType;
import com.jaywithabeanie.monopoly.Objects.Game.Frame.Frame;
import com.jaywithabeanie.monopoly.Objects.Game.GameInfo.GameStatus;
import com.jaywithabeanie.monopoly.Objects.Game.Player.Player;
import com.jaywithabeanie.monopoly.Objects.Game.Player.PlayerInfo.PlayerStatus;
import com.jaywithabeanie.monopoly.Objects.Game.Player.PlayerInfo.Token;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Card.Chance.Chance;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Card.CommunityChest.CommunityChest;
import com.jaywithabeanie.monopoly.Objects.Game.Space.FreeParking.FreeParking;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Go.Go;
import com.jaywithabeanie.monopoly.Objects.Game.Space.GoToJail.GoToJail;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Space;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Tax.TaxType.IncomeTax;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Tax.TaxType.LuxuryTax;
import com.jaywithabeanie.monopoly.Utils.ConsoleColors;

import java.util.*;


/**
 * <span style="font-weight:bold;color:#355e3b">Object</span><br>
 * Instance of a {@link Game}<br>
 * <br>
 * Attributes:<br>
 * <span style="color:#90EE90">{@link #gameStatus}</span> - {@link #getStatus}, {@link #isRunning}, {@link #setStatus}<br>
 * <span style="color:#90EE90">{@link #spaces}</span> - {@link #getSpaces}, {@link #setDefaultSpaces}, {@link #setSpace}<br>
 * <span style="color:#90EE90">{@link #players}</span> - {@link #getPlayers}, {@link #getActivePlayers}, {@link #addPlayer}<br>
 * <span style="color:#90EE90">{@link #currentPlayer}</span> - {@link #setCurrentPlayer}<br>
 * <span style="color:#90EE90">{@link #winner}</span> - {@link #getWinner}, {@link #setWinner}
 */
public class Game {

    /**
     * <span style="font-weight:bold;color:#355e3b">Attribute</span><br>
     * Contains the {@link GameStatus Status} of the {@link Game Game}
     */
    private GameStatus gameStatus = GameStatus.IDLE;

    /**
     * <span style="font-weight:bold;color:#355e3b">Attribute</span><br>
     * Contains every {@link Space Space} of the {@link Game Game}
     */
    private final ArrayList<Space> spaces = new ArrayList<>();

    private final HashMap<Integer, ArrayList<Object>> chanceCards = new HashMap<>(){{
        put(0, new ArrayList<>() {{
            add("You have won second prize in a beauty contest. You receive $10.");
            add(ActionType.RECEIVE_MONEY);
            add(10);
        }});
    }};

    private final HashMap<Integer, ArrayList<Object>> communityChestCards = new HashMap<>(){{
        put(0, new ArrayList<>() {{
            add("You have won second prize in a beauty contest. You receive $10.");
            add(ActionType.RECEIVE_MONEY);
            add(10);
        }});
    }};

    private ArrayList<Object> lastDrawnCard = new ArrayList<>();

    /**
     * <span style="font-weight:bold;color:#355e3b">Attribute</span><br>
     * Contains every {@link Player Player} of the {@link Game Game}
     */
    private final ArrayList<Player> players = new ArrayList<>();

    /**
     * <span style="font-weight:bold;color:#355e3b">Attribute</span><br>
     * Contains the current {@link Player} of the {@link Game}
     */
    private Player currentPlayer;

    /**
     * <span style="font-weight:bold;color:#355e3b">Attribute</span><br>
     * Contains the winning {@link Player} of the {@link Game}
     */
    private Player winner;


    /**
     * <span style="font-weight:bold;color:#355e3b">Constructor</span><br>
     * Creates a new {@link Game} instance
     */
    public Game() {

    }


    /**
     * <span style="font-weight:bold;color:#355e3b">Getter</span> <span style="color:#90EE90">({@link #spaces})</span><br>
     * Returns the {@link Space Spaces} of the {@link Game}
     */
    public ArrayList<Space> getSpaces() {
        return this.spaces;
    }

    /**
     * <span style="font-weight:bold;color:#355e3b">Setter</span> <span style="color:#90EE90">({@link #spaces})</span><br>
     * Sets the default {@link Space Spaces} of the {@link Game}
     */
    public Game setDefaultSpaces() {
        for (int i = 0; i <= 39; i++) {
            this.spaces.add(new Space());
        }
        return this;
    }

    /**
     * <span style="font-weight:bold;color:#355e3b">Setter</span> <span style="color:#90EE90">({@link #spaces})</span><br>
     * Sets a {@link Space Space} of the {@link Game}
     * @param position Position of the Space on the board (0-39)
     * @param space Space to set
     */
    public Game setSpace(int position, Space space) {
        this.spaces.set(position, space);
        return this;

    }


    public Game shuffleCards() {
        return this;
    }

    public ArrayList<Object> drawCard(HashMap<Integer, ArrayList<Object>> cards) {
        HashMap<Integer, ArrayList<Object>> unshuffledCards = (HashMap<Integer, ArrayList<Object>>) cards.clone();
        unshuffledCards.remove(0);
        for (int i = 1; i <= cards.size(); i++) {
            if (i == cards.size()) {
                cards.put(i-1, cards.get(0));
                continue;
            }
            cards.put(i-1, cards.get(i));
        }
        unshuffledCards = cards;
        this.setLastDrawnCard(cards.get(cards.size() - 1));
        return cards.get(cards.size() - 1);
    }

    public HashMap<Integer, ArrayList<Object>> getChanceCards() {
        return this.chanceCards;
    }

    public HashMap<Integer, ArrayList<Object>> getCommunityChestCards() {
        return this.communityChestCards;
    }

    public void setLastDrawnCard(ArrayList<Object> card) {
        this.lastDrawnCard = card;
    }

    public ArrayList<Object> getLastDrawnCard() {
        return this.lastDrawnCard;
    }


    /**
     * <span style="font-weight:bold;color:#355e3b">Getter</span> <span style="color:#90EE90">({@link #players})</span><br>
     * Returns the {@link Player Players} of the {@link Game}
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * <span style="font-weight:bold;color:#355e3b">Getter</span> <span style="color:#90EE90">({@link #players})</span><br>
     * Returns the active {@link Player Players} of the {@link Game}
     */
    public ArrayList<Player> getActivePlayers() {

        // Get active players
        ArrayList<Player> activePlayers = new ArrayList<>();
        for (Player player : this.getPlayers()) {
            if (player.getStatus() != PlayerStatus.DEFEATED) {
                activePlayers.add(player);
            }
        }

        // Return active players
        return activePlayers;

    }

    /**
     * <span style="font-weight:bold;color:#355e3b">Adder</span> <span style="color:#90EE90">({@link #players})</span><br>
     * Adds a {@link Player} to the {@link Game}
     * @param player Player to add
     */
    public Game addPlayer(Player player) {
        this.players.add(player);
        return this;
    }


    /**
     * <span style="font-weight:bold;color:#355e3b">Getter</span> <span style="color:#90EE90">({@link #gameStatus})</span><br>
     * Gets the {@link GameStatus Status} of the {@link Game}
     */
    public GameStatus getStatus() {
        return this.gameStatus;
    }

    /**
     * <span style="font-weight:bold;color:#355e3b">Checker</span> <span style="color:#90EE90">({@link #gameStatus})</span><br>
     * Gets whether the {@link GameStatus Status} of the {@link Game} is {@link GameStatus#RUNNING running}
     */
    public boolean isRunning() {
        return this.getStatus() == GameStatus.RUNNING;
    }

    /**
     * <span style="font-weight:bold;color:#355e3b">Setter</span> <span style="color:#90EE90">({@link #gameStatus})</span><br>
     * Sets the {@link GameStatus Status} of the {@link Game}
     * @param gameStatus Status to set
     */
    public Game setStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;

        if (gameStatus == GameStatus.RUNNING) {
            // Draw board
            Frame.createFrame(this);
        }

        if (gameStatus == GameStatus.FINISHED) {
            System.out.println("Congratulations on winning the game, " + this.getWinner().getName() + "!");
        }

        return this;
    }


    /**
     * <span style="font-weight:bold;color:#355e3b">Setter</span> <span style="color:#90EE90">({@link #currentPlayer})</span><br>
     * Sets the current {@link Player} of the {@link Game}
     * @param player Player to set
     */
    public Game setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        player.setStatus(PlayerStatus.TURN_START);
        return this;
    }


    /**
     * <span style="font-weight:bold;color:#355e3b">Getter</span> <span style="color:#90EE90">({@link #winner})</span><br>
     * Gets the {@link Player winner} of the {@link Game}
     */
    public Player getWinner() {
        return this.winner;
    }

    /**
     * <span style="font-weight:bold;color:#355e3b">Setter</span> <span style="color:#90EE90">({@link #winner})</span><br>
     * Sets the {@link Player winner} of the {@link Game}
     * @param player Player to set
     */
    public Game setWinner(Player player) {
        this.winner = player;
        return this;
    }


    /**
     * <span style="font-weight:bold;color:#355e3b">Method</span><br>
     * Runs the {@link Game}
     */
    public Game run() {

        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        // Create spaces
        {
            // Set 40 empty spaces
            this.setDefaultSpaces();

            // Replace Go space
            this.setSpace(0, new Go());

            // Replace Free Parking space
            this.setSpace(20, new FreeParking());

            // Create Tax Spaces
            {
                IncomeTax incomeTax = new IncomeTax();
                this.spaces.set(4, incomeTax);
                LuxuryTax luxuryTax = new LuxuryTax();
                this.spaces.set(38, luxuryTax);
            }

            // Create Card Spaces
            {
                Chance chance = new Chance();
                this.spaces.set(7, chance);
                this.spaces.set(22, chance);
                this.spaces.set(36, chance);

                CommunityChest communityChest = new CommunityChest();
                this.spaces.set(2, communityChest);
                this.spaces.set(17, communityChest);
                this.spaces.set(33, communityChest);
            }

            // Create Go To Jail Space
            {
                GoToJail goToJail = new GoToJail();
                this.spaces.set(30, goToJail);
            }

            /*
            // Create sets
            Set set_brown = new Set("brown");
            Set set_lightBlue = new Set("light blue");
            Set set_pink = new Set("pink");
            Set set_orange = new Set("orange");
            Set set_red = new Set("red");
            Set set_yellow = new Set("yellow");
            Set set_green = new Set("green");
            Set set_blue = new Set("blue");

            // Create properties
            Street street = new Street(set_brown, "Mediterranean Avenue", 60, List.of(2, 10, 30, 90, 160, 250));
            */

            /*
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
                    new Property("Boardwalk", 400))));*/
        }

        // Separator: 'Settings'
        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Settings" + ConsoleColors.RESET);
        this.setStatus(GameStatus.INITIALIZING);

        // TODO Uncomment after testing
        /*
        int playerCount;
        // Request player count
        {
            // Separator: 'Player Count'
            System.out.println();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "• Player Count" + ConsoleColors.RESET);

            // Request player count
            System.out.print("How many players are you playing with (2-4)? ");
            playerCount = scanner.nextInt();

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

            // Initiate player info variables
            List<Token> tokenOptions = new ArrayList<>(List.of(Token.values()));

            // Request player info per player
            for ( int i = 1; i <= playerCount; i++ ) {

                // Separator: 'Player x'
                System.out.println(ConsoleColors.YELLOW_BOLD + "Player " + i + ConsoleColors.RESET);

                // Request player name
                System.out.print("What is the name of player " + i + "? ");
                String name = scanner.next();

                // Request player token
                int token;
                {
                    // Separator: player token
                    System.out.println("What token will " + name + " play with?");
                    for (int j = 1; j <= tokenOptions.size(); j++) {
                        System.out.println(ConsoleColors.CYAN_BRIGHT + j + ". " + tokenOptions.get(j-1).getName() + ConsoleColors.RESET);
                    }

                    // Request player token
                    token = scanner.nextInt();
                    while ( !(1 <= token && token <= tokenOptions.size()) ) {
                        System.out.print("Enter a valid number between 1-" + tokenOptions.size() + ". ");
                        token = scanner.nextInt();
                    }
                    token--;
                }

                // Add player to game
                Player player = new Player(this, name, tokenOptions.get(token));
                this.addPlayer(player);

                // Remove token from options
                tokenOptions.remove(token);

                // Separator
                System.out.println();

            }
        }

         */

        // TODO Comment after testing
        Player playerA = new Player(this, "a", Token.BATTLESHIP);
        Player playerB = new Player(this, "b", Token.BOOT);
        Player playerC = new Player(this, "c", Token.CAR);
        Player playerD = new Player(this, "d", Token.CAT);
        this.addPlayer(playerA).addPlayer(playerB).addPlayer(playerC).addPlayer(playerD);

        // Separator: 'Game Started'
        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Game Started" + ConsoleColors.RESET);

        this.setStatus(GameStatus.RUNNING);

        // TODO Uncomment after testing
        // Roll dice to determine starting player
        /*
        Player startingPlayer;
        {
            // Separator: 'Determining starting player by rolling the dice...'
            System.out.println(ConsoleColors.YELLOW + "Determining starting player by rolling the dice..." + ConsoleColors.RESET);

            // Initialize rolls
            HashMap<Player, Integer> rolls = new HashMap<>() {{
                for ( Player player : getPlayers()) {
                    put(player, 0);
                }
            }};

            // Roll dice until a starting player is determined
            while (rolls.size() != 1) {

                // Roll dice for each player
                for ( Player player : this.getPlayers() ) {

                    // Ensure player can roll dice
                    if (!rolls.containsKey(player)) continue;

                    // Request player to roll dice
                    System.out.print(ConsoleColors.CYAN + player.getName() + ", roll the dice (r) " + ConsoleColors.RESET);
                    String input = scanner.next();
                    while (!input.equals("r")) {
                        System.out.print("Please use the correct key (r).");
                        input = scanner.next();
                    }

                    // Roll dice for player
                    Action action = new Action(player, ActionType.ROLL_DICE);
                    int currentRoll = (int) player.performAction(action);
                    rolls.put(player, currentRoll);

                }

                // Determine highest value
                int highestRoll = Collections.max(rolls.values());

                // Remove players with lower rolls
                for ( Player player : this.getPlayers()) {
                    if (!rolls.containsKey(player)) continue;
                    if (rolls.get(player) != highestRoll) rolls.remove(player);
                }

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
        }*/

        // TODO Comment after testing

        this.shuffleCards();

        this.setCurrentPlayer(playerA);

        int input;

        // Play the Game as long as there isn't a winner
        winnerLoop:
        while (winner == null) {

            // Loop through turns of players
            playerLoop:
            for ( Player player : this.getPlayers()) {

                while (player.getStatus() != PlayerStatus.IDLE) {

                    // Return if it isn't the player's turn
                    if (player != currentPlayer) continue playerLoop;
                    if (player.getStatus() == PlayerStatus.DEFEATED) continue playerLoop;

                    // Check for a winner
                    if ( winner != null ) break winnerLoop;

                    // Start player's turn
                    player.startTurn();

                    // Add action types to map
                    ArrayList<ActionType> possibleActionTypes = player.getPossibleActionTypes();

                    // Request input: 'Which action would you like to perform?'
                    {

                        // Separator
                        System.out.println();
                        System.out.println(ConsoleColors.YELLOW_UNDERLINED + player.getName() + "'s turn (" + player.getToken().getName() + ")");

                        // Request input: 'Which action would you like to perform?'
                        System.out.println(ConsoleColors.YELLOW_BRIGHT + "Which action would you like to perform (1-" + possibleActionTypes.size() + ")?" + ConsoleColors.RESET);
                        for ( int i = 0; i < possibleActionTypes.size(); i++ ) {
                            System.out.println((i + 1) + ". " + possibleActionTypes.get(i).getText());
                        }
                        System.out.println();

                        input = scanner.nextInt() - 1;
                        while ( !(0 <= input && input < possibleActionTypes.size()) ) {
                            System.out.print("Enter a valid number between 1-" + possibleActionTypes.size() + ". ");
                            input = scanner.nextInt() - 1;
                        }

                        // Determine Action
                        Action action = new Action(possibleActionTypes.get(input));

                        // Perform Action
                        player.performAction(action);

                        // If action progresses turn
                        if (action.getActionType().progressesTurn()) {

                            // If player is in jail
                            if (player.isInJail()) {
                                player.setInJail(false);
                            }

                            // If player is on TURN_START
                            if (player.getStatus() == PlayerStatus.TURN_START) {
                                player.setStatus(PlayerStatus.TURN_MOVED);
                            }

                            // If player is on TURN_MOVED
                            else if (player.getStatus() == PlayerStatus.TURN_MOVED) {

                                // If player rolled doubles
                                if (player.hasRolledDoubles()) {
                                    player.setStatus(PlayerStatus.TURN_START);
                                }

                                // If player hasn't rolled doubles
                                else {
                                    player.endTurn();
                                }

                            }

                        }

                    }

                }

            }

        }

        this.setStatus(GameStatus.FINISHED);

        return this;

    }

}
