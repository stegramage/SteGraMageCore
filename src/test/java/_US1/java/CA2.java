package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA2 {

	@Test
	void invalidMessageTest() {
		MockChannelConverter mockChannel = new MockChannelConverter(40);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage stegramage = SteGraMage.defaultInstance();
		stegramage.setConverter(mockChannel);
		
		assertThrows(IllegalArgumentException.class, () -> stegramage.hide(null, "/path/to/nothig"));
	}

}
