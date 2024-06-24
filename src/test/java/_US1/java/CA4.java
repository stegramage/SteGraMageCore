package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA4 {

	@Test
	void containsAMessageTest() {
		MockChannelConverter mockChannel = new MockChannelConverter(40);
		SteGraMage.loadPlugins("plugins/");
		SteGraMage stegramage = SteGraMage.defaultInstance();
		stegramage.setConverter(mockChannel);
		stegramage.hide("hola", "/path/to/nothig");
		
		assertTrue(arrayNotEquals(mockChannel.getChannelIn(), mockChannel.getChannelOut()));
	}
	
	private boolean arrayNotEquals(char[] expected, char[] obtained) {
		
		if (expected.length == obtained.length) {
			for (int i = 0; i < expected.length; i++) {
				if (expected[i] != obtained[i])
					return true;
			}
			return false;
		}
		return true;
	}


}
