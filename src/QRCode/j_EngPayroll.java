package QRCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.google.zxing.WriterException;
public class j_EngPayroll
{
	public static PDDocument main(PDDocument payroll) throws WriterException, IOException {

		
	    System.out.println("Payroll, Pages = "  + payroll.getNumberOfPages() ); 

	    String Data = "UniquePayrollID + Page - ";
	    
	    int[][] numbers = { {1, 500, 50},
	    		            {2, 360, 740},
	                        {3, 500, 50}};

	    
	    int count = 0;
	    
	    for(PDPage x: payroll.getPages()) {
	    	
			BufferedImage img = QRCode.a_QRCodeGenerator.main(Data + numbers[count][0]);

            File f = new File("MyFile.png");
            ImageIO.write(img, "png", f);

            
	    	PDImageXObject Compile = PDImageXObject.createFromFile("MyFile.png" , payroll);
	    	PDPageContentStream content = new PDPageContentStream(payroll, x, AppendMode.APPEND, true, true);
	    		    	
	    	content.drawImage(Compile, numbers[count][1], numbers[count][2], 50, 50);
	    	
	    	f.delete();
		    content.close();
	    	count++;	
	    }
	    
	    System.out.println("Payroll Completed!!" ); 

	    return payroll;  
	}

	

}