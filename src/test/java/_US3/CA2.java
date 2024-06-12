package _US3;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Codec;
import _SteGraMageCore.DecoratorBuilder;
import _SteGraMageCore.Discover;

class CA2 {

	@Test
	void test() {
		Discover dis = new Discover();
		
		Set<Class<?>> plugins = new HashSet<Class<?>>();
		try {
			plugins = dis.findClasses("plugins/codecsMultiples");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DecoratorBuilder<Codec> cb = new DecoratorBuilder<Codec>(plugins);
		
		ArrayList<String> order = new ArrayList<String>();
		order.add("_SteGraMageCore.ASCIIMessageCodec");
		order.add("ROT13");
		
		Codec codec = null;
		try {
			codec = cb.build(order);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		assertTrue(plugins.contains(codec.getClass()) );
	}

}
