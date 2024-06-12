package _US3;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.ASCIIMessageCodec;
import _SteGraMageCore.Codec;
import _SteGraMageCore.CodecBuilder;
import _SteGraMageCore.Discover;

class CA1 {

	@Test
	void test() {
		Discover dis = new Discover();
		
		Set<Class<?>> plugins = null;
		try {
			plugins = dis.findClasses("plugins/codecsMultiples");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CodecBuilder<Codec> cb = new CodecBuilder<Codec>(plugins);
		
		ArrayList<String> order = new ArrayList<String>();
		order.add("_SteGraMageCore.ASCIIMessageCodec");
		
		Codec codec = null;
		try {
			codec = cb.build(order);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(codec instanceof ASCIIMessageCodec);
	}

}
