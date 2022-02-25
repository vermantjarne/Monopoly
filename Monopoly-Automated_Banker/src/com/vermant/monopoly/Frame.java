package com.vermant.monopoly;

import javax.swing.*;
import java.awt.*;

public class Frame {

    public static JFrame frame;

    public static void createFrame() {

        // Create frame
        frame = new JFrame();

        // Change frame size
        Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimensions.width, dimensions.height);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        // Create device
        JPanel device = new JPanel();
        device.setBounds((frame.getWidth() - 500)/2, (frame.getHeight() - 500)/2, 500, 500);
        device.setBackground(Color.gray);
        device.setLayout(null);

            // Create buttons on device
            JButton button = new JButton("+");
            button.setBounds(0, 0, 500, 500);

            // Add button to device
            device.add(button);

            frame.add(device);


        // Default frame settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

    }


}
