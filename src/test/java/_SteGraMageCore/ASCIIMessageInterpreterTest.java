package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ASCIIMessageInterpreterTest {

	@Test
	public void interpretMessageTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		
		int[] actual = msgInterp.interpretMessage("H");
		int[] expected = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};
				
		assertArrayEquals(expected, actual);
	}

	@Test
	public void interpretMessageEmptyTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		
		int[] actual = msgInterp.interpretMessage("");
		int[] expected = {};
				
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void interpretBytesTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		int[] channel = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};
		String actual = msgInterp.interpretChannel(channel);
		String expected = "H";
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void interpretBytesEmptyTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		int[] channel = new int[0];
		String actual = msgInterp.interpretChannel(channel);
		String expected = "";
				
		assertEquals(expected, actual);
	}

}
