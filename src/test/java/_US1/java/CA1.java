package _US1.java;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.PluginsLoader;
import _SteGraMageCore.SteGraMage;
import _SteGraMageCore.ASCIIMessageCodec;
import _SteGraMageCore.ChannelConverter;
import _SteGraMageCore.Configurator;
import resources.MockChannelConverter;

class CA1 {

	@Test
	void notEnoughSpaceTest() {
		PluginsLoader loader = new PluginsLoader("plugins/");
		SteGraMage stegramage = new SteGraMage();
		List<String> codecs = new ArrayList<String>();
		List<String> converters = new ArrayList<String>();
		codecs.add(ASCIIMessageCodec.class.getName());
		converters.add(ChannelConverter.class.getName());
		Configurator.configure(stegramage, loader.getPlugins(), codecs, converters);
		stegramage.setConverter(new MockChannelConverter(2));
				
		assertThrows(IllegalArgumentException.class, () -> stegramage.hide("hola", "/path/to/nothig"));
	}

}
