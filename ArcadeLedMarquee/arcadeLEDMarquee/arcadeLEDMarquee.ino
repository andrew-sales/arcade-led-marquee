// Receives data and draws a Marquee image
// For 32x64 RGB LED matrix.

// NOTE THIS CAN ONLY BE USED ON A MEGA! NOT ENOUGH RAM ON UNO!

#include <Adafruit_GFX.h>   // Core graphics library
#include <RGBmatrixPanel.h> // Hardware-specific library
#include <SoftwareSerial.h>
#include <Arduino.h>

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
String redByteRead;
String greenByteRead;
String blueByteRead;
String Test;

int ind1;
int ind2;
int ind3;
int ind4;
int ind5;

void setup() {

 Serial.begin(9600);

  matrix.begin();
  
  // fill the screen with 'black'
  matrix.fillScreen(matrix.Color333(0, 0, 0));

//
//
//
//  
//  // draw a pixel in solid white
//  matrix.drawPixel(0, 0, matrix.Color333(7, 7, 7)); 
//  delay(500);
//
//  // fix the screen with green
//  matrix.fillRect(0, 0, matrix.width(), matrix.height(), matrix.Color333(0, 7, 0));
//  delay(500);
//
//  // draw a box in yellow
//  matrix.drawRect(0, 0, matrix.width(), matrix.height(), matrix.Color333(7, 7, 0));
//  delay(500);
//  
//  // draw an 'X' in red
//  matrix.drawLine(0, 0, matrix.width()-1, matrix.height()-1, matrix.Color333(7, 0, 0));
//  matrix.drawLine(matrix.width()-1, 0, 0, matrix.height()-1, matrix.Color333(7, 0, 0));
//  delay(500);
//  
//  // draw a blue circle
//  matrix.drawCircle(10, 10, 10, matrix.Color333(0, 0, 7));
//  delay(500);
//  
//  // fill a violet circle
//  matrix.fillCircle(40, 21, 10, matrix.Color333(7, 0, 7));
//  delay(500);
//  
//  // fill the screen with 'black'
//  matrix.fillScreen(matrix.Color333(0, 0, 0));
//  
//  // draw some text!
//  matrix.setTextSize(1);     // size 1 == 8 pixels high
//  matrix.setTextWrap(false); // Don't wrap at end of line - will do ourselves
//
//  matrix.setCursor(8, 0);    // start at top left, with 8 pixel of spacing
//  uint8_t w = 0;
//  char *str = "AdafruitIndustries";
//  for (w=0; w<8; w++) {
//    matrix.setTextColor(Wheel(w));
//    matrix.print(str[w]);
//  }
//  matrix.setCursor(2, 8);    // next line
//  for (w=8; w<18; w++) {
//    matrix.setTextColor(Wheel(w));
//    matrix.print(str[w]);
//  }
//  matrix.println();
//  //matrix.setTextColor(matrix.Color333(4,4,4));
//  //matrix.println("Industries");
//  matrix.setTextColor(matrix.Color333(7,7,7));
//  matrix.println("LED MATRIX!");
//  
//  // print each letter with a rainbow color
//  matrix.setTextColor(matrix.Color333(7,0,0));
//  matrix.print('3');
//  matrix.setTextColor(matrix.Color333(7,4,0)); 
//  matrix.print('2');
//  matrix.setTextColor(matrix.Color333(7,7,0));
//  matrix.print('x');
//  matrix.setTextColor(matrix.Color333(4,7,0)); 
//  matrix.print('6');
//  matrix.setTextColor(matrix.Color333(0,7,0));  
//  matrix.print('4');
//  matrix.setCursor(34, 24);  
//  matrix.setTextColor(matrix.Color333(0,7,7)); 
//  matrix.print("*");
//  matrix.setTextColor(matrix.Color333(0,4,7)); 
//  matrix.print('R');
//  matrix.setTextColor(matrix.Color333(0,0,7));
//  matrix.print('G');
//  matrix.setTextColor(matrix.Color333(4,0,7)); 
//  matrix.print("B");
//  matrix.setTextColor(matrix.Color333(7,0,4)); 
//  matrix.println("*");


  // whew!
}

