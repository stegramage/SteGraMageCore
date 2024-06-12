package _US3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.ASCIIMessageCodec;
import _SteGraMageCore.Codec;
import _SteGraMageCore.DecoratorBuilder;
import _SteGraMageCore.Discover;

class CA1 {

	@Test
	void concreteComponentTest() {
		Discover dis = new Discover();
		
		Set<Class<?>> plugins =new HashSet<Class<?>>();
		try {
			plugins = dis.findClasses("plugins/codecsMultiples");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		DecoratorBuilder<Codec> cb = new DecoratorBuilder<Codec>(plugins);
		
		List<String> order = new ArrayList<String>();
		order.add("_SteGraMageCore.ASCIIMessageCodec");
		
		Codec codec = cb.buildComponent(order);
		
		assertTrue(codec instanceof ASCIIMessageCodec);
	}

}
