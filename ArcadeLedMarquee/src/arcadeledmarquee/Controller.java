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
private final SerialTest main;




public Controller () { 

    newGUI = new View();
    newGUI.setVisible(true);
    
    main = new SerialTest();

//this.newGUI.selectFileForConversionActionListener (new ListenerSelectFileForConversionButton());
this.newGUI.ConvertButton (new ListenerSelectFileForConversionButton());
this.newGUI.OpenPortButtonListener(new ListenerOpenPortButton());
this.newGUI.ClosePortButtonListener(new ListenerClosePortButton());
this.newGUI.SendDataButtonListener(new ListenerSendDataButton());
}

class ListenerSelectFileForConversionButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
            
            try {
                ImageToText newImageToText = new ImageToText(newGUI.getFileForConversion());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
       
        
        }


class ListenerOpenPortButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
                
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
                
                
                
            
                
        } 
        
       
        
        }


class ListenerClosePortButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
           main.close();
           
        }
}
        
        
class ListenerSendDataButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
           try {
                main.sendCharToArduino(newGUI.getTestConnectionString());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        } 
        
        }
}

