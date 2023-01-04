package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import static src.ChooseWindow.windowHeight;
import static src.ChooseWindow.windowWidth;

/**
 * Publiczna klasa CatchButton zawierająca konstruktor CatchButton przechowujący wszystkie elementy potrzebne do wyświetlenia gry w łapanie przycisku
 */


public class CatchButton extends JFrame implements ActionListener {

    int runningButtonWidth=80, normalButtonWidth=100;
    int runningButtonHeight=80, normalButtonHeight=100;
    int runningButtonX, runningButtonY;
    int normalButtonX=windowWidth/2-normalButtonWidth/2;
    int normalButtonY=windowHeight/2-normalButtonHeight/2;

    double mouseX,mouseY;

    JButton JStart,JAgain,JEnd,runningButton;
    JLabel lSuccess;

    URL LGiconURL;

     CatchButton() {
         //Deklaracja okna
        setSize(windowWidth, windowHeight);
        setTitle("Złap przycisk!");
        setLayout(null);
        getContentPane().setBackground(new Color(153, 217, 234));

         //Deklaracja przycisków

         JStart = new JButton();
         JStart.setBounds(normalButtonX,normalButtonY,normalButtonWidth,normalButtonHeight);
         LGiconURL = getClass().getResource("/src/icons/start.png");
         assert LGiconURL != null : "start button icon not found";
         JStart.setIcon(new ImageIcon(LGiconURL));

         JAgain = new JButton();
         JAgain.setBounds(normalButtonX,normalButtonY,normalButtonWidth,normalButtonHeight);
         LGiconURL = getClass().getResource("/src/icons/again.png");
         assert LGiconURL != null : "again button icon not found";
         JAgain.setIcon(new ImageIcon(LGiconURL));

        JEnd = new JButton();
        JEnd.setBounds(normalButtonX,normalButtonY+100,normalButtonWidth,normalButtonHeight);
        LGiconURL = getClass().getResource("/src/icons/end.png");
        assert LGiconURL != null : "end button icon not found";
        JEnd.setIcon(new ImageIcon(LGiconURL));


        runningButton = new JButton();
        LGiconURL = getClass().getResource("/src/icons/runningButton.png");
        assert LGiconURL != null : "running button icon not found";
        runningButton.setIcon(new ImageIcon(LGiconURL));

        lSuccess = new JLabel("Przycisk złapany!");
        lSuccess.setBounds(windowWidth/2-150, windowHeight/2-130, 500, 80);
        lSuccess.setFont(new Font("Dialog", Font.ITALIC, 40));

        runningButton.addActionListener(this);
        JStart.addActionListener(this);
        JAgain.addActionListener(this);
        JEnd.addActionListener(this);

        add(JStart);
    }

    public void Movement(){
        runningButtonX=(int)(Math.random()*(windowWidth-runningButtonX+1))+runningButtonWidth;
        runningButtonY=(int)(Math.random()*(windowHeight-runningButtonY+1))+runningButtonHeight;

        runningButton.setBounds(runningButtonX,runningButtonY,runningButtonWidth,runningButtonHeight);
    }

    public void RunningButtonSetter(){
        runningButtonX=windowWidth/2-runningButtonWidth/2;
        runningButtonY=windowHeight/2-runningButtonHeight/2;
        runningButton.setBounds(runningButtonX,runningButtonY,runningButtonWidth,runningButtonHeight);
        add(runningButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source==JStart){
            remove(JStart);

            getContentPane().setBackground(new Color(150, 217, 234));

            RunningButtonSetter();
        }

        if(source==runningButton){
            mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            mouseY = MouseInfo.getPointerInfo().getLocation().getY();

            if(mouseX>=(runningButtonX+runningButtonWidth) && mouseX<=(runningButtonX+runningButtonWidth+10)
                    && mouseY<=(runningButtonY+runningButtonHeight) && mouseY>=runningButtonY){

                remove(runningButton);

                getContentPane().setBackground(new Color(153, 234,180 ));

                add(lSuccess);
                add(JAgain);
                add(JEnd);
            }
            else{
                Movement();
            }
        }

        if(source==JAgain){
            getContentPane().setBackground(new Color(154, 241, 241));

            remove(lSuccess);
            remove(JAgain);
            remove(JEnd);

            RunningButtonSetter();
            add(runningButton);
        }

        if(source==JEnd) {
            SwingUtilities.invokeLater(() -> {
                try{
                    ChooseWindow window = new ChooseWindow();
                    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    window.setVisible(true);
                    dispose();
                } catch (Exception exc) {
                    exc.printStackTrace(System.err);
                }
            });
        }
    }
}
