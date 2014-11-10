package cli.nmapgen.com;

import java.awt.image.BufferedImage;

public class SobelGenerator extends AbstractGenerator {
	
	/**
	 * Empty constructor
	 */
	
	public SobelGenerator(){
		super();
	}


	
	// 
	
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
	
}
