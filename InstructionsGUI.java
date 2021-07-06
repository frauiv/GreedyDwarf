package com.company;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class InstructionsGUI extends JFrame{
    private JTextPane textPane;
    private JPanel panel1;

    public InstructionsGUI() {
        add(panel1);
        setTitle("Greedy Dwarf Main Menu");
        setSize(400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