void loop() {



if (Serial.available() > 0) {
   char C = Serial.read();
   
   if(C == '#') {

matrix.fillScreen(matrix.Color333(0, 0, 0));

    
   }
   
   else if (C == '*') {


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
//matrix.fillRect(xCoordByteRead.toInt(), yCoordByteRead.toInt(),20, 20, matrix.Color444(redByteRead.toInt(), greenByteRead.toInt(), blueByteRead.toInt()));

//  // draw a blue circle

//matrix.drawCircle(10, 10, 10, matrix.Color333(0, 0, 1));
////delay(500);
//matrix.fillScreen(matrix.Color333(0, 0, 1));
//delay(5000);
//matrix.fillScreen(matrix.Color333(0, 0, 0));
//delay(5000);
//matrix.fillScreen(matrix.Color333(0, 0, 7));
//
//delay(5000);
//matrix.fillScreen(matrix.Color333(0, 0, 0));
//delay(5000);
//matrix.fillScreen(matrix.Color444(0, 0, 7));
// 
// matrix.setTextColor(matrix.Color333(4,0,7)); 
//  matrix.print("X" + xCoordByteRead);
//  matrix.print("Y" + yCoordByteRead);
//  matrix.print("r" + redByteRead);
//  matrix.print("g" + greenByteRead);
// matrix.print("b" + blueByteRead);
// 
// delay(5000);
////
// matrix.fillScreen(matrix.Color333(0, 0, 0));

   readString=""; //clears variable for new input
 xCoordByteRead="";
 yCoordByteRead="";
 redByteRead="";
 greenByteRead="";
 blueByteRead="";

//matrix.setTextColor(matrix.Color333(4,0,7)); 
// matrix.print(blueByteRead);
      
}
else {     
      readString += C; //makes the string readString

//matrix.setTextColor(matrix.Color333(4,0,7)); 
//matrix.print(readString);
      
//       if (readString == "1,1,1,1,1") {
//
//  matrix.fillRect(0, 0, matrix.width(), matrix.height(), matrix.Color333(0, 0, 7));
//}
//
//else {
//
//  matrix.fillRect(0, 0, matrix.width(), matrix.height(), matrix.Color333(0, 7, 0));
//}
    
}  

//   
//   yCoordByteRead = Serial.read();
//   redByteRead = Serial.read();
//   greenByteRead = Serial.read();
//   blueByteRead = Serial.read();


//   
//matrix.drawPixel(xCoordByteRead.toInt(), yCoordByteRead.toInt(), matrix.Color444(redByteRead.toInt(), greenByteRead.toInt(), blueByteRead.toInt())); 
//  
//   
//   if(xCoordByteRead == '1') {
//      
//    matrix.fillRect(0, 0, matrix.width(), matrix.height(), matrix.Color333(0, 7, 0));
// delay(500);
// matrix.fillScreen(matrix.Color333(0, 0, 0));
//   }
//if(xCoordByteRead == '2'){
//
//  matrix.drawLine(0, 0, matrix.width()-1, matrix.height()-1, matrix.Color333(7, 0, 0));
//  matrix.drawLine(matrix.width()-1, 0, 0, matrix.height()-1, matrix.Color333(7, 0, 0));
// delay(500);
//  // fill the screen with 'black'
// matrix.fillScreen(matrix.Color333(0, 0, 0));
//  
//}
   
    

//byte myArray[5];
//
//if (Serial.available() > 0) {
//
//myArray = Serial.read();

//Serial.print(array[0]);
//}




    
}
}
//void serialParse(void) {
//    bufCount = -1;
//    bufCount = Serial.readBytesUntil('\n', buf, BUFLENGTH);
//    
//if (bufCount > 0) {
//      String message = String(buf);
//      
//      parseBuffer(message);
//    }
//  }
//
//void parseBuffer(char* buf) {
//    dmd.clearScreen(true);
//
//    int i = 0;
//
//    for (byte y = 0; y < SCREEN_HEIGHT; y++) {
//      for (byte x = 0; x < SCREEN_WIDTH; x++) {
//        if ((char)buf[i] == '1') {
//          dmd.drawFilledBox(x, y, x, y, GRAPHICS_NORMAL);
//        }
//        i++;
//      }
//    }
//  }
//
//// Input a value 0 to 24 to get a color value.
//// The colours are a transition r - g - b - back to r.
//uint16_t Wheel(byte WheelPos) {
//  if(WheelPos < 8) {
//   return matrix.Color333(7 - WheelPos, WheelPos, 0);
//  } else if(WheelPos < 16) {
//   WheelPos -= 8;
//   return matrix.Color333(0, 7-WheelPos, WheelPos);
//  } else {
//   WheelPos -= 16;
//   return matrix.Color333(0, WheelPos, 7 - WheelPos);
//  }
//}
