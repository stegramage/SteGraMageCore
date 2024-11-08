package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MessageCodecTest {

	@Test
	public void encodeMessageTest() {
		Encoder<String> msgEnc = new MessageCodec();
		
		Data actual = msgEnc.encode("H");
		int[] expected = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00};
		int[] obtained = toArray(actual);
		
		assertArrayEquals(expected, obtained);
	}

	@Test
	public void encodeMessageEmptyTest() {
		Encoder<String> msgEnc = new MessageCodec();
		
		Data actual = msgEnc.encode("");
		int[] expected = {};
		int[] obtained = toArray(actual);
		
		assertArrayEquals(expected, obtained);
	}
	
	@Test
	public void decodeBytesTest() {
		Decoder<String> msgDec = new MessageCodec();
		int[] channel = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00};
		String actual = msgDec.decode(toData(channel));
		String expected = "H";
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void decodeBytesEmptyTest() {
		Decoder<String> msgDec = new MessageCodec();
		int[] channel = new int[0];
		String actual = msgDec.decode(toData(channel));
		String expected = "";
				
		assertEquals(expected, actual);
	}


	private int[] toArray(Data actual) {
		int[] ret = new int[actual.size()];
		
		for (int i = 0; i < ret.length; i++)
			ret[i] = actual.get(i);
		return ret;
	}

	private Data toData(int[] actual) {
		List<Integer> aux = new ArrayList<Integer>(actual.length);

        for (int j : actual)
			aux.add(j);

		Data data = new Data();
		data.setInfo(aux);

		return data;
	}
}
