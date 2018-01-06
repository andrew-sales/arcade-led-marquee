/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

import static arcadeledmarquee.View.main;
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
public Editor editor;
    
private final View newGUI;
private final SerialConnection main;
private final RGBToLED newRGBToLED;





public Controller () throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException { 

    newGUI = new View();
    newGUI.setVisible(true);  
    newRGBToLED = new RGBToLED();
    
    main = new SerialConnection();

this.newGUI.ConvertButton (new ListenerSelectFileForConversionButton());
this.newGUI.OpenPortButtonListener(new ListenerOpenPortButton());
this.newGUI.ClosePortButtonListener(new ListenerClosePortButton());
this.newGUI.SendDataButtonListener(new ListenerSendDataButton());
this.newGUI.SendRGBButtonListener(new listenerSendRGBButton());
this.newGUI.SendFileButtonListener(new listenerSendFileButton());
this.newGUI.ClearPanelButtonListener(new listenerClearPanelButton());
this.newGUI.SendTextButtonListener(new listenerSendTextButton());
this.newGUI.OpenEditorButtonListener(new listenerOpenEditorButton());



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
		//System.out.println("Started");
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
        
 String transferString;
 
 
 String drawModeIn = newGUI.getDrawMode();
 
 String drawModeOut;
 
 if ("DrawPixel".equals(drawModeIn)) {
     
     transferString = ("$" + newGUI.getXCoordString() + "," + newGUI.getYCoordString() + "," +
            newGUI.getRedLedString() + "," + newGUI.getGreenLedString()  + "," +
            newGUI.getBlueLedString() + "*");
 }
 
 else {
 
 switch (drawModeIn) {
        case "DrawRectangle": drawModeOut = "r";
            break;
        case "FillRectangle": drawModeOut = "q";
            break;
         case "DrawCircle": drawModeOut = "c";
            break;   
         case "FillCircle": drawModeOut = "f";
            break;   
        case "Line": drawModeOut = "l";
            break;     
        case "FillScreen": drawModeOut = "n";
            break;     
        default: drawModeOut ="";
            break;          
        }
      
       
        transferString = ("$" + newGUI.getXCoordString() + "," + newGUI.getYCoordString() + "," + newGUI.getXCoord2String()
               +"," + newGUI.getYCoord2String() + "," +
                newGUI.getRedLedString() + "," + newGUI.getGreenLedString()  + "," +
               newGUI.getBlueLedString() + "," + drawModeOut + "&");       
 
 }      

            try {
                main.sendRGBToArduino(transferString.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } 
}
  

class listenerSendTextButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
           
            String transferString;
            String text = newGUI.getMarqueeText();         
            String textSize = newGUI.getMarqueeTextSize();
            String textWrap = String.valueOf(newGUI.getMarqueeTextWrap());
            
            
            transferString = ("$" + newGUI.getXCoordString() + "," + newGUI.getYCoordString() + "," + textSize
               + "," + textWrap + ","  +
                newGUI.getRedLedString() + "," + newGUI.getGreenLedString()  + "," +
               newGUI.getBlueLedString() + "," + text + "%");     
            
           // System.out.println(transferString);
            
            
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
           //  System.out.println(newGUI.getArduinoDataFile());
             FileReader filereader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(filereader);
             StringBuilder stringBuffer = new StringBuilder();
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
            
            
           editor.closeFrame();
           
        }
}

        
        class listenerOpenEditorButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
              editor = new Editor(main, newGUI);
             
             
             
        }
        
        
        
        
        
        
}


}



