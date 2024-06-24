package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA3 {

	@Test
	void emptyMessageTest() {
		MockChannelConverter mockChannel = new MockChannelConverter(2);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage stegramage = SteGraMage.defaultInstance();
		stegramage.setConverter(mockChannel);
		
		stegramage.hide("", "/path/to/nothig");
		
		assertArrayEquals(mockChannel.getChannelOut(), mockChannel.getChannelIn());
	}

}
