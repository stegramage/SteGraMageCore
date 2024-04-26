package US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.ASCIIMessageInterpreter;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA4 {

	@Test
	void extraerMensajeTest() {
		String msg = "hola";
		MockChannelConverter mch = new MockChannelConverter(40);
		ASCIIMessageInterpreter mi = new ASCIIMessageInterpreter();
		SteGraMage st = new SteGraMage();
		st.setInterpreter(mi);
		st.setConverter(mch);
		
		st.hide(msg, "/path/to/nothig");
		st.unhide("unhide");
		
		assertEquals(msg, st.getMessageUnhided());
	}

}
