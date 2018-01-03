/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Andy
 */
public class Controller {
    
private final View newGUI;
private final SerialConnection main;
private final RGBToLED newRGBToLED;




public Controller () throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException { 

    newGUI = new View();
    newGUI.setVisible(true);  
    newRGBToLED = new RGBToLED();
    
    main = new SerialConnection();

//this.newGUI.selectFileForConversionActionListener (new ListenerSelectFileForConversionButton());
this.newGUI.ConvertButton (new ListenerSelectFileForConversionButton());
this.newGUI.OpenPortButtonListener(new ListenerOpenPortButton());
this.newGUI.ClosePortButtonListener(new ListenerClosePortButton());
this.newGUI.SendDataButtonListener(new ListenerSendDataButton());
this.newGUI.SendRGBButtonListener(new listenerSendRGBButton());
this.newGUI.SendFileButtonListener(new listenerSendFileButton());
this.newGUI.ClearPanelButtonListener(new listenerClearPanelButton());

}

class ListenerSelectFileForConversionButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
            
            try {
                ImageToText newImageToText = new ImageToText(newGUI.getFileForConversion(), newGUI.getOutputFolder());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
       
        
        }


class ListenerOpenPortButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
                
		newGUI.setPortStatusLabel(main.initialize());
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
                //newGUI.setPortStatusLabel("Searching for Port");
                          
        } 

        }


class ListenerClosePortButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
           String portStatus = main.close();
           newGUI.setPortStatusLabel(portStatus);
           
        }
}
        
        
class ListenerSendDataButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
           try {
               System.err.println(newGUI.getTestConnectionString()); 
               main.sendRGBToArduino(newGUI.getTestConnectionString().getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        } 
        
        }

class listenerSendRGBButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
//        byte[] newByteArray = new byte[5];
        
 String transferString = new String();

        if ("DrawCircle".equals(newGUI.getDrawMode())) {
            
           
        
        transferString = (newGUI.getXCoordString() + "," + newGUI.getYCoordString() + "," + newGUI.getXCoord2String()
                +"," + "0" + "," +
                newGUI.getRedLedString() + "," + newGUI.getGreenLedString()  + "," +
                newGUI.getBlueLedString() + "," + "c" + "+");
            System.out.println(transferString);
            
        }
        
        if ("DrawPixel".equals(newGUI.getDrawMode())) {
        
  
        
        transferString = (newGUI.getXCoordString() + "," + newGUI.getYCoordString() + "," +
                newGUI.getRedLedString() + "," + newGUI.getGreenLedString()  + "," +
                newGUI.getBlueLedString() + "*");
            System.out.println(transferString);
        

        }            

            try {
                main.sendRGBToArduino(transferString.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } 
}
        


class listenerSendFileButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
         try {
             File file = new File(newGUI.getArduinoDataFile());
             System.out.println(newGUI.getArduinoDataFile());
             FileReader filereader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(filereader);
             StringBuffer stringBuffer = new StringBuffer();
             String line;
             while ((line = bufferedReader.readLine()) != null) {
                 main.sendRGBToArduino(line.getBytes());
                 System.err.println(line);
                 
             }
             filereader.close();
             
         }  catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
             
        } 
        
        }
}

        class listenerClearPanelButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
           String clear = "#"; 
            try {
                main.sendRGBToArduino(clear.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
}



}



