package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static src.ChooseWindow.windowHeight;
import static src.ChooseWindow.windowWidth;

/**
 * publiczna klasa ShapesDrawing zawierająca konstruktor ShapesDrawing przechowujący wszystkie elementy potrzebne do wyświetlenia kanwy do rysowania kształtów
 */

public class ShapesDrawing extends JFrame implements ActionListener, KeyListener, MouseListener{

    int buttonPanelWidth=120, settingsPanelHeight=30, size=10, positionX, positionY;
    char shape='Q';
    Color color = Color.black;

    JButton colorButton, clearButton, exitButton;
    JPanel buttonPanel, settingsPanel, canva;
    JLabel currentColorText, currentColor, currentShapeText, currentShape, currentSizeText, currentSize, shapesInfo, sizeInfo;

    URL LGiconURL;

    ShapesDrawing(){
        //Deklaracja okna

        setSize(windowWidth,windowHeight);
        setTitle("Draw Shapes");
        setLayout(null);
        setFocusable(true);
        requestFocus();
        getContentPane().setBackground(Color.white);


        //Deklaracja panelu zawierającego przyciski oraz informacje o użytkowych przyciskach klawiatury

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(new Color(153, 217, 234));
        buttonPanel.setBounds(0,0,buttonPanelWidth,windowHeight);


        //Deklaracja elementów buttonPanel

        shapesInfo = new JLabel("<html>Wybór kształtu:<br/>[Q] Koło <br/>[W] Okrąg <br/>[E] Kwadrat</html>");
        shapesInfo.setBounds(10, 0, 100, 100);
        shapesInfo.setFont(new Font("Dialog", Font.ITALIC, 13));

        sizeInfo = new JLabel("<html>Wybór rozmiaru:<br/>[1] 10x10 <br/>[2] 20x20 <br/>[3] 30x30 <br/>[4] 40x40 <br/>[5] 50x50</html>");
        sizeInfo.setBounds(10, 430, 100, 110);
        sizeInfo.setFont(new Font("Dialog", Font.ITALIC, 13));

        colorButton = new JButton();
        colorButton.setBounds(10,100,100,100);
        LGiconURL = getClass().getResource("/src/icons/colorButton.png");
        assert LGiconURL != null : "again button icon not found";
        colorButton.setIcon(new ImageIcon(LGiconURL));
        colorButton.setFont(new Font("Dialog", Font.ITALIC,10));

        clearButton = new JButton();
        clearButton.setBounds(10,210,100,100);
        LGiconURL = getClass().getResource("/src/icons/clearButton.png");
        assert LGiconURL != null : "again button icon not found";
        clearButton.setIcon(new ImageIcon(LGiconURL));
        clearButton.setFont(new Font("Dialog", Font.ITALIC,10));

        exitButton=new JButton();
        exitButton.setBounds(10,320,100,100);
        LGiconURL = getClass().getResource("/src/icons/end.png");
        assert LGiconURL != null : "again button icon not found";
        exitButton.setIcon(new ImageIcon(LGiconURL));
        exitButton.setFont(new Font("Dialog", Font.ITALIC,10));


        //Deklaracja panelu zawierającego informacje o aktualnych ustawieniach

        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setVisible(true);
        settingsPanel.setBackground(new Color(153, 217, 234));
        settingsPanel.setBounds(120,0,windowWidth,settingsPanelHeight);

        //Deklaracja elementów settingsPanel
        currentColorText = new JLabel("Aktualny kolor:");
        currentColorText.setBounds(230, 5, 110, 20);
        currentColorText.setFont(new Font("Dialog", Font.ITALIC, 15));

        currentColor = new JLabel();
        currentColor.setBounds(345, 5, 20, 20);
        currentColor.setOpaque(true);
        currentColor.setBackground(Color.BLACK);

        currentShapeText = new JLabel("Aktualny kształt:");
        currentShapeText.setBounds(470, 5, 110, 20);
        currentShapeText.setFont(new Font("Dialog", Font.ITALIC, 15));

        currentShape = new JLabel("Koło");
        currentShape.setBounds(585, 5, 60, 20);
        currentShape.setFont(new Font("Dialog", Font.ITALIC, 15));

        currentSizeText = new JLabel("Aktualny rozmiar:");
        currentSizeText.setBounds(730, 5, 130, 20);
        currentSizeText.setFont(new Font("Dialog", Font.ITALIC, 15));

        currentSize = new JLabel("10x10");
        currentSize.setBounds(855, 5, 45, 20);
        currentSize.setFont(new Font("Dialog", Font.ITALIC, 15));


        //Dodanie wszystkich elementów buttonPanel

        buttonPanel.add(clearButton);
        buttonPanel.add(colorButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(sizeInfo);
        buttonPanel.add(shapesInfo);


        //Dodanie wszystkich elementów settingsPanel

        settingsPanel.add(currentColorText);
        settingsPanel.add(currentShapeText);
        settingsPanel.add(currentSizeText);
        settingsPanel.add(currentColor);
        settingsPanel.add(currentShape);
        settingsPanel.add(currentSize);


        //Deklaracja kanwy do rysowania

        canva = new JPanel();
        canva.setBounds(buttonPanelWidth,settingsPanelHeight,windowWidth-buttonPanelWidth,windowHeight-settingsPanelHeight);


        //Dodanie wszystkich elementów okna

        add(settingsPanel,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.EAST);
        add(canva);


        //Dodanie Listenerów

        colorButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        addKeyListener(this);
        canva.addMouseListener(this);
    }

    //Metoda przypisująca zmiennej globalnej color aktualnie wybrany kolor, wykorzystująca komponent JColorChooser
    void changingColor(){
        color = JColorChooser.showDialog(null, "Wybierz kolor", Color.black);
    }

    //Metoda wywoływana przez naciśnięcie myszą na kanwie, umieszcza na koordynatach kursora wybrany wcześniej kształt
    void drawShape(){
        if(shape == 'Q'){
            Graphics graphics = canva.getGraphics();
            graphics.setColor(color);
            graphics.fillOval(positionX,positionY,size,size);
    }else{
        if(shape == 'W'){
            Graphics graphics = canva.getGraphics();
            graphics.setColor(color);
            graphics.drawOval(positionX,positionY,size,size);
        }else{
            if(shape == 'E'){
                Graphics graphics = canva.getGraphics();
                graphics.setColor(color);
                graphics.fillRect(positionX,positionY,size,size);
            }
        }
    }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == colorButton){
            changingColor();
            currentColor.setBackground(color);
        }else{
            if(source == clearButton){
                canva.getGraphics().clearRect(0,0,windowWidth-buttonPanelWidth,windowHeight-settingsPanelHeight);
            }else{
                if(source == exitButton){
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
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char source = e.getKeyChar();
        if(source == 'Q' || source == 'q'){
            currentShape.setText("Koło");
            shape = 'Q';
        }else{
            if(source == 'W' || source == 'w'){
                currentShape.setText("Okrąg");
                shape = 'W';
            }else{
                if(source == 'E' || source == 'e'){
                    currentShape.setText("Kwadrat");
                    shape = 'E';
                }else{
                    if(source == '1'){
                        size=10;
                        currentSize.setText(size + "x" + size);
                    }else{
                        if(source == '2'){
                            size=20;
                            currentSize.setText(size + "x" + size);
                        }else{
                            if(source == '3'){
                                size=30;
                                currentSize.setText(size + "x" + size);
                            }else{
                                if(source == '4'){
                                    size=40;
                                    currentSize.setText(size + "x" + size);
                                }else{
                                    if(source == '5'){
                                        size=50;
                                        currentSize.setText(size + "x" + size);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


    @Override
    public void mouseClicked(MouseEvent e) {
        positionX = e.getX()-size/2;
        positionY = e.getY()-size/2;
        drawShape();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
