package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA5 {

	@Test
	void extractMessageTest() {
		String message = "hola";
		MockChannelConverter mockChannel = new MockChannelConverter(40);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage stegramage = SteGraMage.defaultInstance();
		stegramage.setConverter(mockChannel);
		
		stegramage.hide(message, "/path/to/nothig");
		stegramage.unhide("unhide");
		
		assertEquals(message, stegramage.getMessageUnhided());
	}

}
