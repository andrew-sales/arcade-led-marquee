/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy
 */
public class Controller {
    
private final View newGUI;



public Controller () { 

    newGUI = new View();
    newGUI.setVisible(true);

//this.newGUI.selectFileForConversionActionListener (new ListenerSelectFileForConversionButton());
this.newGUI.ConvertButton (new ListenerSelectFileForConversionButton());
}

class ListenerSelectFileForConversionButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
            
            try {
                ImageToText newImageToText = new ImageToText(newGUI.getTestField());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
       
        
        }

}
