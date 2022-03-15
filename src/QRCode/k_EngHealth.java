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

public class k_EngHealth
{
	
	public static PDDocument main(PDDocument Health) throws WriterException, IOException {


	    System.out.println("Health, Pages = "  + Health.getNumberOfPages() ); 

	    String Data = "UniqueHealthID Page - ";
	    
	    int[][] numbers = { {1, 50, 25},
	    	             	{2, 510, 25},
	    	             	{2, 510, 25} };

	    
	    int count = 0;
	    
	    for(PDPage x: Health.getPages()) {
	    	
			BufferedImage img = QRCode.a_QRCodeGenerator.main(Data + numbers[count][0]);

            File f = new File("MyFile.png");
            ImageIO.write(img, "png", f);

            
	    	PDImageXObject Compile = PDImageXObject.createFromFile("MyFile.png" , Health);
	    	PDPageContentStream content = new PDPageContentStream(Health, x, AppendMode.APPEND, true, true);
	    		    	
	    	content.drawImage(Compile, numbers[count][1], numbers[count][2], 50, 50);
	    	
	    	f.delete();
		    content.close();
	    	count++;	
	    }

	    System.out.println("Health completed!!"); 

	    return Health;  
	}

}