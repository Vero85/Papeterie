package fr.eni.ihm;

import javax.swing.*;

public class App {
    private JTextField blablaTextField;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JPanel monPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().monPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
