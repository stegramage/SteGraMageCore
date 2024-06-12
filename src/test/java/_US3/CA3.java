package _US3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Codec;
import _SteGraMageCore.CodecDecorator;
import _SteGraMageCore.DecoratorBuilder;
import _SteGraMageCore.Discover;

class CA3 {

	@Test
	void multipleTimesDecoratedComponentTest() {
		Discover dis = new Discover();
		
		Set<Class<?>> plugins = new HashSet<Class<?>>();
		try {
			plugins = dis.findClasses("plugins/codecsMultiples");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		DecoratorBuilder<Codec> cb = new DecoratorBuilder<Codec>(plugins);
		
		List<String> order = new ArrayList<String>();
		order.add("_SteGraMageCore.ASCIIMessageCodec");
		order.add("ROT13");
		order.add("Base64");
		
		Codec codec = cb.buildComponent(order);
		Class<?>[] cls = new Class<?>[2];
		try {
			cls[1] = Class.forName("ROT13");
			cls[0] = Class.forName("Base64");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		assertTrue(plugins.contains(codec.getClass()));
		assertEqualsClasses(cls, codec);
	}

	private void assertEqualsClasses(Class<?>[] cls, Codec codec) {
		for (Class<?> c : cls) {
			assertEquals(c, codec.getClass());
			if (CodecDecorator.class.isAssignableFrom(codec.getClass())) 
				codec = ((CodecDecorator) codec).getCodec();
		}
	}

}
