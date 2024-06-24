package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA6 {

	@Test
	void extractMessageFromNullChannelTest() {
		MockChannelConverter mockChannel = new MockChannelConverter(40);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage stegramage = SteGraMage.defaultInstance();
		stegramage.setConverter(mockChannel);
		
		assertThrows(IllegalArgumentException.class, () -> stegramage.unhide("unhide"));
	}

}
