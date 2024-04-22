package _SteGraMageCore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SteGraMageTest {

	@Test
	public void unhideTest() {
		SteGraMage st = new SteGraMage();
		byte[] aux = 
			{(byte) 0xf0, (byte) 0xc0, (byte) 0xd0, (byte) 0x11, (byte) 0x40, (byte) 0x60, (byte) 0xe1, (byte) 0x00, 
			(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x00,
			(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01};
	
		byte[] exp =  
			{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, 
			(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x00,
			(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01};
		byte[] res = st.unhide(aux);
		
		assertArrayEquals(exp, res);
		
	}

}
