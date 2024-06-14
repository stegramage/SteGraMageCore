package _US4.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA1 {

	@Test
	void hideMessageWithoutDecoratorTest() {
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
