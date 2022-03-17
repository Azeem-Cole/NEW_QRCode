package QRCode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class GenerateQRCode {

	public BufferedImage generater(String str) throws WriterException, IOException {
				
		String charset = "UTF-8";  
		BufferedImage img = createImg(str, charset);		
		return img;
	}
	
	public BufferedImage createImg(String data, String charset) throws WriterException, IOException  
	{  
			
		//the BitMatrix class represents the 2D matrix of bits  
		//MultiFormatWriter is a factory class that finds the appropriate Writer subclass 
		//for the BarcodeFormat requested and encodes the bar-code with the supplied contents.  
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, 100, 100);	
		BufferedImage img = MatrixToImageWriter.toBufferedImage(matrix); 
		
		return img;
	
	}  



}
