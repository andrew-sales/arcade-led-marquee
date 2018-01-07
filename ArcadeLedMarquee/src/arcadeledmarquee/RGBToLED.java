/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

import java.awt.event.MouseEvent;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 *
 * @author Andy
 */
public class RGBToLED {
    
public int RGB;
public int LEDRGB;

public RGBToLED (){
    
}

public int convertRGBToLED (int RGBValue) {
    
RGB = RGBValue;
 

    if (RGB > 192) {
        
    LEDRGB = 4;
    }
    else if (RGB > 128) {
        LEDRGB = 3;
 
}
    else if (RGB > 64) {
        LEDRGB = 2;
 
}
    else if (RGB > 0) {
        LEDRGB = 1;
 
}
    
    else {
        LEDRGB = 0;
 
}
    
    return LEDRGB;
    
}


public static int convertLEDToRGB (String LEDValue) {
    
String LED = LEDValue;
int RGBLED;

if ("".equals(LED)) {
    
    RGBLED = 0;
}

else if ("0".equals(LED)) {
    
    RGBLED = 0;
}

else if ("4".equals(LED)) {
    
    RGBLED = 255;
}    

else if ("3".equals(LED)) {
    
    RGBLED = 192;
}   

else if ("2".equals(LED)) {
    
    RGBLED = 164;
}

else if ("1".equals(LED)) {
    
    RGBLED = 128;
}   

else {
    
    RGBLED = 255;
}
    return RGBLED;
    
}


//public byte [] createByteArray (int [] inputArray) 
//        
//    {
//        int[] data = inputArray;
//
//        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);        
//        IntBuffer intBuffer = byteBuffer.asIntBuffer();
//        intBuffer.put(data);
//
//        byte[] array = byteBuffer.array();
//
//        for (int i=0; i < array.length; i++)
//        {
//            System.out.println(i + ": " + array[i]);
//        }
//        
//        return array;
//    }

}
