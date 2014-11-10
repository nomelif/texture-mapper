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
	public float[] OPERATOR_Y(){
		// TODO Auto-generated method stub
		return new float[]{
				-1, 0, 1,
				-2, 0, 2,
				-1, 0, 1
			};
		}


	@Override
	public float[] OPERATOR_X() {
		// TODO Auto-generated method stub
		return new float[]{
				1,  2,  1,
				0,  0,  0,
			   -1, -2, -1
			};
	}
	
}
