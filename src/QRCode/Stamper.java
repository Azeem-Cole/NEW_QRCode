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

public class Stamper {

	String data;
	int[][] imgCoord;	
	
	Stamper( String data, int[][]imgCoord ){
		
		this.data = data;
		this.imgCoord = imgCoord;
		
	}

	PDDocument Stamp(PDDocument doc) throws WriterException, IOException {

	    //System.out.println( "Pages = "  + doc.getNumberOfPages() ); 

	    QRCodeGenerator gen = new QRCodeGenerator();
	    int count = 0;
	    
	    for(PDPage page: doc.getPages()) {
	    	
			BufferedImage img = gen.generater(data + this.imgCoord[count][0]);

            File file = new File("MyFile.png");
            ImageIO.write(img, "png", file);
            
	    	PDImageXObject compile = PDImageXObject.createFromFile("MyFile.png" , doc);
	    	PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true);
	    		    	
	    	content.drawImage(compile, this.imgCoord[count][1], this.imgCoord[count][2], 50, 50);
	    	
	    	file.delete();
		    content.close();
	    	count++;	
	    }

	    if(doc.getNumberOfPages() != 0) {
		    System.out.println("completed!!" ); 

	    }
	    return doc;  
	}
	
	
}
