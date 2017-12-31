/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadeledmarquee;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;


/**
 *
 * @author Andy
 */
public class ImageToText {
    
    public String inputFile;
    
    public ImageToText (String imageToConvertFileLocation)throws FileNotFoundException, IOException {
          
        inputFile = imageToConvertFileLocation;
        
        File output_file=new File("C:\\Users\\Andy\\Documents\\pixels.txt"); 
        PrintStream out=new PrintStream(new FileOutputStream(output_file));
        System.setOut(out);
        /*
         * If you try this code,
         * take image of 30 x 30 dimensions'
         * as it takes less time to execute...'
         */
        BufferedImage image = readImage(inputFile);               //Input Image file
        printAllRGBDetails(image);
        
        
    }
    
    public static void printAllRGBDetails(BufferedImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        int pix_num = 1;
        int total_pix=width*height;
        
        System.out.println("--------------------------------------------");
        System.out.println("Image Scrambler");   
        System.out.println("Image Dimension: Height-" + height + ", Width-" + width);
        System.out.println("Total Pixels: " + (height * width));
        System.out.println("--------------------------------------------");
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                int pixel = image.getRGB(i, j);
                String idata=(getRGBPixelData(pixel));
                System.out.print("Pixel ("+i+","+j+"): ");
                System.out.print(idata);
                if(pix_num<total_pix)       //To delete the line that generates at end of file
                {
                    System.out.println("");
                }
                pix_num++;
            }
        }
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
        RGBvalue = red + " " + green + " " + blue;
        return RGBvalue;
    }
    
    
    
    public static BufferedImage readImage(String fileLocation) throws IOException
    {
        BufferedImage img = null;
        img = ImageIO.read(new File(fileLocation));
        return img;
    }
    
}
