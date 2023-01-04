package src;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa main zawierająca statyczną metodę main wywołującą działanie programu
 */

public class Main extends JFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try{
                ChooseWindow window = new ChooseWindow();
                window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        });
    }
}
