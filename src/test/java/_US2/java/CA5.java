package _US2.java;

import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Codec;
import _SteGraMageCore.Discover;
import resources.Assert;

class CA5 {

	@Test
	void multiCodecTest() {
		Discover dis = new Discover();
		
		Set<Class<?>> result = null;
		try {
			result = dis.findClasses("plugins/codificadoresMultiples", Codec.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] expected = {"ROT13", "Base64"};
		
		Assert.equals(expected, result);
	}

}
