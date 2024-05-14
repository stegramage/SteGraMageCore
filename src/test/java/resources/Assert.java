package resources;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class Assert {
	
	public static void equals (String[] expected, Set<Object> obtained) {
		assertEquals(expected.length, obtained.size());
		Set<String> names = new HashSet<String>();
		for (Object o : obtained) {
			names.add(o.getClass().getName());
		}
		
		for (String name : expected) {
			assertTrue(names.contains(name));
		}
	}

}
