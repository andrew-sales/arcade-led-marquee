/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

/**
 *
 * @author Andy
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Editor {
    
    public GridBagConstraints gbc;
    public int gridHeight;
    public int gridWidth;
    public int xPos;
    public int yPos;
    public Point location; 
    int[] cellPos;
    public HashMap<CellPane, Point> individualCell;
    public final SerialConnection main;
    public String redValue;
    public String greenValue;
    public String blueValue;
    public final View newGUI;
    public JFrame frame;
    public boolean mouseDown;

    public Editor(SerialConnection newMain, View newGuiView) {
        
        
        this.main = newMain;
        this.newGUI = newGuiView;
     
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                    frame = new JFrame("Editor");
                    frame.setPreferredSize(new Dimension(850, 450));
                    frame.setLayout(new BorderLayout());
                    frame.add(new TestPane());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
            }
        });
        
    }
    
    public void closeFrame () {
        
        frame.dispose();    
    } 
    

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridBagLayout());

            gbc = new GridBagConstraints();
            gridWidth = 64;
            gridHeight = 32;
            individualCell = new HashMap<>();
            cellPos = new int[gridHeight * gridWidth];
            
            for (int row = 0; row < gridHeight; row++) {
                for (int col = 0; col < gridWidth; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    CellPane cellPane = new CellPane();
                    Border border = null;
                    if (row < (gridHeight - 1)) {
                        if (col < (gridWidth - 1)) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < (gridWidth - 1)) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                
                cellPane.setBorder(border);
                add(cellPane, gbc);
                location = new Point(col, row);
                individualCell.put(cellPane, location);

                }
            }
        }
    }

    public class CellPane extends JPanel {

        private Color defaultBackground;

        public CellPane() {
            
            setBackground( new Color(0, 0, 0) );      
            addMouseListener(new MouseAdapter() {
             
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    defaultBackground = getBackground();
                    
                CellPane thisCellPane = (CellPane)e.getSource();
                Point thisLocation = individualCell.get(thisCellPane);
              
                xPos = (int) thisLocation.getX();
                yPos = (int) thisLocation.getY();
              
                    if (mouseDown) {                
                        drawCell();}
                    }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                    mouseDown = true;           
                    drawCell();        
                }        
        
                @Override
                public void mouseReleased(MouseEvent e) {
                
                    if (e.getButton() == MouseEvent.BUTTON1) {
                    mouseDown = false;
                }
}

            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
        
        public void drawCell () {
        
            int redValue;
            int greenValue;
            int blueValue;
           
            redValue = RGBToLED.convertLEDToRGB(newGUI.getRedLedString());
            greenValue = RGBToLED.convertLEDToRGB(newGUI.getGreenLedString());
            blueValue = RGBToLED.convertLEDToRGB(newGUI.getBlueLedString());

            setBackground( new Color(redValue, greenValue, blueValue) );
                    
            String transferString = ("$" + Integer.toString(xPos) + "," + Integer.toString(yPos) + "," +
                newGUI.getRedLedString() + "," + newGUI.getGreenLedString()  + "," + newGUI.getBlueLedString() + "*");
                    
                try {
                main.sendRGBToArduino(transferString.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }    
    }
        
    }
}

