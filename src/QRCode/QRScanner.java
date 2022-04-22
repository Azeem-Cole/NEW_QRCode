package QRCode;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRScanner {

  public String QRfinder( BufferedImage Image) throws IOException {
    
    BufferedImage image = Image;
    BufferedImage[] images1  =  splitBufferdImage( image, 1); //the entire image
    
    BufferedImage[] images4  =  splitBufferdImage( image, 2); //the image split by a 2x2 grid
    BufferedImage[] images9  =  splitBufferdImage( image, 3); //the image split by a 3x3 grid
    BufferedImage[] images16 =  splitBufferdImage( image, 4); //the image split by a 4x4 grid
    BufferedImage[] images25 =  splitBufferdImage( image, 5); //the image split by a 5x5 grid
    BufferedImage[] imagesAll =  new BufferedImage[55] ; //the total
    
    int countImg = 0;
    for(int allImg = 0; allImg < 25; allImg++) { //place all the smaller buffered image into the large array
    	if(allImg < 1) { 
    		imagesAll[countImg] = images1[allImg];
    		countImg++;
    	}
    	
    	if(allImg < 4) {
    		imagesAll[countImg] = images4[allImg];
    		countImg++;
    	}
    	
    	if(allImg < 9) {
    		imagesAll[countImg] = images9[allImg];
   		  	countImg++;	
       	} 
    	if(allImg < 16) {
  		  	imagesAll[countImg] = images16[allImg];
  		  	countImg++;	
      	}
	  
    	imagesAll[countImg] = images25[allImg];
    	countImg++;	
 	
    }    
    
    String decodedQRMessage = "";
    //int count =0;
    here:
    for(BufferedImage minImage : imagesAll) { //look at each image in the large section for the QR code
    	
    	//saves images
        //ImageIO.write(minImage , "png", new File("C:\\Users\\azeem\\Pictures\\q44\\MyFile"+ (count +1) +".png"));

    	try {
    		decodedQRMessage = readQRCode(minImage);	 //tries checks the image for QR code
    	} catch(Exception e) {
    		;
    	}
    	
    	if (decodedQRMessage.length() > 0) {
            break here;   //if the code
    	}	
    	//count++;
    }
    
    if(decodedQRMessage.length() <= 0) {
    	decodedQRMessage = "Could not find the QR Code on the image";
    }
    return decodedQRMessage;
    		  
  }

  
  public String readQRCode(BufferedImage filePath) throws FileNotFoundException, IOException, NotFoundException {
	  
	  // functions provided by the com.google.zxing library to look for QRcode
	  BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource( filePath)));
	  Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
	  return qrCodeResult.getText();
  }
  
  public BufferedImage[] splitBufferdImage(BufferedImage picture, int parts) {
	  
	  double[] arrayPartsX = new double[ parts ];
	  BufferedImage[] imagesOnGrid = new BufferedImage[parts * parts];
	  
	  for(int x = 0; x < arrayPartsX.length; x++) {
		  
		  arrayPartsX[x] = ((double)x)/parts; 

	  }
	  
	  double[] arrayPartsY = arrayPartsX;
	  
	  
	  // these two for loops create a grid
	  // then takes the bit matrix and add it too the list
	  int count = 0;
	  for(int x = 0; x < arrayPartsX.length; x ++) {
		  for(int y = 0; y < arrayPartsY.length; y ++) {
			  int startX = 0;
		      int startY = 0;
		      if(arrayPartsX[x] != 0) {
		    	  startX =  (int)(picture.getWidth() * (((double) arrayPartsX[x])));
		      } 
		      if(arrayPartsY[y] != 0) {
		    	  startY =  (int)(picture.getHeight() * (((double) arrayPartsY[y])));
		      }
		      
		      imagesOnGrid[count] = picture.getSubimage(startX, startY, (int)(picture.getWidth() * (((double)1)/parts)), (int)(picture.getHeight() * (((double)1)/parts)));
		      count++;
		  }
	  }

  return imagesOnGrid;


  }
  
  /*
  public BufferedImage rotateImage(BufferedImage image, double angle) {
	    double radian = Math.toRadians(angle);
	    double sin = Math.abs(Math.sin(radian));
	    double cos = Math.abs(Math.cos(radian));

	    int width = image.getWidth();
	    int height = image.getHeight();

	    int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
	    int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);

	    BufferedImage rotatedImage = new BufferedImage(
	            nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);

	    // and so on...

	    return rotatedImage;
	}*/
}
