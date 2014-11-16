package cli.nmapgen.com;

import java.awt.Color;
import java.awt.image.BufferedImage;

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
	
	public double[][] Convolve(double[][] image, double[][] kernel){
		
		// Initialize the image to return
		
		double[][] result = new double[image.length][image[0].length];
		
		// Iterate over the image
		
		for(int y = 0; y < result.length; y++){
			for(int x = 0; x < result[0].length; x++){
				
				// Initialize the value of the image
				
				result[y][x] = 0.0;
				
				// Iterate over the kernel
				
				for(int kernel_y = 0; kernel_y < kernel.length; kernel_y++){
					for(int kernel_x = 0; kernel_x < kernel[kernel_y].length; kernel_x++){
						result[y][x] += image[(y + kernel_y-(kernel.length)/2 + result.length) % result.length][(x + kernel_x-(kernel[kernel_y].length)/2  + result[kernel_y].length) % result[kernel_y].length] * kernel[kernel_y][kernel_x] / 256;
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
		
		// Initialize the channels
		
		double[][] red = new double[img.getHeight()][img.getWidth()];
		double[][] green = new double[img.getHeight()][img.getWidth()];
		double[][] blue = new double[img.getHeight()][img.getWidth()];
		
		for(int y = 0; y < img.getHeight(); y++){
			for(int x = 0; x < img.getWidth(); x++){
				Color color = new Color(img.getRGB(x, y));
				red[y][x] = color.getRed();
				green[y][x] = color.getGreen();
				blue[y][x] = color.getBlue();
			}
		}
		
		// Generate average kernel
		
		double[][] OPERATOR_X = this.OPERATOR_X();
		double[][] OPERATOR_Y = this.OPERATOR_Y();
		
		double[][] kernel = new double[OPERATOR_X.length][OPERATOR_X[0].length];
		
		for(int y = 0; y < OPERATOR_Y.length; y++){
			for(int x = 0; x < OPERATOR_X.length; x++){
				kernel[y][x] = (OPERATOR_X[y][x] +OPERATOR_Y[y][x]) / 2;
			}
		}
		
		// Execute the convolutions
		
		red = this.Convolve(red, kernel);
		green = this.Convolve(green, kernel);
		blue = this.Convolve(blue, kernel);
		
		// Combine the channels
		
		for(int y = 0; y < img.getHeight(); y++){
			for(int x = 0; x < img.getWidth(); x++){
				Color color = new Color((int) (red[y][x] * 256), (int) (green[y][x] * 256), (int) (blue[y][x] * 256));
				img.setRGB(x, y, color.getRGB());
			}
		}
		
		// Return the image
		
		return img;
	}

}
