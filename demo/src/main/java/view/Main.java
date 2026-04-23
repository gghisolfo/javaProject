package view;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


// // public class Main {
// //     public static void main(String[] args) {
// //         SwingUtilities.invokeLater(() -> {
// //             new MainFrame().setVisible(true);
// //         });
// //     }
// // }

public class Main {
    public static void main(String[] args) {
        
try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        // UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
    } catch (Exception e) {
        e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));

    }
}

