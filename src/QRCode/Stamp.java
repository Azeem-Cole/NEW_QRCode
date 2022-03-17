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

public class Stamp {

	String data;
	int[][] imgCoord;	
	
	Stamp( String data, int[][]imgCoord ){
		
		this.data = data;
		this.imgCoord = imgCoord;
		
	}

	PDDocument Stamper(PDDocument doc) throws WriterException, IOException {

	    System.out.println( "Pages = "  + doc.getNumberOfPages() ); 

	    GenerateQRCode gen = new GenerateQRCode();
	    int count = 0;
	    
	    for(PDPage page: doc.getPages()) {
	    	
			BufferedImage img = gen.generater(data + this.imgCoord[count][0]);

            File file = new File("MyFile.png");
            ImageIO.write(img, "png", file);
            
	    	PDImageXObject Compile = PDImageXObject.createFromFile("MyFile.png" , doc);
	    	PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true);
	    		    	
	    	content.drawImage(Compile, this.imgCoord[count][1], this.imgCoord[count][2], 50, 50);
	    	
	    	file.delete();
		    content.close();
	    	count++;	
	    }

	    System.out.println("completed!!" ); 
	    return doc;  
	}
	
	
}
