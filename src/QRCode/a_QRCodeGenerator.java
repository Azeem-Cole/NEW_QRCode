package QRCode;

import java.io.File;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class a_QRCodeGenerator {

	public static void main(String[] args) throws WriterException, IOException {
				
		String str = "THE HABIT OF PERSISTENCE IS THE HABIT OF VICTORY.";  //args[0];
		String path = "C:\\Users\\azeem\\Pictures\\abc.png";  
		String charset = "UTF-8";  
		
		String status = generateQRcode(str, path, charset);
		System.out.println(status);  

		
	}
	
	public static String generateQRcode(String data, String path, String charset) throws WriterException, IOException  
	{  
	//the BitMatrix class represents the 2D matrix of bits  
	//MultiFormatWriter is a factory class that finds the appropriate Writer subclass 
	//for the BarcodeFormat requested and encodes the bar-code with the supplied contents.  
	BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, 100, 100);  
	MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path)); 
	
	return "QR CODE HAS BEEN CREATED";
	
	}  



}
