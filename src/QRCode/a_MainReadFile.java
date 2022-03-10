package QRCode;

import QRCode.j_EngPayroll;
import QRCode.j_EngW4;
import QRCode.k_EngEnroll;
import QRCode.k_EngHealth;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class a_MainReadFile
{
	public static void main(String args[]) throws IOException, DataFormatException  
		{


		String fileName = "C:\\Users\\azeem\\Pictures\\combinepdf.pdf"; //args[0]
		
		
		//These are the codes for the all the different files times
		List<String> englishFileIDs = List.of("PEO074","DP0002","phb011", "W-4");
		List<String> spanishFileIDs = List.of("PEO075","DP0003","phb265", "W-4 (SP)");

		
		File file = new File(fileName);

		
		
	    PDDocument document =  Loader.loadPDF(file);
	    PDFTextStripper stripper = new PDFTextStripper();

		String text = "";
	
		if (!document.isEncrypted()) {

			    text = stripper.getText(document);
			    
			    //identify language and call functions accordingly
			    
			    //j_EngPayroll.main();
			    //j_EngW4.main();
			    //k_EngEnroll.main();
			    //k_EngHealth.main();
			    
			    
			}

		    System.out.println("Text:" + text);
		

		document.close();



		}

}