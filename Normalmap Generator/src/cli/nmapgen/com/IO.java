package cli.nmapgen.com;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IO {

	/**
	 * @param args
	 */
	
	/**
	 * Load image to a BufferedImage instance;
	 */
	
	static public BufferedImage load(String path){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * Save image to disk;
	 */
	
	static public void save(BufferedImage img, String path, String format){
		try {
		    File outputfile = new File(path);
		    ImageIO.write(img, format, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
