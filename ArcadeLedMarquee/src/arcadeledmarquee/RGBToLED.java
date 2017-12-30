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
    
public int[] RGB;
public int[] LEDRGB;

public RGBToLED (int[] startRGB){
    
    RGB = startRGB;
    
}

public int[] convertRGBToLED (int[] RGBValue) {
    
RGB = RGBValue;
 
for (int i = 0; i < 3; i++) {
    
    LEDRGB[i] = RGB[i] / 16;
 
}

    return LEDRGB;
    
}
      
}
