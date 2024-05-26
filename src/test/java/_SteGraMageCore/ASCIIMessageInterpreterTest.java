package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ASCIIMessageInterpreterTest {

	@Test
	public void interpretMessageTest() {
		Codec msgInterp = new ASCIIMessageCodec();
		
		int[] actual = msgInterp.encodeMessage("H");
		int[] expected = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};
				
		assertArrayEquals(expected, actual);
	}

	@Test
	public void interpretMessageEmptyTest() {
		Codec msgInterp = new ASCIIMessageCodec();
		
		int[] actual = msgInterp.encodeMessage("");
		int[] expected = {};
				
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void interpretBytesTest() {
		Codec msgInterp = new ASCIIMessageCodec();
		int[] channel = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};
		String actual = msgInterp.decodeChannel(channel);
		String expected = "H";
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void interpretBytesEmptyTest() {
		Codec msgInterp = new ASCIIMessageCodec();
		int[] channel = new int[0];
		String actual = msgInterp.decodeChannel(channel);
		String expected = "";
				
		assertEquals(expected, actual);
	}

}
