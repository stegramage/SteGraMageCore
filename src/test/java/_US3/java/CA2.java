package _US3.java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Codec;
import _SteGraMageCore.DecoratorBuilder;
import _SteGraMageCore.Discover;

class CA2 {

	@Test
	void oneTimeDecoratedComponentTest() {
		Discover dis = new Discover();
		
		Set<Class<?>> plugins = new HashSet<Class<?>>();
		try {
			plugins = dis.findClasses("plugins/codecsMultiples", Codec.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		DecoratorBuilder<Codec> cb = new DecoratorBuilder<Codec>(plugins);
		
		List<String> order = new ArrayList<String>();
		order.add("_SteGraMageCore.ASCIIMessageCodec");
		order.add("ROT13");
		
		Codec codec = cb.buildComponent(order);
		Class<?> cls = null;
		try {
			cls = Class.forName("ROT13");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(plugins.contains(codec.getClass()));
		assertEquals(cls, codec.getClass());
	}

}
