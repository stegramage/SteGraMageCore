package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.ASCIIMessageCodec;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA3 {

	@Test
	void containsAMessageTest() {
		MockChannelConverter mch = new MockChannelConverter(40);
		ASCIIMessageCodec mc = new ASCIIMessageCodec();
		SteGraMage st = new SteGraMage();
		st.setCodec(mc);
		st.setConverter(mch);
		
		st.hide("hola", "/path/to/nothig");
		
		assertTrue(arrayNotEquals(mch.getChannelIn(), mch.getChannelOut()));
	}
	
	private boolean arrayNotEquals(char[] expected, char[] obtained) {
		
		if (expected.length == obtained.length) {
			for (int i = 0; i < expected.length; i++) {
				if (expected[i] != obtained[i])
					return true;
			}
			return false;
		}
		return true;
	}

}
