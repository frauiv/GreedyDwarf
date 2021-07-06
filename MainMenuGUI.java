package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JFrame{
    private JButton startButton;
    private JButton instructionsButton;
    private JPanel rootPanel;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    public MainMenuGUI(){
        add(rootPanel);
        setTitle("Greedy Dwarf Main Menu");
        setSize(600,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructionsGUI instGUI = new InstructionsGUI();
                instGUI.setVisible(true);
                // JOptionPane.showMessageDialog(rootPanel," Use arrows on your keyboard to move your dwarf. \n Avoid the dragon, and it's personal space, you don't want it to go after you!\n Remember, your dwarf is not too strong, so be mindful in the order you pick up the treasures.\n Coins are light, diamonds are heavy, and rubies are in between. \n When you are ready for the next challenge, move your dwarf to the bottom wall, and you'll escape the dragon, and be closer to your win!\n If the dragon catches you, you won't die right a way, but you will lose a part of your collected treasure!");
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gameFrame = new JFrame("Greedy Dwarf");
                // TODO Auto-generated method stub
                Map map = new Map(WIDTH,HEIGHT,BACKGROUND_COLOR,gameFrame);
                MapComponent theMap = new MapComponent(map);
                gameFrame.add(theMap, BorderLayout.CENTER);
                gameFrame.pack();
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setVisible(true);
            }
        });
    }
}
