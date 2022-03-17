package QRCode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.google.zxing.WriterException;


public class ReadMain
{
	public static void main(String args[]) throws IOException, DataFormatException, WriterException  
		{
	
		String fileName = "C:\\Users\\azeem\\Pictures\\Paychex\\allPDF.pdf"; //args[0]
		String outputLocation = "C:\\Users\\azeem\\Pictures\\Paychex\\"; //args[0]
		
		List<String> englishFileIDs = List.of("PEO074","DP0002","phb011", "W-4");
		//List<String> spanishFileIDs = List.of("PEO075","DP0003","phb265", "W-4 (SP)");
		
		File file = new File(fileName);
		
	    PDDocument document =  Loader.loadPDF(file);
	    PDFTextStripper stripper = new PDFTextStripper();
		String text = "";
		
		PDDocument payroll = new PDDocument();
		PDDocument deposit  = new PDDocument();
		PDDocument w4 = new PDDocument();
		PDDocument health = new PDDocument();

		for(int x = 0; x <= document.getNumberOfPages(); x++) {
		
			stripper.setStartPage(x);
			stripper.setEndPage(x);
			
			text = stripper.getText(document).toLowerCase();

	    	if(text.contains(englishFileIDs.get(0).toLowerCase())) {
		    	payroll.addPage( document.getPage(x -1));	
	    	}
	    	
			if(text.contains(englishFileIDs.get(1).toLowerCase())) {  	
				deposit.addPage( document.getPage(x -1));
			  }
			
			if(text.contains(englishFileIDs.get(2).toLowerCase())) {
				health.addPage( document.getPage(x -1));
			}	
			
			if(text.contains(englishFileIDs.get(3).toLowerCase())) {
		    	w4.addPage( document.getPage(x -1));
			}
	    	
			text = "";
		}
		
		
		Stamp payDoc = new Stamp( "payrollID", new int[][]{{1, 500, 50},{2, 360, 740},{3, 500, 50}} );
		Stamp depositDOc = new Stamp( "depositID", new int[][]{{1, 550, 740}} );
		Stamp w4Doc = new Stamp( "w4ID", new int[][]{{1, 500, 50}, {2, 500, 0}, {3, 490, 745}, {4, 490, 745}});
		Stamp healthDoc = new Stamp( "healthID", new int[][]{{1, 50, 25}, {2, 510, 25}, {2, 510, 25}} );
		
		payDoc.Stamper(payroll);
		depositDOc.Stamper(deposit);
		w4Doc.Stamper(w4);
		healthDoc.Stamper(health);
		
		document.save( (outputLocation + "merged.pdf"));
		
		     
		}

}