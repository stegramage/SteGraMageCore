package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PNGConverterTest {

	@Test
	public void extractRedBytetest() {
		PNGConverter c = new PNGConverter();
		int pixel = 0xFF87B6A0;
		int rojo = c.extractByte(pixel, PNGConverter.RED);
		assertEquals(0x87, rojo);
	}
	
	@Test
	public void extractGreenBytetest() {
		PNGConverter c = new PNGConverter();
		int pixel = 0xFF87B6A0;
		int rojo = c.extractByte(pixel, PNGConverter.GREEN);
		assertEquals(0xB6, rojo);
	}
	
	@Test
	public void extractBlueBytetest() {
		PNGConverter c = new PNGConverter();
		int pixel = 0xFF87B6A0;
		int rojo = c.extractByte(pixel, PNGConverter.BLUE);
		assertEquals(0xa0, rojo);
	}
	
}
