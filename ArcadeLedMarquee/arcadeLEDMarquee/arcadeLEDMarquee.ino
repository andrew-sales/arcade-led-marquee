// Receives data and draws a Marquee image
// For 32x64 RGB LED matrix.

// NOTE THIS CAN ONLY BE USED ON A MEGA! NOT ENOUGH RAM ON UNO!

#include <Adafruit_GFX.h>   // Core graphics library
#include <RGBmatrixPanel.h> // Hardware-specific library
#include <SoftwareSerial.h>

#define OE   9
#define LAT 10
#define CLK 11
#define A   A0
#define B   A1
#define C   A2
#define D   A3

RGBmatrixPanel matrix(A, B, C, D, CLK, LAT, OE, false, 64);

#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 32
#define BUFLENGTH 2048

char buf[BUFLENGTH]; //stores the buffer of serial port messages on which LEDs should be on and off
int bufCount; //used to store how many bytes within this buffer are left to read out

String readString;

String xCoordByteRead;
String yCoordByteRead;
String xCoord2ByteRead;
String yCoord2ByteRead;

String redByteRead;
String greenByteRead;
String blueByteRead;

String shapeType;


int ind1;
int ind2;
int ind3;
int ind4;
int ind5;
int ind6;
int ind7;
int ind8;

void setup() {

 Serial.begin(19200);

  matrix.begin();
  
  // fill the screen with 'black'
  matrix.fillScreen(matrix.Color333(0, 0, 0));

}

void loop() {

if (Serial.available() > 0) {

char  c = Serial.read();
   
          if(c == '#') {  
            
            
            //This is for clearing the screen
    
   
            matrix.fillScreen(matrix.Color333(0, 0, 0));

                              }

   if (c == '+') {  

      //       matrix.setTextColor(matrix.Color333(7,0,4)); 


                    ind1 = readString.indexOf(',');  //finds location of first ,
                    xCoordByteRead = readString.substring(0, ind1);   //captures first data String   
                    ind2 = readString.indexOf(',', ind1+1 );   //finds location of second ,
                    yCoordByteRead = readString.substring(ind1+1, ind2+1);   //captures second data String
                    ind3 = readString.indexOf(',', ind2+1 );
                    xCoord2ByteRead = readString.substring(ind2+1, ind3+1); 
                    ind4 = readString.indexOf(',', ind3+1 );
                    yCoord2ByteRead = readString.substring(ind3+1, ind4+1); //captures remain part of data after last
                    
                    ind5 = readString.indexOf(',', ind4+1 );
                    redByteRead = readString.substring(ind4+1, ind5+1);
                    ind6 = readString.indexOf(',', ind5+1 );
                    greenByteRead = readString.substring(ind5+1, ind6+1); //captures remain part of data after last
                    ind7 = readString.indexOf(',', ind6+1 );
                    blueByteRead = readString.substring(ind6+1, ind7+1); //captures remain part of data after last
                    ind8 = readString.indexOf(',', ind7+1 );
                    shapeType = readString.substring(ind7+1); //captures remain part of data after last

                     matrix.println(shapeType);
                    
                    if (shapeType == "r") {
                    
                    
                    matrix.fillRect(xCoordByteRead.toInt(), yCoordByteRead.toInt(), xCoord2ByteRead.toInt(), yCoord2ByteRead.toInt(), matrix.Color444(redByteRead.toInt(), greenByteRead.toInt(), blueByteRead.toInt()));

                    }

                       if (shapeType == "c") {
                    
                    
                           matrix.drawCircle(10, 10, 10, matrix.Color333(0, 0, 7));

                    }


                    readString=""; //clears variable for new input
                     xCoordByteRead="";
                     yCoordByteRead="";
                     xCoord2ByteRead="";
                     yCoord2ByteRead="";
                     
                     redByteRead="";
                     greenByteRead="";
                     blueByteRead="";

                     shapeType="";
                           
  
}


 if (c == '*') {  
  

                      ind1 = readString.indexOf(',');  //finds location of first ,
                      xCoordByteRead = readString.substring(0, ind1);   //captures first data String   
                      ind2 = readString.indexOf(',', ind1+1 );   //finds location of second ,
                      yCoordByteRead = readString.substring(ind1+1, ind2+1);   //captures second data String
                      ind3 = readString.indexOf(',', ind2+1 );
                      redByteRead = readString.substring(ind2+1, ind3+1);
                      ind4 = readString.indexOf(',', ind3+1 );
                      greenByteRead = readString.substring(ind3+1, ind4+1); //captures remain part of data after last
                      ind5 = readString.indexOf(',', ind4+1 );
                      blueByteRead = readString.substring(ind4+1); //captures remain part of data after last

                      matrix.drawPixel(xCoordByteRead.toInt(), yCoordByteRead.toInt(), matrix.Color444(redByteRead.toInt(), greenByteRead.toInt(), blueByteRead.toInt())); 


                     readString=""; //clears variable for new input
                     xCoordByteRead="";
                     yCoordByteRead="";
                     redByteRead="";
                     greenByteRead="";
                     blueByteRead="";

                    }
 
    


       
       
else {     
                  readString += c; //makes the string readString
    
} 

}
}

