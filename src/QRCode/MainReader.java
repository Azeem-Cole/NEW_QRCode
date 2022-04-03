package QRCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.zip.DataFormatException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.google.zxing.WriterException;


public class MainReader
{
	public static void main(String args[]) throws IOException, DataFormatException, WriterException  
		{
	
		String fileName = "C:\\Users\\azeem\\Pictures\\abb.png"; //args[0]
		String outputLocation = "C:\\Users\\azeem\\Pictures\\Paychex\\"; //args[1]
		
		String [] engSpanFileIDs = new String[]{"PEO074","DP0002","phb011","W-4","PEO075","DP0003","phb265", "W-4 (SP)"};		
		
		File file = new File(fileName);
		
        if(fileName.toLowerCase().endsWith(".png") || fileName.toLowerCase().endsWith(".jpeg") || fileName.toLowerCase().endsWith(".jpg")) {
			BufferedImage buffer = ImageIO.read(file);;
			QRScanner finder = new QRScanner();
			String findQR = finder.QRfinder(buffer);
						
		    System.out.println(findQR );
		}
	        
	    if(fileName.toLowerCase().endsWith(".pdf") ) {   
		    PDDocument document =  Loader.loadPDF(file);
		    PDFTextStripper stripper = new PDFTextStripper();
			String text = "";
			
			PDDocument engPayroll = new PDDocument();
			PDDocument engDeposit  = new PDDocument();
			PDDocument engW4 = new PDDocument();
			PDDocument engHealth = new PDDocument();
			PDDocument spanPayroll = new PDDocument();
			PDDocument spanDeposit  = new PDDocument();
			PDDocument spanW4 = new PDDocument();
			PDDocument spanHealth = new PDDocument();
	
			for(int x = 0; x <= document.getNumberOfPages(); x++) {
			
				stripper.setStartPage(x);
				stripper.setEndPage(x);
				
				text = stripper.getText(document).toLowerCase();
	
				// English files
		    	if(text.contains(engSpanFileIDs[0].toLowerCase())) {
			    	engPayroll.addPage( document.getPage(x -1));	
		    	}
		    	
				if(text.contains(engSpanFileIDs[1].toLowerCase())) {  	
					engDeposit.addPage( document.getPage(x -1));
				  }
				
				if(text.contains(engSpanFileIDs[2].toLowerCase())) {
					engHealth.addPage( document.getPage(x -1));
				}	
				
				if(text.contains(engSpanFileIDs[3].toLowerCase())) {
			    	engW4.addPage( document.getPage(x -1));
				}
				
				// English files
		    	if(text.contains(engSpanFileIDs[4].toLowerCase())) {
			    	spanPayroll.addPage( document.getPage(x -1));	
		    	}
		    	
				if(text.contains(engSpanFileIDs[5].toLowerCase())) {  	
					spanDeposit.addPage( document.getPage(x -1));
				  }
				
				if(text.contains(engSpanFileIDs[6].toLowerCase())) {
					spanHealth.addPage( document.getPage(x -1));
				}	
				
				if(text.contains(engSpanFileIDs[7].toLowerCase())) {
			    	spanW4.addPage( document.getPage(x -1));
				}
		    	
				text = "";
			}
			
			Stamp engPayDoc = new Stamp( "payrollID", new int[][]{{1, 500, 40},{2, 360, 740},{3, 500, 50}} );
			Stamp engDepositDOc = new Stamp( "depositID", new int[][]{{1, 550, 740}} );
			Stamp engW4Doc = new Stamp( "w4ID", new int[][]{{1, 500, 50}, {2, 500, 0}, {3, 490, 745}, {4, 490, 745}});
			Stamp engHealthDoc = new Stamp( "healthID", new int[][]{{1, 50, 25}, {2, 510, 25}, {2, 510, 25}} );
			
			Stamp spanPayDoc = new Stamp( "payrollID", new int[][]{{1, 500, 40},{2, 360, 740},{3, 500, 50}} );
			Stamp spanDepositDOc = new Stamp( "depositID", new int[][]{{1, 550, 740}} );
			Stamp spanW4Doc = new Stamp( "w4ID", new int[][]{{1, 500, 50}, {2, 500, 0}, {3, 490, 745}, {4, 490, 745}});
			Stamp spanHealthDoc = new Stamp( "healthID", new int[][]{{1, 50, 25}, {2, 510, 25}, {2, 510, 25}} );
			
			engPayDoc.Stamper(engPayroll);
			engDepositDOc.Stamper(engDeposit);
			engW4Doc.Stamper(engW4);
			engHealthDoc.Stamper(engHealth);
			
			spanPayDoc.Stamper(spanPayroll);
			spanDepositDOc.Stamper(spanDeposit);
			spanW4Doc.Stamper(spanW4);
			spanHealthDoc.Stamper(spanHealth);
	
			document.save( (outputLocation + "merged.pdf"));
			
			     
			}
		}

}