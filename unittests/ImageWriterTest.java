/**
 * 
 */
package unittests;

import renderer.ImageWriter;
import java.awt.Color;

import org.junit.Test;


/**
 * Test of ImageWriterTest - simple grid
 * @author Sari & Devora
 *
 */
public class ImageWriterTest {

	@Test
	public void test() {
		ImageWriter imageWriter = new ImageWriter("firstTest", 1600, 1000, 800, 500,true);
		for (int j = 0; j < 800; j++) {
			for (int i = 0; i < 500; i++) 
			{
				if (i % 50 == 0 || j % 50 == 0) 
					imageWriter.writePixel(j, i, Color.BLUE);

				 else
					imageWriter.writePixel(j, i, Color.PINK);
			}
		}
		imageWriter.writeToImage();
	}

}
