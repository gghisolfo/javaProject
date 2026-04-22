package Rubrica.bin;

import javax.swing.SwingUtilities;

import Rubrica.src.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}


