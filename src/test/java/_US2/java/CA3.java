package _US2.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Discover;
import _SteGraMageCore.Interpreter;

class CA3 {

	@Test
	void isNotInterpreterTest() {
Discover dis = new Discover();
		
		Set<Interpreter> result = null;
		try {
			result = dis.findClasses("plugins/noEsInterprete");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(result.isEmpty());
	}

}