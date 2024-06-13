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

class CA4 {

	@Test
	void emptyConfigurationOrdertest() {
		Discover dis = new Discover();
		
		Set<Class<?>> plugins =new HashSet<Class<?>>();
		try {
			plugins = dis.findClasses("plugins/codecsMultiples", Codec.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		DecoratorBuilder<Codec> cb = new DecoratorBuilder<Codec>(plugins);
		
		List<String> order = new ArrayList<String>();
		
		Codec codec = cb.buildComponent(order);
		
		assertNull(codec);
	}

}