package com.jaywithabeanie.monopoly.Objects.Game.Frame;

import com.jaywithabeanie.monopoly.Objects.Game.Game;
import com.jaywithabeanie.monopoly.Objects.Game.Player.Player;
import com.jaywithabeanie.monopoly.Utils.CalculateAspectRatio;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.synth.Region;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Frame {

    public static JFrame frame;
    public static JLayeredPane board;
    public static HashMap<Integer, ArrayList<Object>> labels = new HashMap<Integer, ArrayList<Object>>();

    public static HashMap<Player, JLabel> balances = new HashMap<>() {{
        put(null, new JLabel());
    }};
    public static HashMap<Player, JLabel> players = new HashMap<>();

    public static HashMap<Integer, ArrayList<Dimension>> spaceLocations = new HashMap<>(){{
        // Move 1 space -> + 65
        put(0, new ArrayList<>(){{
            add(0, new Dimension(690, 710));
            add(1, new Dimension(690, 750));
            add(2, new Dimension(720, 710));
            add(3, new Dimension(720, 750));
        }});
        put(10, new ArrayList<>(){{
            // Just visiting
            add(0, new Dimension(5, 690));
            add(1, new Dimension(5, 730));
            add(2, new Dimension(5, 770));
            add(3, new Dimension(45, 770));
            // Jail
            add(4, new Dimension(30, 690));
            add(5, new Dimension(70, 730));
            add(6, new Dimension(30, 690));
            add(7, new Dimension(70, 730));
        }});
        put(11, new ArrayList<>(){{
            add(0, new Dimension(45, 620));
            add(1, new Dimension(5, 620));
            add(2, new Dimension(45, 660));
            add(3, new Dimension(5, 660));
        }});
        put(20, new ArrayList<>(){{
            add(0, new Dimension(70, 45));
            add(1, new Dimension(70, 5));
            add(2, new Dimension(30, 45));
            add(3, new Dimension(30, 5));
        }});
        put(30, new ArrayList<>(){{
            add(0, new Dimension(720, 70));
            add(1, new Dimension(760, 70));
            add(2, new Dimension(720, 30));
            add(3, new Dimension(760, 30));
        }});
    }};

    public static void createFrame(Game game) {

        // Create frame
        frame = new JFrame("Monopoly");

        // Change frame size
        Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(800, 800);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        // Create panel
        board = new JLayeredPane();

        // Add panel to frame
        frame.add(board);

        // Set panel properties
        board.setBounds((dimensions.width - 804) / 2, 100, 804, 804);

        // Create board label
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(Frame.class.getResource("/resources/Board/Board.png")));
        Image image_board = imageIcon.getImage().getScaledInstance(board.getWidth()-4, board.getHeight()-4, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image_board);
        JLabel label_board = new JLabel(scaledImageIcon);

        // Set border and add board
        label_board.setSize(board.getWidth()-4, board.getHeight()-4);
        label_board.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        board.add(label_board, Integer.valueOf(0));

        // Add players on board
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            JLabel label = new JLabel();
            // Set player token
            {
                URL imageURL = Objects.requireNonNull(Frame.class.getResource("/resources/Tokens/" + player.getToken().getName() + ".png"));
                Dimension dimension = null;
                try {
                    dimension = CalculateAspectRatio.CalculateAspectRatio(imageURL, new Dimension(40, 40));
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
                Image image = null;
                try {
                    image = ImageIO.read(imageURL);
                    image = image.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH);
                    System.out.println(image);
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
                label.setIcon(new ImageIcon(image));
            }
            label.setLocation(Frame.spaceLocations.get(0).get(i).width, Frame.spaceLocations.get(0).get(i).height);
            label.setSize(40, 40);
            board.add(label, Integer.valueOf(1));
            Frame.players.put(player, label);
        }

        // Balances panel
        JPanel panel_balances = new JPanel();
        panel_balances.setBounds(10, 10, 150, 40 + 20 * game.getPlayers().size());
        panel_balances.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 2), new EmptyBorder(5, 5, 5, 5)));
        // Set grid layout
        {
            GridLayout gridLayout = new GridLayout();
            gridLayout.setColumns(1);
            gridLayout.setRows(5);
            gridLayout.setVgap(3);
            panel_balances.setLayout(gridLayout);
        }

        JLabel label_balances = new JLabel("Balances:");
        label_balances.setBounds(0, 0, 150, 40);
        label_balances.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,15));
        panel_balances.add(label_balances);
        // Add player balance labels
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            JLabel label_playerBalance = new JLabel();
            label_playerBalance.setText(" " + player.getName() + ": $" + player.getBalance());
            // Set player token
            {
                URL imageURL = Objects.requireNonNull(Frame.class.getResource("/resources/Tokens/" + player.getToken().getName() + ".png"));
                Dimension dimension = null;
                try {
                    dimension = CalculateAspectRatio.CalculateAspectRatio(imageURL, new Dimension(20, 20));
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
                Image image = null;
                try {
                    image = ImageIO.read(imageURL);
                    image = image.getScaledInstance((int) 22, (int) dimension.getHeight(), Image.SCALE_SMOOTH);
                    System.out.println(image);
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
                label_playerBalance.setIcon(new ImageIcon(image));
            }
            label_playerBalance.setHorizontalTextPosition(JLabel.RIGHT);
            label_playerBalance.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,13));
            label_playerBalance.setForeground(Color.GRAY);
            label_playerBalance.setBounds(0, (20 + 20*i), 150, 20);
            panel_balances.add(label_playerBalance);

            balances.put(player, label_playerBalance);
        }

        frame.add(panel_balances);

        // Default frame settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


}
