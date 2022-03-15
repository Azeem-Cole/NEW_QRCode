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

public class j_EngW4
{
	
	
	public static PDDocument main(PDDocument W4) throws WriterException, IOException {

	    System.out.println("W4, Pages = "  + W4.getNumberOfPages() ); 

	    String Data = "UniqueW4ID, Pages - ";
	    
	    int[][] numbers = { {1, 500, 50},
	    	             	{2, 500, 0},
	    	             	{3, 490, 745},
	    	             	{4, 490, 745}
	    					};

	    
	    int count = 0;
	    
	    for(PDPage x: W4.getPages()) {
	    	
			BufferedImage img = QRCode.a_QRCodeGenerator.main(Data + numbers[count][0]);

            File f = new File("MyFile.png");
            ImageIO.write(img, "png", f);

            
	    	PDImageXObject Compile = PDImageXObject.createFromFile("MyFile.png" , W4);
	    	PDPageContentStream content = new PDPageContentStream(W4, x, AppendMode.APPEND, true, true);
	    		    	
	    	content.drawImage(Compile, numbers[count][1], numbers[count][2], 50, 50);
	    	
	    	f.delete();
		    content.close();
	    	count++;	
	    }
		

	    System.out.println("W4 completed!!" ); 
	    return W4;  
	}
}