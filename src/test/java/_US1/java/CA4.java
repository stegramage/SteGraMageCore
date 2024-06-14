package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA4 {

	@Test
	void extractMessageTest() {
		String msg = "hola";
		MockChannelConverter mch = new MockChannelConverter(40);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage st = SteGraMage.defaultInstance();
		st.setConverter(mch);
		
		st.hide(msg, "/path/to/nothig");
		st.unhide("unhide");
		
		assertEquals(msg, st.getMessageUnhided());
	}

}
