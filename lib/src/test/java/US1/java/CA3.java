package US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.ASCIIMessageInterpreter;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA3 {

	@Test
	void contieneUnMensajeTest() {
		MockChannelConverter mch = new MockChannelConverter(40);
		ASCIIMessageInterpreter mi = new ASCIIMessageInterpreter();
		SteGraMage st = new SteGraMage();
		st.setInterpreter(mi);
		st.setConverter(mch);
		
		st.hide("hola", "/path/to/nothig");
		
		assertTrue(arrayNotEquals(mch.getChannelIn(), mch.getChannelOut()));
	}
	
	private boolean arrayNotEquals(int[] expected, int[] obtained) {
		
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
