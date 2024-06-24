package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA7 {

	@Test
	void extractMessageFromChannelWithoutMessageTest() {
		MockChannelConverter mockChannel = new MockChannelConverter(40);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage stegramage = SteGraMage.defaultInstance();
		stegramage.setConverter(mockChannel);
		stegramage.hide("", "/path/to/nothing");
		
		assertThrows(UnsupportedOperationException.class, () -> stegramage.unhide("unhide"));
	}

}
