package resources;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;


public class Assert {
	
	public static void equals (String[] expected, Set<Class<?>> obtained) {
		assertEquals(expected.length, obtained.size());
		Set<String> names = new HashSet<String>();
		for (Class<?> o : obtained) {
			names.add(o.getName());
		}
		
		for (String name : expected) {
			assertTrue(names.contains(name));
		}
	}

}
