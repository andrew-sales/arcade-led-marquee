/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Andy
 */
public class Controller {
    
private final GUI newGUI;

public Controller () { 

    newGUI = new GUI();
    newGUI.setVisible(true);

this.newGUI.selectFileForConversionActionListener (new ListenerSelectFileForConversionButton());
}

class ListenerSelectFileForConversionButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
//            newGUI.setTestField("Hello");
            //code to handle choosed file here. 
        } 
        }

}
