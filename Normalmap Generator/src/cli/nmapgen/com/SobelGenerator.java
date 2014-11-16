package cli.nmapgen.com;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SobelGenerator extends AbstractGenerator {
	
	/**
	 * Empty constructor
	 */
	
	public SobelGenerator(){
		super();
	}


	@Override
	public double[][] Convolve(double[][] image, double[][] kernel){
		double[][] result = new double[image.length][image[0].length];
		int kernel_height = kernel.length;
		int kernel_width = kernel[0].length;
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for(int y = 0; y < result.length; y++){
			for(int x = 0; x < result[0].length; x++){
				result[y][x] = 0.0;
				for(int y_2 = 0; y_2 < kernel_height; y_2++){
					for(int x_2 = 0; x_2 < kernel_width; x_2++){
						result[y][x] += image[(y + y_2-y_2/2) % image.length][(x + x_2-x_2/2) % image[0].length] * kernel[y_2][x_2];
						if(result[y][x] > max){
							max = result[y][x];
						}
						if(result[y][x] < min){
							min = result[y][x];
						}
					}
				}
			}
		}
		for(int y = 0; y < result.length; y++){
			for(int x = 0; x < result[y].length; x++){
				result[y][x] = Math.abs((result[y][x] - min) / (max - min));
			}
		}
		return result;
	}
	
	@Override
	public double[][] OPERATOR_Y(){
		// TODO Auto-generated method stub
		return new double[][]{
				{-1, 0, 1},
				{-2, 0, 2},
				{-1, 0, 1}
			};
		}


	@Override
	public double[][] OPERATOR_X() {
		// TODO Auto-generated method stub
		return new double[][]{
				{1,  2,  1},
				{0,  0,  0},
			   {-1, -2, -1}
			};
	}
	@Override
	public BufferedImage Generate(BufferedImage img){
		
		// Make original image greyscale
		
		double[][] greyscale = new double[img.getHeight()][img.getWidth()];
		
		for(int x = 0; x < img.getWidth(); x++){
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
	    
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				float red = Math.min(1,(float) X[y][x]);
				float green = Math.min(1,(float) Y[y][x]);
				float blue = Math.min(1,(float) ((X[y][x] + Y[y][x])) * 2);
				Color color = new Color(red, green, blue);
				//Color color = Color.red;
				result.setRGB(x, y, color.getRGB());
			}
		}
		
		return result;
	}
	
}
