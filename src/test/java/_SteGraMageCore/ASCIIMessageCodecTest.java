package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ASCIIMessageCodecTest {

	@Test
	public void interpretMessageTest() {
		Codec<String> msgInterp = new ASCIICodec();
		
		List<Integer> actual = msgInterp.encode("H");
		int[] expected = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00};
		int[] obtained = toArray(actual);
		
		assertArrayEquals(expected, obtained);
	}

	@Test
	public void interpretMessageEmptyTest() {
		Codec<String> msgInterp = new ASCIICodec();
		
		List<Integer> actual = msgInterp.encode("");
		int[] expected = {};
		int[] obtained = toArray(actual);
		
		assertArrayEquals(expected, obtained);
	}
	
	@Test
	public void interpretBytesTest() {
		Codec<String> msgInterp = new ASCIICodec();
		int[] channel = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00};
		String actual = msgInterp.decode(toList(channel));
		String expected = "H";
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void interpretBytesEmptyTest() {
		Codec<String> msgInterp = new ASCIICodec();
		int[] channel = new int[0];
		String actual = msgInterp.decode(toList(channel));
		String expected = "";
				
		assertEquals(expected, actual);
	}


	private int[] toArray(List<Integer> actual) {
		int[] ret = new int[actual.size()];
		
		for (int i = 0; i < ret.length; i++)
			ret[i] = actual.get(i);
		return ret;
	}

	private List<Integer> toList(int[] actual) {
		List<Integer> ret = new ArrayList<Integer>(actual.length);
		
		for (int i = 0; i < actual.length; i++)
			ret.add(actual[i]);
		
		return ret;
	}
}
