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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Editor {
    
    
    public GridBagConstraints gbc;
    public int gridWidth;
    public int gridHeight;
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

//    public static void main(String[] args) {
//        new Editor();
//    }

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
                
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            gridHeight = 64;   //wrong way around needs fixing
            gridWidth = 32;
            individualCell = new HashMap<>();
            cellPos = new int[gridWidth * gridHeight];
            
            for (int row = 0; row < gridWidth; row++) {
                for (int col = 0; col < gridHeight; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    CellPane cellPane = new CellPane();
                    Border border = null;
                    if (row < (gridWidth - 1)) {
                        if (col < (gridHeight - 1)) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < (gridHeight - 1)) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    cellPane.setBorder(border);
                    add(cellPane, gbc);
                    location = new Point(col, row);
                    
                    //System.out.println(location.toString());
                    
                    individualCell.put(cellPane, location);
                   
                    
                    
                //   System.out.println("hello" + individualCell.get(cellPane).toString());
                  //  System.out.println (cellPane);
                    
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
             //  System.out.println (thisCellPane);
                   // setBackground(Color.BLUE);
                     
   // e.getLocationOnScreen();
   // System.out.println(e.getLocationOnScreen());//these co-ords are relative to the component
//System.out.println(gbc.gridx);

          //      System.out.println(this);
            Point thisLocation = individualCell.get(thisCellPane);
              // System.out.println(individualCell.get(thisCellPane));
              
              xPos = (int) thisLocation.getX();
              yPos = (int) thisLocation.getY();
              
           //   System.out.print(RGBToLED.getMouseDown());
              if (mouseDown) {
                  
                  drawCell();
              }
                }

                @Override
        public void mousePressed(MouseEvent e) {
                    
            mouseDown = true;
           // setBackground(Color.RED);
            
           drawCell();
                    
                }        
        
                @Override
        public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
        mouseDown = false;
    }
}
                
                
                
//                @Override
//                public void mouseExited(MouseEvent e) {
//                    setBackground(defaultBackground);
//                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
        
        
        public int getXPos () {
            
            return xPos;
        }
        
        public int getYPos () {
            
            return yPos;
        }
        
        
        public void drawCell () {
        
        int redValue;
           int greenValue;
           int blueValue;
           
           redValue = RGBToLED.convertLEDToRGB(newGUI.getRedLedString());
           greenValue = RGBToLED.convertLEDToRGB(newGUI.getGreenLedString());
           blueValue = RGBToLED.convertLEDToRGB(newGUI.getBlueLedString());
           
//           redValue = 51*Integer.parseInt(newGUI.getRedLedString());
//           greenValue = 51*Integer.parseInt(newGUI.getGreenLedString());
//           blueValue = 51*Integer.parseInt(newGUI.getBlueLedString());
           
           
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

