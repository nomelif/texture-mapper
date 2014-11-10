package cli.nmapgen.com;

import java.awt.Color;
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
	
	public abstract double[][] OPERATOR_X();
	
	/**
	 * Vertical operator to use
	 */
	
	public abstract double[][] OPERATOR_Y();
	
	/**
	 * Empty constructor
	 */
	
	public AbstractGenerator(){
		
	}
	
	/**
	 * Convolution method
	 */
	
	public static double[][] Convolve(double[][] image, double[][] kernel){
		double[][] result = new double[image.length][image[0].length];
		int kernel_height = kernel.length;
		int kernel_width = kernel[0].length;
		for(int y = 0; y < result.length; y++){
			for(int x = 0; x < result[0].length; x++){
				result[y][x] = 0.0;
				for(int y_2 = 0; y_2 < kernel_height; y_2++){
					for(int x_2 = 0; x_2 < kernel_width; x_2++){
						result[y][x] += image[(y + y_2-y_2/2) % image.length][(x + x_2-x_2/2) % image[0].length] * kernel[y_2][x_2];
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Generation method
	 */
	
	public BufferedImage Generate(BufferedImage img){
		
		// Make original image greyscale
		
	    /*BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(),  
	        BufferedImage.TYPE_BYTE_GRAY);  
	        Graphics g = image.getGraphics();  
	        g.drawImage(img, 0, 0, null);  
	        g.dispose();*/
		
		double[][] greyscale = new double[img.getHeight()][img.getWidth()];
		
		for(int x = 0; x < img.getHeight(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				Color color = new Color(img.getRGB(x, y));
				greyscale[y][x] = (color.getRed() + color.getGreen()+ color.getBlue()) / 3;
			}
		}
		
		// Filter image using operator on both axes
		
	    double[][] X = Convolve(greyscale, OPERATOR_X());
	    double[][] Y = Convolve(greyscale, OPERATOR_Y());
		
		// Initialize the image to return
		
		BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		// Fill image to return with data from the other images
		/*int minx = (int) Math.pow(2, 32)/2;
		int miny = (int) Math.pow(2, 32)/2;
		int maxx = (int) -(Math.pow(2, 32)/2);
		int maxy = (int) -(Math.pow(2, 32)/2);*/
	    
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				Color color = new Color(Math.min((float) Math.abs(X[y][x] / 256), 1.0f), Math.min((float) Math.abs(Y[y][x] / 256), 1.0f), Math.min((float) Math.abs((X[y][x] + Y[y][x]) / 512), 1.0f));
				//Color color = Color.red;
				result.setRGB(x, y, color.getRGB());
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
