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

public class k_EngDeposit 
{
	
	public static PDDocument main(PDDocument Deposit) throws WriterException, IOException {



	    System.out.println("Deposit, Pages = "  + Deposit.getNumberOfPages() ); 

	    String Data = "UniqueDepositID Page - ";
	    
	    int[][] numbers = { {1, 550, 740}
	    					};

	    
	    int count = 0;
	    
	    for(PDPage x: Deposit.getPages()) {
	    	
			BufferedImage img = QRCode.a_QRCodeGenerator.main(Data + numbers[count][0]);

            File f = new File("MyFile.png");
            ImageIO.write(img, "png", f);

            
	    	PDImageXObject Compile = PDImageXObject.createFromFile("MyFile.png" , Deposit);
	    	PDPageContentStream content = new PDPageContentStream(Deposit, x, AppendMode.APPEND, true, true);
	    		    	
	    	content.drawImage(Compile, numbers[count][1], numbers[count][2], 50, 50);
	    	
	    	f.delete();
		    content.close();
	    	count++;	
	    }

	    System.out.println( "Deposit Completed!!" ); 

	    return Deposit;  
	}

}