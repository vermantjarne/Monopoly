package com.jaywithabeanie.monopoly.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class CalculateAspectRatio {

    public static Dimension CalculateAspectRatio(URL url, Dimension maxSize) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(url);

        double ratio = Math.min(maxSize.getWidth() / bufferedImage.getWidth(), maxSize.getHeight() / bufferedImage.getHeight());

        return new Dimension(
                (int) (bufferedImage.getWidth() * ratio),
                (int) (bufferedImage.getHeight() * ratio)
        );

    }

}
