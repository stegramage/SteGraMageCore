package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.ASCIIMessageInterpreter;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA2 {

	@Test
	void mensajeVacioTest() {
		MockChannelConverter mch = new MockChannelConverter(2);
		ASCIIMessageInterpreter mi = new ASCIIMessageInterpreter();
		SteGraMage st = new SteGraMage();
		st.setInterpreter(mi);
		st.setConverter(mch);
		
		st.hide("", "/path/to/nothig");
		
		assertArrayEquals(mch.getChannelOut(), mch.getChannelIn());
	}

}
