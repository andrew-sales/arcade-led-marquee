/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class SandBox {
    
    
    public GridBagConstraints gbc;
    public int gridWidth;
    public int gridHeight;
    public int xPos;
    public int yPos;
    public Point location; 
    int[] cellPos;
    public HashMap<CellPane, Point> individualCell;

    public static void main(String[] args) {
        new SandBox();
    }

    public SandBox() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        
        
        
        
        
        public TestPane() {
            setLayout(new GridBagLayout());

            gbc = new GridBagConstraints();
            gridHeight = 5;
            gridWidth = 5;
            individualCell = new HashMap<>();
            cellPos = new int[gridWidth * gridHeight];
            
            for (int row = 0; row < gridWidth; row++) {
                for (int col = 0; col < gridHeight; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    CellPane cellPane = new CellPane();
                    Border border = null;
                    if (row < 4) {
                        if (col < 4) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < 4) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    cellPane.setBorder(border);
                    add(cellPane, gbc);
                    location = new Point(row, col);
                    
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
              
                }

                @Override
        public void mouseClicked(MouseEvent e) {
                    setBackground(Color.RED);
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
        
    }
}
