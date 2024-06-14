package _US4.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA2 {

	@Test
	void hideMessageWithROT13DecoratorTest() {
		String msg = "hola";
		List<String> plugins = new ArrayList<String>();
		plugins.add("ROT13");
		MockChannelConverter mch = new MockChannelConverter(240);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage st = SteGraMage.createInstance(plugins);
		st.setConverter(mch);
		
		st.hide(msg, "/path/to/nothig");
		st.unhide("unhide");
		
		assertEquals(msg + ":ROT13:", st.getMessageUnhided());
	}

}
