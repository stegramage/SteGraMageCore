package _US2.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Discover;
import _SteGraMageCore.Codec;

class CA3 {

	@Test
	void isNotInterpreterTest() {
Discover dis = new Discover();
		
		Set<Class<?>> result = null;
		try {
			result = dis.findClasses("plugins/noEsCodec");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(result.isEmpty());
	}

}
