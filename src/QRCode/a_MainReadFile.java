package QRCode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.google.zxing.WriterException;


public class a_MainReadFile
{
	public static void main(String args[]) throws IOException, DataFormatException, WriterException  
		{
	
		String fileName = "C:\\Users\\azeem\\Pictures\\allPDF.pdf"; //args[0]
		String outputLocation = "C:\\Users\\azeem\\Pictures\\"; //args[0]
		
		List<String> englishFileIDs = List.of("PEO074","DP0002","phb011", "W-4");
		//List<String> spanishFileIDs = List.of("PEO075","DP0003","phb265", "W-4 (SP)");
		
		File file = new File(fileName);
		
		
	    PDDocument document =  Loader.loadPDF(file);
	    PDFTextStripper stripper = new PDFTextStripper();
		String text = "";
		
		PDDocument Payroll = new PDDocument();
		PDDocument Deposit  = new PDDocument();
		PDDocument W4 = new PDDocument();
		PDDocument Health = new PDDocument();

		for(int x = 0; x <= document.getNumberOfPages(); x++) {
		
			stripper.setStartPage(x);
			stripper.setEndPage(x);
			
			text = stripper.getText(document).toLowerCase();

	    	if(text.contains(englishFileIDs.get(0).toLowerCase())) {
		    	Payroll.addPage( document.getPage(x -1));	
	    	}
	    	
			if(text.contains(englishFileIDs.get(1).toLowerCase())) {  	
				Deposit.addPage( document.getPage(x -1));
			  }
			
			if(text.contains(englishFileIDs.get(2).toLowerCase())) {
				Health.addPage( document.getPage(x -1));
				
			}	
			
			if(text.contains(englishFileIDs.get(3).toLowerCase())) {
		    	W4.addPage( document.getPage(x -1));
			}
	    	
			text = "";
		}
		
		
		QRCode.j_EngPayroll.main(Payroll);;
		QRCode.k_EngDeposit.main(Deposit);
		QRCode.j_EngW4.main(W4);
		QRCode.k_EngHealth.main(Health);
		
		document.save( (outputLocation + "merged.pdf"));
		
		     
		}

}