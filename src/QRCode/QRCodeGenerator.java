package QRCode;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


public class QRCodeGenerator {

	public BufferedImage generater(String qrData) throws  IOException, WriterException {
				
		String charset = "UTF-8";  
		BufferedImage img = createImg(qrData, charset);		
		return img;
	}
	
	public BufferedImage createImg(String data, String charset) throws  IOException, WriterException  
	{  
			
		//the BitMatrix class represents the 2D matrix of bits  
		//MultiFormatWriter is a factory class that finds the appropriate Writer subclass 
		//for the BarcodeFormat requested and encodes the bar-code with the supplied contents.
	    
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, 100, 100);	
		BufferedImage img = MatrixToImageWriter.toBufferedImage(matrix); 
		
	    //System.out.println(img ); 

		return img;
	
	}  



}
