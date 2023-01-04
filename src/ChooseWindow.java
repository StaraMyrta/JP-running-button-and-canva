package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * publiczna klasa ChooseWindow zawierająca przyciski pozwalające uruchomić konstruktory CatchButton lub ShapesDrawing
 */

public class ChooseWindow extends JFrame implements ActionListener {

    public static int windowWidth=1200, windowHeight=600;

    JButton drawingShapes = new JButton("Rysowanie kształtów"), catchButton = new JButton("Złap przycisk!");

    public ChooseWindow(){
        setSize(windowWidth,windowHeight);
        setTitle("Choose your game");
        setLayout(null);

        drawingShapes.setBackground(new Color(220, 219, 169));
        drawingShapes.setBounds(0,0,windowWidth/2,windowHeight);
        drawingShapes.setFont(new Font("Dialog", Font.ITALIC,20));

        catchButton.setBackground(new Color(170, 219, 169));
        catchButton.setBounds(windowWidth/2,0,windowWidth/2,windowHeight);
        catchButton.setFont(new Font("Dialog",Font.ITALIC,20));

        add(catchButton);
        add(drawingShapes);

        catchButton.addActionListener(this);
        drawingShapes.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == drawingShapes){
            SwingUtilities.invokeLater(()->{
                try {
                    ShapesDrawing shapesDrawing = new ShapesDrawing();
                    shapesDrawing.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    shapesDrawing.setVisible(true);
                    dispose();
                }catch (Exception exc) {
                    exc.printStackTrace(System.err);
                }
            });
        }else{
            if(source == catchButton){
                SwingUtilities.invokeLater(()->{
                    try{
                    CatchButton catchButton = new CatchButton();
                    catchButton.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    catchButton.setVisible(true);
                    dispose();
                } catch (Exception exc) {
                    exc.printStackTrace(System.err);
                }
            });
            }
        }

    }
}
