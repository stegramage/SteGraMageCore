package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ASCIIMessageInterpreterTest {

	@Test
	public void interpretMessageTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		
		byte[] actual = msgInterp.interpretMessage("H");
		byte[] expected = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00};
				
		assertArrayEquals(expected, actual);
	}

	@Test
	public void interpretMessageEmptyTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		
		byte[] actual = msgInterp.interpretMessage("");
		byte[] expected = new byte[0];
				
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void interpretBytesTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		byte[] channel = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
				(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
				(byte) 0x00, (byte) 0x00, (byte) 0x00};
		String actual = msgInterp.interpretBytes(channel);
		String expected = "H";
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void interpretBytesEmptyTest() {
		Interpreter msgInterp = new ASCIIMessageInterpreter();
		byte[] channel = new byte[0];
		String actual = msgInterp.interpretBytes(channel);
		String expected = "";
				
		assertEquals(expected, actual);
	}

}
