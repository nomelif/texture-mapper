package cli.nmapgen.com;

import java.awt.image.BufferedImage;

public class GaussGenerator extends AbstractGenerator {
	
	public GaussGenerator(){
		super();
	}
	/*@Override
	public double[][] OPERATOR_Y(){
		// TODO Auto-generated method stub
		return new double[][] {
				{1/32d, 1/16d, 1/32d},
				{1/16d, 1/8d, 1/16d},
				{1/32d, 1/16d, 1/32d}
				};
		}

	@Override
	public double[][] OPERATOR_X() {
		// TODO Auto-generated method stub
		return new double[][] {
				{1d/32d, 1d/16d, 1d/32d},
				{1/16d, 1/8d, 1/16d},
				{1/32d, 1/16d, 1/32d}
				};
	}*/
	@Override
	public double[][] OPERATOR_Y(){
		// TODO Auto-generated method stub
		return new double[][] {
				{1/9d, 1/9d, 1/9d},
				{1/9d, 1/9d, 1/9d},
				{1/9d, 1/9d, 1/9d}
				};
		}

	@Override
	public double[][] OPERATOR_X() {
		// TODO Auto-generated method stub
		return new double[][] {
				{1/9d, 1/9d, 1/9d},
				{1/9d, 1/9d, 1/9d},
				{1/9d, 1/9d, 1/9d}
				};
	}
	/*@Override
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
	}*/
	

}
