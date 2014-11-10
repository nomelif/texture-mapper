package cli.nmapgen.com;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public abstract class AbstractGenerator {

	/**
	 * @param args
	 */
	
	/**
	 * Horizontal operator to use
	 */
	
	public abstract float[] OPERATOR_X();
	
	/**
	 * Vertical operator to use
	 */
	
	public abstract float[] OPERATOR_Y();
	
	/**
	 * Empty constructor
	 */
	
	public AbstractGenerator(){
		
	}
	
	/**
	 * Generation method
	 */
	
	public BufferedImage Generate(BufferedImage img){
		
		// Make original image greyscale
		
	    BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(),  
	        BufferedImage.TYPE_BYTE_GRAY);  
	        Graphics g = image.getGraphics();  
	        g.drawImage(img, 0, 0, null);  
	        g.dispose();  
		
		
		ConvolveOp opx = new ConvolveOp(new Kernel(3, 3, OPERATOR_X()));
		ConvolveOp opy = new ConvolveOp(new Kernel(3, 3, OPERATOR_Y()));
		
		// Filter image using operator on both axes
		
		BufferedImage img_x = opx.filter(image, null);
		BufferedImage img_y = opy.filter(image, null);
		
		// Initialize the image to return
		
		BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		// Fill image to return with data from the other images
		int minx = (int) Math.pow(2, 32)/2;
		int miny = (int) Math.pow(2, 32)/2;
		int maxx = (int) -(Math.pow(2, 32)/2);
		int maxy = (int) -(Math.pow(2, 32)/2);
		
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				int red = img_y.getRGB(x, y) & 0x00ff0000 >> 16;
				int green = (img_x.getRGB(x, y) & 0x00ff0000) >> 8;
				int blue = img_x.getRGB(x, y) & 0x00ff0000 + img_y.getRGB(x, y) & 0x00ff0000;
				result.setRGB(x, y, red+green+blue);
			}
		}
		
		/*for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				int  red = (result.getRGB(x, y) & 0x00ff0000) >> 16;
				int  green = (result.getRGB(x, y) & 0x0000ff00) >> 8;
				int  blue = result.getRGB(x, y) & 0x000000ff;
				result.setRGB(x, y, red+green+blue);
			}
		}*/
		
		return result;
	}

}
