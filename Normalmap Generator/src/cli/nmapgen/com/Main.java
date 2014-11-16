package cli.nmapgen.com;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedImage img = IO.load("/home/theo/Insanity/rocks.jpg");
		BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		AbstractGenerator g = new SobelGenerator();
		img = g.Generate(img);
		/*float[] matrix = new float[400];
		for (int i = 0; i < 400; i++)
			matrix[i] = 1.0f/400.0f;

	        BufferedImageOp op = new ConvolveOp( new Kernel(20, 20, matrix), ConvolveOp.EDGE_NO_OP, null );
	    	img2 = op.filter(img, img2);
	    Graphics g2 = img.getGraphics();
	    g2.drawImage(img, 0, 0, null);*/
		System.out.println(1/16d);
		IO.save(img, "/home/theo/Insanity/rocks2.png", "png");
	}

}
