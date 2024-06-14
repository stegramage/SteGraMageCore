package _US1.java;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA1 {

	@Test
	void notEnoughSpaceTest() {
		SteGraMage.loadPlugins("plugins/");
		SteGraMage st = SteGraMage.defaultInstance();
		st.setConverter(new MockChannelConverter(2));
				
		assertThrows(IllegalArgumentException.class, () -> st.hide("hola", "/path/to/nothig"));
	}

}
