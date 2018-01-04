/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/**
 *
 * @author Andy
 */
public class ImageToText {
    
    public String inputFile;
    public String outputFolder;
    public int RGB;
    public int LEDRGB;
    
    public ImageToText (String imageToConvertFileLocation, String imageOutputFolder)throws FileNotFoundException, IOException {
          
        inputFile = imageToConvertFileLocation;
        outputFolder = imageOutputFolder;
        
        File output_file=new File(outputFolder + "\\pixels.txt"); 
        PrintStream out=new PrintStream(new FileOutputStream(output_file));
        System.setOut(out);
        /*
         * If you try this code,
         * take image of 30 x 30 dimensions'
         * as it takes less time to execute...'
         */
        BufferedImage image = readImage(imageToConvertFileLocation);
        System.err.println(imageToConvertFileLocation);//Input Image file
        printAllRGBDetails(image);
        
        
    }
    
    public void printAllRGBDetails(BufferedImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        int pix_num = 1;
        int total_pix=width*height;
        
//        System.out.println("--------------------------------------------");
//        System.out.println("Image Scrambler");   
//        System.out.println("Image Dimension: Height-" + height + ", Width-" + width);
//        System.out.println("Total Pixels: " + (height * width));
//        System.out.println("--------------------------------------------");
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                
                Color myColor = new Color(image.getRGB(i,j));
                
                int red = myColor.getRed();
                int green = myColor.getGreen();
                int blue = myColor.getBlue();
                
              // System.out.println("red" + red);
                
                int newRed = this.convertRGBToLED(red);
                
              //  System.out.println("new red" + newRed);
                
                int newGreen = this.convertRGBToLED(green);
                int newBlue = this.convertRGBToLED(blue);
                
                //int pixel = image.getRGB(i, j);
                
             //   int LEDPixel = this.convertRGBToLED(pixel);
                
             //   String idata=(getRGBPixelData(pixel));
//                System.out.print("Pixel ("+i+","+j+"): ");
                System.out.print(i + "," + j + ",");
                System.out.print(newRed + "," + newGreen + "," + newBlue + "*");
                if(pix_num<total_pix)       //To delete the line that generates at end of file
                {
                    System.out.println("");
                }
                pix_num++;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Conversion Complete");
        
    }
    
    
//    public static String getARGBPixelData(int pixel)
//    {
//        String ARGBvalue="";
//        int alpha = (pixel >> 24) & 0x000000FF;
//        int red = (pixel >> 16) & 0x000000FF;
//        int green = (pixel >>8 ) & 0x000000FF;
//        int blue = (pixel) & 0x000000FF;
//        ARGBvalue = alpha + " " + red + " " + green + " " + blue;
//        return ARGBvalue;
//    }
    
    public static String getRGBPixelData(int pixel)
    {
        String RGBvalue="";
        int red = (pixel >> 16) & 0x000000FF;
        int green = (pixel >>8 ) & 0x000000FF;
        int blue = (pixel) & 0x000000FF;
        if (red==255) {
            red = 1;
        }
        if (green==255) {
            green = 1;
        }
        if (blue==255) {
            blue = 1;
        }
        RGBvalue = red + "," + green + "," + blue;
        return RGBvalue;
    }
    
    
    
    public static BufferedImage readImage(String fileLocation) throws IOException
    {
        BufferedImage img = null;
        img = ImageIO.read(new File(fileLocation));
        return img;
    }
    
    public int convertRGBToLED (int RGBValue) {
    
RGB = RGBValue;
 

    if (RGB > 192) {
        
    LEDRGB = 3;
    }
    else if (RGB > 128) {
        LEDRGB = 2;
 
}
    else if (RGB > 64) {
        LEDRGB = 1;
 
}
    else if (RGB > 0) {
        LEDRGB = 0;
 
}
    
    else {
        LEDRGB = 0;
 
}
    
    

    return LEDRGB;
    
}
   
}
