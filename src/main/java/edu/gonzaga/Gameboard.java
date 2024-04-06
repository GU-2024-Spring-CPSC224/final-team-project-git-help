package edu.gonzaga;

import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class Gameboard {
    
    public static void main(String args[]) {

        new Gameboard().generateGameboardImage();
    }

    private void generateGameboardImage(){

        try {

            JFrame gameBoard = new JFrame();
            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 1472, 908);

            gameBoard.setTitle("Pandemic!");
            BufferedImage img = ImageIO.read(new File("src/main/java/edu/gonzaga/Pandemic_Gameboard.png"));
            JLabel pic = new JLabel(new ImageIcon(img));

            panel.add(pic);
            gameBoard.add(panel);
            gameBoard.setSize(1800, 1000);
            gameBoard.setLayout(null);
            gameBoard.setVisible(true);

            // rest of the code
        } catch (IOException e) {
            // handle the exception, e.g. logging the error or displaying a message to the user
        }
    }
}
