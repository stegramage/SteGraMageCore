package _US2.java;

import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Discover;
import _SteGraMageCore.Interpreter;
import resources.Assert;

class CA4 {

	@Test
	void oneInterpreterTest() {
		Discover dis = new Discover();
		
		Set<Interpreter> result = null;
		try {
			result = dis.findClasses("plugins/interprete");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] expected = {"_rot13.ROT13"};
		
		Assert.equals(expected, result);
	}

}
