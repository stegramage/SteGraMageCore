package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PNGConverterTest {

	@Test
	public void extractAlphaByteTest() {
		PNGConverter c = new PNGConverter();
		int pixel = 0xFF87B6A0;
		byte alpha = c.extractByte(pixel, PNGConverter.ALPHA);
		assertEquals((byte) 0xFF, alpha);
	}
	
	@Test
	public void extractRedByteTest() {
		PNGConverter c = new PNGConverter();
		int pixel = 0xFF87B6A0;
		byte red = c.extractByte(pixel, PNGConverter.RED);
		assertEquals((byte) 0x87, red);
	}
	
	@Test
	public void extractGreenByteTest() {
		PNGConverter c = new PNGConverter();
		int pixel = 0xFF87B6A0;
		byte green = c.extractByte(pixel, PNGConverter.GREEN);
		assertEquals((byte)0xB6, green);
	}
	
	@Test
	public void extractBlueByteTest() {
		PNGConverter c = new PNGConverter();
		int pixel = 0xFF87B6A0;
		byte blue = c.extractByte(pixel, PNGConverter.BLUE);
		assertEquals((byte) 0xa0, blue);
	}

}
