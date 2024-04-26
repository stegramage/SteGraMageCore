package US1.java;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import _SteGraMageCore.ASCIIMessageInterpreter;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA1 {

	@Test
	void espacioInsuficienteTest() {
		MockChannelConverter mch = new MockChannelConverter(2);
		ASCIIMessageInterpreter mi = new ASCIIMessageInterpreter();
		SteGraMage st = new SteGraMage();
		st.setInterpreter(mi);
		st.setConverter(mch);
				
		assertThrows(IllegalArgumentException.class, () -> st.hide("hola", "/path/to/nothig"));
	}

}
