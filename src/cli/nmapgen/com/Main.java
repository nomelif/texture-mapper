package cli.nmapgen.com;

import java.awt.image.BufferedImage;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage img = IO.load("/home/theo/Insanity/rocks.jpg");
		SobelGenerator g = new SobelGenerator();
		img = g.Generate(img);
		IO.save(img, "/home/theo/Insanity/rocks2.jpg", "png");
	}

}
