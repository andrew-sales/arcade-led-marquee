/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

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
    
    //RGB = startRGB;
    
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

public byte [] createByteArray (int [] inputArray) 
        
    {
        int[] data = inputArray;

        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);        
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);

        byte[] array = byteBuffer.array();

        for (int i=0; i < array.length; i++)
        {
            System.out.println(i + ": " + array[i]);
        }
        
        return array;
    }

      
}
