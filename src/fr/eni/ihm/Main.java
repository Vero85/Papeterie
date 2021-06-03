package fr.eni.ihm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // permet de lancer un nouveau thread (un canal séparé)
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        // création d'une fenêtre
                        JFrame app = new GUI();

                    }
                }
        );
    }
}
