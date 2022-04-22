package QRCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.zip.DataFormatException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.google.zxing.WriterException;


public class PaychexQRCodeTool
{
	public static void main(String args[]) throws IOException, DataFormatException, WriterException  
		{
	
		String fileName = args[0]; //"C:\\Users\\azeem\\Pictures\\Paychex\\returnPDF.png";
	    String outputLocation = new File(fileName).getParentFile().getPath();
		
	    
		//String [] engSpanFileIDs = new String[]{"peo074","dp0002","phb011","w-4","peO075","dp0003","phb265", "w-4 (sp)"};		
		File file = new File(fileName);
		
		
		String ext = FilenameUtils.getExtension(fileName).toLowerCase();
		
		// check for some value that is created in a pdf document metadata
		// when it is sent from a scanner.
		// if the data is found, turn the pdf document into an image and look for the qrcode as regular

        if(ext.equalsIgnoreCase("png") ||ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg")) {

			BufferedImage buffer = ImageIO.read(file);;
			QRScanner finder = new QRScanner();
			String findQR = finder.QRfinder(buffer);
						
		    System.out.println(findQR);
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
			
			Object[][] docKeys = {
					  {"peo074","dp0002","phb011","w-4","peO075","dp0003","phb265", "w-4 (sp)"},
					  {engPayroll, engDeposit, engHealth, engW4, spanPayroll, spanDeposit, spanHealth, spanW4}
			};
	
			for(int x = 0; x < document.getNumberOfPages(); x++) {
				
				stripper.setStartPage(x + 1);
				stripper.setEndPage(x + 1);
				
				text = stripper.getText(document).toLowerCase();
				for(int i = 0, columnCount = docKeys[0].length; i < columnCount; i++) {
					
					if(text.contains(docKeys[0][i].toString())) {
						((PDDocument)docKeys[1][i]).addPage(document.getPage(x));
					}
					
					
				}
	
			}			
	
			
			Stamper engPayDoc = new Stamper( "payrollID", new int[][]{{1, 500, 50},{2, 360, 740},{3, 500, 50}} );
            Stamper engDepositDOc = new Stamper( "depositID", new int[][]{{1, 550, 740}} );
            Stamper engW4Doc = new Stamper( "w4ID", new int[][]{{1, 500, 40}, {2, 500, 0}, {3, 490, 745}, {4, 490, 745}});
            Stamper engHealthDoc = new Stamper( "healthID", new int[][]{{1, 50, 25}, {2, 510, 25}, {2, 510, 25}} );
            
            Stamper spanPayDoc = new Stamper( "payrollID", new int[][]{{1, 500, 40},{2, 360, 740},{3, 500, 50}} );
            Stamper spanDepositDOc = new Stamper( "depositID", new int[][]{{1, 550, 740}} );
            Stamper spanW4Doc = new Stamper( "w4ID", new int[][]{{1, 500, 50}, {2, 500, 0}, {3, 490, 745}, {4, 490, 745}});
            Stamper spanHealthDoc = new Stamper( "healthID", new int[][]{{1, 50, 25}, {2, 510, 25}, {2, 510, 25}} );
            
            engPayDoc.Stamp(engPayroll);
            engDepositDOc.Stamp(engDeposit);
            engW4Doc.Stamp(engW4);
            engHealthDoc.Stamp(engHealth);
            
            spanPayDoc.Stamp(spanPayroll);
            spanDepositDOc.Stamp(spanDeposit);
            spanW4Doc.Stamp(spanW4);
            spanHealthDoc.Stamp(spanHealth);
            
			
			document.save( (outputLocation + "\\merged.pdf"));
			
			     
			}
		}

}