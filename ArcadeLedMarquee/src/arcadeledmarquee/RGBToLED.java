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
int RGBLED = 0;

if (null != LED) switch (LED) {
        case "":
            RGBLED = 0;
            break;
        case "0":
            RGBLED = 0;
            break;
        case "4":
            RGBLED = 255;
            break;
        case "3":
            RGBLED = 192;
            break;
        case "2":
            RGBLED = 164;
            break;
        case "1":
            RGBLED = 128;
            break;
        default:
            RGBLED = 255;
            break;
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
